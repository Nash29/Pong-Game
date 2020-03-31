package game_01;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    
    public boolean right,left;//Variavel esquerda e direita
    
    public int x,y;
    
    public int width,height;//Variaveis da dimenção
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 40;//Dimeção da largura do player
        this.height = 5;//Dimeção do tamanho do player
    }
    
    public void tick(){
        if(right){
            x++;
        }
        else if(left){
            x--;
        }
        if(x+width > Game.WIDTH){//Dimeção ate onde o player pode chegar para a direita
            x = Game.WIDTH - width;
        }
        else if(x < 0){//Dimeção ate onde o player pode chegar, para a esquerda
            x = 0;
        }
    }
    
    public void render(Graphics g){
        g.setColor(Color.BLUE);//Cor do jogador
        g.fillRect(x, y, width,height);//Dimeções do jogador
    }
    
}
