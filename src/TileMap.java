import java.awt.Graphics2D;
import java.awt.Image;
import java.io.*;

public class TileMap {
    
    public Image TileSet = null;
    
    int MapX;
    int MapY;
    int NTileX,NTileY;
    int Largura = 60;
    int Altura = 50;
    int TilePLinhaTileset;
    
    int [][]mapa;
	int [][]mapa2;            		
            		
    public TileMap(Image tileset,int tilestelaX,int tilestelaY){
        NTileX = tilestelaX;
        NTileY = tilestelaY;
        TileSet = tileset;
        MapX = 0;
        MapY = 0;
        TilePLinhaTileset = TileSet.getWidth(null) >>4;
    }
    
    public void AbreMapa(String nomemapa){
            	
	try{
    		InputStream In = getClass().getResourceAsStream(nomemapa);
    		
    		DataInputStream data = new DataInputStream(In); 
    	
    		int Versao = data.readInt(); // l� Versao
        	Largura = ReadCInt(data);    // l� Largura
        	Altura = ReadCInt(data);	// l� Largura
           	
        	int ltilex =  ReadCInt(data);// l� Larg Tile
        	int ltiley =  ReadCInt(data);// l� Altura Tile
           	
        	byte nome[] = new byte[32]; 

        	data.read(nome,0,32);       // l� Nome Tilemap
        	data.read(nome,0,32); 
        	
        	int numLayers =  ReadCInt(data);// l� numero de Layers
        	int numTiles =  ReadCInt(data);// l� numero de Tiles
        	
            int BytesPorTiles =  ReadCInt(data); // l� numero de bytes por tile;
           	
            int vago1 =  ReadCInt(data); // l� vago;
            int vago2 =  ReadCInt(data); // l� vago;            
            
        	mapa = new int[Altura][Largura];
        	mapa2 = new int[Altura][Largura];
        	
        	if(BytesPorTiles==1){
                
        	    for(int j = 0; j < Altura; j++){            
                    for(int i = 0; i < Largura; i++){
                    	int dado;
                    	
                    	int b1 = data.readByte();
                    	int b2 = data.readByte();            	
                    	
                    	dado = ((int)b1&0x00ff)|(((int)b2&0x00ff)<<8);            	
                    	
                    	mapa[j][i] = dado;      	
                    }
                }
        	    if(numLayers==2){
	        	    for(int j = 0; j < Altura; j++){            
	                    for(int i = 0; i < Largura; i++){
	                    	int dado;
	                    	
	                    	int b1 = data.readByte();
	                    	int b2 = data.readByte();            	
	                    	
	                    	dado = ((int)b1&0x00ff)|(((int)b2&0x00ff)<<8);            	
	                    	
	                    	mapa2[j][i] = dado;           	
	                    }
	                }
        	    }
                
        	}else if(BytesPorTiles==2){
        	    for(int j = 0; j < Altura; j++){            
                    for(int i = 0; i < Largura; i++){
                    	int dado;
                    	
                    	
                    	int b1 = data.readByte();
                    	int b2 = data.readByte(); 
                    	int b3 = data.readByte(); 
                    	int b4 = data.readByte();                     	
                    	
                    	dado = ((int)b1&0x00ff)|(((int)b2&0x00ff)<<8)|(((int)b3&0x00ff)<<16)|(((int)b4&0x00ff)<<24);           	
                    	
                    	mapa[j][i] = dado;           	
                    }
                }
        	    if(numLayers==2){
	        	    for(int j = 0; j < Altura; j++){            
	                    for(int i = 0; i < Largura; i++){
	                    	int dado;
	                    	
	                    	int b1 = data.readByte();
	                    	int b2 = data.readByte(); 
	                    	int b3 = data.readByte(); 
	                    	int b4 = data.readByte();                     	
	                    	
	                    	dado = ((int)b1&0x00ff)|(((int)b2&0x00ff)<<8)|(((int)b3&0x00ff)<<16)|(((int)b4&0x00ff)<<24);            	
	                    	
	                    	mapa2[j][i] = dado;           	
	                    }
	                } 
        	    }
        	}else{
        	    for(int j = 0; j < Altura; j++){            
                    for(int i = 0; i < Largura; i++){
                    	int dado;
                    	
                    	int b1 = data.readByte(); 
                    	
                    	dado = ((int)b1&0x00ff);            	
                    	
                    	mapa[j][i] = dado;            	
                    }
                }
        	    if(numLayers==2){
	        	    for(int j = 0; j < Altura; j++){            
	                    for(int i = 0; i < Largura; i++){
	                    	int dado;
	                    	
	                    	int b1 = data.readByte(); 
	                    	
	                    	dado = ((int)b1&0x00ff);            	
	                    	
	                    	mapa2[j][i] = dado;	            	        	
	                    }
	                } 
        	    }
        	}
  		

        
        
       	data.close();
    		
    	In.close();
    		
	    }//fim try
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	      System.out.println(e.getMessage()+ "  abreaMapaPau!!!");
	    }    		
		    	
    	
    }
   
    
    public void DesenhaSe(Graphics2D dbg){
    	int offx = MapX&0x0f; 
    	int offy = MapY&0x0f;
    	int somax,somay;
    	if(offx>0){
    		somax = 1;
    	}else{
    		somax = 0;
    	}
    	if(offy>0){
    		somay = 1;
    	}else{
    		somay = 0;
    	}
        for(int j = 0; j < NTileY + somay; j++){            
            for(int i = 0; i < NTileX + somax; i++){
            	
                int tilex = (mapa[j+(MapY>>4)][i+(MapX>>4)]%TilePLinhaTileset)<<4;
                int tiley = (mapa[j+(MapY>>4)][i+(MapX>>4)]/TilePLinhaTileset)<<4;
                dbg.drawImage(TileSet,(i<<4)-offx,(j<<4)-offy,(i<<4)+16-offx,(j<<4)+16-offy,tilex,tiley,tilex+16,tiley+16,null);
                
            }
        }
        for(int j = 0; j < NTileY + somay; j++){            
            for(int i = 0; i < NTileX + somax; i++){
            	
                int tilex = (mapa2[j+(MapY>>4)][i+(MapX>>4)]%TilePLinhaTileset)<<4;
                int tiley = (mapa2[j+(MapY>>4)][i+(MapX>>4)]/TilePLinhaTileset)<<4;
                dbg.drawImage(TileSet,(i<<4)-offx,(j<<4)-offy,(i<<4)+16-offx,(j<<4)+16-offy,tilex,tiley,tilex+16,tiley+16,null);
                
            }
        }        
    }
    
    public void Posiciona(int x,int y){
    	int X = x>>4;
    	int Y = y>>4;
    	
        if(X < 0){
            MapX = 0;
        }else if(X >= (Largura-NTileX)){
            MapX = ((Largura-NTileX))<<4;
        }else{
            MapX = x;
        }
        
        if(Y < 0){
            MapY = 0;
        }else if(Y >= (Altura-NTileY)){
            MapY = ((Altura-NTileY))<<4;
        }else{
            MapY = y;
        }        

    }
    
    public int ReadCInt(DataInputStream data) throws IOException{
        int dado;
    	int b1 = data.readByte();
    	int b2 = data.readByte(); 
    	int b3 = data.readByte(); 
    	int b4 = data.readByte();                     	
    	
    	return dado = ((int)b1&0x00ff)|(((int)b2&0x00ff)<<8)|(((int)b3&0x00ff)<<16)|(((int)b4&0x00ff)<<24);            	    	
    }
}
