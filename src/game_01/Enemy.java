package game_01;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
    
    public double x,y;
    public int width,height;//Ja são inteiros
    
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 40;//Dimeção do inimigo
        this.height = 5;//Dimenção do inimigo
    }
    
    public void tick(){
        x+= (Game.ball.x - x - 6) * 0.4;//Deixa o inimigo mais de vagar ou mais rapido
    }
    
    public void render(Graphics g){
        g.setColor(Color.RED);//Cor do inimigo
        g.fillRect((int)x, (int)y, width, height);//Eixo X/Y tem que iniciar com inteiro
    }
}
