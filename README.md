
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
  - Prepare the code base for tests
    - Add basic tests as scaffolding to enable refactoring
    - Refactor to push side effects to the edges (interactions with console)


[logo]: https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Pierre_ciseaux_feuille_l%C3%A9zard_spock.svg/768px-Pierre_ciseaux_feuille_l%C3%A9zard_spock.svg.png