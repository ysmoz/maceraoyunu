import java.util.Scanner;

public class Player {
    Inventory inventory;
    private int damage;
    private int healthy, rHealthy;
    private int money;
    private int id;
    private String name, cName;
    Scanner scan = new Scanner(System.in);

    public Player(String name) {

        this.name = name;
        this.inventory = new Inventory();

    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealthy() {
        return healthy;
    }

    public void setHealthy(int healthy) {
        this.healthy = healthy;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getrHealthy() {
        return rHealthy;
    }

    public void setrHealthy(int rHealthy) {
        this.rHealthy = rHealthy;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int charMenu() {
        System.out.println("-----------KARAKTER SINIFINI SEÇİNİZ----------");
        System.out.println("1- Samurai \t Damage : 5 \t Health :21 \t Money :25");
        System.out.println("2- Archer \t Damage : 7 \t Health :18 \t Money :30");
        System.out.println("3- Knight \t Damage : 8 \t Health :24 \t Money :15");
        System.out.print("Enter your choice : ");
        int chaID = scan.nextInt();

        while (chaID < 1 || chaID > 3) {
            System.out.print("Please enter a valid input :");
            chaID = scan.nextInt();
        }
        return chaID;
    }

    public int getTotalDamage() {
        return this.getDamage() + this.getInventory().getWeaponDamage();
    }

    public void selectChar() {
        switch (charMenu()) {
            case 1:
                System.out.println();
                System.out.println("samuray karakterini seçtiniz ...");
                this.name = "samuray " + getName();
                this.damage = 5;
                this.rHealthy = 21;
                this.healthy = 21;
                this.money = 25;
                System.out.println("Hasar :" + this.damage + " Sağlık :" + this.healthy + " Altın :" + this.money);
                break;
            case 2:
                System.out.println();
                System.out.println("okçu karakterini seçtiniz ...");
                this.name = "okçu " + getName();
                this.damage = 7;
                this.healthy = 18;
                this.rHealthy = 18;
                this.money = 30;
                System.out.println("Hasar :" + this.damage + " Sağlık :" + this.healthy + " Altın :" + this.money);
                break;
            case 3:
                System.out.println();
                System.out.println("şovalye karakterini seçtiniz ...");
                this.name = "şovalye " + getName();
                this.damage = 8;
                this.healthy = 24;
                this.rHealthy = 24;
                this.money = 15;
                System.out.println("Hasar :" + this.damage + " Sağlık :" + this.healthy + " Altın :" + this.money);
                break;
            default:
                System.out.println("hatalı giriş ...!");
        }
    }
}