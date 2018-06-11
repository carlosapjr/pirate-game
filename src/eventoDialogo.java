
public class eventoDialogo extends Evento {	
	
	public eventoDialogo() {}
	
	public eventoDialogo(String linha) {
		
		String strs[] = linha.split(";");		
		codigo = Integer.parseInt(strs[0]);
		tipo = Integer.parseInt(strs[1]);
		Pvar = Integer.parseInt(strs[2]);
		Pop = Integer.parseInt(strs[3]);
		Pvalor = Integer.parseInt(strs[4]);
		x = Integer.parseInt(strs[5]);
		y = Integer.parseInt(strs[6]);
		dialogo = strs[7];
		dialogo2 = strs[8];
		Avar = Integer.parseInt(strs[9]);
		Aop = Integer.parseInt(strs[10]);
		Avalor = Integer.parseInt(strs[11]);
		mapaAtual = Integer.parseInt(strs[12]);
	}	
}
