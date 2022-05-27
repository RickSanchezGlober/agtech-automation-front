# Agtech Automation Front

## Description

This repository contains the automations scripts for web frontend of AgTech project.

## Requirements

* Install Java JDK 11
* Install Maven
* Create environment variable (JAVA_HOME and MAVEN_HOME)

## Running the scenarios

This will run the build and (after a minute or so) will open the application in the mobile and execute the test:

```shell
 mvn clean test
```

This will run a single story by tag

```shell
 mvn clean test -Dcucumber.filter.tags="@login"
```

This will run a suite based on the tags in the story files:

```shell
 mvn clean test -Dcucumber.filter.tags="@regression"
```

## Viewing the results

* The framework uses the **Allure Report Plugin** to generate the report view for each executed test.
* In a target/ directory, a folder called 'allure-results' has been generated.
* If you open it in any browser you can see the stories that have been run and their running status.
* To run it you must go to path of target/ directory and run the following command:

```shell
  allure generate .\allure-results --output .\allure-report --clean ; allure open --port 5000
```

* Another option is in the root of the project to execute one of the following commands

```shell
mvn allure:report
```

```shell
mvn allure:serve
```

## Structure

The framework follows the *Page Object Model*. The structure of the framework is:

* **PageObjects**, *contains the objects* that will be used in the Page classes, this is used in the *initialization* of the *Page classes* by the
* **pages**, contains *the pages themselves*, these classes define *all the selenium web driver code needed to work within a web page application*, this is the correspondence to reality.
* **runner**: contains the cucumber TestRunner class so far.
* **steps**: contains the *classes that work with the Gherkin language* defined in the steps for the scenarios in the *features file*.
  * **Note:** **Hooks.class** to manage, initialise and close the webDriver. The Hooks.class driver is called in the constructor of **BasePage.class**.
* **utils**: contains the utility or *help classes for some functionality of the project*, e.g. *DataGenerator* and *RestAssuredExtension*.
* **resources**, contains the *resources of the project in general*, e.g. *application properties*, *feature files*, *cumber properties*; in other projects it could also be the webview templates.

## How to create a new page and their Step Definition

1. Define a new Page class that inherit the BasePage class.
2. Define a PageObject type.
3. Define a constructor that pass the driver class to be injected, in this constructor defines PageFactory to create the page using the PageObject.
4. Create the methods that verify, execute something with the *web elements.*
5. Associate it with a *Step def class* to verify *your feature and their scenarios*.
