import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {
   private int dots ;
   private final int ALL_DOTS = 900 ;
   private final int DOT_SIZE = 10 ;
   private final int RANDOM_POSITION = 29 ;

   private Image apple, dot, head ;
   private final int[] x = new int [ALL_DOTS] ;
   private final int[] y = new int [ALL_DOTS] ;

   private int apple_x ;
   private int apple_y ;
   private Timer timer ;

   private boolean left = false, right = true , up = false, down = false ;
   private boolean inGame = true ;
   
   Board() {
      addKeyListener( new TAdapter() ) ;
      setBackground(Color.BLACK);
      setPreferredSize ( new Dimension( 300, 300)) ;
      setFocusable( true ) ;
      loadImages () ;
      initGame () ;
   }

   public void loadImages () {
      ImageIcon i1 = new ImageIcon ( ClassLoader.getSystemResource ( "icons/apple.png")) ;
      ImageIcon i2 = new ImageIcon ( ClassLoader.getSystemResource ( "icons/dot.png")) ;
      ImageIcon i3 = new ImageIcon ( ClassLoader.getSystemResource ( "icons/head.png")) ;
      apple = i1.getImage() ;
      dot = i2.getImage() ;
      head = i3.getImage() ;
   }

   public void initGame () {
      dots = 3 ;        //

      for ( int i = 0 ; i < dots ; i ++ ) {
         y [i] = 50 ;
         x [i] = 50 - i * DOT_SIZE ;
      }

      locateApple () ;
      timer = new Timer(140, this) ;
      timer.start () ;
   }

   public void locateApple () {
      int r = (int)(Math.random() * RANDOM_POSITION );
      apple_x = r * DOT_SIZE ;
      r = (int)(Math.random() * RANDOM_POSITION );
      apple_y = r * DOT_SIZE ;
   }

   public void paintComponent ( Graphics g ) {
      super.paintComponent(g) ;
      draw (g) ;
   }
   public void draw ( Graphics g) {
      if ( inGame ) {
         g.drawImage ( apple, apple_x,apple_y, this) ;
         for ( int i = 0 ; i < dots ; i ++ ) {
            if ( i == 0 )     g.drawImage( head, x[i], y[i], this) ;
            else {
               g.drawImage(dot, x[i], y[i], this ) ;
            }
         }

         Toolkit.getDefaultToolkit().sync();
      }
      else {
         gameOver (g) ;
      }
   }
 
   public void gameOver(Graphics g) {
      String msg = "GAME OVER !" ;
      Font font = new Font("SAN SERIF",Font.BOLD, 14 ) ;
      FontMetrics metrices = getFontMetrics(font) ;
      g.setColor ( Color.WHITE ) ;
      g.setFont ( font ) ;
      g.drawString( msg, (300-metrices.stringWidth(msg))/2, 150 ) ;
   }
 
   public void move () {
      for ( int i = dots ; i > 0 ; i -- ) {
         x [i] = x [i-1] ;
         y [i] = y [i-1] ;
      }
      if ( left )       x [0] -= DOT_SIZE ;
      if ( right )      x [0] += DOT_SIZE ;
      if ( up )         y [0] -= DOT_SIZE ;
      if ( down )       y [0] += DOT_SIZE ;
   }
   public void checkApple() {
      if ( x[0] == apple_x && y[0] == apple_y ) {
         dots ++ ;
         locateApple();
      }
   }

   public void checkCollision () {
      for ( int i = dots ; i > 0 ; i -- ) {
         if ( x[0] == x [i] && y[0] == y[i] ){
            inGame = false ;
            break ;
         }
      }

      if ( x[0] < 0 || y[0] < 0 || x [0] >= 300 || y [0] >= 300 )
         inGame = false ;
      
         if ( !inGame )    timer.stop () ;
   }

   public void actionPerformed( ActionEvent e ){
      if ( inGame ){
         checkApple();
         checkCollision () ; 
         move() ;
      }
      repaint () ;
   }

   public class TAdapter extends KeyAdapter {
      @Override
      public void  keyPressed ( KeyEvent e ) {
         int key = e.getKeyCode() ;
         if ( !right && ( key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT )) {
            left = true ;
            up = false ;
            down = false ;
         }
         if ( !left && ( key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT )) {
            right = true ;
            up = false ;
            down = false ;
         }
         if ( !up && ( key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN )) {
            down = true ;   
            left = false ;
            right = false ;
         }
         if ( !down && ( key == KeyEvent.VK_W || key == KeyEvent.VK_UP )) {
            up = true ; 
            left = false ;
            right = false ;
         }
      }
   }
}
