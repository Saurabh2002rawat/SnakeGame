import javax.swing.JFrame;       // *

public class SnakeGame extends JFrame{
   SnakeGame () {
      super("Snake Game") ;
      add (new Board() );
      pack() ;          // dynamic refreshes on change
      setLocationRelativeTo ( null ) ;       // dialog becomes in center
      setResizable(false);
      setVisible(true) ;
   }
   public static void main(String[] args) {
      new SnakeGame () ;
   }
}