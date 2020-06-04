import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class Graph {
	private String name;
	private int nbArcs;
	private int nbSommets;
	private int numeroCourant = 1;
	private ListeArcs listeArcs = new ListeArcs();
	private ListeSommets listeSommets = new ListeSommets();	
	private Map<Sommet, ArrayList<Sommet>> listeAdjacence = new HashMap<>();
	private WelshPowell wp = new WelshPowell(this);
	private Dsatur ds = new Dsatur(this);
	private Greedy gr = new Greedy(this);
	

	public Graph(String name) {		
		this.name = name;
		/*this.nbArcs = nbArcs;
		this.nbSommets = nbSommets;
		this.listeArcs = listeArcs;
		this.listeSommets = listeSommets;*/
	}
	
	public void afficherTableau(String[] tab) {
		for (int i=0;i<tab.length;i++) {
			System.out.print(tab[i] + "," );
		}
	}
	
	public void lireFichier(String chemin) {
		try{
			   InputStream ips=new FileInputStream(chemin); 
			   InputStreamReader ipsr=new InputStreamReader(ips);
			   BufferedReader br=new BufferedReader(ipsr);
			   String ligne;
			   while ((ligne=br.readLine())!=null){
				   String[] ligneMots = ligne.toLowerCase().split(" ");
				   for (int i=0;i<ligneMots.length;i++) {
					   //afficherTableau(ligneMots);
					   if(ligneMots[i].compareTo("edge") == 0) {
						   nbSommets = Integer.parseInt(ligneMots[i+1]);
						   nbArcs = Integer.parseInt(ligneMots[i+2]);
					   }
					   if(ligneMots[i].compareTo("e") == 0) {
						   listeSommets.ajouterSommetFichier(ligneMots[i+1]);
						   listeSommets.ajouterSommetFichier(ligneMots[i+2]);
						   listeArcs.ajouterArcSommet(listeSommets.trouverSommet(ligneMots[i+1]),listeSommets.trouverSommet(ligneMots[i+2]),numeroCourant);
						   numeroCourant++;
					   }
						   
				   }
			   }
			   br.close(); 
			  }  
			  catch (Exception e){
			   System.out.println(e.toString());
			  }
		
	}


	public void construireListe() {
		Collections.sort(listeSommets.getListe(), new Comparator() { 			

			@Override
			public int compare(Object s1, Object s2) {
				//o1 = Integer.parseInt((Sommet) s1)
				if (Integer.parseInt(((Sommet) s1).getNom())>((Integer.parseInt(((Sommet) s2).getNom()))))
					return 10;
				return -10;
			}
			
		});
		for (int i=0;i<listeSommets.getListe().size();i++) {			
			listeAdjacence.put(listeSommets.getListe().get(i), new ArrayList<Sommet>());
			for (int j=0; j<listeArcs.getListe().size();j++) {
				if(listeArcs.getListe().get(j).getNomSommetDebut().compareTo(listeSommets.getListe().get(i).getNom())==0){
					if(!listeAdjacence.get(listeSommets.getListe().get(i)).contains(listeArcs.getListe().get(j).getSommetFin()))
					listeAdjacence.get(listeSommets.getListe().get(i)).add(listeArcs.getListe().get(j).getSommetFin()); //"(" + listeArcs.getListe().get(j).getNumero() + ")"
				}
				else if(listeArcs.getListe().get(j).getNomSommetFin().compareTo(listeSommets.getListe().get(i).getNom())==0) {
					if(!listeAdjacence.get(listeSommets.getListe().get(i)).contains(listeArcs.getListe().get(j).getSommetDebut()))
					listeAdjacence.get(listeSommets.getListe().get(i)).add(listeArcs.getListe().get(j).getSommetDebut());
				}
				
			}
		}
		
	}
	
	public void calculDegre() {
		for (int i=0;i<listeAdjacence.size();i++) {
			listeSommets.getListe().get(i).setNbDegre(listeAdjacence.get(listeSommets.getListe().get(i)).size());
		}
		
	}
	
	public void remettreCouleuraZero() {
		for (int i=0;i<listeSommets.getListe().size();i++) {
			listeSommets.getListe().get(i).setCouleur(0);
			listeSommets.getListe().get(i).setEstColoré(false);
		}
	}
	
	public void trierSommetsDecroissant() {				
		
		Collections.sort(listeSommets.getListe(), new Comparator() {
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
		
		Collections.sort(listeSommets.getListe(), new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {			
			if (((Sommet) o2).getNbDegre()<((Sommet)o1).getNbDegre())
				return 10;
			else if(((Sommet) o2).getNbDegre()==((Sommet)o1).getNbDegre())
				return 0;
			return -10;
		}
		});
	
	}
	
	public void trierSommetsAleatoire() {
		Random rand = new Random();
		ArrayList<Sommet> listeTampon = new ArrayList<Sommet>();		
		while(listeSommets.getListe().size()>0) {
			int aleatoire = rand.nextInt(listeSommets.getListe().size());
			listeTampon.add(listeSommets.getListe().get(aleatoire));
			listeSommets.getListe().remove(aleatoire);
		}
		listeSommets.getListe().addAll(listeTampon);				
			
		}
	
	
	
	
	public ListeSommets getListeSommets() {
		return listeSommets;
	}

	public void setListeSommets(ListeSommets listeSommets) {
		this.listeSommets = listeSommets;
	}

	public Map<Sommet, ArrayList<Sommet>> getListeAdjacence() {
		return listeAdjacence;
	}

	public void setListeAdjacence(Map<Sommet, ArrayList<Sommet>> listeAdjacence) {
		this.listeAdjacence = listeAdjacence;
	}

	
	public ListeArcs getListeArcs() {
		return listeArcs;
	}

	public void setListeArcs(ListeArcs listeArcs) {
		this.listeArcs = listeArcs;
	}
	
	public ArrayList<Sommet> getVoisins(Sommet s){
		ArrayList<Sommet> res = new ArrayList<Sommet>();
		for (int i=0; i<listeAdjacence.get(s).size();i++) {
			res.add(listeAdjacence.get(s).get(i));
		}
		return res;
	}

	public String afficherListe() {
		String res ="";
		for (int i=0;i<listeAdjacence.size();i++) {
			res+= listeSommets.getListe().get(i).getNom() + " Degré : " + listeSommets.getListe().get(i).getNbDegre() +" - ";
			for (int j=0;j<listeAdjacence.get(listeSommets.getListe().get(i)).size();j++) {
				res+=listeAdjacence.get(listeSommets.getListe().get(i)).get(j).afficherSommet()+ " ";
			}
			res+="\n";
		}
		return res;
	}
	
	
	public String afficherGraphe() {
		return "Nom du graphe : " + name + "\n" + "Nombre de sommets : " + nbSommets + "\nNombre d'arcs : " + 
				nbArcs + "\n--- \nListe des sommets : \n" + listeSommets.afficherListeSommets() + "--- \nListe des arcs : \n" + 
				listeArcs.afficherListeArcs() + "--- \nListe d'adjacence : \n" + afficherListe();
	}
	
	public String afficherNombreCouleursDecroissant() {
		trierSommetsDecroissant();
		long debutwp = System.currentTimeMillis();
		wp.coloration();
		long tempswp = System.currentTimeMillis() - debutwp;
		remettreCouleuraZero();	
		long debutds = System.currentTimeMillis();
		ds.coloration();
		long tempsds = System.currentTimeMillis() - debutds;
		remettreCouleuraZero();		
		long debutgr = System.currentTimeMillis();
		gr.coloration();
		long tempsgr = System.currentTimeMillis() - debutgr;
		return "Nombre de couleurs avec Welsh-Powell avec ordonnancement décroissant : " +  wp.getNbCouleur() + " en " + tempswp + " ms."+"\n---\nNombre de couleurs avec Dsatur avec ordonnancement décroissant : " + ds.getNbCouleur()
		+ " en " + tempsds + " ms."+ "\n---\nNombre de couleurs avec Greedy avec ordonnancement décroissant : " + gr.getNbCouleur() + " en " + tempsgr + " ms.";
	}
	
	public String afficherNombreCouleursCroissant() {
		trierSommetsCroissant();
		long debutwp = System.currentTimeMillis();
		wp.coloration();
		long tempswp = System.currentTimeMillis() - debutwp;
		remettreCouleuraZero();			
		long debutgr = System.currentTimeMillis();
		gr.coloration();
		long tempsgr = System.currentTimeMillis() - debutgr;
		return "---\nNombre de couleurs avec Welsh-Powell avec ordonnancement croissant : " +  wp.getNbCouleur() + " en " + tempswp + " ms."+ "\n---\nNombre de couleurs avec Greedy avec ordonnancement croissant : " + gr.getNbCouleur() + " en " + tempsgr + " ms.";
	}
	
	public String afficherNombreCouleursAleatoire() {
		trierSommetsAleatoire();
		//wp.trierSommets();
		long debutwp = System.currentTimeMillis();
		wp.coloration();
		long tempswp = System.currentTimeMillis() - debutwp;
		remettreCouleuraZero();		
		long debutgr = System.currentTimeMillis();
		gr.coloration();
		long tempsgr = System.currentTimeMillis() - debutgr;
		return "---\nNombre de couleurs avec Welsh-Powell avec ordonnancement aléatoire : " +  wp.getNbCouleur() + " en " + tempswp + " ms."+  "\n---\nNombre de couleurs avec Greedy avec ordonnancement aléatoire : " + gr.getNbCouleur() + " en " + tempsgr + " ms.";
	}
	
	public static void main(String[] args) {
		/*Sommet a = new Sommet("a");
		Sommet b = new Sommet("b");
		Sommet c = new Sommet("c");
		Sommet d = new Sommet("d");
		Sommet e = new Sommet("e");
		Sommet f = new Sommet("f");
		Sommet g = new Sommet("g");
		Arcs a1 = new Arcs(0,"a","c");
		Arcs a2 = new Arcs(1,"a","e");
		Arcs a3 = new Arcs(2,"a","g");
		Arcs a4 = new Arcs(3,"b","e");
		Arcs a5 = new Arcs(4,"b","f");
		Arcs a6 = new Arcs(5,"c","d");
		Arcs a7 = new Arcs(6,"c","e");
		Arcs a8 = new Arcs(7,"d","f");
		Arcs a9 = new Arcs(8,"d","g");
		ListeArcs listeA = new ListeArcs();
		listeA.ajouterArc(a1);
		listeA.ajouterArc(a2);
		listeA.ajouterArc(a3);
		listeA.ajouterArc(a4);
		listeA.ajouterArc(a5);
		listeA.ajouterArc(a6);
		listeA.ajouterArc(a7);
		listeA.ajouterArc(a8);
		listeA.ajouterArc(a9);
		ListeSommets listeS = new ListeSommets();
		listeS.ajouterSommet(a);
		listeS.ajouterSommet(b);
		listeS.ajouterSommet(c);
		listeS.ajouterSommet(d);
		listeS.ajouterSommet(e);
		listeS.ajouterSommet(f);
		listeS.ajouterSommet(g);*/
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez le nom de votre graphe : ");
		String nomGraphe = sc.nextLine();
		Graph grapheTest = new Graph(nomGraphe);
		grapheTest.lireFichier("fichiers/" + nomGraphe + ".col");		
		grapheTest.construireListe();
		grapheTest.calculDegre();
		System.out.println(grapheTest.afficherGraphe());
		System.out.println(grapheTest.afficherNombreCouleursDecroissant());
		System.out.println(grapheTest.afficherNombreCouleursCroissant());
		System.out.println(grapheTest.afficherNombreCouleursAleatoire());
		
		
	}
}
