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
The group is in the Use state. The agreed upon practises are being used to do real work. GitHub is being used to track issues to be completed and for assigning work. Discord is being used as the main communication channel for discussing general issues and problems with completing tasks, as well as for updating on current progress in the project. The work is going well, but some improvement in communication is still needed. At this state, everyone has become well accustomed to working with GitHub; branching, creating issues, merging and reviewing pull requests. Coding in Java is working well, with only some minor misstakes and inconsistencies.

## Installation
Clone the repo and install maven/java and set you java version to 11.0 if not already done so. You can check with the `mvn -V` command once you've installed maven.

For Ubuntu:
```
//Install Maven
$ sudo apt install maven 

//Install jdk/jre v.11 if 'mvn -v' doesn't return java version 11
$ sudo apt-get install openjdk-11-jdk openjdk-11-jre

// List current installed paths for java and select version 11
$ sudo update-alternatives --config java

//Pick the path from the previous command and update JAVA_HOME global var
$ export JAVA_HOME=/usr/lib/jvm/java-11-oracle
```

For Mac OSX:
```
//Install Maven
$ brew install maven 

//Install jdk/jre v.11 if 'mvn -v' doesn't return java version 11
$ brew install openjdk@11

//For the system Java wrappers to find this JDK, symlink it with
$ sudo ln -sfn /usr/local/opt/openjdk@11/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-11.jdk

//Update JAVA_HOME global var
$ export JAVA_HOME=`/usr/libexec/java_home -v 11.0`
```

## Build
To build, you need to cd into the my-app folder and run mvn. However the pom.xml settings file for Maven is outside this folder, so it's recommended to use the following command while located in the project root (i.e. DD2480):
```
(cd my-app && mvn --batch-mode --update-snapshots verify)
## Run
To run the project, like above with building, we recommend being in the project root folder and run the following command:
```
(cd my-app/ && java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App)
```

## Statement of Contributions

### Henrik Åkesson
- Made the original CMV file with skeleton code for the first LIC.
- Made the first version of the Parameters file, containing parameters used in the LIC evaluations. This file was later updated and improved by Robin Eggestig.
- Made the LICutils file, containing help methods for making LIC evaluations.
- Made LIC 5 through 8, with corresponding unit tests. Made three tests for each LIC 5 through 8.
- Completed the README file for the project. This included the description of the project, the general structure of the README and the Checklist for Way-of-Working.
- Created the main test files.

### Robin Eggestig

### Anton Sederlin
- Initiated the project file structure through maven
- Implemented LIC conditions 0,1 & 2 and wrote the corresponding tests.
- Implemented automated webb documentation with javadoc. 
- Reviewed several pull requests and assisted with pair programming when needed. 

### Tsz Ho Wat

### Robert Skoglund

- Created FUV file with randomly generated input
- Made LICs 3 - 5 with correspoinding unit tests
- Review and merged changes thoroughly through diverse issues
- Finalized the LIC conditions and testing
- Helped initiate pair-programming and setting up IDE environments