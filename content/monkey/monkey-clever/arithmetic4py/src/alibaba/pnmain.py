import pandas
import numpy
import sklearn;
from sklearn import preprocessing

import random

from sklearn.ensemble import RandomForestClassifier
from sklearn.ensemble import  GradientBoostingClassifier
from sklearn.metrics import roc_curve, auc
from sklearn.linear_model import LogisticRegression



def convert(data):
	number = preprocessing.LabelEncoder()
	data.sab;
	data['Gender'] = number.fit_transform(data.Gender)
	data['City'] = number.fit_transform(data.City)
	data['Salary_Account'] = number.fit_transform(data.Salary_Account)
	data['Employer_Name'] = number.fit_transform(data.Employer_Name)
	data['Mobile_Verified'] = number.fit_transform(data.Mobile_Verified)
	data['Var1'] = number.fit_transform(data.Var1)
	data['Filled_Form'] = number.fit_transform(data.Filled_Form)
	data['Device_Type'] = number.fit_transform(data.Device_Type)
	data['Var2'] = number.fit_transform(data.Var2)
	data['Source'] = number.fit_transform(data.Source)
	data=data.fillna(0)
	data.sab;
	return data




if __name__=="__main__":
	number=preprocessing.LabelEncoder();
	train=pandas.read_csv('./data.csv');
	test=pandas.read_csv("./test.csv");
	train['Type']='Train';
	fullData=pandas.concat([train,test],axis=0);
	train=convert(train)
