set ProjectPath=%~dp0
cd %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%libAllure\aspectjweaver-1.9.8.jar" -classpath "%ProjectPath%bin;%ProjectPath%libAllure\*;%ProjectPath%libAllure\*;%ProjectPath%extentV5\*;%ProjectPath%libLog4J\*;%ProjectPath%libraries\*;%ProjectPath%libReportNG\*;%ProjectPath%libWebdriverManager\*" org.testng.TestNG "%ProjectPath%bin\runNopCommerceTest.xml"
pause