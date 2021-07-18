import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv1D,MaxPooling1D, Flatten, Dense,Activation,BatchNormalization,LayerNormalization,Dropout
from sklearn.metrics import mean_squared_error

class FeedForwardNeuralNetwork:

    def __init__(self,feature,model):
        df = pd.read_csv('Data/featureVectorWithIndividualStocks.csv');
        size = len(df.columns)-3;
        if (feature >= 58 and feature <= size):
            self.findMSE('Data/featureVectorWithIndividualStocks.csv',feature,model)
        else:
            print("feature range is 58 to "+str(size))


    #Multilayer Perceptron model
    def MLP(self,X_train):
        model = Sequential([
            Dense(units=32,use_bias=False, input_shape=(X_train.shape[1], X_train.shape[2])),
            #BatchNormalization(),
            Activation('relu'),
            Dropout(0.45),
            Dense(units=32,use_bias=False),
            # BatchNormalization(),
            Activation('relu'),
            Dropout(0.45),
            Dense(units=32,use_bias=False),
            # BatchNormalization(),
            Activation('relu'),
            Dropout(0.45),
            Dense(units=32,use_bias=False),
            # BatchNormalization(),
            Activation('relu'),
            Dropout(0.45),
            Dense(units=32,use_bias=False),
            # BatchNormalization(),
            Activation('relu'),
            Dropout(0.45),
            Dense(units=32,use_bias=False),
            # BatchNormalization(),
            Activation('relu'),
            Dropout(0.45),
            Flatten(),
            Dense(1)
        ]);
        return model;

    #Convolutional neural network
    def CNN(self,X_train):
        model = Sequential([
            Conv1D(filters=32,kernel_size=5, input_shape=(X_train.shape[1], X_train.shape[2])),
            LayerNormalization(),
            MaxPooling1D(pool_size=2),
            Activation('relu'),
            Dropout(0.45),
            Conv1D(filters=32,kernel_size=5),
            LayerNormalization(),
            MaxPooling1D(pool_size=2),
            Activation('relu'),
            Dropout(0.45),
            Flatten(),
            Dense(1)
        ]);
        return model;

    # Convolutional neural network including multilayer perceptron
    def CNN_MLP(self,X_train):
        model = Sequential([
            Conv1D(filters=32,kernel_size=5, input_shape=(X_train.shape[1], X_train.shape[2])),
            LayerNormalization(),
            MaxPooling1D(pool_size=2),
            Activation('relu'),
            Dropout(0.45),
            Conv1D(filters=32,kernel_size=5),
            LayerNormalization(),
            MaxPooling1D(pool_size=2),
            Activation('relu'),
            Dropout(0.45),
            Dense(units=32,use_bias=False),
            BatchNormalization(),
            Activation('relu'),
            Dropout(0.45),
            Dense(units=32,use_bias=False),
            BatchNormalization(),
            Activation('relu'),
            Dropout(0.45),
            Flatten(),
            Dense(1)
        ]);
        return model;

    #Creates training and test data. Trains model and compares difference between predicted and actual stock prices.
    def findMSE(self,path,feature,modelType):
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
            y_train.append(training_set_scaled[i,feature]);

        X_train, y_train = np.array(X_train), np.array(y_train);

        #Create x_test and y_test
        for i in range(training_set.shape[0]-50,training_set.shape[0]):
            X_test.append(training_set_scaled[i-20:i, 0:58]);
            y_test.append(training_set_scaled[i,feature]);

        X_test, y_test = np.array(X_test), np.array(y_test);
        #shape of data
        #X_train/X_test -> (samples,timesteps,features) -> (timesteps,samples,features)
        #y_train/y_test -> (samples,)

        #Type of Model used
        if (modelType=="MLP"):
            model = self.MLP(X_train);
        if (modelType=="CNN_MLP"):
            model = self.CNN_MLP(X_train);
        if (modelType=="CNN"):
            model = self.CNN(X_train);
        model.compile(optimizer='adam',loss='mse');
        model.fit(X_train,y_train,epochs=256,batch_size=16);

        predicted_stock_price = model.predict(X_train);
        predicted_stock_price2 = model.predict(X_test);

        print(modelType)
        print("MSE");
        mse = mean_squared_error(y_train,predicted_stock_price);
        print("Train ",mse);
        mse2 = mean_squared_error(y_test,predicted_stock_price2);
        print("Test ",mse2);
        print("")
