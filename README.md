
# Overview
The purpose of this project is to demonstrate a refactoring exercise and introducing unit tests to make the product more manageable and maintanable. 


### Background
This product simulates the popular "rock, paper, scissors" game, also known as [Roshambo](https://en.wikipedia.org/wiki/Rochambeau).

### Exercise
The exercise is to add two additional options to the game: *lizard* and *spock*, as made popular by [The Big Bang Theory](http://bigbangtheory.wikia.com/wiki/Rock_Paper_Scissors_Lizard_Spock).

### Challenges
  - Large single class with large methods
  - No tests
  - Duplicated code
  - Nested `if/else` constructs
  - Mostly side effects

### Steps:
  - Prepare the code base for tests
    - Add basic tests as scaffolding to enable refactoring
    - Refactor to push side effects to the edges (interactions with console)
