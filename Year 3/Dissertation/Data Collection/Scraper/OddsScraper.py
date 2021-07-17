import csv

class OddsScraper:

    def __init__(self):

        self.csvFile = open("Scraper/Data/2011-2012.csv",encoding="utf8")
        self.reader = csv.reader(self.csvFile)

    #retrieves teams and odds data
    def getAttributes(self):

        self.csvFile.seek(0)
        attributes = []
        for row in self.reader:

            attributes = [row[2]]+[row[3]]

            for i in range(23,71,1):
                attributes = attributes+[row[i]]

            return attributes

    #obtains the game data for the game between the home and away team
    def getGameData(self,homeTeam,awayTeam):

        self.csvFile.seek(0)
        gameData = []
        for row in self.reader:
            if len(row) > 4:
                if row[2] == homeTeam and row[3] == awayTeam:

                    gameData = [homeTeam,awayTeam]

                    for i in range(23,71,1):

                        gameData = gameData + [row[i]]

                    return gameData

    #converts to full names into short names used in the CSV files
    def simplifyTeamNames(self,team):

        dictionary = dict()
        dictionary["Manchester City"] = "Man City"
        dictionary["Manchester United"] = "Man United"
        dictionary["Arsenal"] = "Arsenal"
        dictionary["Tottenham Hotspur"] = "Tottenham"
        dictionary["Newcastle United"] = "Newcastle"
        dictionary["Chelsea"] = "Chelsea"
        dictionary["Everton"] = "Everton"
        dictionary["Liverpool"] = "Liverpool"
        dictionary["Fulham"] = "Fulham"
        dictionary["West Bromwich Albion"] = "West Brom"
        dictionary["Swansea City"] = "Swansea"
        dictionary["Norwich City"] = "Norwich"
        dictionary["Sunderland"] = "Sunderland"
        dictionary["Stoke City"] = "Stoke"
        dictionary["Wigan Athletic"] = "Wigan"
        dictionary["Aston Villa"] = "Aston Villa"
        dictionary["Queens Park Rangers"] = "QPR"
        dictionary["Bolton Wanderers"] = "Bolton"
        dictionary["Blackburn Rovers"] = "Blackburn"
        dictionary["Wolverhampton Wanderers"] = "Wolves"

        return dictionary[team]

    #obtains game data between the home and away teams
    #then converts it into a dictionary using the attributes
    def getDictionary(self,homeTeam,awayTeam):

        dictionary = dict()

        gameData = self.getGameData(homeTeam,awayTeam)
        attributes = self.getAttributes()

        for i in range(0,50,1):
            dictionary[attributes[i]] = gameData[i]

        return dictionary

    #obtains all odds data in the form of a list
    def addOddsData(self,homeTeam,awayTeam):

        game = []

        homeTeam2 = self.simplifyTeamNames(homeTeam)
        awayTeam2 = self.simplifyTeamNames(awayTeam)
        game.append(self.getDictionary(homeTeam2,awayTeam2))

        return game

    #range k+3 to matchday
    def findsOdds(self,matchday,k):
        self.csvFile.seek(0)
        rowNumber = 1
        selectedRows = []
        for row in self.reader:
            if (rowNumber < matchday*10+2 and rowNumber > (k+2)*10+1):

                homeOdds = [row[23],row[26],row[29],row[32],row[35],row[38],row[41],row[44],row[47],row[50],row[54],row[55],row[67],row[68]]
                drawOdds = [row[24],row[27],row[30],row[33],row[36],row[39],row[42],row[45],row[48],row[51],row[56],row[57]]
                loseOdds = [row[25],row[28],row[31],row[34],row[37],row[40],row[43],row[46],row[49],row[52],row[58],row[59],row[69],row[70]]
                selectedRows.append([max(homeOdds),max(drawOdds),max(loseOdds)])
            rowNumber = rowNumber + 1

        return selectedRows


#http://www.football-data.co.uk/notes.txt

# B365H = Bet365 home win odds
# B365D = Bet365 draw odds
# B365A = Bet365 away win odds
# BSH = Blue Square home win odds
# BSD = Blue Square draw odds
# BSA = Blue Square away win odds
# BWH = Bet&Win home win odds
# BWD = Bet&Win draw odds
# BWA = Bet&Win away win odds
# GBH = Gamebookers home win odds
# GBD = Gamebookers draw odds
# GBA = Gamebookers away win odds
# IWH = Interwetten home win odds
# IWD = Interwetten draw odds
# IWA = Interwetten away win odds
# LBH = Ladbrokes home win odds
# LBD = Ladbrokes draw odds
# LBA = Ladbrokes away win odds
# PSH and PH = Pinnacle home win odds
# PSD and PD = Pinnacle draw odds
# PSA and PA = Pinnacle away win odds
# SOH = Sporting Odds home win odds
# SOD = Sporting Odds draw odds
# SOA = Sporting Odds away win odds
# SBH = Sportingbet home win odds
# SBD = Sportingbet draw odds
# SBA = Sportingbet away win odds
# SJH = Stan James home win odds
# SJD = Stan James draw odds
# SJA = Stan James away win odds
# SYH = Stanleybet home win odds
# SYD = Stanleybet draw odds
# SYA = Stanleybet away win odds
# VCH = VC Bet home win odds
# VCD = VC Bet draw odds
# VCA = VC Bet away win odds
# WHH = William Hill home win odds
# WHD = William Hill draw odds
# WHA = William Hill away win odds
#
# Bb1X2 = Number of BetBrain bookmakers used to calculate match odds averages and maximums
# BbMxH = Betbrain maximum home win odds
# BbAvH = Betbrain average home win odds
# BbMxD = Betbrain maximum draw odds
# BbAvD = Betbrain average draw win odds
# BbMxA = Betbrain maximum away win odds
# BbAvA = Betbrain average away win odds
#
# MaxH = Oddsportal maximum home win odds
# MaxD = Oddsportal maximum draw win odds
# MaxA = Oddsportal maximum away win odds
# AvgH = Oddsportal average home win odds
# AvgD = Oddsportal average draw win odds
# AvgA = Oddsportal average away win odds
#
#
#
# Key to total goals betting odds:
#
# BbOU = Number of BetBrain bookmakers used to calculate over/under 2.5 goals (total goals) averages and maximums
# BbMx>2.5 = Betbrain maximum over 2.5 goals
# BbAv>2.5 = Betbrain average over 2.5 goals
# BbMx<2.5 = Betbrain maximum under 2.5 goals
# BbAv<2.5 = Betbrain average under 2.5 goals
#
# GB>2.5 = Gamebookers over 2.5 goals
# GB<2.5 = Gamebookers under 2.5 goals
# B365>2.5 = Bet365 over 2.5 goals
# B365<2.5 = Bet365 under 2.5 goals
#
#
# Key to Asian handicap betting odds:
#
# BbAH = Number of BetBrain bookmakers used to Asian handicap averages and maximums
# BbAHh = Betbrain size of handicap (home team)
# BbMxAHH = Betbrain maximum Asian handicap home team odds
# BbAvAHH = Betbrain average Asian handicap home team odds
# BbMxAHA = Betbrain maximum Asian handicap away team odds
# BbAvAHA = Betbrain average Asian handicap away team odds
