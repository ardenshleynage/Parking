package Reparation_Meet_Up;

import java.util.*;

import App.App;
import Reparation.Repa_mod;
import Vehicule.Veh_mod;

public class Rmu_view {
    private static Rmu_ctrl rmuController;
    private static Scanner scanner = new Scanner(System.in, "UTF-8");

    public Rmu_view() {
        String host = "jdbc:mysql://localhost:3306/parking";
        String user = "root";
        String password = "";

        rmuController = new Rmu_ctrl(host, user, password);
    }

    public static int userChoice() {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("\n---Menu---");
            System.out.println("1- Ajouter une association entre une réparation et un véhicule");
            System.out.println("2- Modifier l'association entre une réparation et un véhicule");
            System.out.println("3- Supprimer une association entre une réparation et un véhicule");
            System.out.println("4- Rechercher une association entre une réparation et un véhicule");
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

    public static void LeaveMmu() {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Désirez-vous réellement quitter cette application ?\n1- Oui\n2- Non");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    Rmu_view.main(null);
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

    public static void addAssociationVeh() {
        List<Veh_mod> vehicules = rmuController.getAllVehicules();
        if (vehicules.isEmpty()) {
            System.out.println("Aucun véhicule trouvé.");
            return;
        }

        System.out.println("--- Liste des véhicules ---");
        for (int i = 0; i < vehicules.size(); i++) {
            Veh_mod veh = vehicules.get(i);
            System.out.println((i + 1) + "- Marque : " + veh.getMark() + "\t Modèle : " + veh.getModel()
                    + "\t Immatriculation : " + veh.getNumb_plate());
        }

        int vehChoice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner un véhicule en entrant son numéro (Appuyer sur zéro(0) pour retourner) : ");
            try {
                vehChoice = scanner.nextInt();

                if (vehChoice >= 1 && vehChoice <= vehicules.size()) {
                    Veh_mod selectedVeh = vehicules.get(vehChoice - 1);
                    displayVehiculeDetails(selectedVeh);
                    break;
                } else if (vehChoice == 0) {
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
        addVehQuestion(vehicule);
    }

    public static void addVehQuestion(Veh_mod vehicule) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Est-ce bien le véhicule désiré?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                scanner.nextLine();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    addAssociationVeh();
                    return;
                } else {
                    YoN = true;
                    addAssociationRepa(vehicule);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void addAssociationRepa(Veh_mod vehicule) {
        List<Repa_mod> repa = rmuController.getAllReparations();

        if (repa.isEmpty()) {
            System.out.println("Aucune réparation trouvée.");
            return;
        }

        System.out.println("--- Liste des réparations ---");
        for (int i = 0; i < repa.size(); i++) {
            Repa_mod rep = repa.get(i);
            System.out.println((i + 1) + "- Problėme : " + rep.getProblem() + "\t Solution : " + rep.getSolution());
        }

        int repChoice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner une réparation en entrant son numéro (Appuyer sur zéro(0) pour retourner) : ");
            try {
                repChoice = scanner.nextInt();

                if (repChoice >= 1 && repChoice <= repa.size()) {
                    Repa_mod selectedRep = repa.get(repChoice - 1);
                    displayReparationDetails(selectedRep, vehicule);
                    break;
                } else if (repChoice == 0) {
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

    public static void displayReparationDetails(Repa_mod rep, Veh_mod vehicule) {
        System.out.println("\nDétails de la réparation sélectionné :");
        System.out.println("Problėme : " + rep.getProblem());
        System.out.println("Solution : " + rep.getSolution());
        System.out.println("Prix : " + rep.getPrice());
        System.out.println("Date d'ajout : " + rep.getAdd_date());
        addRepQuestion(rep, vehicule);
    }

    public static void addRepQuestion(Repa_mod rep, Veh_mod vehicule) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Est-ce bien la réparation désirée ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                scanner.nextLine();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    addAssociationRepa(vehicule);
                    return;
                } else {
                    YoN = true;
                    addReparationMeetUp(vehicule, rep);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void addReparationMeetUp(Veh_mod vehicule, Repa_mod rep) {
        Rmu_mod rmu = new Rmu_mod();
        rmu.setIdRepa(rep.getId());
        rmu.setProbRepa(rep.getProblem());
        rmu.setSolRepa(rep.getSolution());
        rmu.setPriceRepa(rep.getPrice());
        rmu.setIdVeh(vehicule.getId());
        rmu.setNumbPlateVehicle(vehicule.getNumb_plate());
        rmu.setMarkVeh(vehicule.getMark());
        rmu.setModelVeh(vehicule.getModel());
        rmu.setColorVeh(vehicule.getColor());
        rmu.setYearVeh(vehicule.getYear());
        rmu.setAdd_date(new java.sql.Date(System.currentTimeMillis()).toString());

        boolean associationExists = rmuController.existsByReparationAndVehicle(vehicule.getNumb_plate(),
                rep.getProblem(), vehicule.getMark(), vehicule.getModel(), vehicule.getYear(), vehicule.getColor());

        if (associationExists) {
            System.out.println("L'association entre la réparation et le véhicule existe déjà.");
        } else {
            boolean isInserted = rmuController.addRmu(rmu);
            if (isInserted) {
                System.out.println("Association entre la réparation et le véhicule ajoutée avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout de l'association.");
            }
        }
    }

    public static void listRmuDel() {
        List<Rmu_mod> rmus = rmuController.getAllRmu();
        if (rmus.isEmpty()) {
            System.out.println("Aucun association entre une réparation et un véhicule trouvé.");
            return;
        }

        System.out.println("---Liste des associations entre une réparation et un véhicule---");
        for (int i = 0; i < rmus.size(); i++) {
            Rmu_mod rm = rmus.get(i);
            System.out.println((i + 1) + "- Problėme :" + rm.getProbRepa() + "\nSolution  :"
                    + rm.getSolRepa()
                    + "\n Prix :" + rm.getPriceRepa() + "\nNuméro de plaque :" + rm.getNumbPlateVehicle()
                    + "\n Marque du vehicule :" + rm.getMarkVeh() + "\nModèle du vehicule :" + rm.getModelVeh()
                    + "\nCouleur du vehicule :" + rm.getColorVeh() + "\nAnnée :" + rm.getYearVeh() + "\n");
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner l'association en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= rmus.size()) {
                    displayRmuDetailsDel(rmus.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Rmu_view.main(null);
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

    public static void displayRmuDetailsDel(Rmu_mod rmu) {
        System.out.println("\nDétails de l'association entre une réparation et un véhicule sélectionné :");
        System.out.println("Problėme : " + rmu.getProbRepa());
        System.out.println("Solution : " + rmu.getSolRepa());
        System.out.println("Prix réparation : " + rmu.getPriceRepa());
        System.out.println("Immatriculation du véhicule : " + rmu.getNumbPlateVehicle());
        System.out.println("Marque du véhicule : " + rmu.getMarkVeh());
        System.out.println("Modèle du véhicule : " + rmu.getModelVeh());
        System.out.println("coleur du véhicule : " + rmu.getColorVeh());
        System.out.println("Année du véhicule : " + rmu.getYearVeh());
        System.out.println("Date d'ajout  : " + rmu.getAdd_date());
        DelRmuQuestion(rmu);
    }

    public static void DelRmuQuestion(Rmu_mod rmu) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println(
                    "Est-ce bien l'association entre une réparation et un véhicule que vous voulez supprimer ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listRmuDel();
                    return;
                } else {
                    YoN = true;
                    rmuController.deleteRmu(rmu);
                    System.out.println("l'association supprimé avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    // Trash (corbeille)
    public static void listRmuTrash() {
        List<Rmu_mod> rmus = rmuController.getAllRmuTrash();
        if (rmus.isEmpty()) {
            System.out.println("Aucun association entre une réparation et un véhicule trouvé.");
            return;
        }

        System.out.println("---Liste des associations entre une réparation et un véhicule---");
        for (int i = 0; i < rmus.size(); i++) {
            Rmu_mod rm = rmus.get(i);
            System.out.println((i + 1) + "- Problėme :" + rm.getProbRepa() + "\nSolution  :"
                    + rm.getSolRepa()
                    + "\n Prix :" + rm.getPriceRepa() + "\nNuméro de plaque :" + rm.getNumbPlateVehicle()
                    + "\n Marque du vehicule :" + rm.getMarkVeh() + "\nModèle du vehicule :" + rm.getModelVeh()
                    + "\nCouleur du vehicule :" + rm.getColorVeh() + "\nAnnée :" + rm.getYearVeh() + "\n");
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner l'association en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= rmus.size()) {
                    displayRmuDetailsTrash(rmus.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Rmu_view.main(null);
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

    public static void displayRmuDetailsTrash(Rmu_mod rmu) {
        System.out.println("\nDétails de l'association entre une réparation et un véhicule sélectionné :");
        System.out.println("Problėme : " + rmu.getProbRepa());
        System.out.println("Solution : " + rmu.getSolRepa());
        System.out.println("Prix réparation : " + rmu.getPriceRepa());
        System.out.println("Immatriculation du véhicule : " + rmu.getNumbPlateVehicle());
        System.out.println("Marque du véhicule : " + rmu.getMarkVeh());
        System.out.println("Modèle du véhicule : " + rmu.getModelVeh());
        System.out.println("coleur du véhicule : " + rmu.getColorVeh());
        System.out.println("Année du véhicule : " + rmu.getYearVeh());
        System.out.println("Date d'ajout  : " + rmu.getAdd_date());
        TrashRmuQuestion(rmu);
    }

    public static void TrashRmuQuestion(Rmu_mod rmu) {
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
                    rmuController.RestoreRmuTrash(rmu);
                    System.out.println("Association restoré avec succès");
                } else if (usrchoice == 3) {
                    listRmuTrash();
                    return;
                } else {
                    YoN = true;
                    SureRmuDelete(rmu);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void SureRmuDelete(Rmu_mod rmu) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Désirez-vous réellement supprimer cette association ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listRmuTrash();
                    return;
                } else {
                    YoN = true;
                    rmuController.SureRmuDelete(rmu);
                    System.out.println("Associtation supprimer avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void QuestionSearchRmuDetails() {
        System.out.print("Veuillez entrer l'immatriculation du véhicule : ");
        String npv = scanner.next() + scanner.nextLine();
        displaySearchRmuDetails(npv);
    }

    public static void displaySearchRmuDetails(String npv) {
        List<Rmu_mod> rmus = rmuController.searchRmu(npv);

        if (rmus.isEmpty()) {
            System.out.println("Aucune réparation trouvée pour l'immatriculation : " + npv);
            return;
        }

        String marque = rmus.get(0).getMarkVeh();
        String modele = rmus.get(0).getModelVeh();
        int annee = rmus.get(0).getYearVeh();
        String couleur = rmus.get(0).getColorVeh();
        String dateAjout = rmus.get(0).getAdd_date();

        StringBuilder reparation = new StringBuilder();
        double totalPrice = 0.0;
        int reparationCount = rmus.size();

        for (Rmu_mod rmu : rmus) {
            reparation.append(rmu.getProbRepa()).append(", ");
            totalPrice += rmu.getPriceRepa();
        }

        if (reparation.length() > 0) {
            reparation.setLength(reparation.length() - 2);
        }

        System.out.println("MARQUE : " + marque);
        System.out.println("MODÈLE: " + modele);
        System.out.println("ANNÉE: " + annee);
        System.out.println("COULEUR: " + couleur);
        System.out.println("REPARATION : " + reparation.toString());
        System.out.println("NOMBRE DE REPARATION : " + reparationCount);
        System.out.println("PRIX TOTAL REPARATION : " + totalPrice);
        System.out.println("DATE D'AJOUT: " + dateAjout);
    }

    public static void displayAndSelectRmu() {
        List<Rmu_mod> rmus = rmuController.getAllRmu();
        if (rmus == null || rmus.isEmpty()) {
            System.out.println("Aucune rėparation disponible.");
            return;
        }

        for (int i = 0; i < rmus.size(); i++) {
            Rmu_mod rmu = rmus.get(i);
            System.out.println((i + 1) + ". " + rmu.getMarkVeh() + " " + rmu.getModelVeh() + " ("
                    + rmu.getNumbPlateVehicle() + ")");
        }

        System.out.print("Sélectionnez le numéro du véhicule désiré : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > rmus.size()) {
            System.out.println("Sélection non valide.");
            return;
        }

        Rmu_mod selectedRmu = rmus.get(choice - 1);
        displayAndUpdateReparation(selectedRmu);
    }

    public static void displayAndUpdateReparation(Rmu_mod rmu) {
        List<Repa_mod> repa = rmuController.getAllReparations();
        if (repa == null || repa.isEmpty()) {
            System.out.println("Aucune reparation disponible.");
            return;
        }

        for (int i = 0; i < repa.size(); i++) {
            Repa_mod rep = repa.get(i);
            System.out.println(
                    (i + 1) + ". " + rep.getProblem() + " - " + rep.getSolution() + " (" + rep.getPrice() + "€)");
        }

        System.out.print("Sélectionnez le numéro de la reparation désirée : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > repa.size()) {
            System.out.println("Sélection non valide.");
            return;
        }

        Repa_mod selectedRep = repa.get(choice - 1);
        boolean isAlreadyAssociated = rmuController.isReparationAlreadyAssociated(rmu.getIdVeh(),
                selectedRep.getProblem());
        if (isAlreadyAssociated) {
            System.out.println("Cette reparation est déjà associée à ce véhicule.");
            return;
        }

        rmu.setProbRepa(selectedRep.getProblem());
        rmu.setSolRepa(selectedRep.getSolution());
        rmu.setPriceRepa(selectedRep.getPrice());

        boolean success = rmuController.updateRmu(rmu);
        if (success) {
            System.out.println("La reparation a été mise à jour avec succès.");
        } else {
            System.out.println("Erreur lors de la mise à jour de la reparation.");
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Rmu_view rmuView = new Rmu_view();
        boolean quit = false;

        while (!quit) {

            int choice = userChoice();

            switch (choice) {
                case 1:
                    addAssociationVeh();
                    break;
                case 2:
                    displayAndSelectRmu();
                    break;
                case 3:
                    listRmuDel();
                    break;
                case 4:
                    QuestionSearchRmuDetails();
                    break;
                case 5:
                    listRmuTrash();
                    break;
                case 6:
                    App.main(args);
                    break;
                case 7:
                    LeaveMmu();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 7.");
                    break;
            }
        }

        scanner.close();

    }

}
