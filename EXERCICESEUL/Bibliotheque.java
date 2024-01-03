import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;

class Auteur{
     String nom;
     List<Livre>livreEcrit;
    public Auteur(String nom){
        this.nom=nom;
        this.livreEcrit=new ArrayList<>();
    }
}
class Livre{
     String titre;
     Auteur auteur;
     int unique;
    public Livre(String titre, Auteur auteur, int identifiant) {
        this.titre = titre;
        this.auteur = auteur;
        this.unique = identifiant;
        auteur.livreEcrit.add(this);
    }
}
class Emprunt{
    Utilisateur client ;
     Date dateEmprunt;
     Date dateRetour;
     Livre livreEmprunt;
     public Emprunt(Utilisateur utilisateur, Livre livre, Date dateEmprunt, Date dateRetour) {
        this.client  = utilisateur;
        this.livreEmprunt = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }
}
class Utilisateur {
    String nom;

    public Utilisateur(String nom) {
        this.nom = nom;
    }
}

public class Bibliotheque {
    Map <Integer,Livre> LivreMap=new HashMap<>();
    Set<Auteur>SetAuteur=new HashSet<>();
    List<Emprunt>emprunts;
    public Bibliotheque() {
        this.LivreMap = new HashMap<>();
        this.SetAuteur = new HashSet<>();
        this.emprunts = new ArrayList<>();
    }
    public void ajouter(String titre, String nomauteur, int identifiant){
        Auteur auteur =new Auteur(nomauteur);
        Livre livre =new Livre(titre, auteur, identifiant);
        LivreMap.put(identifiant,livre);
        SetAuteur.add(auteur);
    }
    public void SupprimerLivre(int identifiantLivre){
        Livre livreSupp=LivreMap.get(identifiantLivre);
        if(livreSupp!=null){
            LivreMap.remove(identifiantLivre);
           livreSupp.auteur.livreEcrit.remove(livreSupp);
        }
        
    }
    public Livre rechercherLivre(int identifiantLivre) {
        Livre livre =LivreMap.get(identifiantLivre);
        return livre;
    }
    public void EmpruntSave(Utilisateur client, Livre livre, Date dateEmprunt, Date dateRetour){
        Emprunt emprunt=new Emprunt(client, livre, dateRetour, dateRetour);
        emprunts.add(emprunt);
        
    }
public List<Livre> RechercheLiverAuteur(String Nom){
    List <Livre> LivreparAuteur =new ArrayList<>();
    for(Auteur auteur : SetAuteur ){
        if(auteur.nom.equals(Nom)){
      LivreparAuteur.addAll(auteur.livreEcrit);
        }
    }
    return LivreparAuteur;
}
public List<Emprunt> EmpruntRetard(){
    List<Emprunt>emprunt =new ArrayList<>();
    Date currentdate= new Date();
    for(Emprunt e :emprunts){
        if(e.dateRetour.before(currentdate)){
           emprunt.add(e);
        }
    }
    return emprunt;
}
public class Main {
    public static void main(String[] args) {
        Auteur auteur1 = new Auteur("Victor Hugo");
        Auteur auteur2 = new Auteur("Jane Austen");

        Livre livre1 = new Livre("Les Misérables", auteur1,1862);
        Livre livre2 = new Livre("Orgueil et Préjugés", auteur2,1813);
        Livre livre3 = new Livre("Notre-Dame de Paris", auteur1,1831);

        Bibliotheque bibliotheque = new Bibliotheque();
        bibliotheque.ajouter(livre1);
        bibliotheque.ajouter(livre2);
        bibliotheque.ajouter(livre3);

        System.out.println("Livres de Victor Hugo:");
        List<Livre> livresVictorHugo = bibliotheque.RechercheLiverAuteur("Victor Hugo");
        for (Livre livre : livresVictorHugo) {
            System.out.println(livre.titre + " (" + livre.unique + ")");
        }

        System.out.println("\nRecherche d'un livre:");
        Livre livreRecherche = bibliotheque.RechercheLiverAuteur("Orgueil et Préjugés");
        if (livreRecherche != null) {
            System.out.println("Titre: " + livreRecherche.titre + ", Auteur: " + livreRecherche.auteur.nom + ", Année: " + livreRecherche.unique);
        }

        System.out.println("\nSuppression d'un livre:");
        bibliotheque.SupprimerLivre(1862);
        for (Livre livre : bibliotheque.LivreMap) {
            System.out.println(livre.titre + " (" + livre.unique + ")");
        }
    }
}
}