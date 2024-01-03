import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class exercice2 {
    private Map<String,String>Telephone;
    public exercice2(){
        this.Telephone=new HashMap<>();
    }
    public void ajouterEntree(String nom ,String numero){
        if(Telephone.containsKey(nom)){
            System.out.println("le nom est deja existe");
        }else{
            Telephone.put(nom, numero);
             System.out.println("le nom est ajouter avec succes");
        }
    }
    public void supprimerEntree(String nom){
        if(Telephone.containsKey(nom)){
            Telephone.remove(nom);
        }else{
            System.out.println("le nom n'est pas existe");
        }
    }
    public String rechercherNumero(String nom){
        if(Telephone.containsKey(nom)){
            return Telephone.get(nom);
        }else{
         return"le nom n'est pas existe";
        }
    }
    public void afficherRepertoire(){
        for(Map.Entry<String,String>e : Telephone.entrySet()){
            System.out.println("Nom : " + e.getKey() + ", Numéro : " + e.getValue());
        }
    }
    public static void main(String[]args){
        exercice2 Telephone =new exercice2();
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu :");
            System.out.println("1. Ajouter une entrée");
            System.out.println("2. Supprimer une entrée");
            System.out.println("3. Rechercher un numéro");
            System.out.println("4. Afficher le répertoire");
            System.out.println("5. Quitter");

            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); 
            switch (choix) { 
        case 1:
            System.out.print("Nom : ");
            String nomAjout = scanner.nextLine();
            System.out.print("Numéro : ");
            String numeroAjout = scanner.nextLine();
            Telephone.ajouterEntree(nomAjout, numeroAjout);
            break;
        case 2:
            System.out.print("Nom : ");
            String nomSuppression = scanner.nextLine();
            Telephone.supprimerEntree(nomSuppression);
            break;
        case 3:
            System.out.print("Nom : ");
            String nomRecherche = scanner.nextLine();
            String resultatRecherche = Telephone.rechercherNumero(nomRecherche);
            System.out.println("Résultat : " + resultatRecherche);
            break;
        case 4:
            Telephone.afficherRepertoire();
            break;
        case 5:
            System.out.println("Programme terminé.");
            System.exit(0);
            break;
        default:
            System.out.println("Option non valide. Veuillez choisir une option valide.");

    }
}
}
}
