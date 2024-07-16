package App;

import java.util.Scanner;

import Maintenance.Maint_view;
import Maintenance_Meet_Up.Mmu_view;
import Reparation.Repa_view;
import Reparation_Meet_Up.Rmu_view;
import Vehicule.Veh_view;

import java.util.InputMismatchException;

public class App {
    public static Scanner scanner = new Scanner(System.in);

    public static int UserWelcome() {
        int u_choice = 0;
        boolean isValidInputvf = false;

        while (!isValidInputvf) {
            System.out.println("\nBienvenue sur le système de gestion de parc automobile.");
            System.out.println(
                    "\n---Menu---\n1- Gérer les véhicule\n2- Gérer les maintenances\n3- Gérer les réparations\n4- Gérer les relations entre véhicules et maintenances\n5- Gérer les relations entre véhicules et réparations\n6- Quitter");
            System.out.print("Votre choix : ");
            try {
                u_choice = scanner.nextInt();
                if (u_choice < 1 || u_choice > 6) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 6.");
                    scanner.nextLine();
                } else {
                    isValidInputvf = true;
                    return u_choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.nextLine();
            }
        }
        return u_choice;
    }

    public static void LeaveApp() {
        boolean YoN = false;
        int usrchoice = 0;

        while (!YoN) {
            System.out.println("Désirez-vous réellement quitter cette application ?\n1- Oui\n2- Non");
            System.out.print("Votre choix : ");
            try {
                usrchoice = scanner.nextInt();
                if (usrchoice < 1 || usrchoice > 2) {
                    System.out.println("\nVeuillez entrer un nombre entier entre 1 et 2.");
                } else if (usrchoice == 2) {
                    App.main(null);
                } else {
                    YoN = true;
                    System.out.println(
                            "\nMerci d'avoir utilisé notre application de gestion de parc automobile ! Au revoir !");
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nVeuillez entrer un nombre entier.");
                scanner.next();
            }
        }
    }

    public static void main(String[] args) {
        int u_welcome = UserWelcome();
        switch (u_welcome) {
            case 1:
                Veh_view.main(args);
                break;
            case 2:
                Maint_view.main(args);
                break;
            case 3:
                Repa_view.main(args);
                break;
            case 4:
                Mmu_view.main(args);
                break;
            case 5:
                Rmu_view.main(args);
                break;
            case 6:
                LeaveApp();
                break;

        }

    }

}
