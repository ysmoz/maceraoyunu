import java.util.Scanner;

public class Game {
    Location location;
    Player player;

    public void start() {

        String selectName;
        Scanner scan = new Scanner(System.in);
        System.out.println("****************OYUN BAŞLADI****************");
        System.out.println();
        System.out.println("Enter your nickname: ");
        selectName = scan.nextLine();
        Player player = new Player(selectName);
        player.selectChar();
        System.out.println("HOŞ GELDİN " + player.getName());
        while (true) {
            System.out.println();
            System.out.println("Choose a location : ");
            System.out.println();
            System.out.println("1. Safe House --> Your safe place, no enemies !");
            System.out.println("2. Tool Store --> (altının yoksa deneme)");
            System.out.println("3. Forest --> You may see a Vampire  !");
            System.out.println("4. Cave --> You may see a Zombie");
            System.out.println("5. River--> you may see a Bear");
            System.out.println("6. Mines--> you may see a Snake");
            System.out.println("Pick the place you want to go : ");
            int select = scan.nextInt();

            while (select < 0 || select > 6) {
                System.out.print("Please enter a valid input : ");
                select = scan.nextInt();
            }
            switch (select) {

                case 2:
                    location = new Toolstore(player);
                    break;
                case 3:
                    if (!player.getInventory().isFirewood())
                        location = new Forest(player);
                    else {
                        System.out.println("You cannot enter that area again! ");
                    }
                    break;
                case 4:
                    if (!player.getInventory().isFood())
                        location = new Cave(player);
                    else{
                        System.out.println("You cannot enter that area again! ");
                    }
                    break;
                case 5:
                    if(!player.getInventory().isWater())
                        location = new River(player);
                    else{
                        System.out.println("You cannot enter that area again! ");
                    }
                    break;
                case 6:
                    location = new Mines(player);
                    break;
                default:
                    location = new SafeHouse(player);

            }
            if (location.getClass().getName().equals("SafeHouse")) {
                if (player.getInventory().isFirewood() && player.getInventory().isFood() && player.getInventory().isWater()) {
                    System.out.println("TEBRİKLER KAZANDINIZ");
                    break;
                }
            }
            if (!location.onLocation()) {
                System.out.println("Game Over !");
                break;
            }
        }
    }


}