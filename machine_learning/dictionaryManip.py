import numpy as np
import parseFile as ps
import bgd
yes = dict()
no = dict()

def loadWordsFromFile(filename):
	tempyes = dict()
	tempno = dict()
	with open(filename,'r') as f:
		line = f.readlines()
		tempyes[line[0]] = line[1]
		tempno[line[0]] = line[-1]
	return (tempyes,tempno)

def storeWordsToFile(yesDict,noDict,filename):
	with open(filename,'w') as f:
		for word in yesDict.keys():
			f.write(str.strip(word))
			f.write(str(' '))
			f.write(str(yesDict[word]))
			f.write(str(' '))
			f.write(str(noDict[word]))
			f.write("\n")
			
def loadFeatureFromFile(filename):
	feature = list()
	(tempyes,tempno) = loadWordsFromFile(filename)
	for word in tempyes.keys():
	#FEATURE VECTOR DECISION#
		if(tempyes[word]/(tempyes[word]+tempno[word])>=.75):
			feature.append(word)
	return feature
	
(yes,no) = loadWordsFromFile('tester.txt')
valText = ps.parse()
#for each tweet, manipulate dictionary (part of training)
for row in valText:
	#pop V bit from end of row
	V = row.pop(-1)
	for word in row:
		#if the key does not exist in the dictionary, add it with 1 weight
		if yes.get(word, 'none') == 'none':
			if(V==1):
				yes[word] = 1	
				no[word] = 0
			else:
				yes[word] = 0
				no[word] = 1
		else:
			if(V==1):
				yes[word] += 1
				#print words[word]
			else:
				no[word] += 1
storeWordsToFile(yes, no,'tester.txt')

valText = ps.parse()

featWrds = loadfeaturefromfile('tester.txt')
w = numpy.ones(len(x))
#for each tweet, train/update weights
for row in valText:
	#pop V bit from end of row and append it to the target vector
	V = row.pop(-1)
	#target.append(V)
	#convert from words to binary feature vector
	x = list()
	for word in featWrds:
		if word in row:
			x.append(1)
		else:
			x.append(0)
	#bdg will predict, compare, and possibly update
	w = bgd(x,V,w,0.5)
	
#done training!!!#
#now test#
correct = 0
total = 0
valText = ps.parse()
for row in valText:
	V = row.pop(-1)
	if (predict(row,w)-V!=0):
		total+=1
	else:
		correct+=1
		total+=1
print (float(correct)/total)








##SKLEARN'S LOGISTIC REGRESSION##
target = list()
input = ps.parse()
featWrds = loadfeaturefromfile('tester.txt')
x = list()
for tweet in input:
	target.append(tweet.pop(-1));
	featNum = featWrds
	for wrd in featNum:
		if wrd in tweet:
			wrd = 1
		else:
			wrd = 0
	x.append(featNum)
model = LogisticRegression(penalty='l2',dual=False,fit_intercept = False,class_weight = 'auto',solver='newton-cg')
model.fit(x,target)