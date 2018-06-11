import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PainelJogo extends JPanel implements Runnable {
	
	public static PainelJogo instancia = null;
	
	public static final int telaLargura = 320,telaAltura = 320;
	int MouseX,MouseY, direcaoEspada;
	public int money;
	public static int mapaAtual = 0;

	private Thread animator;
	private boolean running = false;
	public boolean gameOver = false;
	public boolean TelaAbertura = true;
	public  boolean jogoParado = false;
	public  boolean esquerda, direita, cima, baixo, ataque = false,inventorio=false;
    private BufferedImage dbImage;
    public  BufferedImage Personagemcharset;	
    public  BufferedImage Inimigocharset;
    public  BufferedImage Capitaocharset;
    public  BufferedImage Pessoacharset;
    public  BufferedImage Bosscharset;
    public  BufferedImage Telagameover;
    public  BufferedImage Telainicial;
    public static BufferedImage buyIcon;
    public static BufferedImage sellIcon;
    public static BufferedImage espadaIcon;
    public static BufferedImage ouroIcon;
	private Graphics2D dbg;		  
	TileMap mapa[] = new TileMap[4];
	BufferedImage tileset[] = new BufferedImage[4];
	public  BufferedImage imgEspada;
	public  BufferedImage sangue;	
	public  Personagem pirata;
	public  Espada espada;
	public  Pessoas pessoa;
	public  Pessoas capitao;
	public  Boss boss;
	public  int inimigosDerrotados;
	
	ArrayList<Sprite> listaDeParticulas = new ArrayList<Sprite>();
	ArrayList<Sprite> listaDeInimigos = new ArrayList<Sprite>();
	ArrayList<Sprite> listaDePessoas = new ArrayList<Sprite>();
	
	public static GerenciadorDeEventos eventos;
	public static Random random = new Random();

	public PainelJogo(){
		
		instancia = this;
		
		setBackground(Color.white);
		setPreferredSize( new Dimension(telaLargura, telaAltura));
		
		setFocusable(true);	
		requestFocus(); 	
		
		if (dbImage == null){
			dbImage = new BufferedImage(telaLargura, telaAltura,BufferedImage.TYPE_INT_ARGB);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}else{
				dbg = (Graphics2D)dbImage.getGraphics();
			}
		}	
		

		addKeyListener( new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				
					int keyCode = e.getKeyCode();
					
					if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){esquerda = true;}
	                if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){direita = true;}
	                if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){cima = true;}
	                if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){baixo = true;}
	                if(keyCode == KeyEvent.VK_ENTER){ 
	                	if(TelaAbertura)
	                		TelaAbertura = false;
						GerenciadorDeEventos.evDialogo = false;
						jogoParado = false;
						inventorio = false;
						GerenciadorDeEventos.shopAtivo = false;
						if(GerenciadorDeEventos.terminou)
							gameOver = true;
					}
					if(keyCode == KeyEvent.VK_I){
						if(inventorio){
							inventorio = false;
							jogoParado = false;
							} else{ 
								inventorio = true;
								jogoParado = true;
								}
						}
			}
			@Override
				public void keyReleased(KeyEvent e ) {
					int keyCode = e.getKeyCode();
					
					if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){esquerda = false;}
	                if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){direita = false;}
	                if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){cima = false;}
	                if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){baixo = false;}
				}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				MouseX = e.getX();
				MouseY = e.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				MouseX = e.getX();
				MouseY = e.getY();
			}
		});
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {ataque = false; }
			
			@Override
			public void mousePressed(MouseEvent arg0) {	ataque = true;	}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {	}
		});
		
		try {
			
			tileset[0] =  ImageIO.read( getClass().getResource("Tileset Praia.png") );
			tileset[1] =  ImageIO.read( getClass().getResource("Mapa corredor.png") );
			tileset[2] =  ImageIO.read( getClass().getResource("cave.png") );
			tileset[3] =  ImageIO.read( getClass().getResource("InnFloor1.png") );
			
			Personagemcharset = ImageIO.read( getClass().getResource("pirate.png") );
			Inimigocharset = ImageIO.read( getClass().getResource("pirata.png") );
			imgEspada = ImageIO.read( getClass().getResource("espada.png") );
			sangue = ImageIO.read( getClass().getResource("sangue.png") );
			Capitaocharset = ImageIO.read( getClass().getResource("capitao.png") );
			Pessoacharset = ImageIO.read( getClass().getResource("pessoa.png") );
			buyIcon =  ImageIO.read( getClass().getResource("buy.png"));
	        sellIcon =  ImageIO.read( getClass().getResource("sell.png"));
	        Bosscharset = ImageIO.read( getClass().getResource("bosses.png") );
	        Telagameover = ImageIO.read( getClass().getResource("gameover.png") );
	        Telainicial = ImageIO.read( getClass().getResource("inicio.png") );
	        Telainicial = ImageIO.read( getClass().getResource("inicio.png") );
	        ouroIcon = ImageIO.read( getClass().getResource("goldenCoin.png") );
	        espadaIcon = ImageIO.read( getClass().getResource("espadaIcon.png") );
			
		}
		catch(IOException e) {
			System.out.println("Load Image error:");
		}
		
		mapa[0] = new TileMap(tileset[0], 20, 20);
		mapa[0].AbreMapa("Mapa A.map");	
		mapa[1] = new TileMap(tileset[1], 20, 20);
		mapa[1].AbreMapa("Mapa B.map");
		mapa[2] = new TileMap(tileset[2], 20, 20);
		mapa[2].AbreMapa("Mapa C.map");
		mapa[3] = new TileMap(tileset[3], 20, 20);
		mapa[3].AbreMapa("Mapa D.map");
		
		pirata = new Personagem(100, 100, Personagemcharset);
		
		capitao = new Pessoas(290, 410, Capitaocharset,59,84,0);
		listaDePessoas.add(0,capitao);
		pessoa = new Pessoas(30, 108, Pessoacharset,40,40,3);
		listaDePessoas.add(1,pessoa);
		boss = new Boss(100, 100, Bosscharset,32,48,3);
		
		eventos = new GerenciadorDeEventos(this.getClass().getResourceAsStream("/eventos.csv"));
		
		espada = new Espada(imgEspada);
	}

	public void addNotify(){	super.addNotify(); 	startGame(); 	}

	private void startGame(){					
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	} 

	public void stopGame(){ running = false; }	

	public void run(){	

		running = true;
		
		long DifTime,TempoAnterior;
		DifTime = 0;
		TempoAnterior = System.currentTimeMillis();
		
		while(running) {
		
			gameUpdate((int)DifTime); 
			gameRender(); 
			paintImmediately(0, 0, 640, 480); 
		
			try {
				Thread.sleep(0); 
			}	
			catch(InterruptedException ex){}
			
			DifTime = System.currentTimeMillis() - TempoAnterior;
			TempoAnterior = System.currentTimeMillis();		
		}
	System.exit(0); 
	} 

	int timerfps = 0;
	int timerTiro = 0;

	private void gameUpdate(int diftime){
		
		if(!jogoParado && !gameOver && !TelaAbertura){							// Gambiarra para pausar a porra toda
		if(cima){
			pirata.velY = -pirata.vel;
			pirata.anim = 3;
			
			direcaoEspada = 3;
		}else if(baixo){
			pirata.velY = pirata.vel;
			pirata.anim = 0;
			direcaoEspada = 3;
		}else{
			pirata.velY = 0;
		}
		
		if(esquerda){
			pirata.velX =-pirata.vel;
			pirata.anim = 1;
			direcaoEspada = 0;
		}else if(direita){
			pirata.velX = pirata.vel;
			pirata.anim = 2;
			direcaoEspada = 1;
		}else{
			pirata.velX = 0;
		}
		pirata.SimulaSe(diftime);
		for(int i = 0; i < listaDeParticulas.size();i++){
			Sprite part = listaDeParticulas.get(i);
			part.SimulaSe(diftime);
			if(part.vivo==false){
				listaDeParticulas.remove(i);
				i--;
			}
		}
		
		if(mapaAtual == 1){
		for(int i = 0; i < listaDeInimigos.size();i++){
			Sprite part = listaDeInimigos.get(i);
			part.SimulaSe(diftime);
			if(part.vivo==false){
				listaDeInimigos.remove(i);
				i--;
			}
		}
		}
		
		if(ataque)
			espada.SimulaSe(diftime);
		if(mapaAtual == 2)
			boss.SimulaSe(diftime);
		eventos.simulaSe(diftime);
		}
		mapa[mapaAtual].Posiciona((int)(pirata.X-telaAltura/2),(int)(pirata.Y-telaAltura/2));
		
	}

	private void gameRender(){
		
		Color cor = Color.BLACK;
		
		dbg.fillRect(0, 0, 320, 320);
		
		mapa[mapaAtual].DesenhaSe(dbg);	
		
		pirata.DesenhaSe(dbg, mapa[mapaAtual].MapX, mapa[mapaAtual].MapY);	
		if(mapaAtual == 2 && boss.vivo)
			boss.DesenhaSe(dbg, mapa[mapaAtual].MapX, mapa[mapaAtual].MapY);
		eventos.desenhaSe(dbg);
		
		for(int i = 0; i < listaDeParticulas.size();i++)  listaDeParticulas.get(i).DesenhaSe(dbg,mapa[mapaAtual].MapX,mapa[mapaAtual].MapY);
		
		if(mapaAtual == 1)
		for(int i = 0; i < listaDeInimigos.size();i++)	  listaDeInimigos.get(i).DesenhaSe(dbg,mapa[mapaAtual].MapX,mapa[mapaAtual].MapY);
		
		
		for(int i = 0; i < listaDePessoas.size();i++)     listaDePessoas.get(i).DesenhaSe(dbg,mapa[mapaAtual].MapX,mapa[mapaAtual].MapY);
			
		if(ataque)
		espada.DesenhaSe(dbg, mapa[mapaAtual].MapX, mapa[mapaAtual].MapY);
		
		if (mapaAtual == 2){
			dbg.setColor(new Color(cor.getRed(),cor.getGreen(),cor.getBlue(),100));	
			dbg.fillRect(0, 0, 320, 320);
			
		}
		
		if(inventorio){
			dbg.setColor(new Color(cor.getRed(),cor.getGreen(),cor.getBlue(),100));	
			dbg.fillRect(100, 100, 100, 100);
			dbg.drawImage(ouroIcon, 100, 180, 20, 20, null);
			dbg.setColor(Color.WHITE);
			dbg.drawString(" "+money, 110, 200);
			dbg.drawImage(espadaIcon, 110, 120, 50, 50, null);
			dbg.setColor(Color.WHITE);
			dbg.drawString("Dano - 10", 140, 160);
		}else{
			dbg.setColor(new Color(cor.getRed(),cor.getGreen(),cor.getBlue(),100));	
			dbg.fillRect(0,0, 150, 15);
			dbg.setColor(Color.WHITE);
			dbg.drawString(" Inventório 'I'", 10, 10);
		}
		
		if (gameOver){
			dbg.fillRect(0, 0, 320, 320);
			dbg.drawImage(Telagameover, 0, 0, 320, 320, null);
		}
		if(TelaAbertura)
			dbg.drawImage(Telainicial, 0, 0, 320, 320, null);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (dbImage != null)
			g.drawImage(dbImage, 0, 0, null);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PainelJogo painel = new PainelJogo();
	    frame.getContentPane().add(painel);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);

	}
}
