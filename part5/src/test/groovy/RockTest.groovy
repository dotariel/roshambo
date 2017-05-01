import org.junit.*

class RockTest {

  @Test
  public void should_tie_self() {
    assert new Rock().beats(new Rock()) == 0
  }

  @Test
  public void should_beat_scissors() {
   assert new Rock().beats(new Scissors()) == 1
  }

  @Test
  public void should_not_beat_paper() {
   assert new Rock().beats(new Paper()) == -1
  }
}