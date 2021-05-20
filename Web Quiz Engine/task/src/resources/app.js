var xmlHttp = new XMLHttpRequest();

xmlHttp.open( "GET", "/birthdays", false); // false for synchronous request
xmlHttp.send( null );

var birthdays = JSON.parse(xmlHttp.responseText);

const today = new Date();
const tomorrow = new Date(today);
tomorrow.setDate(tomorrow.getDate() + 1);



// change string format of date into Date format
birthdays = birthdays.map(
    function (item) {
        var result = {}
        var dta = item.date.split('.') // parse by dot into array [d, m, y]

        result.fio = item.fio
        result.date = new Date(dta[2], dta[1] - 1, dta[0])

        return result
    }
)


var todayOutput = getArrayForDate(today)
var tomorrowOutput = getArrayForDate(tomorrow)
var weekOutput = []

var adate = new Date(today)
adate.setDate(adate.getDate() + 1) // tomorrow

for (var i = 1; i < 8; i++) {
    adate.setDate(adate.getDate() + 1)
    res = getArrayForDate(adate);
    if (res.length > 0) weekOutput = weekOutput.concat(res)
}

var monthOutput = []

for (var i = 1; i < 23; i++) {
    adate.setDate(adate.getDate() + 1)
    res = getArrayForDate(adate);
    if (res.length > 0) monthOutput = monthOutput.concat(res)
}

var pastOutput = []
adate = new Date(today)
adate.setDate(adate.getDate() - 8) // week ago

for (var i = 1; i < 8; i++) {
    adate.setDate(adate.getDate() + 1)
    res = getArrayForDate(adate);
    if (res.length > 0) pastOutput = pastOutput.concat(res)
}


// function must take a day (today, tomorrow, period by days) and return array with birthdays
function getArrayForDate(currentDate) {
    var result = []

    birthdays.forEach(
        function (item) {
            var date = item.date
            
            if (currentDate.getDate() == date.getDate() && currentDate.getMonth() == date.getMonth()) {
                var yearsOld = currentDate.getYear() - date.getYear()
                var res = {text : item.fio + " " + item.date.toLocaleDateString() + " (" + yearsOld + ")"}
                result.push(res)
            }
        }
    )

    return result
}


var app = new Vue({
    el: '#app',
    data: {
        today: today.toLocaleDateString(),
        tomorrow: tomorrow.toLocaleDateString(),
        pasts : pastOutput,
        todays: todayOutput,
        tomorrows: tomorrowOutput,
        weeks: weekOutput,
        months: monthOutput
    }
});
