import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GerenciadorDeEventos {
	
	public static ArrayList<Evento> listaDeEventos = new ArrayList<Evento>();
	
	public static int variaveis[] = new int[100];
	public static boolean evDialogo = false;
	public static boolean shopAtivo = false;
	public static boolean terminou = false;
	Color cor = Color.BLACK;
	int timer = 0;
	//Itens it;
	
	public GerenciadorDeEventos() {}
	
	public GerenciadorDeEventos(InputStream in) {
		 BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
		 
		 String line = "";
		 
		 try {
			while((line=bfr.readLine())!=null){
				 if(line.charAt(0)!='#'){
					 
					 Evento ev;
					 int tipo;
					 String strs[] = line.split(";");
					 tipo = Integer.parseInt(strs[1]);
					 System.out.println(tipo);
					 switch (tipo) {
					case 1:
						ev = new Evento(line);
						listaDeEventos.add(ev);
						break;
					case 2:
						ev = new eventoDialogo(line);
						listaDeEventos.add(ev);
						break;
					case 3:
						ev = new eventoInimigo(line);
						listaDeEventos.add(ev);
						break;
					case 4:
						ev = new eventoIten(line);
						listaDeEventos.add(ev);
						break;
					case 5:
						ev = new eventoShop(line);
						listaDeEventos.add(ev);
						break;
					}		
				 }
			 }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void simulaSe(int diftime){
		
		timer += diftime;
		for(int i = 0; i < listaDeEventos.size();i++){
			Evento ev = listaDeEventos.get(i);
			ev.testaAtivo();
			if(ev.ativo && ev.mapaAtual == PainelJogo.mapaAtual){
				int bx = ((int)(PainelJogo.instancia.pirata.X+16))>>4;			// /16
				int by = ((int)(PainelJogo.instancia.pirata.Y+36))>>4;			// /16
				
				if(ev.x == bx && ev.y == by && timer > 600){
					switch (ev.tipo) {							
					case 1:
						PainelJogo.instancia.pirata.X = ev.xdest<<4; 				// vezes 16
						PainelJogo.instancia.pirata.Y = ev.ydest<<4; 				// vezes 16
						PainelJogo.mapaAtual = ev.mapaDestino; 
						break;
					case 2:
						PainelJogo.instancia.jogoParado = true;
						evDialogo = true;
						if(ev.Avar == 6 && timer > 800)
							terminou  = true;
						break;
						
					case 3:
						ev.criaInimigo();
						break;
						
					case 4:	break;
					
					case 5:
						PainelJogo.instancia.jogoParado = true;
						shopAtivo = true;
						break;
					}
						
					if(timer > 200*2){
						timer = 0;
					}else{
						timer -= 200;
					}
					ev.executaAcao();
				}
			}
				
		}
		
	}
	
	public void desenhaSe(Graphics2D dbg){
		
		
		for(int i = 0; i < listaDeEventos.size();i++){
			Evento ev = listaDeEventos.get(i);
			
			int evx = ev.x <<4; // vezes 16
			int evy = ev.y <<4; // vezes 16
			
			if(ev.mapaAtual == PainelJogo.mapaAtual){
				if(ev.ativo){
					dbg.setColor(Color.YELLOW);
					int bx = ((int)(PainelJogo.instancia.pirata.X+16))>>4;			// /16
					int by = ((int)(PainelJogo.instancia.pirata.Y+36))>>4;			// /16
					
					if(ev.x == bx && ev.y == by){
						dbg.setColor(new Color(cor.getRed(),cor.getGreen(),cor.getBlue(),80));	
						dbg.fillRect(0, 250, 320, 320);
						dbg.setColor(Color.WHITE);
						dbg.drawString(ev.dialogo, 10, 280);
						
						if(!ev.dialogo2.equalsIgnoreCase("1"))
							dbg.drawString(ev.dialogo2, 10, 290);
						
						dbg.drawString("Pressione 'Enter' para continuar", 60, 310);
						System.out.println(ev.dialogo+"\n"+ev.dialogo2);
						}
					if(shopAtivo){
					ev.criaShop(dbg);						
					}
				}else{
					dbg.setColor(Color.RED);
				}
				
				dbg.drawRect(evx-PainelJogo.instancia.mapa[PainelJogo.mapaAtual].MapX, evy-PainelJogo.instancia.mapa[PainelJogo.mapaAtual].MapY, 16, 16);
			}
		}
	}
	
	public boolean colideEvento(Evento ev){	
				
				if((ev.x<<4) > PainelJogo.instancia.pirata.X + PainelJogo.instancia.pirata.charw)
					return true;
				if((ev.x<<4) < PainelJogo.instancia.pirata.X)
					return true;
				if((ev.y<<4) > PainelJogo.instancia.pirata.Y + PainelJogo.instancia.pirata.charh)
					return true;
				if((ev.y<<4)< PainelJogo.instancia.pirata.Y)
					return true;
				
	        return false;          
	            
	}
}
