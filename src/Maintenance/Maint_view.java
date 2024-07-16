package Maintenance;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import App.App;

public class Maint_view {
    private static Maint_ctrl maintController;
    private static Scanner scanner = new Scanner(System.in);

    public Maint_view() {
        String host = "jdbc:mysql://localhost:3306/parking";
        String user = "root";
        String password = "";

        maintController = new Maint_ctrl(host, user, password);
    }

    public static int userChoice() {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("\n---Menu---");
            System.out.println("1- Ajouter une maintenance");
            System.out.println("2- Modifier les informations d'une maintenance");
            System.out.println("3- Supprimer une maintenance");
            System.out.println("4- Rechercher des maintenance par type");
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

    public static void updateMaintenance(Maint_mod maint) {
        System.out.println("Modification d'une maintenance");

        try {
            String type;
            while (true) {
                System.out.print("Entrer le type de maintenance (valeur actuelle = " + maint.getType() + ") : ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    type = maint.getType();
                    break;
                } else {
                    type = input;
                }

                if (maintController.existsByType(type)) {
                    System.out.println("Ce type de maintenance existe déjà. Veuillez entrer un autre type.");
                    continue;
                }

                boolean isValid = true;
                for (char c : type.toCharArray()) {
                    if (Character.isDigit(c)) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer un type valide (lettres, &, -, ', et espaces uniquement, sans chiffres).");
                }
            }

            String description;
            while (true) {
                System.out.print("Entrer la description de la maintenance (valeur actuelle = " + maint.getDescription()
                        + ") : ");
                description = scanner.nextLine().trim();

                if (description.isEmpty()) {
                    description = maint.getDescription();
                    break;
                }

                if (maintController.existsByDescription(description)) {
                    System.out.println(
                            "Cette description de maintenance existe déjà. Veuillez entrer une autre description.");
                } else if (description.isEmpty()) {
                    System.out.println("La description ne peut pas être vide.");
                } else {
                    break;
                }
            }

            double price;
            while (true) {
                System.out.print(
                        "Entrer le prix de la maintenance (en Gourde) (valeur actuelle = " + maint.getPrice() + ") : ");
                String priceInput = scanner.nextLine().trim();

                if (priceInput.isEmpty()) {
                    price = maint.getPrice();
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

            maint.setType(type);
            maint.setDescription(description);
            maint.setPrice(price);
            maint.setAdd_date(add_date);

            maintController.updateMaintenance(maint);
            System.out.println("Maintenance modifiée avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally {
            scanner.nextLine();
        }
    }

    public static void addMaintenance() {
        System.out.println("Ajouter une maintenance");
        try {
            String type;
            boolean isValid;

            while (true) {
                System.out.print("Entrer le type de maintenance : ");
                type = scanner.next() + scanner.nextLine();
                isValid = true;

                if (maintController.existsByType(type)) {
                    System.out.println("Ce type de maintenance existe déjà. Veuillez entrer un autre type.");
                    continue;
                }

                for (char c : type.toCharArray()) {
                    if (Character.isDigit(c)) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer un type valide (lettres, &, -, ', et espaces uniquement, sans chiffres).");
                }
            }

            String description;
            while (true) {
                System.out.print("Entrer la description de la maintenance : ");
                description = scanner.nextLine();

                if (maintController.existsByDescription(description)) {
                    System.out.println(
                            "Cette description de maintenance existe déjà. Veuillez entrer une autre description.");
                    continue;
                }

                if (description.isEmpty()) {
                    System.out.println("La description ne peut pas être vide.");
                } else {
                    break;
                }
            }

            double price = 0;
            while (true) {
                System.out.print("Entrer le prix de la maintenance (en Gourde) : ");
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

            Maint_mod maint = new Maint_mod(0, type, description, price, add_date);
            maintController.addMaintenance(maint);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally {
            scanner.nextLine();
        }
    }

    public static void listMaintenanceMod() {

        List<Maint_mod> maints = maintController.getAllMaintenance();
        if (maints.isEmpty()) {
            System.out.println("Aucune maintenance trouvé.");
            return;
        }

        System.out.println("---Liste des maintenances---");
        for (int i = 0; i < maints.size(); i++) {
            Maint_mod mt = maints.get(i);
            System.out.println((i + 1) + "- Type :" + mt.getType() + "\t Description :" + mt.getDescription()
                    + "\t Prix :" + mt.getPrice());
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner une maintenance en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= maints.size()) {
                    displayMaintenanceDetails(maints.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Maint_view.main(null);
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

    public static void displayMaintenanceDetails(Maint_mod maint) {
        System.out.println("\nDétails de la maintenance sélectionné :");
        System.out.println("Type : " + maint.getType());
        System.out.println("Description : " + maint.getDescription());
        System.out.println("Prix : " + maint.getPrice());
        System.out.println("Date d'ajout : " + maint.getAdd_date());
        ModQuestionMaintenance(maint);
    }

    public static void ModQuestionMaintenance(Maint_mod maint) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Est-ce bien la maintenance que vous voulez modifier ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                scanner.nextLine();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listMaintenanceMod();
                    return;
                } else {
                    YoN = true;
                    updateMaintenance(maint);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void LeaveMaintenance() {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Désirez-vous réellement quitter cette application ?\n1- Oui\n2- Non");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    Maint_view.main(null);
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

    public static void listMaintDel() {
        List<Maint_mod> maints = maintController.getAllMaintenance();
        if (maints.isEmpty()) {
            System.out.println("Aucune maintenance trouvé.");
            return;
        }

        System.out.println("---Liste des maintenances---");
        for (int i = 0; i < maints.size(); i++) {
            Maint_mod mt = maints.get(i);
            System.out.println((i + 1) + "- Type :" + mt.getType() + "\t Description :" + mt.getDescription()
                    + "\t Prix :" + mt.getPrice());
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner une maintenance en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= maints.size()) {
                    displayMaintenanceDetailsDel(maints.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Maint_view.main(null);
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

    public static void displayMaintenanceDetailsDel(Maint_mod maint) {
        System.out.println("\nDétails de la maintenance sélectionné :");
        System.out.println("Type : " + maint.getType());
        System.out.println("Description : " + maint.getDescription());
        System.out.println("Prix : " + maint.getPrice());
        System.out.println("Date d'ajout : " + maint.getAdd_date());
        DelQuestionMaintenance(maint);
    }

    public static void DelQuestionMaintenance(Maint_mod maint) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Est-ce bien la maintenance que vous voulez supprimer ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listMaintDel();
                    return;
                } else {
                    YoN = true;
                    maintController.deleteMaintenance(maint);
                    System.out.println("Maintenance supprimé avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    // Trash (corbeille)
    public static void listMaintTrash() {

        List<Maint_mod> maints = maintController.getAllMaintenanceTrash();
        if (maints.isEmpty()) {
            System.out.println("Corbeille vide.");
            return;
        }

        System.out.println("---Liste des maintenances---");
        for (int i = 0; i < maints.size(); i++) {
            Maint_mod mt = maints.get(i);
            System.out.println((i + 1) + "- Type :" + mt.getType() + "\t Description :" + mt.getDescription()
                    + "\t Prix :" + mt.getPrice());
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner une maintenance en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= maints.size()) {
                    displayMaintenanceDetailsTrash(maints.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Maint_view.main(null);
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

    public static void displayMaintenanceDetailsTrash(Maint_mod maint) {
        System.out.println("\nDétails de la maintenance sélectionné :");
        System.out.println("Type : " + maint.getType());
        System.out.println("Description : " + maint.getDescription());
        System.out.println("Prix : " + maint.getPrice());
        System.out.println("Date d'ajout : " + maint.getAdd_date());
        TrashQuestionMaintenance(maint);
    }

    public static void TrashQuestionMaintenance(Maint_mod maint) {
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
                    maintController.RestoreTrashMaintenance(maint);
                    System.out.println("Maintenance restoré avec succès");
                } else if (usrchoice == 3) {
                    listMaintTrash();
                    return;
                } else {
                    YoN = true;
                    SureDeleteMaint(maint);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void SureDeleteMaint(Maint_mod maint) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Désirez-vous réellement supprimer cette maintenance ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listMaintTrash();
                    return;
                } else {
                    YoN = true;
                    maintController.SureDeleteMaintenance(maint);
                    System.out.println(" maintenance supprimer avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void searchByType() {
        System.out.print("Entrez le type : ");
        String typeuser = scanner.next() + scanner.nextLine();

        List<Maint_mod> maints = maintController.searchType(typeuser);
        if (maints == null) {
            System.out.println("Erreur lors de la recherche par type.");
        } else if (maints.isEmpty()) {
            System.out.println("Aucune maintenance trouvé pour le type fourni.");
        } else {
            System.out.println("Résultat trouvez pour la recherche :" + typeuser);
            System.out.println("Nombre de  maintenance trouvés : " + maints.size());
            for (Maint_mod maint : maints) {
                System.out.println("\n" + maint);
            }
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Maint_view vehView = new Maint_view();
        boolean quitMaint = false;

        while (!quitMaint) {

            int choice = userChoice();

            switch (choice) {
                case 1:
                    addMaintenance();
                    break;
                case 2:
                    listMaintenanceMod();
                    break;
                case 3:
                    listMaintDel();
                    break;
                case 4:
                    searchByType();
                    break;
                case 5:
                    listMaintTrash();
                    break;
                case 6:
                    App.main(args);
                    break;
                case 7:
                    quitMaint = true;
                    LeaveMaintenance();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 7.");
                    break;
            }
        }

        scanner.close();
    }
}
