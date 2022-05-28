import java.util.Scanner;

public class Toolstore extends NormalLoc {
    public Toolstore(Player player) {
        super(player, "TOOL STORE");
    }

    @Override
    public boolean onLocation() {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Money : " + player.getMoney());
        System.out.println("EN İYİ SİLAHLAR İÇİN  1 ||EN İYİ ZIRHLAR İÇİN  2||ÇIKIŞ İÇİN 0");
        int select = scan.nextInt();
        int selItemNum;
        switch (select) {
            case 1:
                selItemNum = weaponMenu();
                buyWeapon(selItemNum);
                break;
            case 2:
                selItemNum = armorMenu();
                buyArmor(selItemNum);
                break;
            default:
                break;

        }
        return true;
    }


    public int armorMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("ZIRHLAR");
        System.out.println("Hafif Zırh  1 ---> HASAR : 2 --- PARA : 25");
        System.out.println("Orta Zırh   2 ---> HASAR : 3 --- PARA : 35");
        System.out.println("Ağır Zırh   3 ---> HASAR : 7 --- PARA : 45");
        System.out.println("GERİ 4");
        System.out.println("seciminiz ");
        return scan.nextInt();
    }

    public void buyArmor(int itemNum) {
        int avoid = 0, price = 0;
        String aName = null;

        switch (itemNum) {
            case 1:
                avoid = 1;
                aName = "Light Armour";
                price = 15;
                break;
            case 2:
                avoid = 3;
                aName = "Medium Armour";
                price = 25;
                break;
            case 3:
                avoid = 5;
                aName = "Heavy Armour";
                price = 40;
                break;
            case 4:
                System.out.println("Exiting.");
                break;
            default:
                System.out.println("Invalid Input !");
                break;
        }
        if (price > 0) {
            if (player.getMoney() >= price) {
                player.getInventory().setArmorDefence(avoid);
                player.getInventory().setArmorName(aName);
                player.setMoney(player.getMoney() - price);
                System.out.println("You've bought " + aName + " Defence : " + player.getInventory().getArmorDefence());
                System.out.println("Current Money :" + player.getMoney());
            } else {
                System.out.println("Not enough money !");
            }
        }
    }

    public int weaponMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("SİLAHLAR");
        System.out.println("Tabanca     1 ---> HASAR : 2 --- PARA : 15");
        System.out.println("Kılıç       2 ---> HASAR : 2 --- PARA : 25");
        System.out.println("Tüfek       3 ---> HASAR : 2 --- PARA : 40");
        System.out.println("GERİ 4");
        System.out.println("seciminiz ");

        return scan.nextInt();
    }

    public void buyWeapon(int itemNum) {
        int damage = 0, price = 0;
        String wName = null;

        switch (itemNum) {
            case 1:
                damage = 2;
                wName = "Gun";
                price = 25;
                break;
            case 2:
                damage = 3;
                wName = "Sword";
                price = 35;
                break;
            case 3:
                damage = 7;
                wName = "Rifle";
                price = 45;
                break;
            case 4:
                System.out.println("Exiting.");
                break;
            default:
                System.out.println("Invalid Input !");
                break;
        }

        if (price > 0) {
            if (player.getMoney() > price) {
                player.getInventory().setWeaponDamage(damage);
                player.getInventory().setWeaponName(wName);
                player.setMoney(player.getMoney() - price);
                System.out.println("You've bought " + wName + "  Old Damage :" + player.getDamage() + ", New Damage : "
                        + player.getTotalDamage());
                System.out.println("Current Money :" + player.getMoney());
            } else {
                System.out.println("Not Enough Money !");
            }
        }

    }


}