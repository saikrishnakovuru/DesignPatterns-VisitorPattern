# cs542-fall-22-assign4-team-kbasam1-skovuru1

## Name: saikrishna kovuru

## Name: kishorekumar Basam

## slack days used: 0

## slack days remaining: 3

---

---

Following are the commands and the instructions to run ANT on your project.

#### Note: build.xml is present in VisitorPattern/src folder.

---

## Instruction to clean:

####Command: ant -buildfile ant -buildfile VisitorPattern/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

---

## Instruction to compile:

####Command: ant -buildfile VisitorPattern/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

---

## Instruction to run:

####Command: ant -buildfile VisitorPattern/src/build.xml run -Darg0=Input.txt -Darg1=US_UK_words.txt -Darg2=5 -Darg3=k_most_freq_words.txt -Darg4=Converted_input.txt -Darg5=errorLog.txt

-Darg2 displays the top most k frequemcy values.

## you can run it in the following manner:

ant -buildfile VisitorPattern/src/build.xml run -Darg0=Input.txt -Darg1=US_UK_words.txt -Darg2=5 -Darg3=k_most_freq_words.txt -Darg4=Converted_input.txt -Darg5=errorLog.txt

Note: Arguments accept the absolute path of the files.

---

## Description:

Initially in the driver class we will be having a condition to check no. of parameters were being passed, if the no. of commands passes are more than or less than 6 then an error will be thrown.

we have the boundary conditions initially that checks if the given input files are present or not, if any of teh input filesa are absent an error will be dispyaled so that the input files should be present.
We also have to condition for the file input so that the input files can neverbe also empty

We solved this assignment using 3 patterns
Visitor
Strategy
Iterator

Visitor:
SpellCheckVisitor amd TopKMostFreqVisitor implements the visitor interface so that every time a new thing arises the visitors will be visited and they will get into notice

Strategy:
Sicce we have to implement different strategies with same input we go for strategy.
Here in our case we used strategy because we have the same input but the different implementations for case sensitive and non case sensitive.
When te words are sensitive we are supposed to leave the word as it is and when non case sensitive we should change the word based on the input preferences.

Iterator:
We used iterator to check if there is any of the input available and alo to check it it has the next variable/data to process.
Instead of using generic this we implement our own.

ProcessUSToUKFile ---
This file converts and put the sensitive and non case sensitive values into the map respectively, this is a type of file processor.

MyLogger--
MyLogger is used to print the vlaues which is a very good example of singleton pattern.

FLOW OF THE PROGRAMME:
File data will be sent to the file processor and processed data will be set to list of words.
Top k most freq words will be displayed according to the value entred in the terminal.
[java] K most frequent words
[java] the : 63
[java] and : 27
[java] of : 25
[java] to : 20
[java] a : 17
here is how the values are displayed based on the values given in console.

we run the analysis so that the values which are changed based on US_Uk words will be changed and displayed as below

Input Text after Case Sensitive Check:
The hoomans earliest noted adventures of Humans

Input Text after non case InSensitive Check:
The hoomans earliest noted adventures of hoomans

in the above way we ignore when it is non case sensitive values.

EXCEPTIONS HANDLED:
IOException
UnsupportedOperationException
NullPointerException
FileNotFoundException
NumberFormatException

Done!.

### Academic Honesty statement:

---

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.""

Date: 11/Dec/2022
