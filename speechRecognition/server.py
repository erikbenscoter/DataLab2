#!/usr/bin/python           # This is server.py file
import SpeechAPI as sp
import threading
import thread
import time
import socket               # Import socket module
import os

s = socket.socket()         # Create a socket object
host = '0.0.0.0'			 # Get local machine name
port = 8002             # Reserve a port for your service.
try:
	s.bind((host, port))        # Bind to the port
except:
	"there was a problem connecting to the port"
print "host = " + host + "port = " + str(port)
s.listen(5)                 # Now wait for client connection.
threadCounter = 0
listThread = []
minimalConfidence = 0

def convert3gToWav(fileName,newFileName):
	print "Converting "+str(fileName)+" to wav format"
	os.system("ffmpeg -i "+str(fileName)+" -vn -acodec pcm_s16le -ar 44100 -ac 2 "+str(newFileName))


def saveFile(threadNumber, client):
	#local variables
	confidence = 0
	threshold = 1;

	#start receiving
	print "i made it to the thread!!"
	extension = ".3gp"
	newExtension = ".wav"
	fileName = 'test'+str(threadNumber)
	f = open(str(fileName)+str(extension),'wb')
	streamed = client.recv(1024)
	print "received part"
	timeout = False
	while(streamed and timeout == False):
		f.write(streamed)
		print "wrote to file\n"
		client.settimeout(4)
		try:
			streamed = client.recv(1024)
		except:
			timeout = True
		print "received part"
	f.close()
	print "file closed\n"
	convert3gToWav(str(fileName)+str(extension),str(fileName)+str(newExtension))

	print "beginning transcribing \n"
	try:
		(originalString,confidence) = sp.transcribeNow(str(fileName)+str(newExtension),threshold)
		print "exiting transcribing\n"
		answer =  str(originalString) + "\t" + str(confidence)+"\n"
		if(confidence >= minimalConfidence):
			print "we are confident enough in the transcription"
			confident = 1
		else:
			answer =  "we are not very confident in the transcription\n"
			confident = 0
		canSend = 1
		print "about to send..."
		#while canSend:
		client.settimeout(4)
		print "line before sending..."
		try:
			canSend = client.send(str(answer))
			print "sent"
		except:
			print "there was an exception"
	except Exception,e:
		print str(e)
		answer = str(e) + "\n"
		client.settimeout(4)
		print "line before sending..."
		try:
			canSend = client.send(str(answer))
			print "sent"
		except:
			print "there was an exception"

	client.close()


	

while True:
	print "at the top again \n"
	c, addr = s.accept()     # Establish connection with client.
	print 'Got connection from', addr
	try:
		t = threading.Thread(target = saveFile, args = (threadCounter, c, ))
		t.start()
		listThread.append(t)
		print listThread
		
	except:
		print "there was a problem creating the thread"
	threadCounter = threadCounter + 1
	#print "there are " + str(threading.activeCount()) + " strings running"






