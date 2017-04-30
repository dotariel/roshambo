package org.dotariel.roshambo

import org.junit.*

class RoundTest {

  @Test
  public void should_make_decision() {

    def one = new Paper()
    def two = new Scissors()

    new Round(one,two).decide() == [winner: two]
  }
}