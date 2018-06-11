
public class eventoIten extends Evento {	
	
	public eventoIten() {}
	
	public eventoIten(String linha) {
		
		String strs[] = linha.split(";");
		codigo = Integer.parseInt(strs[0]);
		tipo = Integer.parseInt(strs[1]);
		Pvar = Integer.parseInt(strs[2]);
		Pop = Integer.parseInt(strs[3]);
		Pvalor = Integer.parseInt(strs[4]);
		x = Integer.parseInt(strs[5]);
		y = Integer.parseInt(strs[6]);
		item = Integer.parseInt(strs[7]);
		Avar = Integer.parseInt(strs[9]);
		Aop = Integer.parseInt(strs[10]);
		Avalor = Integer.parseInt(strs[11]);
		mapaAtual = Integer.parseInt(strs[12]);
		mapaDestino = Integer.parseInt(strs[13]);
	}	
}
