Assignment - 3
Shali Saseendran

Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code

ant -buildfile src/build.xml run -Darg0=firstarg -Darg1=SECOND -Darg2=THIRD

ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=0

-----------------------------------------------------------------------

## To create tarball for submission
tar -czvf shali_saseendran_assign_3.tar.gz shali_saseendran_assign_3

## To create untarball for submission

tar -xvzf shali_saseendran_assign_3.tar.gz shali_saseendran_assign_3

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.â€

[Date:04-02-2017] 

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

Cell class implements Subject, ArrayList of Listener Object is used to store the list of Listener per Subject.
Time Complexity: O(n)
Space Complexity: O(n) where n is the number of listeners read from the file.

Apart from this, HashMap is used for the implementation of Cycle detection Algorithm.
DFS alike algorithm is implemented on the HashMap to detect the formation of cycle.

-----------------------------------------------------------------------
Logger.java: 
The debug value used for the following output.
DEBUG_VALUE=4 [Print to stdout every time a constructor is called]
DEBUG_VALUE=3 [Print to stdout every time a Subject calls removeObserver() method]
DEBUG_VALUE=2 [Print to stdout every time a Subject calls registerObserver() method]
DEBUG_VALUE=1 [Print to stdout when a cycle is detected]
DEBUG_VALUE=0 []

-----------------------------------------------------------------------
Provide list of citations (urls, etc.) from where you have taken code
(if any).

