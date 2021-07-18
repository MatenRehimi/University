from sklearn import svm
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_val_score
from sklearn.model_selection import GridSearchCV

class Model:

    def __init__(self,Xtrain,Ytrain,Xtest,Ytest):

        self.Xtrain = Xtrain
        self.Ytrain = Ytrain
        self.Xtest = Xtest
        self.Ytest = Ytest
        self.YtestUsed = []

    #Predicts probability from k+3 to matchday
    #not k+1 or k+2 as they are needed for sufficient data
    def predictProbabilities(self,k):

        probabilities = []
        start = 0
        matchday = 2

        while matchday < (self.Xtrain.shape[0]/10):
            print("matchday",matchday+k+1)
            print("")
            SVMhomeWin = svm.SVC(C=1.0, kernel='rbf', gamma=1, probability=True, max_iter=-1)
            SVMdraw = svm.SVC(C=1.0, kernel='rbf', gamma=1, probability=True, max_iter=-1)
            SVMawayWin = svm.SVC(C=1.0, kernel='rbf', gamma=1, probability=True, max_iter=-1)
            print("Home")
            home = self.findOptimalParameters(SVMhomeWin,self.Xtrain[0:10*matchday,:],self.Ytrain[0][0:10*matchday],self.Xtrain[10*matchday:10*matchday+10,:],self.Ytrain[0][10*matchday:10*matchday+10])
            print("Draw")
            draw = self.findOptimalParameters(SVMdraw,self.Xtrain[0:10*matchday,:],self.Ytrain[1][0:10*matchday],self.Xtrain[10*matchday:10*matchday+10,:],self.Ytrain[1][10*matchday:10*matchday+10])
            print("Away")
            away = self.findOptimalParameters(SVMawayWin,self.Xtrain[0:10*matchday,:],self.Ytrain[2][0:10*matchday],self.Xtrain[10*matchday:10*matchday+10,:],self.Ytrain[2][10*matchday:10*matchday+10])
            self.YtestUsed.append([self.Ytrain[0][10*matchday:10*matchday+10],self.Ytrain[1][10*matchday:10*matchday+10],self.Ytrain[2][10*matchday:10*matchday+10]])
            matchday = matchday + 1
            probabilities.append([home,draw,away])


        SVMhomeWin = svm.SVC(C=1.0, kernel='rbf', gamma=1, probability=True, max_iter=-1)
        SVMdraw = svm.SVC(C=1.0, kernel='rbf', gamma=1, probability=True, max_iter=-1)
        SVMawayWin = svm.SVC(C=1.0, kernel='rbf', gamma=1, probability=True, max_iter=-1)

        print("matchday",matchday+k+1)
        print(" ")
        print("Home")
        home = self.findOptimalParameters(SVMhomeWin,self.Xtrain,self.Ytrain[0],self.Xtest,self.Ytest[0])
        print("Draw")
        draw = self.findOptimalParameters(SVMdraw,self.Xtrain,self.Ytrain[1],self.Xtest,self.Ytest[1])
        print("Away")
        away = self.findOptimalParameters(SVMawayWin,self.Xtrain,self.Ytrain[2],self.Xtest,self.Ytest[2])
        self.YtestUsed.append([self.Ytest[0],self.Ytest[1],self.Ytest[2]])

        probabilities.append([home,draw,away])

        matchday = 1
        for probability in probabilities:
            print("matchday",matchday+k+2)
            print(probability)
            matchday = matchday + 1
        return probabilities

    #Uses grid search to optimise parameters
    def findOptimalParameters(self,svm,featureMatrix,Ytrain,Xtest,Ytest):

        c_range = [1,10,100,1000]
        gamma_range = [2**-5,2**-3,2**-1,2**1]
        param_grid = dict(C = c_range,gamma = gamma_range)
        clf = GridSearchCV(svm,param_grid,cv=5,scoring="accuracy",error_score='raise')
        try:
            clf.fit(featureMatrix,Ytrain)
            print("predict")
            print(clf.best_params_)
            prediction = list(clf.predict(Xtest))
            print(prediction)
            print("Actual")
            print(Ytest)
            accuracy = clf.score(Xtest,Ytest)
            print("accuracy",accuracy)
            # prediction = clf.predict_proba(Xtest)
            # print(prediction)

        except Exception as e:
            print('An error occured.')
            print(e)

        print(" ")
        return prediction
