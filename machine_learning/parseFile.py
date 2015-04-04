import re
#snowball = python verb processing

def parse():
	tweets=[]
	with open("annotated_tweets.txt") as f:
		tweets = f.readlines()


	doubleArray = [];
	count = 0
	#will have some number then the code then the tweet
	for tweet in tweets:
		#get verdict
		tokened = re.split('\ ',tweet)
		tokened.pop(0)
		groundTruth = tokened.pop(0)
		if(groundTruth == 'health' or groundTruth=='sick'):
			tokened.append(1)
			doubleArray.append(tokened)
			print("healthy")
		else:
			if( groundTruth == 'no'):
		 		tokened.append(0)
		 		doubleArray.append(tokened)
		 		print("sick")
		 	else:
		 		#do nothing they are scrap
		 		count = count + 1
		 		print("not applicable")
		 		print count
	return doubleArray
parse()