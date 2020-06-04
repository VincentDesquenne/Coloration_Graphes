import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Dsatur {
	private Graph g;
	private ListeSommets listeSommetsDsatur = new ListeSommets();
	private int nbCouleur = 0;

	public Dsatur(Graph g) {
		this.g=g;
	}
	
	/*public void trierSommets() {		
		listeSommetsDsatur.getListe().addAll(g.getListeSommets().getListe());
		
		Collections.sort(listeSommetsDsatur.getListe(), new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {			
			if (((Sommet) o2).getNbDegre()>((Sommet)o1).getNbDegre())
				return 10;
			else if(((Sommet) o2).getNbDegre()==((Sommet)o1).getNbDegre())
				return 0;
			return -10;
		}
		});
	
	}*/
	
	public void coloration() {		
		listeSommetsDsatur.getListe().addAll(g.getListeSommets().getListe());
		ArrayList<Sommet> listeSommetsNonColories = new ArrayList<Sommet>();
		ArrayList<Integer> tabCouleur = new ArrayList<Integer>();
		listeSommetsNonColories.addAll(listeSommetsDsatur.getListe());
		Sommet s = listeSommetsNonColories.get(0);
		s.setCouleur(1);
		s.setEstColoré(true);
		listeSommetsNonColories.remove(0);
		Collections.sort(listeSommetsNonColories, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {			
				if(calculDSAT((Sommet)o2)-calculDSAT((Sommet)o1)!=0)
					return calculDSAT((Sommet)o2)-calculDSAT((Sommet)o1);
				else return ((Sommet) o2).getNbDegre()-((Sommet)o1).getNbDegre();
			}
			});
		while(listeSommetsNonColories.size()>0) {
			s=listeSommetsNonColories.get(0);
			ArrayList<Sommet> couleur = g.getVoisins(s);
			int ppc=plusPetiteCouleur(couleur);
			s.setCouleur(ppc);
			s.setEstColoré(true);
			if(!tabCouleur.contains(ppc))
				tabCouleur.add(ppc);
			listeSommetsNonColories.remove(0);
			Collections.sort(listeSommetsNonColories, new Comparator() {
				@Override
				public int compare(Object o1, Object o2) {			
					if(calculDSAT((Sommet)o2)-calculDSAT((Sommet)o1)!=0)
						return calculDSAT((Sommet)o2)-calculDSAT((Sommet)o1);
					else return ((Sommet) o2).getNbDegre()-((Sommet)o1).getNbDegre();
				}
				});
		}
		nbCouleur = tabCouleur.size();
		
		
		
		
		
		
		/*listeSommetsDsatur.getListe().get(0).setCouleur(nbCouleur);	
		listeSommetsDsatur.getListe().get(0).setEstColoré(true);
		nbCouleur++;
		int DSATactuel = 0;
		int compteurSommetColoré = 1;	
		ArrayList<Integer> tableauCouleur = new ArrayList<Integer>();
		tableauCouleur.add(nbCouleur);
		Sommet sommetActuel = listeSommetsDsatur.getListe().get(1);
		int indice = 0;
		while(compteurSommetColoré<listeSommetsDsatur.getListe().size()) {
			for (int i=0;i<listeSommetsDsatur.getListe().size();i++) {
				if(listeSommetsDsatur.getListe().get(i).getEstColoré()) {
					compteurSommetColoré++;
				}
				else {				
					if (calculDSAT(listeSommetsDsatur.getListe().get(i))>DSATactuel && !listeSommetsDsatur.getListe().get(i).getEstColoré()){ 
						//sommetActuel = listeSommetsDsatur.getListe().get(i);
						indice = i;
						DSATactuel = calculDSAT(listeSommetsDsatur.getListe().get(i));						
					}
					}
				
			}
			//sommetActuel.setCouleur(plusPetiteCouleur(sommetActuel));
			listeSommetsDsatur.getListe().get(indice).setCouleur(plusPetiteCouleur(listeSommetsDsatur.getListe().get(indice)));
			//sommetActuel.setEstColoré(true);
			listeSommetsDsatur.getListe().get(indice).setEstColoré(true);
			
			if(!tableauCouleur.contains(listeSommetsDsatur.getListe().get(indice).getCouleur()))
				tableauCouleur.add(listeSommetsDsatur.getListe().get(indice).getCouleur());
			
			
		}
		
		nbCouleur = tableauCouleur.size();*/

	}
	
	public void calculDSATSommets() {
		
	}
	
	public int calculDSAT(Sommet s) {
		ArrayList<Integer> tabCouleursVoisins = new ArrayList<Integer>();		
		ArrayList<Sommet> tabVoisins = new ArrayList<Sommet>();
		tabVoisins.addAll(g.getVoisins(s)); 
		boolean voisinColorié = false;
		for (int i=0;i<tabVoisins.size();i++) {
			if(tabVoisins.get(i).getEstColoré()) {
				voisinColorié = true;
				if(!tabCouleursVoisins.contains(tabVoisins.get(i).getCouleur()))
					tabCouleursVoisins.add(tabVoisins.get(i).getCouleur());				
			}
		}
		if(!voisinColorié) {
			return s.getNbDegre();
		}
		return (tabCouleursVoisins.size());
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
    
	/*public int plusPetiteCouleur(Sommet s) {
		int i=0;
		
			for(Sommet voisins : g.getVoisins(s)) {
				if(voisins.getCouleur()==i)
					i++;
			}
		
		return i;
//		for (int j=0;j<g.getVoisins(s).size();j++) {
//			if (g.getVoisins(s).get(j).getCouleur()==i)
//				j=0;
//				i++;
//		}
//		return i;
	}*/
	
	public int nbCol(ListeSommets ls) {
		ArrayList<Integer> couleursActuelles = new ArrayList<Integer>();
		for (int i=0;i<ls.getListe().size();i++) {
			if(!couleursActuelles.contains(ls.getListe().get(i).getCouleur())){
				couleursActuelles.add(ls.getListe().get(i).getCouleur());
			}
		}
		return couleursActuelles.size();
	}

	public int getNbCouleur() {
		return nbCouleur;
	}

	public void setNbCouleur(int nbCouleur) {
		this.nbCouleur = nbCouleur;
	}
	
	
}
