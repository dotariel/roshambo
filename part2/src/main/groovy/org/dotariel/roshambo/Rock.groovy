package org.dotariel.roshambo

class Rock implements Token {
  public int beats(Paper other) { 
    return -1
  }

  public int beats(Scissors other) {
    return 1
  }

  public int beats(Token other) { 
    return 0
  } 
}
