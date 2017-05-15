# Goals
Now that we have some basic test scaffolding in place, lets start refactoring a bit.

- Refactor single-class application to remove code duplication
- Add unit tests for refactored methods
- There are many side effects to contend with
- All the inputs, outputs to/from the console need to be pushed to the edges, so that we can isolate the functionality we want to test.

#### Changes
We can start by making some simple refactorings just to move some code around.
  - move Strings to constants/methods for better isolation
  - add `prompt()` method to clean up some duplicated code.
  - add unit tests for `Program`
    - **should_read_console_input** - Mock `System.console()` to prove that we can return the input in lower-case.
    - **should_build_score_message**   - Isolate the score message
    - **should_format_prompt_message** - Isolate the message prompt from the console interaction
    - **should_randomize_computer_choice** - Isolate the logic for choosing a random option for the computer player
    - **should_determine_winner** - Isolate the logic to determine the winning option


#### Next Steps
Next, we can start to isolate parts of the application for more focused testing and maintenance.
