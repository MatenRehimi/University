import urllib.request
from urllib.request import Request, urlopen

from bs4 import BeautifulSoup

from PlayerCard import PlayerCard

import csv
import os

import numpy as np

class WebScraper:

    #Creates a local csv file to speed up the program
    def __init__(self):
        self.cardData = dict()

        try:
            self.csvFile = open("Scraper/Data/FIFAStats.csv",encoding="utf8")
        except:
            self.createCSVfile()
            self.csvFile = open("Scraper/Data/FIFAStats.csv",encoding="utf8")

        self.reader = csv.reader(self.csvFile)

        counter = 0
        for row in self.reader:
            if counter%2 is 0:
                key = row[0]
            else:
                self.cardData[key]=row
            counter = counter + 1

    #Creates local csv file and writes data to it
    def createCSVfile(self):
        self.findAllCardData()
        myFile = open("Scraper/Data/FIFAStats.csv", 'w')
        with myFile:
            writer = csv.writer(myFile)
            for key,value in self.cardData.items():
                writer.writerows([[key],value])


    #finds the card data for a single team for all teams
    def findAllCardData(self):
        teams = ["Manchester-City","Manchester-United","Arsenal","Tottenham-Hotspur","Newcastle-United","Chelsea","Everton","Liverpool","Fulham",
        "West-Bromwich-Albion","Swansea-City","Norwich-City","Sunderland","Stoke-City","Wigan-Athletic","Aston-Villa","Queens-Park-Rangers",
        "Bolton-Wanderers", "Blackburn-Rovers","Wolverhampton-Wanderers"]

        for team in teams:
            self.cardData[team] = self.findCardData(team)

    #finds the card data for a single team
    def findCardData(self,team):
        cardData = []

        soup = self.findHTML(team)

        for link in soup.find_all('div', {"class": ["playercard-rating", "playercard-name", "playercard-position",
        "playercard-attr playercard-attr1", "playercard-attr playercard-attr2", "playercard-attr playercard-attr3",
        "playercard-attr playercard-attr4", "playercard-attr playercard-attr5", "playercard-attr playercard-attr6"]}):
            text = link.text.strip()
            if "Players" not in text:
                cardData.append(link.text.strip())

        return cardData

    #collects webpage data and parses it to html
    def findHTML(self,team):

        url = "https://www.futhead.com/12/clubs/" + team + "/"

        req = Request(url, headers = {"User-Agent": "Mozilla/5.0"})
        webpage = urlopen(req).read()
        bs1 = BeautifulSoup(webpage, 'html.parser')

        return bs1

    def getCardData(self,team):
        team = team.replace(" ","-")
        return self.cardData[team]

    #turns the card data into a player card object and filters out players with multiple cards
    #the multiple cards are averaged
    def createCards(self,team):

        cardData = self.getCardData(team)
        cards = []

        for i in range(0,len(cardData),9):

            playerCard = PlayerCard(int(cardData[i]),cardData[i+1],cardData[i+2],int(cardData[i+3][:2]),
            int(cardData[i+4][:2]),int(cardData[i+5][:2]),int(cardData[i+6][:2]),int(cardData[i+7][:2]),
            int(cardData[i+8][:2]))

            sizeOfCards = len(cards)

            cards.append(playerCard)

        cards = self.filter(cards)

        return cards

    #collects unique cards then creates a unique card if there are cards for the same players
    #same player cards are combined and the average is added
    def filter(self, cards):

        uniqueNames = set()

        for i in cards:
            uniqueNames.add(i.name)

        uniqueNames = sorted(list(uniqueNames))

        uniqueCards = []

        for i in uniqueNames:

            uniqueCards.append(self.combineCards(list(filter(lambda playercard: playercard.name==i, cards))))

        return uniqueCards

    #combines FIFA cards into a new averaged player card object
    def combineCards(self,players):

        size = len(players)
        rating = 0
        pace = 0
        shot = 0
        passing = 0
        dribbling = 0
        defending = 0
        heading = 0

        for player in players:

            rating = rating + player.rating
            pace = pace + player.pace
            shot = shot + player.shot
            passing = passing + player.passing
            dribbling = dribbling + player.dribbling
            defending = defending + player.defending
            heading = heading + player.heading

        rating = int(rating/size+0.5)
        pace = int(pace/size+0.5)
        shot = int(shot/size+0.5)
        passing = int(passing/size+0.5)
        dribbling = int(dribbling/size+0.5)
        defending = int(defending/size+0.5)
        heading = int(heading/size+0.5)

        playercard = PlayerCard(rating, players[0].name, players[0].position, pace, shot, passing,
        dribbling, defending, heading)

        return playercard

    #separates cards based of their groups
    def separateCards(self,cards):

        goalkeepers,defenders,midfielders,strikers = [],[],[],[]

        for playerCard in cards:
            position = playerCard.getPosition()
            if position == "GK":
                goalkeepers.append(playerCard)
            elif position in ["LB","LWB","CB","RB","RWB"]:
                defenders.append(playerCard)
            elif position in ["RM","LM","CM","CDM","CAM","RW","LW"]:
                midfielders.append(playerCard)
            else:
                strikers.append(playerCard)

        return (goalkeepers,defenders,midfielders,strikers)

    #Finds the average of each group
    def findAverageOfCards(self,group):

        totalPace,totalShot,totalPassing,totalDribbling,totalDefending,totalHeading = 0,0,0,0,0,0
        size = len(group)

        for card in group:
            totalPace = totalPace+card.getPace()
            totalShot = totalShot+card.getShot()
            totalPassing = totalPassing+card.getPassing()
            totalDribbling = totalDribbling+card.getDribbling()
            totalDefending = totalDefending+card.getDefending()
            totalHeading = totalHeading+card.getHeading()

        return [totalPace/size,totalShot/size,totalPassing/size,totalDribbling/size,totalDefending/size,totalHeading/size]

    #Collects all the FIFA data into a single list
    def getFIFAdata(self,homeTeam,awayTeam):

        data = []
        homeTeamCards = self.createCards(homeTeam)
        separateHomeTeamCards = self.separateCards(homeTeamCards)

        for group in separateHomeTeamCards:

            data.append(self.findAverageOfCards(group))

        awayTeamCards = self.createCards(awayTeam)
        separateAwayTeamCards = self.separateCards(awayTeamCards)
        for group in separateAwayTeamCards:
            data.append(self.findAverageOfCards(group))

        return data
