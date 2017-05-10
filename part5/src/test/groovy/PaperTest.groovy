import org.junit.*

class PaperTest {

  @Test
  public void should_return_code() {
    assert new Paper().getCode() == 'p'
  }

  @Test
  public void should_tie_self() {
    assert new Paper().beats(new Paper()) == 0
  }

  @Test
  public void should_beat_rock() {
   assert new Paper().beats(new Rock()) == 1
  }

  @Test
  public void should_not_beat_scissors() {
   assert new Paper().beats(new Scissors()) == -1
  }
}