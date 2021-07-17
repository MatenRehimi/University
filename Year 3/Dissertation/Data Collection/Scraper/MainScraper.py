from Scraper.PlayerDataScraper import PlayerDataScraper
from Scraper.WebScraper import WebScraper
from Scraper.VenueScraper import VenueScraper
from Scraper.TeamWagesScraper import TeamWagesScraper
from Scraper.OddsScraper import OddsScraper
from Scraper.FormScraper import FormScraper

from scipy.sparse import dok_matrix
from scipy.sparse import csr_matrix
from sklearn.preprocessing import normalize

class MainScraper:

    def __init__(self):

        self.playerDataScraper = PlayerDataScraper()
        self.webScraper = WebScraper()
        self.venueScraper = VenueScraper()
        self.teamWagesScraper = TeamWagesScraper()
        self.oddsScraper = OddsScraper()
        self.formScraper = FormScraper()

    #collects all data until the matchday and converts it to a dok matrix
    def dataMatrix(self,matchday,k):
        games = self.formScraper.getAllGamesUntilMatchday(matchday,k)
        for game in games:
            print(game)
        data = self.playerDataScraper.collectTrainPlayerData(games,k)

        counter = 0
        for match in data:

            match.append(self.webScraper.getFIFAdata(games[counter][1],games[counter][2]))
            match.append(self.venueScraper.getVenueData(games[counter][1],games[counter][2]))
            match.append(self.teamWagesScraper.addWagesData(games[counter][1],games[counter][2]))
            match.append(self.oddsScraper.addOddsData(games[counter][1],games[counter][2]))
            homeFormData = self.formScraper.findFormK(games[counter][1],k,counter+1+k)[0]
            match.append(homeFormData)
            match.append(self.formScraper.findAllForm(homeFormData,games[counter][1],k))
            awayFormData = self.formScraper.findFormK(games[counter][2],k,counter+k)[0]
            match.append(awayFormData)
            match.append(self.formScraper.findAllForm(awayFormData,games[counter][1],k))
            counter = counter + 1

        return self.convertToDokMatrix(data,10*(matchday-k),k)

    #Converts data from a list to a dok matrix
    def convertToDokMatrix(self,data,size,k):
        games = dok_matrix((size,10000), dtype = float)

        for match in range(0,len(data),1):
            counter = 0
            for stat in range(0,len(data[match]),1):
                if stat in range(0,2*k,1):
                    for singleGame in range(0,len(data[match][stat]),1):
                        for individualData in data[match][stat][singleGame]:
                            try:

                                games[match,counter]=float(data[match][stat][singleGame][individualData])
                                counter = counter + 1
                            except Exception as e:
                                pass
                                # print(e)

                if stat in [2*k]:
                    for groupData in data[match][stat]:
                        for i in range(0,len(groupData),1):
                            try:
                                games[match,counter]=float(groupData[i])
                                counter = counter + 1
                            except Exception as e:
                                pass
                                # print(e)

                if stat in [2*k+2]:
                    for individualData in data[match][stat]:
                        try:
                            games[match,counter]=float(data[match][stat][individualData])
                            counter = counter + 1
                        except Exception as e:
                            pass
                            # print(e)

                if stat in [2*k+3]:
                    for groupData in data[match][stat]:
                        for individualData in groupData:
                            try:
                                games[match,counter]=float(groupData[individualData])
                                counter = counter + 1
                            except Exception as e:
                                pass
                                # print(e)

                if stat in [2*k+4,2*k+5,2*k+6,2*k+7]:
                    for groupsOfData in data[match][stat]:
                        for groupData in groupsOfData:
                            for individualData in groupData:
                                try:
                                    games[match,counter]=float(groupData[individualData])
                                    counter = counter + 1
                                except Exception as e:
                                    pass
                                    # print(e)
        return normalize(games.tocsr(),axis = 0)

    #converts results H,D,A into three arrays where 1 is true and -1 is false
    def collectResults(self,matchday,k):
        games = self.formScraper.getAllGamesUntilMatchday(matchday,k)
        Yh = []
        Yd = []
        Ya = []
        for game in games:
            if game[3]=='H':
                Yh.append(1)
                Yd.append(-1)
                Ya.append(-1)
            if game[3]=='D':
                Yh.append(-1)
                Yd.append(1)
                Ya.append(-1)
            if game[3]=='A':
                Yh.append(-1)
                Yd.append(-1)
                Ya.append(1)

        return [Yh,Yd,Ya]

    #Finds the odds for the games up to the matchday
    def findOdds(self,matchday,k):

        return self.oddsScraper.findsOdds(matchday,k)
