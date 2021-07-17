import csv

class TeamWagesScraper:

    def __init__(self):

        self.csvFile = open("Scraper/Data/TeamWages.csv",encoding="utf8")
        self.reader = csv.reader(self.csvFile)

    #Finds a team in the TeamWages.csv file
    def findTeam(self, team):

        self.csvFile.seek(0)
        for row in self.reader:
            if(team==row[0]):
                return row

    #Makes a dictionary of wages and other similar data
    def addWagesData(self,homeTeam,awayTeam):

        data = []

        dictionary = dict()

        team = self.findTeam(homeTeam)
        opposition = self.findTeam(awayTeam)

        dictionary["Home Team Season Wages"] = team[1]
        dictionary["Away Team Season Wages"] = opposition[1]
        dictionary["Home Team Final League Standing"] = team[2]
        dictionary["Away Team Final League Standing"] = opposition[2]
        dictionary["Home Team Points Gained "] = team[3]
        dictionary["Away Team Points Gained"] = opposition[3]

        return dictionary
