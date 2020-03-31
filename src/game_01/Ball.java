package game_01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
    
    public double x,y;
    public int width,height;
    
    public double dx, dy;
    public double speed = 1.7;//Velocidade da bola
    
    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 4;//Dimeção da bola
        this.height = 4;//Dimeção da bola
        
        int angle = new Random().nextInt(120 - 45) + 45 + 1;//Angulo que a bola se movimentara randomicamente
        dx = Math.cos(Math.toRadians(angle));//Cosseno do angulo em que a bola ira se movimentar
        dy = Math.sin(Math.toRadians(angle));//Seno do angulo em que a bola ira se movimentar
    }
    
    public void tick(){
        if(x+(dx*speed) + width >= Game.WIDTH){
            dx*=-1;
        }else if(x+(dx*speed) < 0){
            dx*=-1;
        }
        
        if(y >= Game.HEIGHT){//Caso inimigo fizer um ponto
            //Ponto do inimigo
            System.out.println("Ponto do inimigo!");//Mensagem
            new Game();//Reinicia o game
            return;
        }else if(y < 0){//Caso player ganhar
            //Ponto do jogador
            System.out.println("Ponto do jogador");//Mensagem
            new Game();//Reinicia o game
            return;
        }
        
        Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
        
        Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);//Angulos em que a bola pode parar com o player
        Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);//Angulos em que a bola pode parar com o inimigo
        
        if(bounds.intersects(boundsPlayer)){
            int angle = new Random().nextInt(120 - 45) + 45 + 1;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if(dy > 0)
                dy*=-1;
        }else if(bounds.intersects(boundsEnemy)){
            int angle = new Random().nextInt(120 - 45) + 45 + 1;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if(dy < 0)
                dy*=-1;
        }
        
        x+=dx*speed;//Velocidade da bola
        y+=dy*speed;//Velocidade da bola
        
        
    }
    
    public void render(Graphics g){
        g.setColor(Color.yellow);//Cor da bola
        g.fillRect((int)x, (int)y, width, height);//Eixo X/Y tem que iniciar com inteiro
    }
}
