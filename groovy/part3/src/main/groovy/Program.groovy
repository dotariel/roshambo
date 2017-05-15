class Program {

  static int humanScore = 0
  static int compScore = 0
  static String wantToPlay
  static String playerChoice
  static String computerChoice

  private static final String PLAY            = "Would you like to play Roshambo? (Yes) or (No)"
  private static final String OPTIONS         = "Choose from either (R)ock, (P)aper, or (S)cissors"
  private static final String COMPUTER_WINS   = "The computer wins."
  private static final String PLAYER_WINS     = "You win"
  private static final String TIE             = "The result is a tie."

  public static void main(String[] args) {
    wantToPlay = prompt(PLAY)

    startGame()
  }

  private static void startGame() {
    if (wantToPlay == "yes") {
      playerChoice = prompt(OPTIONS)

      makeSelection()
    }
    else if (wantToPlay == "no") {
      System.out.println("Good Bye")
    }
    else {
      wantToPlay = prompt(PLAY)
      startGame()
    }
  }

  private static void makeSelection() {
    if (playerChoice == "r" || playerChoice == "p" || playerChoice == "s") {
      computerChoice = computerChoice()

      System.out.println(getMatchupMessage(playerChoice, computerChoice))

      int gameResult = compareToWin(playerChoice, computerChoice)

      switch(gameResult) {
        case 0:
          System.out.println(TIE)
          break
        case -1:
          System.out.println(COMPUTER_WINS)
          compScore++;
          break
        case 1:
          System.out.println(PLAYER_WINS)
          humanScore++
          break
      }

      System.out.println(getScoreMessage(humanScore, compScore))

      wantToPlay = prompt(PLAY)
      
      if (wantToPlay == "yes" || wantToPlay == "no") {
        startGame()
      }
      else {
        wantToPlay = prompt(PLAY)
        startGame()
      }
    }
    else {
      playerChoice = prompt(OPTIONS)
      makeSelection()
    }
  }

  protected static String computerChoice() {
    Random rnd = new Random()
    int i = rnd.nextInt(3)

    if (i == 0) return "r"
    if (i == 1) return "p"

    return "s"
  }

  protected static int compareToWin(String humanChoice, String compChoice) {
    if (humanChoice == "r" && compChoice == "p") return -1
    if (humanChoice == "r" && compChoice == "s") return 1
    if (humanChoice == "p" && compChoice == "s") return -1
    if (humanChoice == "p" && compChoice == "r") return 1
    if (humanChoice == "s" && compChoice == "r") return -1
    if (humanChoice == "s" && compChoice == "p") return 1

    return 0
  }

  private static String prompt(String message) {
    return System.console().readLine(formatPrompt(message)).toLowerCase()
  }

  private static String formatPrompt(String message) {
    message + ": "
  }

  private static String getMatchupMessage(String playerChoice, String computerChoice) {
    return "You chose ${playerChoice} and the computer chose ${computerChoice}"    
  }
  
  private static String getScoreMessage(int humanScore, int compScore) {
    return "Score is You = ${humanScore} and the Machine = ${compScore}"
  }
}
