package org.dotariel.roshambo

import org.junit.*

class ScissorsTest {

  @Test
  public void should_tie_self() {
    assert new Scissors().beats(new Scissors()) == 0
  }

  @Test
  public void should_beat_paper() {
   assert new Scissors().beats(new Paper()) == 1
  }

  @Test
  public void should_not_beat_rock() {
   assert new Scissors().beats(new Rock()) == -1
  }
}