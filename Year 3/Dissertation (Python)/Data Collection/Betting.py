class Betting:

    def __init__(self):
        x = 1

    #Implemented a bet matching strategy that invests 1/10 of the budget on predictions from the SVM model
    def findFinalBalance(self,total,predictedGameResults,actualGameResults,odds):

        counter = 0

        for tenMatches in range(0,len(predictedGameResults),1):
            for match in range(0,10,1):
                totalProfit = 0
                if (predictedGameResults[tenMatches][0][match] == 1):
                    if (actualGameResults[tenMatches][0][match] == 1):
                        totalProfit = totalProfit + 0.1*total*float(odds[counter][0])
                    else:
                        totalProfit = totalProfit - 0.1*total

                if (predictedGameResults[tenMatches][1][match] == 1):
                    if (actualGameResults[tenMatches][1][match] == 1):
                        totalProfit = totalProfit + 0.1*total*float(odds[counter][1])
                    else:
                        totalProfit = totalProfit - 0.1*total

                if (predictedGameResults[tenMatches][2][match] == 1):
                    if (actualGameResults[tenMatches][2][match] == 1):
                        totalProfit = totalProfit + 0.1*total*float(odds[counter][2])
                    else:
                        totalProfit = totalProfit - 0.1*total
                counter = counter + 1
            total = total + totalProfit
            #print("total Profit",totalProfit)
            print("total",total)


        return total
