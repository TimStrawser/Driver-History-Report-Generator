The Root Drive History Application

@author: Tim Strawser
@version: 9-5-18
@contact: tstrawser777@gmail.com


@description:
	This is a Java application that processes an input file consisting of customer names (Drivers), and logs 
	of their various trips. A report is then generated and output to a file, detailing each driver's name, 
	how many miles they traveled, and their average driving speed. The application locates the input files inside 
	an input directory and outputs the reports to an output directory. Both directories are located in the project
	folder.

@ToRun: Execute the RootDriveHistoryApp.jar file, from command line in the project directory "java -jar RootDriveHistoryApp.jar"	

@requirements:
	The Java SE Development Kit 8

@design approach:
	I approached this project with a pure object oriented design. By strictly defining all object's states, 
	behaviors, and relationships with one another, it will allow for a more predictable and scalable application.
	I decided to break the project down into 2 domain classes, 1 action class, and 1 demo/driver class which 
	contains the main method. The action class is the DriverHistoryReportGenerator class, which models a report 
	generator object. A report generator object has a list of Driver objects which is modeled by the Driver class. 
	Every driver has attributes such as a name, their total miles driven, and a list of trips taken. Every trip 
	is then modeled by the Trip class. Each trip object has a beginning time, and ending time, and a distance. 
	By breaking down each concept into it's real world properties, good coherence is acheived. 
	
@testing approach:
	I used JUnit test cases to thoroughly test every method. The constructors were tested for every class to determine
	if an object of the class was successully created in memory and if the object's instance variable were properly
	initialized. I think it is important to design every method to return a value. If a method performs an action
	that does not require a return, I often have it return a boolean value to validate success and ensure post conditions
	have been met before the program continues. 

@thanks!: Thank you for taking the time to review this application and I greatly appreciate the opportunity!
