# terraform-ui
user interface to run terraform (tf) files.

## How to run?

* Place the terraform executable (terraform.exe) in resource directory of project (src\main\resources)
#### Clone the version of terraform-ui you want to run.
```
git clone https://github.com/mohnishbasha/terraform-ui.git
```
#### change directory to the root of the project
#### run below command from root of project to create the artifact
```
cd terraform-ui/
mvn package
```
#### execute below to start the application
```
java -jar target/terraform-ui-X.X.jar
```
#### access application
```
http://localhost:8090/
```
# System requirement
* Java
* Maven
* Mysql

# Demo
![demo.gif](https://github.com/mohnishbasha/terraform-ui/blob/master/demo/demo.gif "demo")
