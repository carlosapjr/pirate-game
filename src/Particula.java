import java.awt.Color;
import java.awt.Graphics2D;


public class Particula extends Sprite {

	double angulo = 0;
	double constvida = 0;
	
	float velX = 0;
	float velY = 0;
	
	int vel = 100;
	int raio = 45;	
	int tempoDeVida = 100;
	int timerVida = 0;
	int corint = 0;
	
	Color cor = Color.white;	
	
	public Particula(float X,float Y,int vel,double angulo, Color cor,int tempoDeVida) {
		super();
		
		this.vel = vel;
		this.raio = raio;
		this.angulo = angulo;
		this.X = X;
		this.Y = Y;
		this.tempoDeVida = tempoDeVida;
		
		this.cor = cor;
		
		
		velX = (float)(vel*Math.cos(angulo));
		velY = (float)(vel*Math.sin(angulo));
		
		vivo = true;
		
		corint = cor.getRGB()&0x00ffffff;
		
		constvida = 1/(double)tempoDeVida;
		
	}

	@Override
	public void SimulaSe(int diftime) {
		
		timerVida +=diftime;
		
		X+=velX*diftime/1000.0f;
		Y+=velY*diftime/1000.0f;
		
		if(timerVida>tempoDeVida){
			vivo = false;
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int MapX, int MapY) {
		
		dbg.setColor(new Color(cor.getRed(),cor.getGreen(),cor.getBlue(),((255*(tempoDeVida-timerVida))/tempoDeVida)));		
		dbg.fillRect((int)X-1-MapX, (int)Y-1-MapY, 3, 3);
	}

}
