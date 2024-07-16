package Maintenance_Meet_Up;

import java.util.*;
import App.App;
import Maintenance.Maint_mod;
import Vehicule.Veh_mod;

public class Mmu_view {
    private static Mmu_ctrl mmuController;
    private static Scanner scanner = new Scanner(System.in, "UTF-8");

    public Mmu_view() {
        String host = "jdbc:mysql://localhost:3306/parking";
        String user = "root";
        String password = "";

        mmuController = new Mmu_ctrl(host, user, password);
    }

    public static int userChoice() {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("\n---Menu---");
            System.out.println("1- Ajouter une association entre une maintenance et un véhicule");
            System.out.println("2- Modifier l'association entre une maintenance et un véhicule");
            System.out.println("3- Supprimer une association entre une maintenance et un véhicule");
            System.out.println("4- Rechercher une association entre une maintenance et un véhicule");
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
                    Mmu_view.main(null);
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
        List<Veh_mod> vehicules = mmuController.getAllVehicules();
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
                    addAssociationMaint(vehicule);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void addAssociationMaint(Veh_mod vehicule) {
        List<Maint_mod> maints = mmuController.getAllMaintenance();

        if (maints.isEmpty()) {
            System.out.println("Aucune maintenance trouvée.");
            return;
        }

        System.out.println("--- Liste des maintenances ---");
        for (int i = 0; i < maints.size(); i++) {
            Maint_mod maint = maints.get(i);
            System.out.println((i + 1) + "- Type : " + maint.getType() + "\t Description : " + maint.getDescription());
        }

        int maintChoice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner une maintenance en entrant son numéro (Appuyer sur zéro(0) pour retourner) : ");
            try {
                maintChoice = scanner.nextInt();

                if (maintChoice >= 1 && maintChoice <= maints.size()) {
                    Maint_mod selectedMaint = maints.get(maintChoice - 1);
                    displayMaintenanceDetails(selectedMaint, vehicule);
                    break;
                } else if (maintChoice == 0) {
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

    public static void displayMaintenanceDetails(Maint_mod maint, Veh_mod vehicule) {
        System.out.println("\nDétails de la maintenance sélectionné :");
        System.out.println("Type : " + maint.getType());
        System.out.println("Description : " + maint.getDescription());
        System.out.println("Prix : " + maint.getPrice());
        System.out.println("Date d'ajout : " + maint.getAdd_date());
        addMaintQuestion(maint, vehicule);
    }

    public static void addMaintQuestion(Maint_mod maint, Veh_mod vehicule) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Est-ce bien la maintenance désirée ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                scanner.nextLine();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    addAssociationMaint(vehicule);
                    return;
                } else {
                    YoN = true;
                    addMaintenanceMeetUp(vehicule, maint);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void addMaintenanceMeetUp(Veh_mod vehicule, Maint_mod maint) {
        Mmu_mod mmu = new Mmu_mod();
        mmu.setIdMaint(maint.getId());
        mmu.setTypeMaint(maint.getType());
        mmu.setDescriptionMaint(maint.getDescription());
        mmu.setPriceMaint(maint.getPrice());
        mmu.setIdVeh(vehicule.getId());
        mmu.setNumbPlateVehicle(vehicule.getNumb_plate());
        mmu.setMarkVeh(vehicule.getMark());
        mmu.setModelVeh(vehicule.getModel());
        mmu.setColorVeh(vehicule.getColor());
        mmu.setYearVeh(vehicule.getYear());
        mmu.setAdd_date(new java.sql.Date(System.currentTimeMillis()).toString());

        boolean associationExists = mmuController.existsByMaintenanceAndVehicle(vehicule.getNumb_plate(),
                maint.getType(), vehicule.getMark(), vehicule.getModel(), vehicule.getYear(), vehicule.getColor());

        if (associationExists) {
            System.out.println("L'association entre la maintenance et le véhicule existe déjà.");
        } else {
            boolean isInserted = mmuController.addMmu(mmu);
            if (isInserted) {
                System.out.println("Association entre la maintenance et le véhicule ajoutée avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout de l'association.");
            }
        }
    }

    public static void listMmuDel() {
        List<Mmu_mod> mmus = mmuController.getAllMmu();
        if (mmus.isEmpty()) {
            System.out.println("Aucun association entre une maintenance et un véhicule trouvé.");
            return;
        }

        System.out.println("---Liste des associations entre une maintenance et un véhicule---");
        for (int i = 0; i < mmus.size(); i++) {
            Mmu_mod mm = mmus.get(i);
            System.out.println((i + 1) + "- Type de maintenance :" + mm.getTypeMaint() + "\nDescription  :"
                    + mm.getDescriptionMaint()
                    + "\n Prix :" + mm.getPriceMaint() + "\nNuméro de plaque :" + mm.getNumbPlateVehicle()
                    + "\n Marque du vehicule :" + mm.getMarkVeh() + "\nModèle du vehicule :" + mm.getModelVeh()
                    + "\nCouleur du vehicule :" + mm.getColorVeh() + "\nAnnée :" + mm.getYearVeh() + "\n");
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner l'association en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= mmus.size()) {
                    displayMmuDetailsDel(mmus.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Mmu_view.main(null);
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

    public static void displayMmuDetailsDel(Mmu_mod mmu) {
        System.out.println("\nDétails de l'association entre une maintenance et un véhicule sélectionné :");
        System.out.println("Type de maintenance : " + mmu.getTypeMaint());
        System.out.println("Description de la maintenance : " + mmu.getDescriptionMaint());
        System.out.println("Prix maintenance : " + mmu.getPriceMaint());
        System.out.println("Immatriculation du véhicule : " + mmu.getNumbPlateVehicle());
        System.out.println("Marque du véhicule : " + mmu.getMarkVeh());
        System.out.println("Modèle du véhicule : " + mmu.getModelVeh());
        System.out.println("coleur du véhicule : " + mmu.getColorVeh());
        System.out.println("Année du véhicule : " + mmu.getYearVeh());
        System.out.println("Date d'ajout  : " + mmu.getAdd_date());
        DelMmuQuestion(mmu);
    }

    public static void DelMmuQuestion(Mmu_mod mmu) {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println(
                    "Est-ce bien l'association entre une maintenance et un véhicule que vous voulez supprimer ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    listMmuDel();
                    return;
                } else {
                    YoN = true;
                    mmuController.deleteMmu(mmu);
                    System.out.println("l'association supprimé avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    // Trash (corbeille)
    public static void listMmuTrash() {
        List<Mmu_mod> mmus = mmuController.getAllMmuTrash();
        if (mmus.isEmpty()) {
            System.out.println("Aucun association entre une maintenance et un véhicule trouvé.");
            return;
        }

        System.out.println("---Liste des associations entre une maintenance et un véhicule---");
        for (int i = 0; i < mmus.size(); i++) {
            Mmu_mod mm = mmus.get(i);
            System.out.println((i + 1) + "- Type de maintenance :" + mm.getTypeMaint() + "\nDescription  :"
                    + mm.getDescriptionMaint()
                    + "\n Prix :" + mm.getPriceMaint() + "\nNuméro de plaque :" + mm.getNumbPlateVehicle()
                    + "\n Marque du vehicule :" + mm.getMarkVeh() + "\nModèle du vehicule :" + mm.getModelVeh()
                    + "\nCouleur du vehicule :" + mm.getColorVeh() + "\nAnnée :" + mm.getYearVeh() + "\n");
        }

        int choice = 0;
        while (true) {
            System.out.print(
                    "Veuillez sélectionner l'association en entrant son numéro (Appuyer sur zéro(0) pour retourner): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= mmus.size()) {
                    displayMmuDetailsTrash(mmus.get(choice - 1));
                    break;
                } else if (choice == 0) {
                    Mmu_view.main(null);
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

    public static void displayMmuDetailsTrash(Mmu_mod mmu) {
        System.out.println("\nDétails de l'association entre une maintenance et un véhicule sélectionné :");
        System.out.println("Type de maintenance : " + mmu.getTypeMaint());
        System.out.println("Description de la maintenance : " + mmu.getDescriptionMaint());
        System.out.println("Prix maintenance : " + mmu.getPriceMaint());
        System.out.println("Immatriculation du véhicule : " + mmu.getNumbPlateVehicle());
        System.out.println("Marque du véhicule : " + mmu.getMarkVeh());
        System.out.println("Modèle du véhicule : " + mmu.getModelVeh());
        System.out.println("coleur du véhicule : " + mmu.getColorVeh());
        System.out.println("Année du véhicule : " + mmu.getYearVeh());
        System.out.println("Date d'ajout  : " + mmu.getAdd_date());
        TrashMmuQuestion(mmu);
    }

    public static void TrashMmuQuestion(Mmu_mod mmu) {
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
                    mmuController.RestoreMmuTrash(mmu);
                    System.out.println("Association restoré avec succès");
                } else if (usrchoice == 3) {
                    listMmuTrash();
                    return;
                } else {
                    YoN = true;
                    SureMmuDelete(mmu);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void SureMmuDelete(Mmu_mod mmu) {
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
                    listMmuTrash();
                    return;
                } else {
                    YoN = true;
                    mmuController.SureMmuDelete(mmu);
                    System.out.println("Association supprimer avec succès");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void QuestionSearchMmuDetails() {
        System.out.print("Veuillez entrer l'immatriculation du véhicule : ");
        String npv = scanner.next() + scanner.nextLine();
        displaySearchMmuDetails(npv);
    }

    public static void displaySearchMmuDetails(String npv) {
        List<Mmu_mod> mmus = mmuController.searchMmu(npv);

        if (mmus.isEmpty()) {
            System.out.println("Aucune maintenance trouvée pour l'immatriculation : " + npv);
            return;
        }

        String marque = mmus.get(0).getMarkVeh();
        String modele = mmus.get(0).getModelVeh();
        int annee = mmus.get(0).getYearVeh();
        String couleur = mmus.get(0).getColorVeh();
        String dateAjout = mmus.get(0).getAdd_date();

        StringBuilder maintenanceTypes = new StringBuilder();
        double totalPrice = 0.0;
        int maintenanceCount = mmus.size();

        for (Mmu_mod mmu : mmus) {
            maintenanceTypes.append(mmu.getTypeMaint()).append(", ");
            totalPrice += mmu.getPriceMaint();
        }

        // Remove the trailing comma and space
        if (maintenanceTypes.length() > 0) {
            maintenanceTypes.setLength(maintenanceTypes.length() - 2);
        }

        System.out.println("MARQUE : " + marque);
        System.out.println("MODÈLE: " + modele);
        System.out.println("ANNÉE: " + annee);
        System.out.println("COULEUR: " + couleur);
        System.out.println("MAINTENANCE : " + maintenanceTypes.toString());
        System.out.println("NOMBRE DE MAINTENANCE : " + maintenanceCount);
        System.out.println("PRIX TOTAL MAINTENANCE : " + totalPrice);
        System.out.println("DATE D'AJOUT: " + dateAjout);
    }

    public static void displayAndSelectMmu() {
        List<Mmu_mod> mmus = mmuController.getAllMmu();
        if (mmus == null || mmus.isEmpty()) {
            System.out.println("Aucune maintenance disponible.");
            return;
        }

        for (int i = 0; i < mmus.size(); i++) {
            Mmu_mod mmu = mmus.get(i);
            System.out.println((i + 1) + ". " + mmu.getMarkVeh() + " " + mmu.getModelVeh() + " ("
                    + mmu.getNumbPlateVehicle() + ")");
        }

        System.out.print("Sélectionnez le numéro du véhicule désiré : ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice < 1 || choice > mmus.size()) {
            System.out.println("Sélection non valide.");
            return;
        }

        Mmu_mod selectedMmu = mmus.get(choice - 1);
        displayAndUpdateMaintenance(selectedMmu);
    }

    public static void displayAndUpdateMaintenance(Mmu_mod mmu) {
        List<Maint_mod> maints = mmuController.getAllMaintenance();
        if (maints == null || maints.isEmpty()) {
            System.out.println("Aucune maintenance disponible.");
            return;
        }

        for (int i = 0; i < maints.size(); i++) {
            Maint_mod maint = maints.get(i);
            System.out.println(
                    (i + 1) + ". " + maint.getType() + " - " + maint.getDescription() + " (" + maint.getPrice() + "€)");
        }

        System.out.print("Sélectionnez le numéro de la maintenance désirée : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > maints.size()) {
            System.out.println("Sélection non valide.");
            return;
        }

        Maint_mod selectedMaint = maints.get(choice - 1);
        boolean isAlreadyAssociated = mmuController.isMaintenanceAlreadyAssociated(mmu.getIdVeh(),
                selectedMaint.getType());
        if (isAlreadyAssociated) {
            System.out.println("Cette maintenance est déjà associée à ce véhicule.");
            return;
        }

        mmu.setTypeMaint(selectedMaint.getType());
        mmu.setDescriptionMaint(selectedMaint.getDescription());
        mmu.setPriceMaint(selectedMaint.getPrice());

        boolean success = mmuController.updateMmu(mmu);
        if (success) {
            System.out.println("La maintenance a été mise à jour avec succès.");
        } else {
            System.out.println("Erreur lors de la mise à jour de la maintenance.");
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Mmu_view mmuView = new Mmu_view();
        boolean quit = false;

        while (!quit) {

            int choice = userChoice();

            switch (choice) {
                case 1:
                    addAssociationVeh();
                    break;
                case 2:
                    displayAndSelectMmu();
                    break;
                case 3:
                    listMmuDel();
                    break;
                case 4:
                    QuestionSearchMmuDetails();
                    break;
                case 5:
                    listMmuTrash();
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
