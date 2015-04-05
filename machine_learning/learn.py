import math as m
#total original string, dictionary, sick or not
def learn(origStr, totalDict, groundTruth):

	#load weights (handle if null)
	#grab top 10% of dictionary-->list of words

	
	#sort dictionary as tuples
	totalDict = {'fourth':4, 'second':2, 'third':3, 'first':1}
	sortedValues = []
	sortedValues = list(value for value in sorted(totalDict.values()))

	sortedValues.reverse()
	#get total entries in dictionary
	totalEntriesDictionary = len(totalDict)
	#find top 10%
	tenPercentOfDictionary = int(m.ceil(float(totalEntriesDictionary)*.1))

	topTenPercentKeys = []

	sortedValues = sortedValues[0:tenPercentOfDictionary]
	iterator = 0

	#iterate through and find the top ten % of keys
	for key in totalDict.keys():
		if totalDict[key] in sortedValues:
			topTenPercentKeys.append(key)

	#check to see if the keys are in the original string

	coefficient = []		#comes from the weight that will change
	quantifiedArray = []	#comes from whether it is in the original string or not

	#get 1s and zeros to figure out if the word is in the original string
	for element in originalString:
		if element in topTenPercentKeys:
			quantifiedArray.append(1)
		else:
			quantifiedArray.append(0)
	
	#comapre last used to current topTen

	lastUsed = loadWordsFromFile("LastUsed.txt")	#get dictionary of last top ten
	



	



		
		#sort 
		#save keys
		#for key in dictionary.keys

	#if in original string = 1 if not in original string 0
	#multiply by the weight
	#if greater than 0 sick if less than 0 not sick
	#save weights to file

	#return sickOrNot
#def loadWeight():
#def storeWeight():
learn()