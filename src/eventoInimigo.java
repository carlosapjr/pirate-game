import java.util.Random;


public class eventoInimigo extends Evento {
	
	int wmundo, hmundo;
	
public eventoInimigo() {}
	
	public eventoInimigo(String linha) {
		
		String strs[] = linha.split(";");
		codigo = Integer.parseInt(strs[0]);
		tipo = Integer.parseInt(strs[1]);
		Pvar = Integer.parseInt(strs[2]);
		Pop = Integer.parseInt(strs[3]);
		Pvalor = Integer.parseInt(strs[4]);
		x = Integer.parseInt(strs[5]);
		y = Integer.parseInt(strs[6]);
		quantidadeInimigo = Integer.parseInt(strs[7]);
		Avar = Integer.parseInt(strs[9]);
		Aop = Integer.parseInt(strs[10]);
		Avalor = Integer.parseInt(strs[11]);
		mapaAtual = Integer.parseInt(strs[12]);
		mapaDestino = Integer.parseInt(strs[13]);
	}
	
	public void criaInimigo(){

		 wmundo = PainelJogo.instancia.mapa[mapaAtual].Largura*16;					// define altura e largura do mundo x16
		 hmundo = PainelJogo.instancia.mapa[mapaAtual].Altura*16;
		 

		 Random random = new Random();
		 
		 for (int i = 0; i < quantidadeInimigo; i++) {
			 Inimigo per = new Inimigo(random.nextInt(wmundo-32),random.nextInt(hmundo-48),PainelJogo.instancia.Inimigocharset,1);
             per.vel = 50+random.nextInt(50);
             per.velX = random.nextInt(100)-50;
             per.velY = random.nextInt(100)-50;
             while(per.colidecircular()||per.colidecenario()){
                     per.X = random.nextInt(wmundo-32);
                     per.Y = random.nextInt(hmundo-48);
             }
             PainelJogo.instancia.listaDeInimigos.add(per);
		}
	}
	
}
