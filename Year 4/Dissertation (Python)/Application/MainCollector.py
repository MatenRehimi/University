import datetime as dt
import pandas as pd
import os.path
from functools import reduce
from YahooCollector import YahooCollector
from PerthMintCollector import PerthMintCollector

class MainCollector:

    def __init__(self):
        self.createVectorWithIndividualStocks();

    def createVectorWithIndividualStocks(self):
        yahooCollector = YahooCollector(dt.datetime(2016,11,29),dt.datetime(2030,1,10));
        perthMintCollector = PerthMintCollector();
        csvFiles = yahooCollector.csvFiles;
        csvFiles.append(perthMintCollector.naturalResources);

        data = pd.read_html('https://en.wikipedia.org/wiki/List_of_S%26P_500_companies');
        table = data[0];
        symbols = table['Symbol'];
        x=[]
        for s in symbols:
            s = s.replace('.','-');
            if (os.path.exists('S&P500/'+s+'.csv')==True):
                csv = pd.read_csv('S&P500/'+s+'.csv')
                rows = len(csv)
                if (rows > 5000):
                    csvFiles.append(csv);
            else:
                print(s + ".csv does not exist");

        completeVector = reduce(lambda left,right: pd.merge(left,right,on=['Date']), csvFiles);
        completeVector.to_csv("Data/featureVectorWithIndividualStocks.csv");
