package Reparation;

import java.util.*;
import App.App;

public class Repa_view {
    private static Repa_ctrl repController;
    private static Scanner scanner = new Scanner(System.in);

    public Repa_view() {
        String host = "jdbc:mysql://localhost:3306/parking";
        String user = "root";
        String password = "";

        repController = new Repa_ctrl(host, user, password);
    }

    public static int userChoice() {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("\n---Menu---");
            System.out.println("1- Ajouter une réparation");
            System.out.println("2- Modifier les informations d'une réparation");
            System.out.println("3- Supprimer une réparation");
            System.out.println("4- Rechercher des réparations par problème");
            System.out.println("5- Corbeille");
            System.out.println("6- Retour");
            System.out.println("7- Quitter");
            System.out.print("Votre choix : ");
            try {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 7) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 7.");
                    scanner.nextLine();
                } else {
                    isValidInput = true;
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }
        }
        return choice;
    }

    public static void updateReparations(Repa_mod rep) {
        System.out.println("Modification d'une réparation");

        try {
            String problem;
            while (true) {
                System.out.print("Entrer le problème de la réparation (valeur actuelle = " + rep.getProblem() + ") : ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    problem = rep.getProblem();
                    break;
                } else {
                    problem = input;
                }

                if (repController.existsByProblem(problem)) {
                    System.out.println("Ce problème de réparation existe déjà. Veuillez entrer un autre problème.");
                    continue;
                }

                boolean isValid = true;
                for (char c : problem.toCharArray()) {
                    if (Character.isDigit(c)) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer un problème valide (lettres, &, -, ', et espaces uniquement, sans chiffres).");
                }
            }

            String solution;
            while (true) {
                System.out.print("Entrer la solution de la réparation (valeur actuelle = " + rep.getSolution()
                        + ") : ");
                solution = scanner.nextLine().trim();

                if (solution.isEmpty()) {
                    solution = rep.getSolution();
                    break;
                }

                if (repController.existsBySolution(solution)) {
                    System.out.println(
                            "Cette solution de réparation existe déjà. Veuillez entrer une autre description.");
                } else if (solution.isEmpty()) {
                    System.out.println("La solution ne peut pas être vide.");
                } else {
                    break;
                }
            }

            double price;
            while (true) {
                System.out.print(
                        "Entrer le prix de la réparation (en Gourde) (valeur actuelle = " + rep.getPrice() + ") : ");
                String priceInput = scanner.nextLine().trim();

                if (priceInput.isEmpty()) {
                    price = rep.getPrice();
                    break;
                }

                try {
                    price = Double.parseDouble(priceInput);
                    if (price >= 2861 && price <= 715267) {
                        break;
                    } else {
                        System.out.println("Veuillez entrer un prix valide (entre 2861 et 715267).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre entier ou décimal.");
                }
            }

            String add_date = java.time.LocalDate.now().toString();

            rep.setProblem(problem);
            rep.setSolution(solution);
            rep.setPrice(price);
            rep.setAdd_date(add_date);

            repController.updateReparations(rep);
            System.out.println("Réparation modifiée avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally {
            scanner.nextLine();
        }
    }

    public static void addReparations() {
        System.out.println("Ajouter une réparation");
        try {
            String problem;
            boolean isValid;

            while (true) {
                System.out.print("Entrer le problème : ");
                problem = scanner.next() + scanner.nextLine();
                isValid = true;

                if (repController.existsByProblem(problem)) {
                    System.out.println("Ce probléme existe déjà. Veuillez entrer un autre problème.");
                    continue;
                }

                for (char c : problem.toCharArray()) {
                    if (Character.isDigit(c)) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer un problème valide (lettres, &, -, ', et espaces uniquement, sans chiffres).");
                }
            }

            String solution;
            while (true) {
                System.out.print("Entrer la solution de la réparation : ");
                solution = scanner.nextLine();

                if (repController.existsBySolution(solution)) {
                    System.out.println(
                            "Cette solution de réparation existe déjà. Veuillez entrer une autre description.");
                    continue;
                }

                if (solution.isEmpty()) {
                    System.out.println("La solution ne peut pas être vide.");
                } else {
                    break;
                }
            }

            double price = 0;
            while (true) {
                System.out.print("Entrer le prix de la réparation (en Gourde) : ");
                try {
                    price = scanner.nextDouble();
                    if (price >= 2861 && price <= 715267) {
                        break;
                    } else {
                        System.out.println("Veuillez entrer un prix valide (entre 2861 et 715267).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Veuillez entrer un nombre entier ou décimal.");
                    scanner.nextLine();
                }
            }

            String add_date = java.time.LocalDate.now().toString();

            Repa_mod rep = new Repa_mod(0, problem, solution, price, add_date);
            repController.addReparations(rep);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally {
            scanner.nextLine();
        }
    }

    public static void listReparationsMod() {

        List<Repa_mod> repa = repController.getAllReparations();
        if (repa.isEmpty()) {
            System.out.println("Aucune réparation trouvé.");
            return;
        }

        System.out.println("---Liste des réparations---");
        for (int i = 0; i < repa.size(); i++) {
            Repa_mod rp = repa.get(i);
            System.out.println((i + 1) + "- Problème :" + rp.getProblem() + "\t Solution :" + rp.getSolution()
                    + "\t Prix :" + rp.getPrice());
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner une réparation en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= repa.size()) {
                    displayReparationsDetails(repa.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Repa_view.main(null);
                    return;
                } else {
                    System.out.println("Veuillez entrer un nombre valide.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }
        }
    }

    public static void displayReparationsDetails(Repa_mod rep) {
        System.out.println("\nDétails de la réparation sélectionné :");
        System.out.println("Problème : " + rep.getProblem());
        System.out.println("Solution : " + rep.getSolution());
        System.out.println("Prix : " + rep.getPrice());
        System.out.println("Date d'ajout : " + rep.getAdd_date());
        ModQuestionReparations(rep);
    }

    public static void ModQuestionReparations(Repa_mod rep) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Est-ce bien la réparation que vous voulez modifier ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                scanner.nextLine();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listReparationsMod();
                    return;
                } else {
                    YoN = true;
                    updateReparations(rep);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void LeaveReparations() {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Désirez-vous réellement quitter cette application ?\n1- Oui\n2- Non");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    Repa_view.main(null);
                } else {
                    YoN = true;
                    System.out.println("Merci d'utiliser notre application de gestion de véhicules.");
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void listRepDel() {
        List<Repa_mod> repa = repController.getAllReparations();
        if (repa.isEmpty()) {
            System.out.println("Aucune réparation trouvé.");
            return;
        }

        System.out.println("---Liste des réparations---");
        for (int i = 0; i < repa.size(); i++) {
            Repa_mod rp = repa.get(i);
            System.out.println((i + 1) + "- Problème :" + rp.getProblem() + "\t Solution :" + rp.getSolution()
                    + "\t Prix :" + rp.getPrice());
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner une réparation en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= repa.size()) {
                    displayReparationsDetailsDel(repa.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Repa_view.main(null);
                    return;
                } else {
                    System.out.println("Veuillez entrer un nombre valide.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }
        }
    }

    public static void displayReparationsDetailsDel(Repa_mod rep) {
        System.out.println("\nDétails de la réparation sélectionné :");
        System.out.println("Problème : " + rep.getProblem());
        System.out.println("Solution : " + rep.getSolution());
        System.out.println("Prix : " + rep.getPrice());
        System.out.println("Date d'ajout : " + rep.getAdd_date());
        DelQuestionReparation(rep);
    }

    public static void DelQuestionReparation(Repa_mod rep) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Est-ce bien la réparation que vous voulez supprimer ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listRepDel();
                    return;
                } else {
                    YoN = true;
                    repController.deleteReparations(rep);
                    System.out.println("réparation supprimé avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    // Trash (corbeille)
    public static void listRepTrash() {

        List<Repa_mod> repa = repController.getAllReparationsTrash();
        if (repa.isEmpty()) {
            System.out.println("Corbeille vide.");
            return;
        }

        System.out.println("---Liste des réparations---");
        for (int i = 0; i < repa.size(); i++) {
            Repa_mod rp = repa.get(i);
            System.out.println((i + 1) + "- Problème :" + rp.getProblem() + "\t Solution :" + rp.getSolution()
                    + "\t Prix :" + rp.getPrice());
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner une réparation en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= repa.size()) {
                    displayReparationsDetailsTrash(repa.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Repa_view.main(null);
                    return;
                } else {
                    System.out.println("Veuillez entrer un nombre valide.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }
        }
    }

    public static void displayReparationsDetailsTrash(Repa_mod rep) {
        System.out.println("\nDétails de la réparation sélectionné :");
        System.out.println("Problème : " + rep.getProblem());
        System.out.println("Solution : " + rep.getSolution());
        System.out.println("Prix : " + rep.getPrice());
        System.out.println("Date d'ajout : " + rep.getAdd_date());
        TrashQuestionReparations(rep);
    }

    public static void TrashQuestionReparations(Repa_mod rep) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println(
                    "Qu'est-ce-que vous voulez faire avec l'élement selctionné ?\n1- Supprimer définitivement\n2- Restoré\n3- Annuler (retour)");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 3) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 3.");
                } else if (usrchoice == 2) {
                    YoN = true;
                    repController.RestoreTrashReparations(rep);
                    System.out.println("réparation restoré avec succès");
                } else if (usrchoice == 3) {
                    listRepTrash();
                    return;
                } else {
                    YoN = true;
                    SureDeleteRep(rep);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void SureDeleteRep(Repa_mod rep) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Désirez-vous réellement supprimer cette réparation ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listRepTrash();
                    return;
                } else {
                    YoN = true;
                    repController.SureDeleteReparations(rep);
                    System.out.println("Réparation supprimer avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void searchByProblem() {
        System.out.print("Entrez le problème : ");
        String typeuser = scanner.next() + scanner.nextLine();

        List<Repa_mod> repa = repController.searchProblem(typeuser);
        if (repa == null) {
            System.out.println("Erreur lors de la recherche par problème.");
        } else if (repa.isEmpty()) {
            System.out.println("Aucune réparation trouvé pour le problème fourni.");
        } else {
            System.out.println("Résultat trouvez pour la recherche :" + typeuser);
            System.out.println("Nombre de véhicules trouvés : " + repa.size());
            for (Repa_mod rep : repa) {
                System.out.println("\n" + rep);
            }
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Repa_view repView = new Repa_view();
        boolean quitMaint = false;

        while (!quitMaint) {

            int choice = userChoice();

            switch (choice) {
                case 1:
                    addReparations();
                    break;
                case 2:
                    listReparationsMod();
                    break;
                case 3:
                    listRepDel();
                    break;
                case 4:
                    searchByProblem();
                    break;
                case 5:
                    listRepTrash();
                    break;
                case 6:
                    App.main(args);
                    break;
                case 7:
                    quitMaint = true;
                    LeaveReparations();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 7.");
                    break;
            }
        }

        scanner.close();
    }

}
