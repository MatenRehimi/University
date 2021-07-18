import csv
class PlayerDataScraper:

    def __init__(self):

        self.csvFile = open("Scraper/Data/PlayerStats.csv",encoding="utf8")
        self.reader = csv.reader(self.csvFile)

    #Collects the attributes for the player stats csv file
    def getAttributes(self):
        self.csvFile.seek(0)
        for row in self.reader:
            return row

    #Collects all player data for a single game
    def collectMatch(self,team,matchday):

        self.csvFile.seek(0)
        match = []

        for row in self.reader:
            try:
                if (int(row[1]) == matchday and row[5] == team):
                    match.append(row)
            except ValueError:
                pass

        return match

    #Collects all player data for multiple matches
    def collectMatches(self,team,matchday,k):

        matches = []

        for i in range(matchday-k,matchday,1):
            matches.append(self.collectMatch(team,i))

        return matches


    #splits team data into goalkeepers, defenders, midfielders and strikers
    def separateTeamData(self,matches):

        goalkeepers,defenders,midfielders,strikers = [],[],[],[]
        games,game = [], []

        for match in matches:
            for player in match:

                position = int(player[10])

                if position == 1:
                    goalkeepers.append(player)
                if position == 2:
                    defenders.append(player)
                if position == 4:
                    midfielders.append(player)
                if position == 6:
                    strikers.append(player)

            game.extend([goalkeepers,defenders,midfielders,strikers])
            games.append(game)
            game = []
            goalkeepers,defenders,midfielders,strikers = [],[],[],[]

        return games

    #Uses the time played as weighting for all attributes
    def applyWeightingToGroups(self,matches):

        for match in matches:
            for group in match:
                totalWeight = 0
                for player in group:
                    totalWeight = totalWeight + float(player[12])
                for player in group:
                    for i in range(13,209,1):
                        player[i] = (float(player[12])/totalWeight)*float(player[i])

        return matches

    #sums the player data into groups
    def sumWeightedGroupStats(self,matches):

        for match in matches:
            for group in match:
                for player in range(1,len(group),1):
                    for i in range(13,209,1):
                        group[0][i] = group[0][i]+group[player][i]

        for match in matches:
            for i in range(0,len(match),1):
                try:
                    if (match[i]):
                        match[i] = match[i][0]
                except Exception as e:
                    print(e)

        return matches

    #creates a dictionary of groups
    def createDictionary(self,matches):

        games = []
        game = []

        attributes = self.getAttributes()

        for match in matches:
            for groupStat in match:
                if (groupStat != []):

                    if int(groupStat[10]) == 1:
                        positionName = "goalkeeper"
                    elif int(groupStat[10]) == 2:
                        positionName = "defender"
                    elif int(groupStat[10]) == 4:
                        positionName = "midfielder"
                    else:
                        positionName = "striker"

                    dictionary = {attributes[0]:groupStat[0],attributes[1]:groupStat[1],attributes[5]:groupStat[5],attributes[7]:groupStat[7],attributes[9]:groupStat[9],attributes[10]:groupStat[10]}
                    for i in range(13,len(attributes),1):
                        dictionary[groupStat[9] + " " + positionName + " " + attributes[i]] = groupStat[i]
                    game.append(dictionary)
            games.append(game)
            game = []

        return games

    #converts the matches collected to a dictionary of group stats
    def convertMatchesToDictionary(self,team,matchday,k):

        matches = self.collectMatches(team,matchday,k)
        matches = self.separateTeamData(matches)
        matches = self.applyWeightingToGroups(matches)
        matches = self.sumWeightedGroupStats(matches)
        matches = self.createDictionary(matches)

        return matches

    #Combines the dictionaries for both the home and away team
    def computeGameData(self,team,matchday,k):
        match = self.collectMatch(team,matchday)[7]
        venue = match[9]
        opposition = match[7]

        if (venue == "Home"):
            homeMatches = self.convertMatchesToDictionary(team,matchday,k)
            awayMatches = self.convertMatchesToDictionary(opposition,matchday,k)

        else:
            homeMatches = self.convertMatchesToDictionary(opposition,matchday,k)
            awayMatches = self.convertMatchesToDictionary(team,matchday,k)

        return homeMatches + awayMatches

    #Collects train player data to use for modelling
    def collectTrainPlayerData(self,games,k):

        gameData = []

        for i in range(0,len(games),1):
            print(games[i])
            gameData.append(self.computeGameData(games[i][1],games[i][0],k))

        return gameData
