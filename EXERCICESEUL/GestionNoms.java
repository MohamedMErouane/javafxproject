import java.util.Set;
import java.util.HashSet;

public class GestionNoms {
Set<String>Noms=new HashSet<>();
public void ajouterNom(String nom){
    if(Noms.contains(nom)){
        System.out.println("le nom est deja exsiste");
    }else{
        Noms.add(nom);
        System.out.println("le nom est ajouter");
    }

}
public void SupprimerNoms(String nom){
    if(Noms.contains(nom)){
      Noms.remove(nom);
      System.out.println("le nom est Supprimer");
    }else{
     System.out.println("le nom n'est pas exsiste");
    }
}
public void AfficherNoms(){
    for(String nom :Noms) 
    System.out.println(nom);
}
    public static void main(String []args ){
        GestionNoms obj =new GestionNoms();
        obj.ajouterNom("Hassan");
        obj.ajouterNom("mohamed");
        obj.ajouterNom("najat");
        obj.ajouterNom("Amina");
        obj.ajouterNom("hanane");
        obj.ajouterNom("mbarka");
        obj.ajouterNom("adil");
        obj.AfficherNoms();
        obj.SupprimerNoms("mohamed");
        obj.AfficherNoms();
    }
}