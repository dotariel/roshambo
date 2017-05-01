# Goals
Now that we have refactored a bit and have some more isolated testing, we can add the new functionality.
- Break down single-class application into classes
- More refactoring, including some naming improvements


#### Considerations
- Introduce new options (spock,lizard)
- Isolate the options into separate classes so they can be individually tested against each other


#### Changes
  - Add `Option` abstract class to describe contract for all Options
  - Add `Option` implementations for `Rock`, `Paper`, `Scissors` and move unit tests
  - Add `OptionFactory` class to construct an `Option` from a String
  - Replace `Program.computerChoice()` with randomized Option from the available options exposed by `OptionFactory`
  - Refactor message strings and test expectations to match format
  - Refactor each iteration into a `Round`
  - Replace `Program.compareToWin()` with `Round` implementation
  - Add `Game` class to keep track of rounds and score

#### Next Steps
  - More pushing to the edges
