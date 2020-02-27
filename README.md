# MoneyManagingApp

How to setup Maven (on IntelliJ):

(1) Go to Run/Run... (or Alt+Shift+F10)

(2) Create 2 new configurations

(3) One of the configurations should have the following in the command line: clean package

(4) The other should have: surefire-report:report-only

clean package compiles the entire program and runs the test. You can find the compiled jar under: /target/MoneyManagingApp-1.0-SNAPSHOT.jar (or something like this im guessing lmao)

To run this, navigate to the /target folder with a console and type: java -jar (name of jar)

surefire-report:report-only will generate a html report under /target/sites/surefire-report.html. You can then open this in a browser to get a (prettier?) version of the xml file that contains the report under /target/surefire-reports/TestMoneyManager.text

If you want to add a library, find the Maven code and just copy and paste it into the pom.xml under the dependencies bit :)
