import numpy as np
import parseFile as ps
import sklearn.linear_model as slm
from nltk.stem.lancaster import LancasterStemmer
from nltk.corpus import stopwords


model = slm.LogisticRegression(penalty='l2',dual=False,fit_intercept = False,class_weight = 'auto')


def loadWordsFromFile(filename):
	tempyes = dict()
	tempno = dict()
	with open(filename,'r') as f:
		lines = f.readlines()
		for line in lines:
			word = line.split()
			if len(word) == 3:
				tempyes[word[0]] = int(word[1])
				tempno[word[0]] = int(word[2])
	return (tempyes,tempno)

def storeWordsToFile(yesDict,noDict,filename):
	with open(filename,'w') as f:
		for word in yesDict.keys():
			if(word != " "):
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

#THIS SHOULD ONLY BE RUN ON AN EMPTY TESTER.TEXT FILE
def onlyOnInit():
	st = LancasterStemmer()
	yes = dict()
	no = dict()
	(yes,no) = loadWordsFromFile('tester.txt')
	valText = ps.parse()
	stemmedWordList = []
	#for each tweet, manipulate dictionary (part of training)
	for row in valText:
		#pop V bit from end of row
		V = row.pop(-1)
		filtered_words = [w for w in row if not w in stopwords.words('english')]
		#print "stemmed" + str([w for w in row if w in stopwords.words('english')])
		for word in filtered_words:
			#if the key does not exist in the dictionary, add it with 1 weight
			try:
				prevWord = word
				word = st.stem(str(word))
				word = str(word)
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
			except:
				print "Exception " + str(prevWord)
	storeWordsToFile(yes, no,'tester.txt')

'''
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






'''

##SKLEARN'S LOGISTIC REGRESSION##
def trainData():
	target = list()
	input = ps.parse()
	featWrds = loadFeatureFromFile('tester.txt')
	twtCounter = 0
	x = list()
	for tweet in input:
		twtCounter = twtCounter + 1
		target.append(tweet.pop(-1));
		featNum = []
		counter = 0
		for wrd in featWrds:
			if wrd in tweet:
				featNum.append(1)
			else:
				featNum.append(0)
			counter = counter + 1
		x.append(featNum)
		#print x	
	
	model.fit(x,target)

	givenAnswer = model.predict(x)

	listToSum = abs(givenAnswer - target)
	print sum(givenAnswer)
	print twtCounter

def predict(symptomString):
	featWord = loadFeatureFromFile('tester.txt')
	
	wordList = symptomString.split()
	featNum = []
	for word in featWord:
		foundWord = False
		for inputWord in symptomString.split():
			if word == inputWord:
				foundWord = True
				print word
		if foundWord:
			featNum.append(1)
		else:
			featNum.append(0)

	print featNum
	return model.predict(featNum)
