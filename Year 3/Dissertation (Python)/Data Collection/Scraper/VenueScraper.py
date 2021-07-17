import csv

from math import radians, cos, sin, asin, sqrt

class VenueScraper:

    def __init__(self):

        self.csvFile = open("Scraper/Data/Venues.csv",encoding="utf8")
        self.reader = csv.reader(self.csvFile)

    #finds a row of data containing a certain team
    def findTeam(self,team):
        self.csvFile.seek(0)
        for row in self.reader:
            if (team == row[2]):
                return row
        print(team)
        return None

    #finds the Capacity for the team specified
    def findCapacity(self,team):
        self.csvFile.seek(0)
        for row in self.reader:
            if (team == row[2]):
                return row[13]
        return 0

    #calculates distance away team had to travel
    def calculateDistance(self, homeTeamName, awayTeamName):

        AVERAGE_RADIUS_OF_EARTH_KM = 6371

        team1 = self.findTeam(homeTeamName)
        team2 = self.findTeam(awayTeamName)

        lon1 = float(team1[8])
        lat1 = float(team1[7])

        lon2 = float(team2[8])
        lat2 = float(team2[7])

        # convert decimal degrees to radians
        lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, lon2, lat2])

        # haversine formula
        dlon = lon2 - lon1
        dlat = lat2 - lat1
        a = sin(dlat/2)**2 + cos(lat1) * cos(lat2) * sin(dlon/2)**2
        c = 2 * asin(sqrt(a))

        return c*AVERAGE_RADIUS_OF_EARTH_KM

    #Collects all venue data into a list
    def getVenueData(self,homeTeam, awayTeam):
        data = []
        data.append(self.findCapacity(homeTeam))
        data.append(self.findCapacity(awayTeam))
        data.append(self.calculateDistance(homeTeam,awayTeam))

        return data
