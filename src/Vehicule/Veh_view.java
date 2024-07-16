package Vehicule;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.util.List;

import App.App;

import java.time.LocalDate;

public class Veh_view {
    private static Veh_ctrl vehController;
    private static Scanner scanner = new Scanner(System.in,"UTF-8");

    public Veh_view() {
        String host = "jdbc:mysql://localhost:3306/parking";
        String user = "root";
        String password = "";

        vehController = new Veh_ctrl(host, user, password);
    }

    public static int userChoice() {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("\n---Menu---");
            System.out.println("1- Ajouter un véhicule");
            System.out.println("2- Modifier les informations d'un véhicule");
            System.out.println("3- Supprimer un véhicule");
            System.out.println("4- Rechercher des véhicules par modèle ou immatriculation");
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

    public static void addVehicule() {
        System.out.println("Ajouter un véhicule");

        try {
            String numb_plate;
            while (true) {
                System.out.print("Entrer l'immatriculation (8 caractères) : ");
                numb_plate = scanner.next().trim();

                if (numb_plate.length() != 8) {
                    System.out.println("L'immatriculation doit être exactement de 8 caractères.");
                    continue;
                }

                if (numb_plate.isEmpty()) {
                    System.out.println("L'immatriculation ne peut pas être vide. Veuillez entrer une immatriculation.");
                    continue;
                }

                if (Pattern.matches("^[a-zA-Z0-9-]+$", numb_plate)) {
                    if (vehController.existsByNumbPlate(numb_plate)) {
                        System.out.println(
                                "L'immatriculation existe déjà. Veuillez entrer une immatriculation différente.");
                    } else {
                        break;
                    }
                } else {
                    System.out.println(
                            "Veuillez entrer une immatriculation valide (lettres, chiffres et tirets uniquement).");
                }
            }

            String mark;
            while (true) {
                System.out.print("Entrer la marque : ");
                mark = scanner.next() + scanner.nextLine();

                if (Pattern.matches("^[a-zA-Z0-9&\\-\\s]+$", mark)) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer une marque valide (lettres, chiffres, &, - et espaces uniquement).");
                }
            }

            String model;
            while (true) {
                System.out.print("Entrer le modèle : ");
                model = scanner.next() + scanner.nextLine();

                if (Pattern.matches("^[a-zA-Z0-9&\\-\\s]+$", model)) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer un modèle valide (lettres, chiffres, &, - et espaces uniquement).");
                }
            }

            int year = 0;
            while (true) {
                System.out.print("Entrer l'année : ");
                try {
                    year = scanner.nextInt();
                    if (year >= 1900 && year <= LocalDate.now().getYear()) {
                        break;
                    } else {
                        System.out.println(
                                "Veuillez entrer une année valide (entre 1900 et " + LocalDate.now().getYear() + ").");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Veuillez entrer un nombre entier.");
                    scanner.nextLine();
                }
            }

            String color;
            while (true) {
                System.out.print("Entrer la couleur : ");
                color = scanner.next() + scanner.nextLine();

                if (Pattern.matches("^[a-zA-Z0-9&\\-\\s]+$", color)) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer une couleur valide (lettres, chiffres, &, - et espaces uniquement).");
                }
            }

            int kilometer = 0;
            while (true) {
                System.out.print("Entrer le kilométrage : ");
                try {
                    kilometer = scanner.nextInt();
                    if (kilometer >= 0 && kilometer <= 500000) {
                        break;
                    } else {
                        System.out.println("Veuillez entrer un kilométrage valide (entre 0 et 500000).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Veuillez entrer un nombre entier.");
                    scanner.nextLine();
                }
            }

            String add_date = java.time.LocalDate.now().toString();

            Veh_mod vehicule = new Veh_mod(0, numb_plate, mark, model, year, color, kilometer, add_date);
            vehController.addVehicule(vehicule);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally {
            scanner.nextLine();
        }
    }

    public static void updateVehicule(Veh_mod vehicule) {

        try {
            String numb_plate;
            System.out.println("Modification d'un véhicule");
            while (true) {
                System.out.print("Entrer la nouvelle immatriculation (10 caractères maximum) (valeur actuelle = "
                        + vehicule.getNumb_plate() + ") : ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    numb_plate = vehicule.getNumb_plate();
                    break;
                } else {
                    numb_plate = input;
                }

                if (numb_plate.length() != 8) {
                    System.out.println("L'immatriculation doit être exactement de 8 caractères.");
                    continue;
                }

                if (Pattern.matches("^[a-zA-Z0-9-]+$", numb_plate)) {
                    if (vehController.existsByNumbPlate(numb_plate)) {
                        System.out.println(
                                "L'immatriculation existe déjà. Veuillez entrer une immatriculation différente.");
                    } else {
                        break;
                    }
                } else {
                    System.out.println(
                            "Veuillez entrer une immatriculation valide (lettres, chiffres et tirets uniquement).");
                }
            }

            String mark;
            while (true) {
                System.out.print("Entrer la nouvelle marque (valeur actuelle = " + vehicule.getMark() + ") : ");
                mark = scanner.nextLine().trim();

                if (mark.isEmpty()) {
                    mark = vehicule.getMark();
                    break;
                }
                if (Pattern.matches("^[a-zA-Z0-9&\\-\\s]+$", mark)) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer une marque valide (lettres, chiffres, &, - et espaces uniquement).");
                }
            }

            String model;
            while (true) {
                System.out.print("Entrer le nouveau modèle (valeur actuelle = " + vehicule.getModel() + ") : ");
                model = scanner.nextLine().trim();

                if (model.isEmpty()) {
                    model = vehicule.getModel();
                    break;
                }

                if (Pattern.matches("^[a-zA-Z0-9&\\-\\s]+$", model)) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer un modèle valide (lettres, chiffres, &, - et espaces uniquement).");
                }
            }

            int year = 0;
            while (true) {
                System.out.print("Entrer la nouvelle année (valeur actuelle = " + vehicule.getYear() + ") : ");
                String yearInput = scanner.nextLine().trim();

                if (yearInput.isEmpty()) {
                    year = vehicule.getYear();
                    break;
                }

                try {
                    year = Integer.parseInt(yearInput);
                    if (year >= 1900 && year <= LocalDate.now().getYear()) {
                        break;
                    } else {
                        System.out.println(
                                "Veuillez entrer une année valide (entre 1900 et " + LocalDate.now().getYear() + ").");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre entier.");
                }
            }

            String color;
            while (true) {
                System.out.print("Entrer la nouvelle couleur (valeur actuelle = " + vehicule.getColor() + ") : ");
                color = scanner.nextLine().trim();

                if (color.isEmpty()) {
                    color = vehicule.getColor();
                    break;
                }

                if (Pattern.matches("^[a-zA-Z0-9&\\-\\s]+$", color)) {
                    break;
                } else {
                    System.out.println(
                            "Veuillez entrer une couleur valide (lettres, chiffres, &, - et espaces uniquement).");
                }
            }

            int kilometer = 0;
            while (true) {
                System.out
                        .print("Entrer le nouveau kilométrage (valeur actuelle = " + vehicule.getKilometer() + ") : ");
                String kilometerInput = scanner.nextLine().trim();

                if (kilometerInput.isEmpty()) {
                    kilometer = vehicule.getKilometer();
                    break;
                }

                try {
                    kilometer = Integer.parseInt(kilometerInput);
                    if (kilometer >= 0 && kilometer <= 500000) {
                        break;
                    } else {
                        System.out.println("Veuillez entrer un kilométrage valide (entre 0 et 500000).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre entier.");
                }
            }

            String add_date = java.time.LocalDate.now().toString();

            vehicule.setNumb_plate(numb_plate);
            vehicule.setMark(mark);
            vehicule.setModel(model);
            vehicule.setYear(year);
            vehicule.setColor(color);
            vehicule.setKilometer(kilometer);
            vehicule.setAdd_date(add_date);

            vehController.updateVehicule(vehicule);
            System.out.println("Véhicule modifié avec succès!");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally {
            scanner.nextLine();
        }

    }

    public static void Leave() {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Désirez-vous réellement quitter cette application ?\n1- Oui\n2- Non");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    Veh_view.main(null);
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

    public static void listVehiculesMod() {

        List<Veh_mod> vehicules = vehController.getAllVehicules();
        if (vehicules.isEmpty()) {
            System.out.println("Aucun véhicule trouvé.");
            return;
        }

        System.out.println("---Liste des véhicules---");
        for (int i = 0; i < vehicules.size(); i++) {
            Veh_mod veh = vehicules.get(i);
            System.out.println((i + 1) + "- Marque :" + veh.getMark() + "\t Modèle :" + veh.getModel()
                    + "\t Immatriculation :" + veh.getNumb_plate());
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner un véhicule en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= vehicules.size()) {
                    displayVehiculeDetails(vehicules.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Veh_view.main(null);
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

    public static void displayVehiculeDetails(Veh_mod vehicule) {
        System.out.println("Détails du véhicule sélectionné : ");
        System.out.println("Immatriculation : " + vehicule.getNumb_plate());
        System.out.println("Marque : " + vehicule.getMark());
        System.out.println("Modèle : " + vehicule.getModel());
        System.out.println("Année : " + vehicule.getYear());
        System.out.println("Couleur : " + vehicule.getColor());
        System.out.println("Kilométrage : " + vehicule.getKilometer());
        System.out.println("Date d'ajout : " + vehicule.getAdd_date());
        ModQuestion(vehicule);
    }

    public static void ModQuestion(Veh_mod vehicule) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Est-ce bien le véhicule que vous voulez modifier ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                scanner.nextLine();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listVehiculesMod();
                    return;
                } else {
                    YoN = true;
                    updateVehicule(vehicule);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void listVehiculesDel() {
        List<Veh_mod> vehicules = vehController.getAllVehicules();
        if (vehicules.isEmpty()) {
            System.out.println("Aucun véhicule trouvé.");
            return;
        }

        System.out.println("---Liste des véhicules---");
        for (int i = 0; i < vehicules.size(); i++) {
            Veh_mod veh = vehicules.get(i);
            System.out.println((i + 1) + "- Marque :" + veh.getMark() + "\t Modèle :" + veh.getModel()
                    + "\t Immatriculation :" + veh.getNumb_plate());
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner un véhicule en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= vehicules.size()) {
                    displayVehiculeDetailsDel(vehicules.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Veh_view.main(null);
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

    public static void displayVehiculeDetailsDel(Veh_mod vehicule) {
        System.out.println("Détails du véhicule sélectionné :");
        System.out.println("Immatriculation : " + vehicule.getNumb_plate());
        System.out.println("Marque : " + vehicule.getMark());
        System.out.println("Modèle : " + vehicule.getModel());
        System.out.println("Année : " + vehicule.getYear());
        System.out.println("Couleur : " + vehicule.getColor());
        System.out.println("Kilométrage : " + vehicule.getKilometer());
        System.out.println("Date d'ajout : " + vehicule.getAdd_date());
        DelQuestion(vehicule);
    }

    public static void DelQuestion(Veh_mod vehicule) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Est-ce bien le véhicule que vous voulez supprimer ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listVehiculesDel();
                    return;
                } else {
                    YoN = true;
                    vehController.deleteVehicule(vehicule);
                    System.out.println("Véhicule supprimé avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    // Trash (corbeille)
    public static void listVehiculesTrash() {

        List<Veh_mod> vehicules = vehController.getAllVehiculesTrash();
        if (vehicules.isEmpty()) {
            System.out.println("Corbeille vide.");
            return;
        }

        System.out.println("---Liste des véhicules---");
        for (int i = 0; i < vehicules.size(); i++) {
            Veh_mod veh = vehicules.get(i);
            System.out.println((i + 1) + "- Marque :" + veh.getMark() + "\t Modèle :" + veh.getModel()
                    + "\t Immatriculation :" + veh.getNumb_plate());
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner un véhicule en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= vehicules.size()) {
                    displayVehiculeDetailsTrash(vehicules.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Veh_view.main(null);
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

    public static void displayVehiculeDetailsTrash(Veh_mod vehicule) {
        System.out.println("Détails du véhicule sélectionné :");
        System.out.println("Immatriculation : " + vehicule.getNumb_plate());
        System.out.println("Marque : " + vehicule.getMark());
        System.out.println("Modèle : " + vehicule.getModel());
        System.out.println("Année : " + vehicule.getYear());
        System.out.println("Couleur : " + vehicule.getColor());
        System.out.println("Kilométrage : " + vehicule.getKilometer());
        System.out.println("Date d'ajout : " + vehicule.getAdd_date());
        TrashQuestion(vehicule);
    }

    public static void TrashQuestion(Veh_mod vehicule) {
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
                    vehController.RestoreTrash(vehicule);
                    System.out.println("Véhicule restoré avec succès");
                } else if (usrchoice == 3) {
                    listVehiculesTrash();
                    return;
                } else {
                    YoN = true;
                    SureDelete(vehicule);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void SureDelete(Veh_mod vehicule) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Désirez-vous réellement supprimer ce véhicule ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listVehiculesTrash();
                    return;
                } else {
                    YoN = true;
                    vehController.SureDelete(vehicule);
                    System.out.println("Véhicule supprimer avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void searchVehicle() {
        boolean YoN = false;
        int usrchoice = 0;
        while (!YoN) {
            System.out.println("Choisissez une option de recherche:");
            System.out.println("1- Rechercher par modèle");
            System.out.println("2- Rechercher par immatriculation");
            System.out.println("3- Quitter");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                scanner.nextLine();
                if (usrchoice < 1 || usrchoice > 3) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 3.");
                } else if (usrchoice == 1) {
                    searchByModel();
                } else if (usrchoice == 2) {
                    searchByNumbPlate();
                } else {
                    YoN = true;
                    Veh_view.main(null);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
            ;

        }
    }

    public static void searchByModel() {
        System.out.print("Entrez le modèle : ");
        String model = scanner.nextLine().trim();
    
        List<Veh_mod> vehicles = vehController.searchVehiclesByModel(model);
        if (vehicles == null) {
            System.out.println("Erreur lors de la recherche par modèle.");
        } else if (vehicles.isEmpty()) {
            System.out.println("Aucun véhicule trouvé pour le modèle fourni.");
        } else {
            System.out.println("Résultat trouvez pour la recherche :"+model);
            System.out.println("Nombre de véhicules trouvés : " + vehicles.size());
            for (Veh_mod vehicle : vehicles) {
                System.out.println("\n"+vehicle);
            }
        }
    }

    public static void searchByNumbPlate() {
        System.out.print("Entrez l'immatriculation : ");
        String numbPlate = scanner.nextLine().trim();
    
        List<Veh_mod> vehicles = vehController.searchVehiclesByPlate(numbPlate);
        if (vehicles == null) {
            System.out.println("Erreur lors de la recherche par immatriculation.");
        } else if (vehicles.isEmpty()) {
            System.out.println("Aucun véhicule trouvé pour l'immatriculation fourni.");
        } else {
            System.out.println("Résultat trouvez pour la recherche : "+numbPlate);
            System.out.println("Nombre de véhicules trouvés : " + vehicles.size());
            for (Veh_mod vehicle : vehicles) {
                System.out.println("\n"+vehicle);
            }
        }
    }
    
    

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Veh_view vehView = new Veh_view();
        boolean quit = false;

        while (!quit) {

            int choice = userChoice();

            switch (choice) {
                case 1:
                    addVehicule();
                    break;
                case 2:
                    listVehiculesMod();
                    break;
                case 3:
                    listVehiculesDel();
                    break;
                case 4:
                    searchVehicle();
                    break;
                case 5:
                    listVehiculesTrash();
                    break;
                case 6:
                    App.main(args);
                    break;
                case 7:
                    quit = true;
                    Leave();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 7.");
                    break;
            }
        }

        scanner.close();
    }
}