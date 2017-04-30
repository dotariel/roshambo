package org.dotariel.roshambo

class Scissors implements Token {
  public int beats(Paper other) { 
    return 1
  }

  public int beats(Rock other) {
    return -1
  }

  public int beats(Token other) { 
    return 0
  } 
}
