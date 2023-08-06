@Main
Feature: As a user I have to see click button and label screen on main screen

  Scenario: As a user when I open the app the label is displayed as a "0"
  When the app is opened
  Then it’s main screen contains a button with text Click me and a label with text 0

  Scenario: As a user when I click the button that displayed "0" should be displayed "1"
  Given the label is "0"
  When clicking the button
  Then the label should change to "1"

  Scenario: As a user when I click the button that displayed "1" should be displayed "2"
    //Given the label is "1"
    When clicking the button
    Then the label should change to "2"

  Scenario: As a user when I click the button that displayed "2" should be displayed "MAX"
    Given the label is "2"
    When clicking the button
    Then the label should change to "MAX"

#    soft assertion yap senaryoya birden fazla senaryo ekle, clikable mi. orada mi gibi
#  utilities altinda common method olustur





