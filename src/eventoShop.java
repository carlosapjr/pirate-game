import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class eventoShop extends Evento {
	
	public eventoShop(String linha){
		String strs[] = linha.split(";");
		codigo = Integer.parseInt(strs[0]);
		tipo = Integer.parseInt(strs[1]);
		Pvar = Integer.parseInt(strs[2]);
		Pop = Integer.parseInt(strs[3]);
		Pvalor = Integer.parseInt(strs[4]);
		x = Integer.parseInt(strs[5]);
		y = Integer.parseInt(strs[6]);
		itemShop = Integer.parseInt(strs[7]);			// Colunas 7 e 8 para trazer o item que será utilizado na loja
		itemShop2 = Integer.parseInt(strs[8]);			// Mas se quiser pode aumentar sm problemas
		Avar = Integer.parseInt(strs[9]);
		Aop = Integer.parseInt(strs[10]);
		Avalor = Integer.parseInt(strs[11]);
		mapaAtual = Integer.parseInt(strs[12]);
		mapaDestino = Integer.parseInt(strs[13]);
	}
	public void criaShop(Graphics2D dbg){
		
		Color cor = Color.WHITE;
		dbg.setColor(new Color(cor.getRed(),cor.getGreen(),cor.getBlue(),170));	
		dbg.fillRect(100, 100, 300, 300);
		
		switch (itemShop) {			// Item definido no arquivo de execel (Ficou de exemplo tipo pode ser as espadas)
		case 4:
			//dbg.drawImage(PainelJogo.lancaChamasIcon, 130, 200, 80, 80, null);
			break;
		case 5:
			//dbg.drawImage(PainelJogo.laserIcon, 130, 200, 80, 80, null);
			break;
		case 6:
			//dbg.drawImage(PainelJogo.vidaIcon, 130, 200, 80, 80, null);
			break;
		}
		
		switch (itemShop2) {
		case 4:
			//dbg.drawImage(PainelJogo.lancaChamasIcon, 270, 200, 80, 80, null);
			break;
		case 5:
			//dbg.drawImage(PainelJogo.laserIcon, 270, 200, 80, 80, null);
			break;
		case 6:
			//dbg.drawImage(PainelJogo.vidaIcon, 270, 200, 80, 80, null);
			break;
		}
		
		dbg.drawImage(PainelJogo.buyIcon, 150, 300, 40, 40, null);
		dbg.drawImage(PainelJogo.buyIcon, 300, 300, 40, 40, null);
		
		dbg.setColor(new Color(cor.getRed(),cor.getGreen(),cor.getBlue(),100));	
		dbg.fillRect(0, 410, 480, 480);
//		dbg.drawImage(PainelJogo.ouroIcon, 110, 360, 30, 30, null);
//		dbg.setColor(Color.WHITE);
//		dbg.drawString(" "+PainelJogo.instancia.pontos, 130, 390);
//		if(PainelJogo.instancia.arma1)
//		dbg.drawImage(PainelJogo.arma, 10, 420, 60, 60, null);
//		if(PainelJogo.instancia.arma2)
//		dbg.drawImage(PainelJogo.uziIcon, 80, 425, 60, 60, null);
//		if(PainelJogo.instancia.arma3)
//		dbg.drawImage(PainelJogo.fogueteIcon, 170, 420, 100, 60, null);
//		if(PainelJogo.instancia.arma4)
//		dbg.drawImage(PainelJogo.lancaChamasIcon, 300, 420, 60, 60, null);
//		if(PainelJogo.instancia.arma5)
//		dbg.drawImage(PainelJogo.laserIcon, 400, 430, 50, 50, null);
		
		dbg.drawImage(PainelJogo.sellIcon, 30, 410, 20, 20, null);
		dbg.drawImage(PainelJogo.sellIcon, 110, 410, 20, 20, null);
		dbg.drawImage(PainelJogo.sellIcon, 210, 410, 20,20 , null);
		dbg.drawImage(PainelJogo.sellIcon, 320, 410, 20,20, null);
		dbg.drawImage(PainelJogo.sellIcon, 420, 410, 20, 20, null);
	}

}
