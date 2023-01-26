public class Main {
    public static void main(String[] args) {
        //init les stats du jeu et initializer ses champs a 0
        StatistiquesJeu stats = new StatistiquesJeu();
        stats.nbReussite = 0;
        stats.nbParties = 0;
        stats.nbEssaisTotal = 0;

        int nbLettres;

        boolean finPartie = false;
        while(!finPartie){
            nbLettres = UtilitaireEntreesSorties.lireInt("Entrez un nombre entier entre 3 et 26: ",3,26);

            if(!UtilitaireEntreesSorties.utilisateurAnnule()){
                Jotto.playJotto(nbLettres, stats);
            } else{
                finPartie = true;
            }

        }




        UtilitaireEntreesSorties.afficherStats(stats);
        System.out.println("\nMerci d'avoir jouer a Jotto!\n");

    }
}