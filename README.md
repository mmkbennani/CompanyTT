# CompanyTT

We bill customers at the end of the day, based on an input file which contains all customer journeys.  You need to write a process that from the input file passed in at the command line will generate a summary of the customers trips and total costings.  The input file and output files are defined and an example of each are included, see "CandidateInputExample.txt" and "CandidateOutputExample.txt". To help with understanding the relationship between the input and output we have given time values as small integers, we know you can not travel between stations in one millisecond!  The input file given, when passed as a path to your program as the first command line parameter and the output file path passed as the second command line parameter, must generate the valid JSON output of the given output file (differences in whitespace and ordering are of course permitted - as per the JSON standard).

A customer always "taps" their customer travel card at a barrier/gate which allows us to record their entry and then their exit with a second "tap" as the leave at the destination.  You can assume that:
1) For each customers' journey we always get an entry and exit tap pair in a file. An entry will always be before an exit.
2) The customers first tap represents an entry at the beginning of the journey and the second tap their exit at the end of the journey.
3) A customer may have multiple journeys in a day.

[Input File explanation]
A snippet of input file has the following format:

{
  "taps": [
    {
	  "unixTimestamp": 1572242400,
	  "customerId": 1,
	  "station": "A"
	},
	{
	  "unixTimestamp": 1572244200,
	  "customerId": 1,
	  "station": "D"
	},
	{
	  "unixTimestamp": 1572282000,
	  "customerId": 1,
	  "station": "D"
	},
	{
	  "unixTimestamp": 1572283800,
	  "customerId": 1,
	  "station": "A"
	}
  ]
}

This represents customer who had id 1 taking a journey from station A at the unix time of 1572242400 which represents 2019-10-28T06:00+00:00 and complete their journey by exiting Station D at the unix time of 1572244200 which represents 2019-10-28T06:30+00:00.  The customer then returns in the opposite direction at 2019-10-28T17:30+00:00 (1572282000).


[Output File explanation]
The output file has the following format:
{
  "customerSummaries": [
    {
	  	"customerId": 1,
		"totalCostInCents": 480,
		"trips": [
		 {
		   "stationStart": "A",
		   "stationEnd: "D",
		   "startedJourneyAt": 1572242400,
		   "costInCents": 240,
		   "zoneFrom": 1
		   "zoneTo": 2
		 },
		 {
		   "stationStart": "D",
		   "stationEnd: "A",
		   "startedJourneyAt": 1572282000,
		   "costInCents": 240,
		   "zoneFrom": 2
		   "zoneTo": 1
		 }
		]
	}
  ]
}

For each customer we output:
1) "customerId" - the customers identifier.
2) "totalCostInCents" - the total cost of the days journeys in cents.

For each of the customers' trips we output the following:
1) "stationStart" - the station where the journey started at.
2) "stationEnd" - the station where the journey ended at.
3) "startedJourneyAt" - the time when journey started at.
4) "costInCents" - the cost of the journey in cents.
5) "zoneFrom" the zone which the user was charged from.
6) "zoneTo" - the zone which the user was charged to.


