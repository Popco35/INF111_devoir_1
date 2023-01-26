public class Jotto {

    public static void playJotto(int nbLettres, StatistiquesJeu stats){

        char[] mot;
        String motUtilisateur;
        mot = UtilitaireFonctions.generateMot(nbLettres);

        boolean finPartie = false;

        while(!finPartie){
            mot = UtilitaireFonctions.generateMot(nbLettres);

            boolean motDevine = false;

            while(!motDevine){
                int nbDoublons;
                motUtilisateur = UtilitaireFonctions.demanderMot(nbLettres);
                if(!UtilitaireEntreesSorties.utilisateurAnnule()) {
                    nbDoublons = UtilitaireFonctions.nbDoublons(mot, motUtilisateur);

                    if (nbDoublons == nbLettres) {
                        stats.nbParties += 1;
                        stats.nbEssaisTotal += 1;
                        stats.nbReussite += 1;
                        motDevine = true;
                        System.out.println("Bravo, vous avec devine le mot!");
                    } else {
                        stats.nbEssaisTotal += 1;
                        System.out.println("Vous avez " + nbDoublons + " lettres qui sont dans les deux mots");
                    }
                } else {
                    motDevine = true;
                    stats.nbParties +=1;
                }


            }

            String demande = UtilitaireEntreesSorties.lireString("Voulez jouer une autre partie avec le "+
                    "meme nombre de lettres? [y/n]: ",false);
            if(demande.equals("n")){
                finPartie = true;
            }
        }


    }
}
