import org.junit.*
import groovy.mock.interceptor.*

class RoundTest {
  
  @Test
  public void should_determine_winner() {
    def paper = new Paper()
    def scissors = new Scissors()
    def rock = new Rock()
    
    assert new Round(paper,scissors).winner == scissors
    assert new Round(rock,paper).winner == paper
    assert new Round(rock,scissors).winner == rock
  }

  @Test
  public void should_determine_tie() {
    assert new Round(new Rock(), new Rock()).winner == null
  }

  @Test
  public void should_produce_summary() {
    assert new Round(new Rock("fred"), new Rock("barney")).summary == 'The result is a tie.'
    assert new Round(new Rock("fred"), new Paper("barney")).summary == 'barney wins.'
    assert new Round(new Rock("fred"), new Scissors("barney")).summary == 'fred wins.'
  }
}
