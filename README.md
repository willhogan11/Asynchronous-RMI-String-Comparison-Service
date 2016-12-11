# RMI String Comparison Application

- **Student Name:** Will Hogan
- **Student Number:** G00318460
- **College Name:** GMIT
- **Course:** Software Development
- **Module:** Distributed Systems
- **Lecturer:** John Healy
- **Current College Year:** 4th Year 
- **Project Title:** RMI String Comparison Application

---

# Application Overview

I have created a Web application for my college module _Distributed Systems_ that compares two strings and returns the 
edit distance for the selected Algorithm. 

The user is asked to enter two strings (they can be the same or different). The user must also select a String Comparison algorithm
from the drop down list. When the user clicks the compare button, they are directed to another page that informs the user that 
their request is pending. The page is set to refresh every 10 seconds and if the request has been processed, the result will be updated 
and displayed on the page.
In the background, a Remote Method Invocation (RMI) is made and the request is processed by the String 
comparison classes that are situated remotely. (note: They have been bundled together for the sake of project completeness, see **Execution Instructions** for independent deployment)

---

# Design Patterns
Although not a prerequisite for this module, i opted to incorporate flavours of various Design Patterns in my application, while still holding true
to the fundamentals of Object Oriented design principles. 

I used the Factory pattern for creating a Singleton instance of a users request. I felt that this would be beneficial as there must only be one request at any given time. 
I used the Facade / Structural Design pattern to add a further layer of encapsulation between the user and the actual string comparison itself. 
Please read the comments in the java file _StringAlgoFacade_ for a detailed breakdown of how this was accomplished or view the UML diagram at the bottom of this readme for a visual representation. 

---

# GitHub Project Management
I extensively used github services to manage my Commits, Branches, Issues and Milestones (All issues and milestones are closed now, but they can still be viewed). 

---

# Execution Instructions
To run this application, you'll need a Web Application Archive (War) and the String-Service java application archive (Jar). Both of which are located within the _**Deployment**_ folder in this repository. You'll also need to download and setup the latest version Apache Tomcat as your webserver. Here's the link [http://tomcat.apache.org/](http://tomcat.apache.org/). 

Once you have setup tomcat, you'll need to put the _**comparator.war**_ file in the webapps folder, situated within the apache tomcat directory. It should unbundle the war file for you, but if you are having issues with this, simply rename the file extention to .zip,  you'll then be able to extract everything you need. using the command line, navigate to the bin directory and type ```startup.bat```, which starts tomcat. 

After that, you'll need to start the RMI server. 
Run the jar file by navigating to the directory you have downloaded the _**string-service.jar**_ file to and type the following command into the command line. ```java –cp ./string-service.jar ie.gmit.sw.StringServiceServant```. This will start the RMI server.

**Note the port number i used for the RMI part of the application is 1099, if you have something else running on that port, you'll encounter issues.**

At this point, go to your browser and type the following ```localhost/comparator```. The port number will depend on what you have setup yourself. i used port 8080. 

---

# UML Class Diagram
![title](https://github.com/willhogan11/Comparator/blob/master/UML.png)
