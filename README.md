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

# Execution Instructions

---

# UML Class Diagram
![title](https://github.com/willhogan11/Comparator/blob/master/UML.png)
