
public class Arcs {
	private int numero;
	private String nomSommetDebut;
	private String nomSommetFin;
	private Sommet sommetDebut;
	private Sommet sommetFin;

	
	public Arcs(int numero, String nomDeb, String nomFin) {
		this.numero = numero;

		nomSommetDebut = nomDeb;
		nomSommetFin = nomFin;
		
	}
	
	public Arcs(int numero, Sommet sommetDebut, Sommet sommetFin) {
		this.numero = numero;
		this.sommetDebut = sommetDebut;
		this.sommetFin = sommetFin;
		this.nomSommetDebut = sommetDebut.getNom();
		this.nomSommetFin = sommetFin.getNom();
	}
	
	public String afficherArc() {
		return numero + " (" + nomSommetDebut + "," + nomSommetFin + ")";
	}

	public int getNumero() {
		return numero;
	}
	

	public String getNomSommetDebut() {
		return nomSommetDebut;
	}

	public String getNomSommetFin() {
		return nomSommetFin;
	}

	public Sommet getSommetDebut() {
		return sommetDebut;
	}

	public void setSommetDebut(Sommet sommetDebut) {
		this.sommetDebut = sommetDebut;
	}

	public Sommet getSommetFin() {
		return sommetFin;
	}

	public void setSommetFin(Sommet sommetFin) {
		this.sommetFin = sommetFin;
	}
	
	
}
