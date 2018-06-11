import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Personagem extends Sprite{
	
	BufferedImage charset = null;
	float velX = 0;
	float velY = 0;
	int vel = 100;
	int charw = 32;
	int charh = 48;	
	int frame = 0;
	int frametimer = 0;
	int tempoentreframes = 200;	
	int anim = 0;
	int raio = 8;	
	int vida = 100;
	
	public Personagem(float x,float y,BufferedImage charset) {
		this.X = x;
		this.Y = y;
		this.charset = charset;
		vivo = true;
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
		
		if(X < 0){	X = 0;	}
		if(X > wmundo){	X = wmundo;	}
		if(Y < 0){	Y = 0;	}
		if(Y > hmundo){	Y = hmundo;	}	
		
		if (colidecenario()){
			this.X = xold;
			this.Y = yold;	
		}
		if (colidecircular()){
			this.X = xold;
			this.Y = yold;	
		}
		
		if (colidePessoas()){
			this.X = xold;
			this.Y = yold;
		}
		if(PainelJogo.instancia.mapaAtual == 2)
		if (colideBoss()){
			this.X = xold;
			this.Y = yold;	
			this.vida -=5;
		}
		
		frame = (frametimer/tempoentreframes)%4;
	}


	@Override
	public void DesenhaSe(Graphics2D dbg,int MapX,int MapY){
		
		dbg.drawImage(charset,(int)(X-MapX), (int)(Y-MapY), (int)(X-MapX)+charw, (int)(Y-MapY)+charh,frame*charw,anim*charh,frame*charw+charw, anim*charh+charh, null);
	
	}
	
	public boolean colidecenario(){
		
		int bx = ((int)(X+16))>>4;	// /16
		int by = ((int)(Y+36))>>4;	// /16		
		
		if(PainelJogo.instancia.mapa[PainelJogo.mapaAtual].mapa2[by][bx]>0){
			System.out.println("colidiuCenário");
			return true;
		}
		return false;
	}
	public boolean colidecircular(){
		for(int i = 0; i < PainelJogo.instancia.listaDeInimigos.size();i++){
			Inimigo pers = (Inimigo)PainelJogo.instancia.listaDeInimigos.get(i);
			
				float difx = (X+charw/2)-(pers.X+pers.charw/2);
				float dify = (Y+charh/2)-(pers.Y+pers.charh/2);
				
				float r2 = (raio+pers.raio)*(raio+pers.raio);
				if(pers.mapa == PainelJogo.instancia.mapaAtual)
				if(r2>(difx*difx+dify*dify)){
					System.out.println("colidiuCircular");
					return true;				
			}
		}
		return false;
	}
	
	public boolean colidePessoas(){
		for(int i = 0; i < PainelJogo.instancia.listaDePessoas.size();i++){
			Pessoas pers = (Pessoas)PainelJogo.instancia.listaDePessoas.get(i);
			
				float difx = (X+charw/2)-(pers.X+pers.charw/2);
				float dify = (Y+charh/2)-(pers.Y+pers.charh/2);				
				float r2 = (raio+24)*(raio+24);
				
				if(pers.mapa == PainelJogo.instancia.mapaAtual)
				if(r2>(difx*difx+dify*dify)){
					System.out.println("colidiuPessoa");
					return true;
				}
		}
		return false;
	}
	
	public boolean colideBoss(){
		
		Boss pers = PainelJogo.instancia.boss;
		
			float difx = (X+charw/2)-(pers.X+pers.charw/2);
			float dify = (Y+charh/2)-(pers.Y+pers.charh/2);
			
			float r2 = (raio+pers.raio)*(raio+pers.raio);
			
			if(r2>(difx*difx+dify*dify)){
				System.out.println("colidiuBoss");
				return true;				
		}
	return false;
}

}
