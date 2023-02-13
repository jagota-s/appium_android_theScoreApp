# Appium Java Project with Page Object Model Implementation

This repository contains Appium automation scripts for TheScore Android app. The scripts are written in Java and use the Appium library to interact with the app.

# Prerequisites
- Java 8 or higher
- Maven
- Appium
- Android SDK
- Node
- An emulator or a real device for testing

# Setting up the System
- Install Java 8 or higher. You can download the latest version of Java from the Oracle website

- Install Maven. You can download Maven from the Apache Maven website

- Install Appium. You can download Appium from the Appium website.

- Install Android SDK. You can download the Android SDK from the Android website.

- Set up an emulator. You can follow the instructions on the Android website to create a virtual device.


# Getting Started
Clone the repository to your local machine using the below URL and import the project into your preferred Java IDE.

https://github.com/jagota-s/appium_android_theScoreApp.git

Before running the tests, make sure that you have installed all the required components and configured your environment correctly.

# The folder structure of this repository is as follows:

- src/test/resources: This folder contains the resources needed for the tests, such as the .apk files under the test.

- src/test/java: This folder contains the test classes that define the test cases for the automation scripts.

- testData/ : This folder contains the .json file for the test which can be used to pass desired test data values to the test.

- data.properties: This file contains the values for appium server configuration.

- reports: Extent reports generated will be placed here with their screenshots (in case of failure)
  * [Sample Pass report](https://github.com/jagota-s/appium_android_theScoreApp/blob/main/reports/index_Pass_Sample.html)
  * [Sample Fail report](https://github.com/jagota-s/appium_android_theScoreApp/blob/main/reports/index_Fail.html)

The page objects in this repository are classes that represent the various pages or screens in the TheScore Android app. They contain methods that perform actions on the app, such as clicking a button or entering text into a field. The test classes use the methods from the page objects to interact with the app and perform the tests.

# Running the Scripts in the local environment 

- Open the terminal and navigate to the root directory of the project.
- Run the following command to start the Appium server:
```
appium
```
- In another terminal window, navigate to the root directory of the project and run the following commands to execute the automation scripts:
```
mvn install
```
- To run in the emulator, start the virtual device in Android Studio and retrieve the following parameters:
- Ip address, Port number of the server, and device name of the virtual device initialized in the previous step.
- Run the below maven command with parameters identified in previous steps:
```
mvn clean install -PRegression -DipAddress= -Dport= -DdeviceName= 
```
- Test report will be generated in the /reports folder

# Running the Scripts in the Saucelabs
 ```
mvn clean install -PRegression -DrunTimeEnv=cloud
```
- Test report will be generated in the /reports folder



## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.
