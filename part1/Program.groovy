class Legacy {

  static int humanScore = 0
  static int compScore = 0
  static String wantToPlay
  static String playersChoice
  static String computerPick

  public static void main(String[] args) {
    wantToPlay = System.console().readLine("Would you like to play RoShamBo? Enter Yes or No: ").toLowerCase()

    startGame()
  }

  private static void startGame() {
    if (wantToPlay == "yes") {
      playersChoice = System.console().readLine("Make a selection: r for rock, p for paper, s for scissors: ").toLowerCase()

      makeSelection()
    }
    else if (wantToPlay == "no") {
      System.out.println("Good Bye")
    }
    else {
      wantToPlay = System.console().readLine("Please Enter Yes or No: ").toLowerCase()
      startGame()
    }
  }

  private static void makeSelection() {
    if (playersChoice == "r" || playersChoice == "p" || playersChoice == "s") {
      computerPick = computerChoice()

      System.out.println("You chose ${playersChoice} and the computer chose ${computerPick}")

      int gameResult = compareToWin(playersChoice, computerPick)

      switch(gameResult) {
        case 0:
          System.out.println("The result is a tie.")
          break
        case -1:
          System.out.println("The computer wins")
          compScore++;
          break
        case 1:
          System.out.println("You win")
          humanScore++
          break
      }

      System.out.println("Score is You = ${humanScore} and the Machine = ${compScore}")

      wantToPlay = System.console().readLine("Would you like to play again? ").toLowerCase()
      
      if (wantToPlay == "yes" || wantToPlay == "no") {
        startGame()
      }
      else {
        wantToPlay = System.console().readLine("Please enter Yes or No").toLowerCase()
        startGame()
      }
    }
    else {
      playersChoice = System.console().readLine("Your only choices are r,p, and s. Choose again: ").toLowerCase()
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

  private static int compareToWin(String humanChoice, String compChoice) {
    if (humanChoice == "r" && compChoice == "p") return -1
    if (humanChoice == "r" && compChoice == "s") return 1
    if (humanChoice == "p" && compChoice == "s") return -1
    if (humanChoice == "p" && compChoice == "r") return 1
    if (humanChoice == "s" && compChoice == "r") return -1
    if (humanChoice == "s" && compChoice == "p") return 1

    return 0
  }  
}
