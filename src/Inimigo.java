import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Inimigo extends Sprite{


	float velX = 0;
	float velY = 0;
	
	BufferedImage charset = null;
	
	int vel = 100;
	int charw = 32;
	int charh = 48;	
	int frame = 0;
	int frametimer = 0;
	int tempoentreframes = 200;	
	int anim = 0;	
	int tipo = 0;
	int charx = 0;
	int chary = 0;	
	int raio = 8;	
	int vida = 100;
	int mapa;
	
	public Inimigo(float x,float y,BufferedImage charset,int mapa) {
		this.X = x;
		this.Y = y;
		this.charset = charset;
		vivo = true;
		this.mapa = mapa;
	}

	@Override
public void SimulaSe(int diftime) {
		
		frametimer+=diftime;

		float xold = X;
		float yold = Y;		
		
		X+=velX*diftime/1000.0f;
		Y+=velY*diftime/1000.0f;
		
		int wmundo = (PainelJogo.instancia.mapa[PainelJogo.mapaAtual].Largura*16)-32;
		int hmundo = (PainelJogo.instancia.mapa[PainelJogo.mapaAtual].Altura*16)-48;
		
		if(X < 0){	velX = -velX;	}
		if(X > wmundo){	velX = -velX;	}
		if(Y < 0){	velY = -velY;	}
		if(Y > hmundo){	velY = -velY;	}	
		
		if (colidecenario() || colidecircular()){
			velX = -velX;
			velY = -velY;
		}
		
		frame = (frametimer/tempoentreframes)%4;
	}


	@Override
	public void DesenhaSe(Graphics2D dbg,int MapX,int MapY){

		dbg.drawImage(charset,(int)(X-MapX), (int)(Y-MapY), (int)(X-MapX)+charw, (int)(Y-MapY)+charh,charx+ frame*charw,chary+ anim*charh,charx+ frame*charw+charw, chary + anim*charh+charh, null);
		dbg.setColor(Color.RED);
		dbg.fillRect((int)(X-(MapX+charw/charw)), (int)(Y-5-(MapY+charh/charh)), 30, 3);
		if (vida > 100){
			dbg.setColor(Color.GREEN);
			dbg.fillRect((int)(X-(MapX+charw/charw)), (int)(Y-5-(MapY+charh/charh)), 30, 3);
			dbg.setColor(Color.CYAN);
			dbg.fillRect((int)(X-(MapX+charw/charw)), (int)(Y-5-(MapY+charh/charh)), (vida/30)*3, 3);
		}else if(vida > 200){
			dbg.setColor(Color.CYAN);
			dbg.fillRect((int)(X-(MapX+charw/charw)), (int)(Y-5-(MapY+charh/charh)), 30, 3);
		}else{
			dbg.setColor(Color.GREEN);
			dbg.fillRect((int)(X-(MapX+charw/charw)), (int)(Y-5-(MapY+charh/charh)), vida/3, 3);
		}
			
	}
	
	public boolean colidecenario(){
		
		int bx = ((int)(X+16))>>4;	// /16
		int by = ((int)(Y+36))>>4;	// /16		
		
		if(PainelJogo.instancia.mapa[1].mapa2[by][bx]>0){
			return true;
		}
		
		return false;
	}
	public boolean colidecircular(){
			Personagem pers = PainelJogo.instancia.pirata;
			
				float difx = (X+charw/2)-(pers.X+pers.charw/2);
				float dify = (Y+charh/2)-(pers.Y+pers.charh/2);
				
				float r2 = (raio+pers.raio)*(raio+pers.raio);
				
				if(r2>(difx*difx+dify*dify)){
					return true;
				
			}
		return false;
	}

}

