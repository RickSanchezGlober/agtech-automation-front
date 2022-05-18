@regression @managePets
Feature: Manage pet API services

  Background:
    Given Retrieve data from api services with path v2/pet from addPet.txt and save variables bellow
      | PetId | id |

  @addPets
  Scenario: Add Pet
    Then The service responds with status 200

  @findPets
  Scenario: Find Pet by id
    When Find pet by id PetId in the service whith path v2/pet/
    Then The service responds with status 200

  @updatePets
  Scenario: Update an existing pet
    When Update an existing pet with path v2/pet from updateExistingPet.txt
    Then The service responds with status 200
