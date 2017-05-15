
# Overview
The purpose of this project is to demonstrate a refactoring exercise and introducing unit tests to make the product more manageable and maintanable. 

### Background
This product is a walk-through of a legacy implementation of the popular "rock, paper, scissors" game, also known as [Roshambo](https://en.wikipedia.org/wiki/Rochambeau).

![alt text][logo]

### Exercise
The exercise is to add two additional options to the game: *lizard* and *spock*, as made popular by [The Big Bang Theory](http://bigbangtheory.wikia.com/wiki/Rock_Paper_Scissors_Lizard_Spock).

### Challenges
  - Large single class with large methods
  - No tests
  - Duplicated code
  - Nested `if/else` constructs
  - Mostly side effects

### Steps:
  - Add unit test scaffolding for application
  - Refactor application into isolated components
  - Add new features with tests
