function handleDateInput(idOfTextBox){
	var txtBox = document.getElementById(idOfTextBox);
	var inTxtBox = txtBox.value;
	var length = inTxtBox.length;

	if(length >2){
		if(inTxtBox.charAt(2) != '-'){
			var firstPart = splice(inTxtBox, 0, 1 );
			var secondPart = '-';
			var thirdPart = splice(inTxtBox, 2, inTxtBox.length);
			txtBox.value = firstPart + secondPart + thirdPart;
		}
	}
	if(length>5){
		if(inTxtBox.charAt(5) != '-'){
			var firstPart = splice(inTxtBox, 0, 4 );
			var secondPart = '-';
			var thirdPart = splice(inTxtBox, 5, inTxtBox.length);
			txtBox.value = firstPart + secondPart + thirdPart;
		}
	}
}
function splice(string, startNumber, endNumber){
	var newString = "";
	for(i=startNumber; i<=endNumber; i++){
		newString = newString + string.charAt(i);
	}
	return newString;
}