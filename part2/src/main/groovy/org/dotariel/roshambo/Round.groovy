package org.dotariel.roshambo

class Round {
  Token one
  Token two

  Map decision = [:]

  public Round(Token one, Token two) {
    this.one = one
    this.two = two
  }
  
  public decide() {
    [winner: two]
  }
}
