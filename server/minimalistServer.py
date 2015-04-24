#!/usr/bin/python           # This is server.py file
import SpeechAPI as sp
import threading
import thread
import time
import socket               # Import socket module

s = socket.socket()         # Create a socket object
host = '0.0.0.0'			 # Get local machine name
port = 7999                # Reserve a port for your service.
try:
	s.bind((host, port))        # Bind to the port
except:
	"there was a problem connecting to the port"
print "host = " + host + "port = " + str(port)
s.listen(5)                 # Now wait for client connection.
threadCounter = 0
listThread = []
minimalConfidence = 70.00



def saveFile(threadNumber, client):
	client.settimeout(5);
	streamed = "didn't receive nothin'"
	try:
		streamed = client.recv(1024)
	except:
		print "ran into exception:"
	print "received part"
	print str(streamed)
	try:
		client.send("Hey, I got it")
		print "sending Hey I got it"
	except:
		print "problem!!!!!!!!"
	client.close()
	print "closed the connection"
	


	

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






