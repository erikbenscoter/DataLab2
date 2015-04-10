#!/usr/bin/python           # This is server.py file
import SpeechAPI as sp
import socket               # Import socket module

s = socket.socket()         # Create a socket object
host = '0.0.0.0'			 # Get local machine name
port = 8000                # Reserve a port for your service.
s.bind((host, port))        # Bind to the port
print "host = " + host + "port = " + str(port)
s.listen(5)                 # Now wait for client connection.

f = open('test.wav','wb')

while True:
	print "at the top again \n"
	c, addr = s.accept()     # Establish connection with client.
	print 'Got connection from', addr
   
	#start receiving
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
	sp.transcribeNow()
	print "exiting transcribing\n"

c.close()                # Close the connection
