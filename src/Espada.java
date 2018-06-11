import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Espada extends Sprite{
	
	BufferedImage img = null;
	int frame = 0;
	int frametimer = 0;
	int tempoentreframes = 50;	
	int anim = 0;
	int imgw = 32;
	int imgh = 40;
	
	public Espada(BufferedImage imagem){
            this.img = imagem;
	}

	@Override
	public void SimulaSe(int diftime){ 
		
		frametimer+=diftime;
		int direcao = PainelJogo.instancia.direcaoEspada;
		
		if(direcao == 1){
			this.X = (PainelJogo.instancia.pirata.X + PainelJogo.instancia.pirata.charw/2)+(this.imgw/5);
			this.Y = (PainelJogo.instancia.pirata.Y +PainelJogo.instancia.pirata.charh/2)+(this.imgh/2);
		}else
			if(direcao == 0){
			this.X = (PainelJogo.instancia.pirata.X + PainelJogo.instancia.pirata.charw/2)-(this.imgw/5) ;
			this.Y = (PainelJogo.instancia.pirata.Y)- (this.imgh/2) ;
		}
            
		frame = (frametimer/tempoentreframes)%4;
		
		Inimigo inimigo = null;
		Boss boss = null;
		
		if(direcao==1 || direcao==0){
			if(PainelJogo.instancia.mapaAtual == 1)
            if((inimigo=colideRetangulo()) != null ){
            	
            	inimigo.vida += -10 ;
            	
                for(int i = 0; i < 40; i++)
                	PainelJogo.instancia.listaDeParticulas.add(new Particula((inimigo.X+PainelJogo.instancia.pirata.charw/2), (inimigo.Y+PainelJogo.instancia.pirata.charh/2),
                			(int)(90*PainelJogo.random.nextDouble()), PainelJogo.random.nextDouble()*-90, Color.RED, 300));
            	
                if (inimigo.vida < 0){
                	inimigo.vivo = false;
                	PainelJogo.instancia.money += 10;
                }
                
            	System.out.println("Colidiu");
            }
			if(PainelJogo.instancia.mapaAtual == 2)
            if((boss=colideBoss()) != null){
            	boss.vida += -5 ;
            	
                for(int i = 0; i < 40; i++)
                	PainelJogo.instancia.listaDeParticulas.add(new Particula((boss.X+PainelJogo.instancia.pirata.charw/2), (boss.Y+PainelJogo.instancia.pirata.charh/2),
                			(int)(90*PainelJogo.random.nextDouble()), PainelJogo.random.nextDouble()*-90, Color.GREEN, 300));
            	
                if (boss.vida < 0){
                	boss.vivo = false;
                	PainelJogo.instancia.money += 50;
                }
                
            	System.out.println("Colidiu");
            }
            
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int MapX, int MapY){  
		
		if(PainelJogo.instancia.direcaoEspada == 1)
			dbg.drawImage(this.img,(int)(PainelJogo.instancia.pirata.X+PainelJogo.instancia.pirata.charw/2)+40 -MapX, (int)(PainelJogo.instancia.pirata.Y+PainelJogo.instancia.pirata.charh/2)+(imgh/2)-MapY, (int)(this.X-MapX)-imgw, (int)(this.Y-MapY)-24,frame*imgw,anim*imgh,frame*imgw+imgw, anim*imgh+imgh, null);
		
		if(PainelJogo.instancia.direcaoEspada == 0)
			dbg.drawImage(this.img,(int)(PainelJogo.instancia.pirata.X-PainelJogo.instancia.pirata.charw/2)-8 -MapX, (int)(PainelJogo.instancia.pirata.Y+PainelJogo.instancia.pirata.charh/2)+(imgh/2)-MapY, (int)(this.X-MapX)+imgw, (int)(this.Y-MapY)+imgh,frame*imgw,anim*imgh,frame*imgw+imgw, anim*imgh+imgh, null);
	
	}
	
	
	private Inimigo colideRetangulo(){
		
		for(int i = 0; i < PainelJogo.instancia.listaDeInimigos.size();i++){	
			
			Inimigo inimigo = (Inimigo)PainelJogo.instancia.listaDeInimigos.get(i);		
			
			if(this.X > inimigo.X + inimigo.charw)
				continue;
			if(this.X + (this.imgw/5) < inimigo.X)
				continue;
			if(this.Y > inimigo.Y + inimigo.charh)
				continue;
			if(this.Y + this.imgh < inimigo.Y)
				continue;
			return inimigo;
		}
        return null;          
            
	}
	
private Boss colideBoss(){
			
		Boss boss = PainelJogo.instancia.boss;
		
			if(this.X > boss.X + boss.charw)
				return boss;
			if(this.X + (this.imgw/5) < boss.X)
				return boss;
			if(this.Y > boss.Y + boss.charh)
				return boss;
			if(this.Y + this.imgh < boss.Y)
				return boss;
			
        return null;          
            
	}
}
