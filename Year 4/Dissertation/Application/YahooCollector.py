import datetime as dt
import pandas_datareader.data as web
import pandas as pd
import os.path
import csv

class YahooCollector:
    def __init__(self,start,end):
        self.collectSP500(start,end);
        self.csvFiles = self.collectFeatureVectorWithoutIndividualStocks(start,end);

    def collectFeatureVectorWithoutIndividualStocks(self,start,end):
        symbols = ['VTSAX','VTIAX','VBTLX','VNQ','BTC-GBP','ETH-GBP','XRP-GBP','LTC-GBP','USDT-GBP','BCH-GBP'];
        csvFiles = [];
        if (os.path.exists('Data')==False):
            os.makedirs('Data');
        for s in symbols:
            if (os.path.exists('Data/'+s+'.csv')==False):
                if(s=="VNQ"):
                    df = web.DataReader(s,'yahoo');
                    df.columns = [s+" high", s+" low", s+" open", s+" close",s+" volume",s+" adj close"];
                    df.to_csv('Data/'+s+'.csv');
                else:
                    df = web.DataReader(s,'yahoo',start,end).drop(columns="Volume");
                    df.columns = [s+" high", s+" low", s+" open", s+" close",s+" adj close"];
                    df.to_csv('Data/'+s+'.csv');
            csvFiles.append(pd.read_csv('Data/'+s+'.csv'));

        return csvFiles;

    def collectSP500(self,start,end):
         data = pd.read_html('https://en.wikipedia.org/wiki/List_of_S%26P_500_companies');
         table = data[0];
         symbols = table['Symbol'];
         if (os.path.exists('S&P500')==False):
             os.makedirs('S&P500');

         for s in symbols:
             s = s.replace('.','-');
             if (os.path.exists('S&P500/'+s+'.csv')==False):
                 print('creating ' + s);
                 df = web.DataReader(s, 'yahoo', start, end);
                 df.columns = [s+" high", s+" low", s+" open", s+" close",s+" volume",s+" adj close"]
                 df.to_csv('S&P500/' + s+ '.csv');



    def collectIndividualStock(self,symbol):
        stock = pd.read_csv('S&P500/'+symbol+'.csv', usecols=['Date',symbol+' high',symbol+' low']);
        return stock;
