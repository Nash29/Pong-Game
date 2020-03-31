package game_01;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener{
    
    private static final long serialVersionUID = 1L;
    public static int WIDTH = 160;//Dimeção do quandrado
    public static int HEIGHT = 120;//Dimeção do quandrado
    public static int SCALE = 3;//Escala de 3X
    
    public BufferedImage layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    
    public static Player player; //Classe Player
    public static Enemy enemy; //Classe Inimigo
    public static Ball ball; //Classe Ball
    
     
    public Game(){
        
        this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        this.addKeyListener(this);
        player = new Player(100,HEIGHT-5);//Dimeção onde o Player ira iniciar
        enemy = new Enemy(100,0);//Dimeção onde o inimigo ira iniciar
        ball = new Ball(100,HEIGHT/2 - 1);//Dimeção onde a bola ira iniciar
    }
    
    public static void main(String[] args) {
        Game game = new Game();//game recebe novo Game
        JFrame frame = new JFrame();
        frame.setResizable(false);//Usuario não redimencionara a janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//quando clicar para sair ele realmente ira sair 
        frame.add(game);//Pega todas as propriedades do game
        frame.pack();//Calcula as dimenções
        frame.setLocationRelativeTo(null);//Fixar a janela no centro
        frame.setVisible(true);//Visibilidade verdadeira
        
        new Thread(game).start();
    }
    
    public void tick(){
        player.tick();
        enemy.tick();
        ball.tick();
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = layer.getGraphics();
        g.setColor(Color.BLACK);//Cor do fundo
        g.fillRect(0, 0, WIDTH, HEIGHT);//Dimeções do quadro
        player.render(g);//Rederização do Player
        enemy.render(g);//Rederização do inimigo
        ball.render(g);//Rederização da bola
        
        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH*SCALE,HEIGHT*SCALE,null);
        
        bs.show();
    }

    
    @Override
    public void run() {//Se void RUN for verdadeiro, vai começar a rodar a 60 FPS
        requestFocus();//Inicia o jogo sem precisar clicar na tela
        while(true){
            tick();
            render();
            try {
                Thread.sleep(1000/60);//60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D ){
            player.right = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D ){
            player.right = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            player.left = false;
        }
    }
    
     @Override
    public void keyTyped(KeyEvent e) {
    }  
}
