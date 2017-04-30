package org.dotariel.roshambo

class Paper implements Token {
  public int beats(Rock other) {
    return 1
  }

  public int beats(Scissors other) {
    return -1
  }

  public int beats(Token other) {
    return 0  
  }
}
