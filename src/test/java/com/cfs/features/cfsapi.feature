Feature: CFS API scenarios

  Scenario: Validate the origin of a word
    Given I find the origin of word "insurance"
    Then I validate the word origin as "[[[[late Middle English (originally as ensurance in the sense ‘ensuring, assurance, a guarantee’): from Old French enseurance, from enseurer (see ensure). insurance (sense 1) dates from the mid 17th century]]]]"



 Scenario: Validate the error code 400
    Given I provide with word "insurance" and grammatical feature "@#$"
    Then I validate the response status as "400"

Scenario: Validate the error code 404
    Given I provide the word "123"
    Then I validate the response status as "404"


  Scenario: Validate the translation of word
    Given I translate word "Test" to language "fr"
    Then I validate the translation text as ""

  Scenario: Validate the error code 400
    Given I provide with translation word "test",language "es" and grammatical feature "@#$"
    Then I validate the response status as "400"

  Scenario: Validate the error code 404
    Given I provide the translation word "123"
    Then I validate the response status as "404"