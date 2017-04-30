# Goals
Now that we have some basic test scaffolding in place, lets start refactoring a bit.

- Refactor single-class application to remove code duplication
- Add unit tests for refactored methods
- There are many side effects to contend with
- All the inputs, outputs to/from the console need to be pushed to the edges, so that we can isolate the functionality we want to test.

#### Changes
We can start by making some simple refactorings just to move some code around.
  - move Strings to constants/methods for better isolation
    ```
      private static final String PLAY            = "Would you like to play Roshambo? (Yes) or (No)"
      private static final String OPTIONS         = "Choose from either (R)ock, (P)aper, or (S)cissors"
      private static final String COMPUTER_WINS   = "The computer wins."
      private static final String PLAYER_WINS     = "You win"
      private static final String TIE             = "The result is a tie."
      private static final String BEAT_MACHINE    = "Keep beating the machine"
      private static final String YOU_ARE_LOSING  = "You are getting your ass handed to you!!!"
      private static final String SCORE_TIED      = "Can someone go ahead already, geez!"
    ```

  - add `prompt()` method to clean up some duplicated code.
    ```
      private static String prompt(String message) {
        return System.console().readLine(formatPrompt(message)).toLowerCase()
      }
    ```

  - add unit tests for Program
    - **should_read_console_input** - Mock `System.console()` to prove that we can return the input in lower-case.

      ```
      @Test
      public void should_read_console_input() {
        System.metaClass.'static'.console = { -> [ readLine: { String s -> "user input" } ] }
        assert Program.prompt("Would you like to play?") == 'user input'
      }
      ```
    - **should_build_score_message**   - Isolate the score message

      ```
      @Test
      public void should_build_score_message() {
        assert Program.getScoreMessage(2,3) == "Score is You = 2 and the Machine = 3"
        assert Program.getScoreMessage(0,0) == "Score is You = 0 and the Machine = 0"
      }
      ```
    - **should_format_prompt_message** - Isolate the message prompt from the console interaction

      ```
      @Test
      public void should_format_prompt_message() {
        assert Program.formatPrompt("hello") == "hello: "
      }
      ```

#### Next Steps
Next, we can start to isolate parts of the application for more focused testing and maintenance.
