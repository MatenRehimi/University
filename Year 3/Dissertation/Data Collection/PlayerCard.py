class PlayerCard:

    def __init__(self, rating, name, position, pace, shot, passing, dribbling, defending, heading):

        self.rating = rating
        self.name = name
        self.position = position
        self.pace = pace
        self.shot = shot
        self.passing = passing
        self.dribbling = dribbling
        self.defending = defending
        self.heading = heading

    def getPosition(self):
        return self.position

    def getPace(self):
        return self.pace

    def getShot(self):
        return self.shot

    def getPassing(self):
        return self.passing

    def getDribbling(self):
        return self.dribbling

    def getDefending(self):
        return self.defending

    def getHeading(self):
        return self.heading

    #string representation of a card
    def __str__(self):
        print("------------------")
        print(" " +  str(self.rating),  self.name + "         ")
        print("|" + self.position + "              |")
        print("|                |")
        print("|" + "PAC", str(self.pace), "DRI", str(self.dribbling) + "   |")
        print("|" + "SHO", str(self.shot), "DEF", str(self.defending) + "   |")
        print("|" + "PAS", str(self.passing), "HEA", str(self.heading) + "   |")
        print("------------------")
        return ""
