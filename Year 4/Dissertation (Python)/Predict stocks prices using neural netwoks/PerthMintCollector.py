import datetime as dt
import pandas as pd
import os.path

class PerthMintCollector:
    def __init__(self):
        if (os.path.exists('Data/londonfixes-current.csv')==False):
            self.naturalResources = self.collect();
        else:
            self.naturalResources = pd.read_csv('Data/londonfixes-current.csv', index_col=0);

    def collect(self):
        df = pd.read_csv("http://www.perthmint.com.au/treasury/londonfixes-current.csv?_ga=2.153188344.1557581358.1578755164-1059954146.1574948163",
            usecols=[0,1,2,3,4,5,6,7], index_col=False);
        #Rename columns
        df.columns = ["Date","Gold_AM_FIX","Gold_PM_FIX","Silver_FIX","Platinum_AM_FIX","Planinum_PM_FIX","Palladium_AM_FIX","Palladium_PM_FIX"];
        #10:30 and 3pm
        #Drop first 4 rows
        df = df.drop([0,1,2,3]);
        #Remove Dates with no null value
        df = df[df.Gold_AM_FIX.notnull()];
        #Convert Date format from 30/12/15 to 2015-12-30
        df['Date']=pd.to_datetime(df.Date, dayfirst=True)
        df.to_csv('Data/londonfixes-current.csv', index=False)
        return pd.read_csv('Data/londonfixes-current.csv', index_col=0);
