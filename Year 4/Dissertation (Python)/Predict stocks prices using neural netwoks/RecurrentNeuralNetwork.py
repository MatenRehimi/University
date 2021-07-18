import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM,Dropout,Dense,BatchNormalization,Activation,Flatten
from sklearn.metrics import mean_squared_error

class RecurrentNeuralNetwork:

    #checks if feature is valid then finds the MSE.
    def __init__(self,feature):
        df = pd.read_csv('Data/featureVectorWithIndividualStocks.csv');
        size = len(df.columns)-3;
        if (feature >= 58 and feature <= size):
            self.findMSE('Data/featureVectorWithIndividualStocks.csv',feature)
        else:
            print("feature range is 58 to "+str(size))

    #LSTM model
    def LSTM(self,X_train):
        model = Sequential([
            LSTM(units=20,return_sequences=True,input_shape=(None, 58)),
            BatchNormalization(),
            Activation('relu'),
            Dropout(0.5),
            LSTM(units=50,return_sequences=True),
            BatchNormalization(),
            Activation('relu'),
            Dropout(0.5),
            LSTM(units=50,return_sequences=True),
            BatchNormalization(),
            Activation('relu'),
            Dropout(0.5),
            LSTM(units=50,return_sequences=True),
            BatchNormalization(),
            Activation('relu'),
            Dropout(0.5),
            LSTM(units=50,return_sequences=True),
            BatchNormalization(),
            Activation('relu'),
            Dense(1)
        ]);
        return model;

    #Creates training and test data. Trains model and compares difference between predicted and actual stock prices.
    def findMSE(self,path,feature):
        featureVector = pd.read_csv(path, index_col=0);
        featureVector = featureVector.dropna();
        featureVector.drop('Date', axis=1, inplace=True);
        training_set = featureVector.to_numpy();

        #Normalise data
        training_set_scaled = (training_set - np.mean(training_set, axis=0).reshape(1, -1)) / (np.std(training_set, axis=0).reshape(1, -1) + 1e-08)
        X_train, y_train, X_test, y_test = [],[],[],[];

        #Create X_train and y_train
        for i in range(20, training_set.shape[0]-50):
            X_train.append(training_set_scaled[i-20:i, 0:58]);
            y_train.append(training_set_scaled[i-20:i,feature].reshape(-1,1));

        X_train, y_train = np.array(X_train), np.array(y_train);

        #Create x_test and y_test
        for i in range(training_set.shape[0]-50,training_set.shape[0]):
            X_test.append(training_set_scaled[i-20:i, 0:58]);
            y_test.append(training_set_scaled[i-20:i,feature].reshape(-1,1));

        X_test, y_test = np.array(X_test), np.array(y_test);
        #X_train/X_test -> (samples,timesteps,features)
        #y_train/y_Test -> (samples,timesteps,feature)
        model = self.LSTM(X_train);
        model.compile(optimizer='adam',loss='mse');
        model.fit(X_train,y_train,epochs=50,batch_size=16);

        predicted_stock_price = model.predict(X_train);
        predicted_stock_price = np.reshape(predicted_stock_price,(training_set.shape[0]-50-20,20))
        predicted_stock_price = [x[19] for x in predicted_stock_price]
        y_train = np.reshape(y_train,(training_set.shape[0]-50-20,20))
        y_train = [x[19] for x in y_train]

        predicted_stock_price2 = model.predict(X_test);
        predicted_stock_price2 = np.reshape(predicted_stock_price2,(50,20))
        predicted_stock_price2 = [x[19] for x in predicted_stock_price2]
        y_test = np.reshape(y_test,(50,20))
        y_test = [x[19] for x in y_test]

        print("LSTM")
        print("MSE");
        mse = mean_squared_error(y_train,predicted_stock_price);
        print("Train ",mse);
        mse2 = mean_squared_error(y_test,predicted_stock_price2);
        print("Test ",mse2);
