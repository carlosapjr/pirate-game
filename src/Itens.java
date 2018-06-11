import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Itens extends Sprite{
	
	BufferedImage img = null;
	int tempoDeVida = 1000;
    int tempo, itemAtual;
	 
	public Itens(float X, float Y, int atual, BufferedImage imagem){
            this.img = imagem;
            this.X = X;
            this.Y = Y;
            this.itemAtual = atual;
	}
	public Itens(BufferedImage imagem){
        this.img = imagem;
}

	@Override
	public void SimulaSe(int diftime){ 
		
            tempo += diftime;
            
//            if(colideRetangulo()){
//            	switch (itemAtual) {
//				case 0: PainelJogo.instancia.money += 10;	break;
//				case 1: PainelJogo.instancia.arma4 = true;	break;
//				case 2: PainelJogo.instancia.pers.vida += 10;	break;
//			}
//                
//                PainelJogo.instancia.listaDeItens.remove(this);
//            }
//            
//            if(colidePessoas()){
//            	PainelJogo.instancia.pers.vivo = false;
            }
//            if(tempo > tempoDeVida)
//                GamePanel.instancia.listaDeItens.remove(this);
//	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int MapX, int MapY){   dbg.drawImage(img,(int)X-MapX, (int)Y-MapY, null);	 }
	
	private boolean colideRetangulo(){
		
//            for(int i = 0; i < PainelJogo.instancia.listaDeItens.size();i++){
//                Itens item = (Itens)PainelJogo.instancia.listaDeItens.get(i);
//                Personagem pers = PainelJogo.instancia.pers;

//                    if(this.X > pers.X + pers.charw)
//                        continue;
//                    if(this.X + this.img.getWidth() < pers.X)
//                        continue;
//                    if(this.Y > pers.Y + pers.charh)
//                        continue;
//                    if(this.Y + this.img.getHeight() < pers.Y)
//                        continue;
//                    
//                    return  true;
//                }
            
            return false;
	}
	
	public boolean colidePessoas(){
		
//		for(int i = 0; i< PainelJogo.instancia.listaDePersonagens.size(); i++){
//			Personagem pers = (Personagem)PainelJogo.instancia.listaDePersonagens.get(i);
			
//			if(this.X > pers.X + pers.charw)
//                continue;
//            if(this.X + this.img.getWidth() < pers.X)
//                continue;
//            if(this.Y > pers.Y + pers.charh)
//                continue;
//            if(this.Y + this.img.getHeight() < pers.Y)
//                continue;
//            
//            return  true;
//        }
    
    return false;
	}
}