import parseFile as ps
words = dict()

def loadWordsFromFile(filename):
	tempwords = dict()
	with open(filename,'r') as f:
		line = f.readlines()
		tempwords[line[0]] = line[-1]
	return tempwords

def storeWordsToFile(dictionary,filename):
	with open(filename,'w') as f:
		for word in dictionary.keys():
			#print word
			#f.write(str(word))
			f.write(str.strip(word))
			f.write(str(' '))
			#f.write(str('abc'))
			f.write(str(dictionary[word]))
			f.write("\n")
			
words = loadWordsFromFile('tester.txt')
valText = ps.parse()
#for each tweet
for row in valText:
	#pop V bit from end of row
	V = row.pop(-1)
	for word in row:
		#if the key does not exist in the dictionary, add it with -1/1 weight
		if words.get(word, 'none') == 'none':
			if(V==1):
				words[word] = 1
			else:
				words[word] = -1
		else:
			if(V==1):
				words[word] += 1
				#print words[word]
			else:
				words[word] -= 1
storeWordsToFile(words,'tester.txt')
#learn(row,words,V)