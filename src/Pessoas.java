import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Pessoas extends Sprite{

	BufferedImage charset = null;
	
	int charw = 59;
	int charh = 84;	
	int tipo = 0;
	int charx = 0;
	int chary = 0;
	int mapa = 0;
	
	public Pessoas(float x,float y,BufferedImage charset, int charw, int charh, int mapa) {
		this.X = x;
		this.Y = y;
		this.charset = charset;
		this.charw = charw;
		this.charh = charh;	
		this.mapa = mapa;
	}

	@Override
	public void SimulaSe(int diftime) {}

	@Override
	public void DesenhaSe(Graphics2D dbg, int MapX, int MapY) {
		
		if (PainelJogo.instancia.mapaAtual == mapa)
			dbg.drawImage(charset,(int)(X-MapX), (int)(Y-MapY), (int)(X-MapX)+charw, (int)(Y-MapY)+charh,charx,chary,charx+charw, chary +charh, null);	
	}

}
