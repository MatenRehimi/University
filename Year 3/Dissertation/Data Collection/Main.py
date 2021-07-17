from Scraper.MainScraper import MainScraper
from Model import Model
from Betting import Betting

#Predicts games from k+3 to matchday
def predictGames(matchday,k):

    mainScraper = MainScraper()
    X = mainScraper.dataMatrix(matchday,k)
    Y = mainScraper.collectResults(matchday,k)

    rows = X.shape[0]
    Xtrain = X[0:rows-10,:]
    print(Xtrain.shape[0])
    Xtest = X[rows-10:rows,:]

    numberOfGames = len(Y[0])
    Ytrain = [Y[0][0:numberOfGames-10],Y[1][0:numberOfGames-10],Y[2][0:numberOfGames-10]]
    Ytest = [Y[0][numberOfGames-10:],Y[1][numberOfGames-10:],Y[2][numberOfGames-10:]]

    model = Model(Xtrain,Ytrain,Xtest,Ytest)
    predictedGameResults = model.predictProbabilities(k)
    actualGameResults = model.YtestUsed

    odds = mainScraper.findOdds(matchday,k)
    betting = Betting()
    total = betting.findFinalBalance(100,predictedGameResults,actualGameResults,odds)
    print(total)

if __name__ == "__main__":

    predictGames(38,2)
