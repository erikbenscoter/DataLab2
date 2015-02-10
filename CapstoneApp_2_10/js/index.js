//recording object
var rec;
var isChrome = !!navigator.webkitGetUserMedia;
var recIndex = 0;
function microphonePrompt()
{
	navigator.getUserMedia ||
		  (navigator.getUserMedia = navigator.mozGetUserMedia ||
		  navigator.webkitGetUserMedia || navigator.msGetUserMedia);
		 
		
		navigator.getUserMedia({audio: true}, onSuccess, onError);
		 
		function onSuccess(stream) {
			window.AudioContext = window.AudioContext || window.webkitAudioContext;
			var audioContext = new AudioContext();
			// Create an AudioNode from the stream.
			var mediaStreamSource = audioContext.createMediaStreamSource(stream);
			// Connect it to the destination to hear yourself (or any other node for processing!)
			mediaStreamSource.connect(audioContext.destination);
			//now we can hear ourselves...but not recording
			//this line is unknown
			var Recorder = isChrome ? window.StereoRecorder : window.MediaStreamRecorder;
			rec = new Recorder(mediaStreamSource);
			rec.record();
		}
		 
		function onError() {
			 document.write('There has been a problem retrieving the streams - did you allow access?');
		}
}

function stopRecording()
{
	rec.stop();
	saveAudio();
}


function saveAudio() {
    rec.exportWAV( doneEncoding );
    // could get mono instead by saying
    // audioRecorder.exportMonoWAV( doneEncoding );
}
//we don't need to make a download available
function doneEncoding( blob ) {
    rec.setupDownload( blob, "myRecording" + ((recIndex<10)?"0":"") + recIndex + ".wav" );
    recIndex++;
}