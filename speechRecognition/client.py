#!/usr/bin/python           # This is client.py file

import socket               # Import socket module
import time
def openFile():
	
	f = open("input.wav","rb")
	return f
	
def readFromFile(f):
	l = f.read(1024)
	if(l==True):
		print "read 1024 bytes from file \n"
	return l

s = socket.socket()         # Create a socket object
host = 'localhost'    # Get local machine name
port = 8000                # Reserve a port for your service.

print "host = " + host +" port = " + str(port)

#file setup
f = openFile()

if(not f):
	print "there was an error opening the file \n"
else:
	print "successfully opened file \n"

#place the connection
s.connect((host, port))
l = readFromFile(f)
while(l):
	s.send(l)
	print "sent part \n"
	l=readFromFile(f)
f.close()
print "closed file\n"

s.settimeout(2*60)
print "line before receiving..."
concatonatedString = ""
try:
	returnedString = s.recv(1024)
	
except:
	print "ran across exception"

print "line after receiving..."
concatonatedString = "" + returnedString
continueChecking = True
print concatonatedString
s.close()                    # Close the socket when done