import org.junit.*

class LizardTest {

  @Test
  public void should_return_code() {
    assert new Lizard().getCode() == 'l'
  }

  @Test
  public void should_tie_self() {
    assert new Lizard().beats(new Lizard()) == 0
  }

  @Test
  public void should_not_beat_scissors() {
   assert new Lizard().beats(new Scissors()) == -1
  }

  @Test
  public void should_beat_paper() {
   assert new Lizard().beats(new Paper()) == 1
  }

  @Test
  public void should_beat_spock() {
   assert new Lizard().beats(new Spock()) == 1
  }

  @Test
  public void should_not_beat_rock() {
   assert new Lizard().beats(new Rock()) == -1
  }

}