import java.util.ArrayList;

public class ListeArcs {
	private ArrayList<Arcs> liste = new ArrayList<Arcs>();
	
	public void ajouterArc(Arcs a) {
		liste.add(a);
	}
	
	public void ajouterArcNonExistant(String nomDeb, String nomFin, int numeroArc) {
		Sommet s1 = new Sommet(nomDeb);
		Sommet s2 = new Sommet(nomFin);
		Arcs a = new Arcs(numeroArc, s1.getNom(), s2.getNom());
		liste.add(a);
	}
	
	public void ajouterArcFichier(String nomDeb, String nomFin, int numeroArc) {			
		Arcs a = new Arcs(numeroArc, nomDeb, nomFin);
		liste.add(a);
		
	}
	
	public void ajouterArcSommet(Sommet sommetDebut, Sommet sommetFin, int numeroArc) {
		Arcs a = new Arcs(numeroArc, sommetDebut, sommetFin);		
		liste.add(a);
	}
	
	public boolean verifArc(String nomDeb, String nomFin) {
		for (int i=0;i<liste.size();i++) {
			if(liste.get(i).getNomSommetDebut().compareTo(nomFin) == 0) {
				if(liste.get(i).getNomSommetFin().compareTo(nomDeb) == 0)
					return true;
			}
			
		}
		return false;
	
	}
	
	public String afficherListeArcs() {
		String res = "";
		for (int i=0; i<liste.size(); i++) {
			res +=liste.get(i).afficherArc() + "\n";
		}
		return res;
	}
	
	public ArrayList<Arcs> getListe(){
		return liste;
	}
	
	
}
