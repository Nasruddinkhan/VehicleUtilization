var multiYears = new Array();
var currentDate = new Date();
var currentYear = currentDate.getFullYear();

// create year array
for(y = 0; y <= 32; y++){
  var forwardYears = currentYear + y;  
  multiYears.push(forwardYears);
}

//var multiYears = [ "2015", "2016", "2017", "2018", "2019", "2020"];
var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];
var htmlOptions = "";
var i;


var currentDate = new Date();

for (m = 0; m < multiYears.length; m++) {
  var multiYear = multiYears[m];
  var startMonth = 1;
  // if it's the current year, only show from this month onwards
  if (multiYear == currentDate.getYear() + 1900) {
      startMonth = currentDate.getMonth() + 1;
  }

  for (i = startMonth ; i <= 12; i++) {
      htmlOptions += '<option value="' + ("0" + i).slice(-2) + "-" + multiYear + '">' + monthNames[i - 1] + " " + multiYear + '</option>';
  }
}

var htmlStart = '<select size="1" id="multiDrop" name="multiDrop">';
var htmlOptionsHeader = '<option value="_">Navigate by month</option>';
var htmlEnd ='</select>';
var html = htmlStart + htmlOptionsHeader + htmlOptions + htmlEnd;

document.body.innerHTML += html;
