var groaPasser;
function switchSubPage(nextSubPage)
{

	document.getElementById(previousSubPage).style.opacity = 0;
	document.getElementById(previousSubPage).style.bottom = "100%";
	document.getElementById(previousSubPage).style.top = "0%";
	document.getElementById(previousSubPage).style.pointerEvents = "none";
	document.getElementById(nextSubPage).style.opacity = 1;
	document.getElementById(nextSubPage).style.bottom = "10%";
	document.getElementById(nextSubPage).style.top = "12.5%";
	document.getElementById(nextSubPage).style.pointerEvents = "auto";

	previousSubPage = nextSubPage;
	
}
function switchPageNavIcons(nextSubPage, btnToAnimate){
	var btn = document.getElementById(btnToAnimate);
	btn.style.animation = "wiggle 1s";
	btn.style.WebkitAnimation = "wiggle 1s";
	switchSubPage(nextSubPage);
	groaPasser = btn;
	setTimeout("getRidOfAnimation()",1000);
	
}

function getRidOfAnimation(){
	groaPasser.style.animation = "";
	groaPasser.style.webkitAnimation = "";

}
function switchSubPageWithDepressingButton(buttonID, nextSubPage)
{ 
	document.getElementById(buttonID).style.boxShadow = "0px 0px 0px #888888";
	timeoutHelperNextSubPage = nextSubPage;
	setTimeout("timeoutHelper()",800);
	
}
timeoutHelperNextSubPage = null;
function timeoutHelper()
{
	switchSubPage(timeoutHelperNextSubPage);
}