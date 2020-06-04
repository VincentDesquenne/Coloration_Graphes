import java.util.ArrayList;



public class ListeSommets {
	private ArrayList<Sommet> liste = new ArrayList<Sommet>();
	
	
	public void ajouterSommet(Sommet s) {
		liste.add(s);
	}
	
	public void ajouterSommetFichier(String nom) {		
		if(!verifPresence(nom)) {
			Sommet s = new Sommet(nom);
			liste.add(s);
		}
	}
	
	public Sommet trouverSommet(String nom) {
		for (int i=0;i<liste.size();i++) {
			if (liste.get(i).getNom().compareTo(nom)==0)
				return liste.get(i);
		}
		return null;
	}
	
	public void setListe(ArrayList<Sommet> liste) {
		this.liste = liste;
	}

	public boolean verifPresence(String nom) {		
		for (int i=0;i<liste.size();i++) {
			if(liste.get(i).getNom().compareTo(nom) == 0)
				return true;
		}
		return false;
	}
	
	public String afficherListeSommets() {
		String res = "";
		for (int i=0;i<liste.size();i++) {
			res+= liste.get(i).afficherSommet() + "\n";
		}
		return res;
	}

	public ArrayList<Sommet> getListe() {
		return liste;
	}
	
}
