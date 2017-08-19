# terraform-ui
user interface to run terraform (tf) files.

## How to run?
* Checkout the version of terraform-ui you want to run.
* Place the terraform executable (terraform.exe) in resource directory of project (src\main\resources)

#### run below command from root of project to create the artifact
```
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

# Demo
![demo.gif](https://github.com/mohnishbasha/terraform-ui/blob/master/demo/demo.gif "demo")
