import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class WelshPowell {
	private Graph g;
	private ListeSommets listeSommetsWelsh = new ListeSommets();
	//private ArrayList<Sommet> listeSommetsWelsh1 = new ArrayList<Sommet>();
	//private Map<String, ArrayList<String>> listeAdjacenceWelsh = new HashMap<>();
	private int nbCouleur;
	
	public WelshPowell(Graph g) {
		this.g=g;		
		
	}
	
	/*public void trierSommets() {
		//ListeSommets listeSommetsWelsh = new ListeSommets();
		listeSommetsWelsh.getListe().addAll(g.getListeSommets().getListe());
		
		Collections.sort(listeSommetsWelsh.getListe(), new Comparator() {
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
	
	public ListeSommets getListeSommetsWelsh() {
		return listeSommetsWelsh;
	}

	public void setListeSommetsWelsh(ListeSommets listeSommetsWelsh) {
		this.listeSommetsWelsh = listeSommetsWelsh;
	}
	
	public String afficherNbCouleur() {
		return nbCouleur+" ";
	}

	public void coloration() {
		listeSommetsWelsh.getListe().addAll(g.getListeSommets().getListe());
		nbCouleur = 1;
		while (!listeSommetsWelsh.getListe().isEmpty()) { 		
			
			   int index = 0; 			
			   
			   while (index < listeSommetsWelsh.getListe().size()) { 
				   int indexVoisin = 0;
				   Sommet x = listeSommetsWelsh.getListe().get(index); 
				   ArrayList<Sommet> tabVoisins = new ArrayList<Sommet>();
				   tabVoisins.addAll(g.getVoisins(x)); 
				   boolean conflit = false; 			 
				   while (indexVoisin<tabVoisins.size() && !conflit) { 
					   Sommet y = tabVoisins.get(indexVoisin);			     
			 
					   if (y.getCouleur()==nbCouleur){ 			      
						   conflit = true; 
			      
					   } 
					   indexVoisin++;
				   } 
			 
				   if (!conflit) { 
					   x.setCouleur(nbCouleur); 
					   x.setEstColoré(true);
					   listeSommetsWelsh.getListe().remove(index); 
				   } else { 
					   index++; 
				   } 
			   	} 			 
			   
			   	nbCouleur++; 
		} 
		/*nbCouleur = 1;
		//Sommet x = new Sommet();
		//Sommet y = new Sommet();		
		while(!listeSommetsWelsh.getListe().isEmpty()) {
			int index = 0;
			Sommet x = listeSommetsWelsh.getListe().get(0);
			x.setCouleur(nbCouleur);
			listeSommetsWelsh.getListe().remove(x);
			Sommet y = listeSommetsWelsh.getListe().get(0);
			while(index<listeSommetsWelsh.getListe().size()) {
				boolean conflit = false;
				ArrayList<Sommet> tabVoisin = g.getVoisins(y);
				for (int i=0;i<tabVoisin.size();i++) {						
						if(tabVoisin.get(i).getCouleur()==nbCouleur)
							conflit = true;
						}
				
			
				
				if(!conflit) {
					y.setCouleur(nbCouleur);
					listeSommetsWelsh.getListe().remove(y);
				}
				else {
					index++;
				}
				//y=listeSommetsWelsh.getListe().get(0);
				}
			nbCouleur++;
				
			}*/
		}

	public int getNbCouleur() {
		return nbCouleur - 1;
	}

	public void setNbCouleur(int nbCouleur) {
		this.nbCouleur = nbCouleur;
	}
	

}
