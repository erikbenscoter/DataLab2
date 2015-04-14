#!/usr/bin/python           # This is server.py file
import SpeechAPI as sp
import threading
import thread
import time
import socket               # Import socket module

s = socket.socket()         # Create a socket object
host = '0.0.0.0'			 # Get local machine name
port = 8000                # Reserve a port for your service.
try:
	s.bind((host, port))        # Bind to the port
except:
	"there was a problem connecting to the port"
print "host = " + host + "port = " + str(port)
s.listen(5)                 # Now wait for client connection.
threadCounter = 0
listThread = []
minimalConfidence = 50.00
confident = 0


def saveFile(threadNumber):
	#start receiving
	print "i made it to the thread!!"
	fileName = 'test'+str(threadNumber)+'.wav'
	f = open(str(fileName),'wb')
	streamed = c.recv(1024)
	print "received part"
	while(streamed):
		f.write(streamed)
		print "wrote to file\n"
		streamed = c.recv(1024)
		print "received part"
	f.close()
	print "file closed\n"
	print "beginning transcribing \n"
	(originalString,confidence) = sp.transcribeNow(fileName)
	print "exiting transcribing\n"
	print str(originalString) + "\t" + str(confidence)
	#print "thread "+ str(threadNumber) +" is running"
	#add the full string to the database

	#while loop for confidence change the threshold
	if(confidence >= minimalConfidence):
		print "we are confident enough in the transcription"
		confident = 1
	else:
		confident = 0


	

while True:
	print "at the top again \n"
	c, addr = s.accept()     # Establish connection with client.
	print 'Got connection from', addr
	try:
		t = threading.Thread(target = saveFile, args = (threadCounter, ))
		t.start()
		listThread.append(t)
		print listThread
		
	except:
		print "there was a problem creating the thread"
	threadCounter = threadCounter + 1
	#print "there are " + str(threading.activeCount()) + " strings running"

c.close()                # Close the connection




