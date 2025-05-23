<h1 align="center">Practice Data Driven Database SQLite Front End Testing</h1>

![Badge en Desarollo](https://img.shields.io/badge/STATUS-IN%20EVOLUTION-blue)
![Static Badge](https://img.shields.io/badge/Java-red?style=flat-square)
![GitHub followers](https://img.shields.io/github/followers/luisrestrepo6940?style=flat&logo=github)
![Static Badge](https://img.shields.io/badge/Serenity-8A2BE2?style=flat-square)
![Static Badge](https://img.shields.io/badge/SQLite_JDBC-org.xerial-blue?style=flat-square)

### CONTENT

* [Introduction](#introduction).
* [Requirements](#requirements).
* [Recommended](#recommended).
* [Configuration](#configuration).
* [Troubleshooting](#troubleshooting).
* [Maintainers](#maintainers).

### INTRODUCTION

Automated graphical user interface testing project by taking test data from an SQLite database, through a data-driven strategy using SQLite JDBC for accessing and creating SQLite database files in Java (it includes native libraries).

#### The project directory structure
The project has build scripts for Gradle, and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main
  + test
    + java
      + co
        + com
          + certification
            + practiceautomatedtesting
              + runners
              + stepdefinitions
    + resources
      + features                  Feature files
dataDriven.sqlite
```
#### The sample scenario
Both variations of the sample project uses the sample Cucumber scenario. In this scenario, Sergey (who likes to search for stuff) is performing a search on the internet:

*Data replacement pattern*
* *externaldata* = Data replacement pattern.
* *jdbc:sqlite:dataDriven.sqlite* = SQLite URL.
* *test_data* = SQLite table.
* all(*) = SQLite attributes.
* *true* = Boolean flag to choose whether to replace all data or replace a random piece of data.

```Gherkin
Feature: buy products on the website

  Scenario Outline: check product purchase
    Given the user access to the web site
    And user login
      | user   | password   |
      | <user> | <password> |
    When user adds the products to the cart
      | amount   |
      | <amount> |
    Then user can view the products added to the shopping cart
      | amount   |
      | <amount> |
    And user fills out the purchase form
      | firstName   | lastName   | postalCode   |
      | <firstName> | <lastName> | <postalCode> |
    And user can Checkout until confirmation
      | confirmationMessage   |
      | <confirmationMessage> |

    Examples:
      | user          | password     | amount | firstName | lastName | postalCode | confirmationMessage       |
      #@externaldata@jdbc:sqlite:dataDriven.sqlite@test_data@*@true
      | standard_user | secret_sauce | 3      | Steven    | Marley   | 5106       | Thank you for your order! |

```
#### The Screenplay implementation
The sample code in the master branch uses the Screenplay pattern. The Screenplay pattern describes tests in terms of actors and the tasks they perform. Tasks are represented as objects performed by an actor, rather than methods. This makes them more flexible and composable, at the cost of being a bit more wordy. Here is an example:
```java

 @Given("the user access to the web site")
    public void theUserAccessToTheWebSite() {
        theActorInTheSpotlight().attemptsTo(OpenTheApplication.startApplication(EnvironmentSpecificConfiguration.from(
                environmentVariables).getProperty("base.url")));
    }

    @And("user login")
    public void userLogin(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(Login.authenticating(mapList.get(0).get(USER), mapList.get(0).
                get(PASSWORD)));
    }

    @When("user adds the products to the cart")
    public void userAddsTheProductsToTheCart(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(AddProductsToShoppingCart.addProductCart(Integer.parseInt(mapList.get(0).
                get(AMOUNT))));
    }

    @Then("user can view the products added to the shopping cart")
    public void userCanViewTheProductsAddedToTheShoppingCart(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(CheckShoppingCart.checkShopping(mapList.get(0).get(AMOUNT)));
    }

    @And("user fills out the purchase form")
    public void userFillsOutThePurchaseForm(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(CheckOutInformation.information(mapList.get(0).get(FIRST_NAME),
                mapList.get(0).get(LAST_NAME), mapList.get(0).get(POSTAL_CODE)));
    }

    @And("user can Checkout until confirmation")
    public void userCanCheckoutUntilConfirmation(DataTable dataTable) {
        mapList = dataTable.asMaps(String.class, String.class);
        theActorInTheSpotlight().should(seeThat(CheckConfirmationMessage.getMessage(mapList.get(0).
                get(CONFIRMATION_MESSAGE)), Matchers.is(true)));
    }
}
```
Screenplay classes emphasise reusable components and a very readable declarative style, whereas Lean Page Objects and Action Classes opt for a more imperative style.

[![Go Back Badge](https://img.shields.io/badge/Back-gray?style=flat)](#content)

### REQUIREMENTS

* SQLite JDBC » 3.49.1.0 - SQLite JDBC is a library for accessing and creating SQLite database files in Java.
* Serenity-core: 4.2.0.
* Serenity-junit: 4.2.0.
* Serenity-screenplay: 4.2.0.
* Serenity-cucumber: 4.2.0.
* Serenity-ensure: 4.2.0.
* Serenity-screenplay-webdriver: 4.2.0.
  
[![Go Back Badge](https://img.shields.io/badge/Back-gray?style=flat)](#content)

### RECOMMENDED

Use amazon corretto - 11.0.24 and SQLite JDBC » 3.49.1.0 - SQLite JDBC is a library for accessing and creating SQLite database files in Java.

[![Go Back Badge](https://img.shields.io/badge/Back-gray?style=flat)](#content)

### CONFIGURATION

Download or clone the repository and configure the settings and project structure with Amazon Corretto-11...

#### Simplified WebDriver configuration and other Serenity extras
The sample projects both use some Serenity features which make configuring the tests easier. In particular, Serenity uses the `serenity.conf` file in the `src/test/resources` directory to configure test execution options.
###### Webdriver configuration
The WebDriver configuration is managed entirely from this file, as illustrated below:
```java

headless.mode = true

environments {
  chrome {
    webdriver {
      driver = chrome
      autodownload = true
      capabilities {
        browserName = "chrome"
        "goog:chromeOptions" {
          args = ["start-maximized", "incognito"]
        }
      }
    }
  }
  edge {
    webdriver {
      driver = edge
      autodownload = true
      capabilities {
        browserName = "MicrosoftEdge"
        "ms:edgeOptions" {
          args = ["start-maximized", "InPrivate"]
        }
      }
    }
  }
  firefox {
    webdriver {
      driver = firefox
      autodownload = true
      capabilities {
        browserName = "firefox"
        "moz:firefoxOptions" {
          args = ["-Private"]
        }
      }
    }
  }
}

```
Serenity uses WebDriverManager to download the WebDriver binaries automatically before the tests are executed.

#### Executing the tests
To run the sample project, you can either just run the `TestRunnerBuyProducts` test runner class, or run either `gradle test` from the command line.

##### Using test runner

```gradle
:test --tests "co.com.certification.practiceautomatedtesting.runners.practiceautomatedtesting.TestRunnerBuyProducts" -Denvironment=firefox
```

<img width="499" alt="image" src="https://github.com/user-attachments/assets/e28ed94c-3570-4d3e-968b-1c88b7dd0986" />

##### Generating a report with a specific browser
```gradle
gradle clean test --tests co.com.certification.practiceautomatedtesting.runners.practiceautomatedtesting.TestRunnerBuyProducts -Denvironment=firefox aggregate --info

```
By default, the tests will run using Chrome. You can run them in Firefox or edge by overriding the `driver` system property, e.g.

```gradle
gradle clean test -Ddriver=firefox
```
Or
```gradle
gradle clean test -Ddriver=edge
```

[![Go Back Badge](https://img.shields.io/badge/Back-gray?style=flat)](#content)

### TROUBLESHOOTING

Please write or contact the Teams user or email luis.f.restrepo@accenture.com

[![Go Back Badge](https://img.shields.io/badge/Back-gray?style=flat)](#content)

### MAINTAINERS

Luis Fernando Restrepo Agudelo - luis.f.restrepo@accenture.com

[![Go Back Badge](https://img.shields.io/badge/Back-gray?style=flat)](#content)
