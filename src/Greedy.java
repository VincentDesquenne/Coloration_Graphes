import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Greedy {
	private Graph g;
	private ListeSommets listeSommetsGreedy = new ListeSommets();
	private int nbCouleur = 0;

	public Greedy(Graph g) {
		this.g=g;
	}
	
	/*public void trierSommetsDecroissant() {		
		listeSommetsGreedy.getListe().addAll(g.getListeSommets().getListe());
		
		Collections.sort(listeSommetsGreedy.getListe(), new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {			
			if (((Sommet) o2).getNbDegre()>((Sommet)o1).getNbDegre())
				return 10;
			else if(((Sommet) o2).getNbDegre()==((Sommet)o1).getNbDegre())
				return 0;
			return -10;
		}
		});
	
	}
	
	public void trierSommetsCroissant() {		
		listeSommetsGreedy.getListe().addAll(g.getListeSommets().getListe());
		
		Collections.sort(listeSommetsGreedy.getListe(), new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {			
			if (((Sommet) o2).getNbDegre()<((Sommet)o1).getNbDegre())
				return 10;
			else if(((Sommet) o2).getNbDegre()==((Sommet)o1).getNbDegre())
				return 0;
			return -10;
		}
		});
	
	}*/
	
	public void coloration() {
		listeSommetsGreedy.getListe().addAll(g.getListeSommets().getListe());
		ArrayList<Integer> tabCouleur = new ArrayList<Integer>();
		while(!listeSommetsGreedy.getListe().isEmpty()) {
			Sommet s = listeSommetsGreedy.getListe().get(0);
			ArrayList<Sommet> tabVoisins = g.getVoisins(s);
			int ppc = plusPetiteCouleur(tabVoisins);
			s.setCouleur(ppc);
			s.setEstColoré(true);
			if(!tabCouleur.contains(ppc))
				tabCouleur.add(ppc);
			listeSommetsGreedy.getListe().remove(0);			
		}
		nbCouleur = tabCouleur.size();
	}
	
	public boolean verifCouleur(ArrayList<Sommet> tab, int x){
        Iterator<Sommet> it=tab.iterator();
        while(it.hasNext())
            if(it.next().getCouleur()==x)
                return false;
        return true;
    }
	
    public int plusPetiteCouleur(ArrayList<Sommet> voisins){
        int i=1;
        while(true){
            if(verifCouleur(voisins,i)){
                return i;
            }
            i++;
        }
    }

	public int getNbCouleur() {
		return nbCouleur;
	}

	public void setNbCouleur(int nbCouleur) {
		this.nbCouleur = nbCouleur;
	}
    

}
