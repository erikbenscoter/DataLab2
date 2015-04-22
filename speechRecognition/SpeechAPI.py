import speech_recognition as sr
def transcribeNow(fileName, p_threshold):
	r = sr.Recognizer()
	r.energy_threshold = 10

	r.dynamic_energy_adjustment_ratio = 1.1
	with sr.WavFile(str(fileName)) as source:              # use "test.wav" as the audio source
	    
	    audio = r.record(source)                        # extract audio data from the file
	try:
	    list = r.recognize(audio,True)                  # generate a list of possible transcriptions
	    print("Possible transcriptions with threshold of" +str(p_threshold)+" :")
	    for prediction in list:
	        print(" " + prediction["text"] + " (" + str(prediction["confidence"]*100) + "%)")
	    return (list[0]["text"],list[0]["confidence"]*100)
	except Exception,e :                                 # speech is unintelligible
	    print e

