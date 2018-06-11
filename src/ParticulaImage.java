import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class ParticulaImage extends Particula {

	BufferedImage img = null;
	 
	public ParticulaImage(float X, float Y, int vel, double angulo, BufferedImage img,int tempoDeVida) {
		super(X, Y, vel, angulo, Color.white, tempoDeVida);		
		this.img = img;
	}
	
	@Override
	public void DesenhaSe(Graphics2D dbg, int MapX, int MapY) {
		
		double prop = (tempoDeVida-timerVida)/(double)tempoDeVida;
		double propinv = 1.0-prop;
		double scale = 0.1+propinv*2;
		
		AffineTransform trans = dbg.getTransform();
		Composite comp = dbg.getComposite();
			
		
			dbg.translate(X-MapX, Y-MapY);
			dbg.scale(scale, scale);
		
			dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)prop));
			
			dbg.drawImage(img, null, -(img.getWidth()/2), -(img.getHeight()/2));
		
			
		dbg.setComposite(comp);
		dbg.setTransform(trans);
	}

}
