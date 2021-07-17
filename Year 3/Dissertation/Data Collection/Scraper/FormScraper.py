import csv
import numpy as np

class FormScraper:

    def __init__(self):

        self.currentSeasonCSV = open("Scraper/Data/2011-2012.csv",encoding="utf8")
        self.currentSeasonReader = csv.reader(self.currentSeasonCSV)

        self.previousSeasonCSV = open("Scraper/Data/2010-2011.csv",encoding="utf8")
        self.previousSeasonReader = csv.reader(self.previousSeasonCSV)

    #collects attributes - not including the betting attributes
    def getAttributes(self):

        self.currentSeasonCSV.seek(0)
        attributes = []
        for row in self.currentSeasonReader:

            for i in range(1,23,1):
                attributes = attributes+[row[i]]
            return attributes

    #Collects all games for both seasons
    #Best to worst:QPR, norwich and swansea city got promoted to the 2011-2012 season
    #Best to worst:Birmingham, Blackpool and west Ham got relegated in the 2010-2011 season
    def getAllGames(self,team):

        team1 = team
        team2 = team

        self.currentSeasonCSV.seek(0)
        self.previousSeasonCSV.seek(0)
        currentSeasonGames = []
        previousSeasonGames = []

        if team1 == "Swansea":
            team2 = "West Ham"
        if team1 == "Norwich":
            team2 = "Blackpool"
        if team1 == "QPR":
            team2 = "Birmingham"

        for row in self.currentSeasonReader:

            if len(row) > 4:
                if row[2] == team1 or row[3] == team1:
                    currentSeasonGames.append(row)

        for row in self.previousSeasonReader:

            if len(row) > 4:
                if row[2] == team2 or row[3] == team2:
                    previousSeasonGames.append(row)

        return (previousSeasonGames,currentSeasonGames)

    #Collects all game data until the matchday specified
    def getAllGamesUntilMatchday(self,matchday,k):

        self.currentSeasonCSV.seek(0)

        matches = []
        md = k+1
        line = 0

        for row in self.currentSeasonReader:
            if len(row) > 4:
                if line > k*10 and line < matchday*10+1:
                    matches.append([md,self.lengthenTeamNames(row[2]),self.lengthenTeamNames(row[3]),row[6]])
                    if len(matches) % 10 == 0:
                        md = md +1

            line = line + 1

        return matches

    #creates a dictionary for All Forms which is the latest k games
    def createDictionaryForAllForm(self,form,counter):

        attributes = self.getAttributes()

        formType = None

        if counter == 1:
            formType = "Home"
        if counter == 2:
            formType = "Away"
        if counter == 3:
            formType = "Combined"

        dictionary = dict()

        dictionary["Average " + formType + ": Win Rate"]=form[0]
        dictionary["Average " + formType + ": Loss Rate"]=form[1]
        dictionary["Average " + formType + ": Draw Rate"]=form[2]
        dictionary["Average " + formType + ": FTHG"]=form[3]
        dictionary["Average " + formType + ": FTAG"]=form[4]
        dictionary["Average " + formType + ": HTHG"]=form[5]
        dictionary["Average " + formType + ": HTAG"]=form[6]

        for i in range(7,19,1):
            dictionary["Average " + formType + ": " + attributes[i+3]]=form[i]

        return [dictionary]

    #Finds the average of the data and creates a dictionary from the information
    def findAverageForm(self,form,team,k,counter):

        data = []
        subGroup = []

        wins = 0
        draws = 0
        losses = 0

        for game in form:
            if game[5]=="H":
                if game[1]==team:
                    wins = wins + 1
                else:
                    losses = losses + 1

            if game[5]=="A":
                if game[2]==team:
                    wins = wins + 1
                else:
                    losses = losses + 1

            if game[5]=="D":
                draws = draws + 1

        for game in form:
            subGroup.append(float(game[3]))
            subGroup.append(float(game[4]))
            subGroup.append(float(game[6]))
            subGroup.append(float(game[7]))
            for i in range(10,22,1):

                subGroup.append(float(game[i]))

            data.append(subGroup)
            subGroup = []

        data = np.array(data, dtype=float)
        manipulatedData = [(float(wins)/k)] + [float(losses)/k] + [float(draws)/k] + list(np.mean(data,dtype=float, axis=0))
        manipulatedData = self.createDictionaryForAllForm(manipulatedData,counter)

        return manipulatedData

    #Creates a dictionary for k-form
    def createDictionary(self,form,version):

        counter = 1
        attributes = self.getAttributes()
        data = []

        for game in form:
            dictionary = dict()
            for i in range(1,23,1):
                dictionary[version + " " + str(counter) + ": " + attributes[i-1]] = game[i]
            data.append(dictionary)
            dictionary = []

            counter = counter + 1

        return data

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

    #converts short names into the longer version
    def lengthenTeamNames(self,team):

        dictionary = dict()
        dictionary["Man City"] = "Manchester City"
        dictionary["Man United"] = "Manchester United"
        dictionary["Arsenal"] = "Arsenal"
        dictionary["Tottenham"] = "Tottenham Hotspur"
        dictionary["Newcastle"] = "Newcastle United"
        dictionary["Chelsea"] = "Chelsea"
        dictionary["Everton"] = "Everton"
        dictionary["Liverpool"] = "Liverpool"
        dictionary["Fulham"] = "Fulham"
        dictionary["West Brom"] = "West Bromwich Albion"
        dictionary["Swansea"] = "Swansea City"
        dictionary["Norwich"] = "Norwich City"
        dictionary["Sunderland"] = "Sunderland"
        dictionary["Stoke"] = "Stoke City"
        dictionary["Wigan"] = "Wigan Athletic"
        dictionary["Aston Villa"] = "Aston Villa"
        dictionary["QPR"] = "Queens Park Rangers"
        dictionary["Bolton"] = "Bolton Wanderers"
        dictionary["Blackburn"] = "Blackburn Rovers"
        dictionary["Wolves"] = "Wolverhampton Wanderers"

        return dictionary[team]

    # 3 < k < 10 is suitable
    #get all games
    #find k last home games, k last away games and k last both games
    #include other relevant info
    def findFormK(self,team,k,matchday):
        team = self.simplifyTeamNames(team)
        team1 = team
        team2 = team

        if team1 == "Swansea":
            team2 = "West Ham"
        if team1 == "Norwich":
            team2 = "Blackpool"
        if team1 == "QPR":
            team2 = "Birmingham"

        games = self.getAllGames(team)

        previousSeasonGames = games[0]
        currentSeasonGames = games[1]

        allGames = previousSeasonGames + currentSeasonGames
        data = []

        form = []
        formKHome = []
        formKAway = []
        formKCombination = []

        for i in range(37+matchday,0,-1):
            if(i >75):
                i = 75
            if (allGames[i][2] == team1 or allGames[i][2] == team2)  and len(formKHome) < k:
                formKHome.append(allGames[i])
            if (allGames[i][3] == team1 or allGames[i][3] == team2) and len(formKAway) < k:
                formKAway.append(allGames[i])
            if len(formKCombination) < k:
                formKCombination.append(allGames[i])

        formKHome = self.createDictionary(formKHome,"Home")
        formKAway = self.createDictionary(formKAway,"Away")
        formKCombination = self.createDictionary(formKCombination,"Combined")
        form.append(formKHome)
        form.append(formKAway)
        form.append(formKCombination)
        data.append(form)
        return data

    #finds all form by collecting the data from the previous k games then finds the average
    def findAllForm(self,forms,team,k):

        data = []
        counter = 1

        for form in forms:
            listForm = []

            for i in range(0,k,1):
                listForm.append(list(form[i].values()))

            data.append(self.findAverageForm(listForm,team,k,counter))
            listForm = []
            counter = counter + 1

        return data

# HS = Home Team Shots
# AS = Away Team Shots
# HST = Home Team Shots on Target
# AST = Away Team Shots on Target
# HHW = Home Team Hit Woodwork
# AHW = Away Team Hit Woodwork
# HC = Home Team Corners
# AC = Away Team Corners
# HF = Home Team Fouls Committed
# AF = Away Team Fouls Committed
# HFKC = Home Team Free Kicks Conceded
# AFKC = Away Team Free Kicks Conceded
# HO = Home Team Offsides
# AO = Away Team Offsides
# HY = Home Team Yellow Cards
# AY = Away Team Yellow Cards
# HR = Home Team Red Cards
# AR = Away Team Red Cards
