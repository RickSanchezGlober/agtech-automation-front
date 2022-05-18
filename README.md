# Framework YY

## Requirements:

[comment]: <> (* Install appium server &#40;For Linux you can use the image **Appium-linux-1.18.3.AppImage** from https://github.com/appium/appium-desktop/releases or install via npm **"npm install -g appium"**&#41;)

[comment]: <> (* Install adb manager &#40;to emulate devices&#41;, you can have it with android studio)

[comment]: <> (    * Command for linux to see the list names of devices **emulator -list-avds**)

[comment]: <> (    * Command for linux to start the device **emulator -avd device_name**)
* Install Java 11
* Install Maven
* Create environment variable (JAVA_HOME and MAVEN_HOME)
* Install Allure (To install Allure, download and install Scoop) [download here](https://docs.qameta.io/allure/#_installing_a_commandline)
* If you do not have administrator permission on your computer to install Allure [see more](https://github.com/ScoopInstaller/Install#for-admin)

[comment]: <> (    * **You must point to this path so you can work with the devices:**  )

[comment]: <> (        * export ANDROID_SDK_HOME=/user.dir/Android/Sdk)

[comment]: <> (        * export PATH=${PATH}:$ANDROID_SDK_HOME/emulator:$ANDROID_SDK_HOME/tools:$PATH**)
[comment]: <> (        * export PATH=${PATH}:$ANDROID_SDK_HOME/emulator:$ANDROID_SDK_HOME/tools:$PATH**)


## Running the stories
Run the project runner, located in the runner directory.

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

# Framework YY description

## Structure

The framework follows the *Page Object Model*. The structure of the framework is:

- **PageObjects**, *contains the objects* that will be used in the Page classes, this is used in the *initialization* of the *Page classes* by the
- **pages**, contains *the pages themselves*, these classes define *all the selenium web driver code needed to work within a web page application*, this is the correspondence to reality.
- **runner**: contains the cucumber TestRunner class so far.
- **steps**: contains the *classes that work with the Gherkin language* defined in the steps for the scenarios in the *features file*...
    - **Note:** **Hooks.class** to manage, initialise and close the webDriver. The Hooks.class driver is called in the constructor of **BasePage.class**.
- **utils**: contains the utility or *help classes for some functionality of the project*, e.g. *DataGenerator* and *RestAssuredExtension*.
- **resources**, contains the *resources of the project in general*, e.g. *application properties*, *feature files*, *cumber properties*; in other projects it could also be the webview templates.

## How to create a new page and their Step Def

1. Define a new Page class that inherit the BasePage class.
2. Define a PageObject type.
3. Define a constructor that pass the driver class to be injected, in this constructor defines PageFactory to create the page using the PageObject.
4. Create the methods that verify, execute something with the *web elements.*
5. Associate it with a *Step def class* to verify *your feature and their scenarios*.

##To Do
1. Configure the cucumber option to run the tests via the console.
2. Configure the capabilities to manage the browser inside **application.properties**
3. Configure the capabilities to manage Android WebDriver inside **application.properties**
4. Create a config directory to have:
   * **AndroidWebDriverConfigProperties.class**,
   * **RestAssuredConfigProperties.class**
   * **WebDriverConfigProperties.class**, 

to initialise and consume from **application.properties**
5. Migrating to **TestNG** and using the **Cucumber SprintBoot dependency**
6. Add in the **Hooks.class** the **method takeScreenshot** for the report.