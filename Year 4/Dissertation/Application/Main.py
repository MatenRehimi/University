from MainCollector import MainCollector
from FeedForwardNeuralNetwork import FeedForwardNeuralNetwork
from RecurrentNeuralNetwork import RecurrentNeuralNetwork

#Maincollector not needed as feature vector has been produced already due to a potential change in S&P 500.
#Due to 40MB limit, feature vector has been calculated already.
#In addition, only run one type of model at a time
if __name__ == "__main__":

    #maincollector = MainCollector()
    #Types of FeedForwardNeuralNetwork are CNN, MLP and CNN_MLP
    #FNN_CNN = FeedForwardNeuralNetwork(109,"CNN")
    #FNN_MLP = FeedForwardNeuralNetwork(109,"MLP")
    #FNN_CNN_MLP = FeedForwardNeuralNetwork(109,"CNN_MLP")
    RNN_LSTM = RecurrentNeuralNetwork(106)
