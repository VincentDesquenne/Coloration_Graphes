
public class Sommet {
	private int numero;
	private String nom;
	private int nbDegre;
	private int couleur;
	private boolean estColoré;
	
	public int getCouleur() {
		return couleur;
	}

	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}

	public Sommet(String nom){
		this.nom = nom;
		
	}
	
	public Sommet() {
	
	}
	
	public String afficherSommet() {
		return nom;
	}

	

	public String getNom() {
		return nom;
	}

	public int getNbDegre() {
		return nbDegre;
	}

	public void setNbDegre(int nbDegre) {
		this.nbDegre = nbDegre;
	}
	
	public boolean getEstColoré() {
		return estColoré;
		
	}
	
	public void setEstColoré(boolean b) {
		estColoré = b;
	}
	

}
