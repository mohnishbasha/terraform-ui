# terraform-ui
user interface to run terraform (tf) files.

## How to run from source ?

* Download terraform binary from terraform.io
* Place the terraform binary|executable (terraform|terraform.exe) path in global.properties (src/main/resources)
* Provide the location of directory where files will be placed in global.properties (src/main/resources)
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

## How to run as docker container ?
* TBD [working on this, will be out soon .. ]

# System requirement
* Java
* Maven
* Mysql

# Questions
* contact: mohinish_ce [@] yahoo dot com

# Demo
![demo.gif](https://github.com/mohnishbasha/terraform-ui/blob/master/demo/demo.gif "demo")
