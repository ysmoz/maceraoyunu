import java.util.Random;
import java.util.Scanner;

public abstract class BattleLoc extends Location {
    Obstacle obstacle;
    String award;

    BattleLoc(Player player, String name, Obstacle obstacle, String award) {
        super(player);
        this.name = name;
        this.obstacle = obstacle;
        this.award = award;
    }

    public int printStart() {
        int obsCount = obstacle.count();
        System.out.println("You are here now : " + this.getName());
        System.out.println("Be aware ! " + obsCount + " " + obstacle.getName() + "s lives there!");
        System.out.print("<F>ight or <R>un :");
        return obsCount;
    }

    @Override
    public boolean onLocation() {
        if (this.getName().equals("Mines")) {
            return getLocationMines();
        }
        Scanner scan = new Scanner(System.in);
        int obsCount = printStart();
        String selectCase = scan.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("F")) {
            if (combat(obsCount)) {
                System.out.println(this.getName() + " has been cleared !");
                if (this.award.equals("Food") && !player.getInventory().isFood()) {
                    System.out.println("You won " + this.award);
                    player.getInventory().setFood(true);
                } else if (this.award.equals("Water") && !player.getInventory().isWater()) {
                    System.out.println("You won " + this.award);
                    player.getInventory().setWater(true);
                } else if (this.award.equals("Firewood") && !player.getInventory().isFirewood()) {
                    System.out.println("You won " + this.award);
                    player.getInventory().setFirewood(true);
                }
                return true;
            }
            if (player.getHealthy() <= 0) {
                System.out.println("You died !");
                return false;
            }
        }
        return true;
    }

    public boolean getLocationMines() {
        Scanner scan = new Scanner(System.in);
        int obsCount = printStart();
        String selCase = scan.nextLine();
        selCase = selCase.toUpperCase();
        if (selCase.equals("F")) {
            if (combat(obsCount)) {
                System.out.println(this.getName() + " has been cleared !");

                Random random = new Random();
                int luckyNumber = random.nextInt(101);

                if (luckyNumber >= 55) {
                    System.out.println("You've won nothing");
                }

                else if(luckyNumber >= 30) {
                    System.out.println("You won random amount of money!");
                    luckyNumber = random.nextInt(101);
                    if (luckyNumber >= 50) {
                        System.out.println("You won 1 money! ");
                        player.setMoney(player.getMoney() + 1);
                    }
                    else if (luckyNumber >= 20) {
                        System.out.println("You won 3 money! ");
                        player.setMoney(player.getMoney() + 3);
                    }
                    else {
                        System.out.println("Jackpot! You won 10 money! ");
                        player.setMoney(player.getMoney() + 10);
                    }
                }

                else if (luckyNumber >= 15) {
                    System.out.println("You won random armour!");
                    luckyNumber = random.nextInt(101);
                    if (luckyNumber >= 50) {
                        System.out.println("You won light armour! ");
                        if (player.getInventory().getArmorDefence() > 1) {
                            System.out.println("But you have better armour! No changes");
                        }
                        else {
                            player.getInventory().setArmorDefence(1);
                            System.out.println("You equipped your armour !");
                        }
                    }
                    else if (luckyNumber >= 20) {
                        System.out.println("You won medium armour! ");
                        if (player.getInventory().getArmorDefence() > 3) {
                            System.out.println("But you have better armour! No changes");
                        }
                        else {
                            player.getInventory().setArmorDefence(3);
                            System.out.println("You equipped your armour !");
                        }
                    }
                    else {
                        System.out.println("You won heavy armour! ");
                        if (player.getInventory().getArmorDefence() > 5) {
                            System.out.println("But you have better armour! No changes");
                        }
                        else {
                            player.getInventory().setArmorDefence(5);
                            System.out.println("You equipped your armour !");
                        }
                    }
                }

                else {
                    System.out.println("You won random weapon!");
                    luckyNumber = random.nextInt(101);
                    if (luckyNumber >= 50) {
                        System.out.println("You won gun! ");
                        if (player.getInventory().getWeaponDamage() > 2) {
                            System.out.println("But you have better weapon! No changes");
                        }
                        else {
                            player.getInventory().setWeaponDamage(2);
                            System.out.println("You equipped your weapon !");
                        }
                    }
                    else if (luckyNumber >= 20) {
                        System.out.println("You won medium weapon! ");
                        if (player.getInventory().getWeaponDamage() > 3) {
                            System.out.println("But you have better armour! No changes");
                        }
                        else {
                            player.getInventory().setWeaponDamage(3);
                            System.out.println("You equipped your armour !");
                        }
                    }
                    else {
                        System.out.println("You won heavy armour! ");
                        if (player.getInventory().getWeaponDamage() > 7) {
                            System.out.println("But you have better armour! No changes");
                        }
                        else {
                            player.getInventory().setWeaponDamage(7);
                            System.out.println("You equipped your armour !");
                        }
                    }
                }

                return true;
            }

            if(player.getHealthy() <= 0) {
                System.out.println("You died !");
                return false;
            }

        }
        return true;
    }
    public boolean combat(int obsCount) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < obsCount; i++) {


            int defObsHealth = obstacle.getHealthy();
            playerStats();
            enemyStats();
            while (player.getHealthy() > 0 && obstacle.getHealthy() > 0) {
                System.out.print("<H>it or <R>un :");
                String selCase = scan.nextLine();
                selCase = selCase.toUpperCase();
                Random random = new Random();
                boolean playerStarts = random.nextInt(2) == 1;

                if (playerStarts) {
                    if (selCase.equals("H")) {
                        System.out.println("You hit !");
                        obstacle.setHealthy(obstacle.getHealthy() - player.getTotalDamage());
                        afterHit();
                        if (obstacle.getHealthy() > 0) {
                            System.out.println();
                            System.out.println("You got hit by the monster !");
                            player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInventory().getArmorDefence()));
                            afterHit();
                        }
                    } else {
                        return false;
                    }
                } else {
                    if (selCase.equals("H")) {
                        System.out.println("You got hit by the monster !");
                        player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInventory().getArmorDefence()));
                        afterHit();
                        if (obstacle.getHealthy() > 0) {
                            System.out.println();
                            System.out.println("You hit !");
                            obstacle.setHealthy(obstacle.getHealthy() - player.getTotalDamage());
                            afterHit();
                        }
                    } else {
                        return false;
                    }
                }
            }

            if (obstacle.getHealthy() < player.getHealthy()) {
                System.out.println("You've defeated the enemy !");
                player.setMoney(player.getMoney() + obstacle.getAward());
                System.out.println("Current Money : " + player.getMoney());
                obstacle.setHealthy(defObsHealth);
            } else {
                return false;
            }
            System.out.println("-------------------");
        }
        return true;
    }

    public void playerStats() {
        System.out.println("Player Information\n--------------");
        System.out.println("Health:" + player.getHealthy());
        System.out.println("Damage:" + player.getTotalDamage());
        System.out.println("Money:" + player.getMoney());
        if (player.getInventory().getWeaponDamage() > 0) {
            System.out.println("Weapon:" + player.getInventory().getWeaponName());
        }
        if (player.getInventory().getArmorDefence() > 0) {
            System.out.println("Armour:" + player.getInventory().getArmorName());
        }
    }

    public void enemyStats() {
        System.out.println("\n" + obstacle.getName() + " Information\n--------------");
        System.out.println("Health:" + obstacle.getHealthy());
        System.out.println("Damage:" + obstacle.getDamage());
        System.out.println("Award:" + obstacle.getAward());
    }

    public void afterHit() {
        System.out.println("Player's Health:" + player.getHealthy());
        System.out.println(obstacle.getName() + "'s Health:" + obstacle.getHealthy());
        System.out.println();
    }

}