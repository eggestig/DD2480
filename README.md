# DECIDE


## 1. Project Description

The program is part of an anti-ballistic missile system.
The program utilizes a DECIDE() function to determine whether an interceptor should be launched based upon input radar tracking information. This radar tracking information is available at the instant the function is called.

DECIDE() determines which combination of the several possible Launch Interceptor Condi- tions (LIC’s) are relevant to the immediate situation. The interceptor launch button is normally considered locked; only if all relevant combinations of launch conditions are met will the launch- unlock signal be issued.

DECIDE() determines whether each of fifteen LIC’s is true for an input set of up to 100 planar data points representing radar echoes. The fifteen elements of a Conditions Met Vector (CMV) will be assigned boolean values true or false; each element of the CMV corresponds to one LIC’s condition.

The input Logical Connector Matrix (LCM), defines which individual LIC’s must be consid- ered jointly in some way. The LCM is a 15x15 symmetric matrix with elements valued ANDD, ORR, or NOTUSED. The combination of LCM and CMV is stored in the Preliminary Unlocking Matrix (PUM), a 15x15 symmetric matrix.
Another input, the Preliminary Unlocking Vector (PUV) represents which LIC actually matters in this particular launch determination. Each element of the UV indicates how to combine the PUM values to form the corresponding element of the Final Unlocking Vector (FUV), a 15-element vector. If, and only if, all the values in the FUV are true, should the launch-unlock signal be generated.

### 1.1 Input Variables
The values of the following global variables are available for the function:

 - NUMPOINTS The number of planar data points.
- POINTS Array containing the coordinates of data points.
- PARAMETERS Struct holding parameters for LIC’s (see below).
- LCM Logical Connector Matrix.
- PUV Preliminary Unlocking Vector.

### 1.2 Output Variables
The ouput is:
- LAUNCH Final launch / no launch decision encoded as ”YES”, ”NO” on the standard output.

In addition, the following intermediate results are 
computed.
- CMV Conditions Met Vector.
- PUM Preliminary Unlocking Matrix.
- FUV Final Unlocking Vector.

## Checklist for Way-of-Working

Principles Established state. The principles and contraints included; having planed ahead to the deadline for the project to have been completed and running well, and to meet as a group IRL at least one day before the deadline and finish any remaining issues. It was agreed on in the group that descisions on the project were to be discussed in Discord as our main communications channel. The different skill-sets of the group members had been identified to better divide the work load between the group members in the future. It was agreed upon that the work will be done in everyones own environment unless someone needs help with completing a task, in which case pair-programming will be used to solve the issue.

Established state. The work had been divided as to evenly distribute the work load, in accordance with the specific skill-sets of the group members. The project instructions had been discussed and understood between the group members to create a plan of how to divide the tasks and to complete in time for the deadline. GitHub had been choosen to keep track of tasks, using GitHub issues. GitHub issues was to be used for assigning tasks for group members to complete the different parts of the project, add features, improve existing features and fix bugs in the program. Coding conventions had been agreed upon, specifically for committing code as to always commit with a clear and comprehensive description. The grading criteria was discussed in the group to understand the criterias for completing the project. It was agreed upon that two people not having done the issue will have to review it before it is merged with the main branch on the git repository.

Use state & In Place. The agreed upon practises are being used to do real work. GitHub is being used to track issues to be completed and for assigning work. Discord is being used as the main communication channel for discussing general issues and problems with completing tasks. Discord was used for sending messages in the chat as well as for live meeting calls. 

Working well. The work is going well, but some missconceptions and some lack in communications has led us to restructure some of the work load and led to some set-backs. The issue is discussed in the group how to solve it before deadline. The group met IRL to re-structure and complete the project. GitHub issues is working well to keep track of issues and for assignment. How easy it is to work with GitHub varies in the group, but it generally works very well. Group members more accustomed to some of the git actions help others in the group with working with github, eg. branching, commits and merges. The group members continiously improved their skills for both coding and working with GitHub.

Retired. The project is finished and working well. While it became a stressful situation in the end, with some issues in planing and some last-minuite re-structuring, the group learned a lot about better planning and structuring for the future. The communication has become better in the group and the way-of-working has become clear and effortless. For the future, it will be discussed more clear and in detail what the work load is for the project and how to divide it within the group. More regular meetings will be held to better keep track progress and more quickly pick up on any issues.  

## Statement of Contributions

### Henrik Åkesson
- Made the original CMV file with skeleton code for the first LIC.
- Made the first version of the Parameters file, containing parameters used in the LIC evaluations. This file was later updated and improved by Robin Eggestig.
- Made the LICutils file, containing help methods for making LIC evaluations.
- Made LIC 5 through 8, with corresponding unit tests. Made three tests for each LIC 5 through 8.
- Completed the README file for the project. This included the description of the project, the general structure of the README and the Checklist for Way-of-Working.

### Robin Eggestig

### Anton Sederlin

### Tsz Ho Wat

### Robert Skoglund