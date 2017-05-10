import org.junit.*

class SpockTest {

  @Test
  public void should_return_code() {
    assert new Spock().getCode() == 'v'
  }

  @Test
  public void should_tie_self() {
    assert new Spock().beats(new Spock()) == 0
  }

  @Test
  public void should_beat_scissors() {
   assert new Spock().beats(new Scissors()) == 1
  }

  @Test
  public void should_not_beat_paper() {
   assert new Spock().beats(new Paper()) == -1
  }

  @Test
  public void should_beat_rock() {
   assert new Spock().beats(new Rock()) == 1
  }

  // @Test
  // public void should_not_beat_lizard() {
  //  assert new Spock().beats(new Lizard()) == 1
  // }

}