public class SafeHouse extends NormalLoc {

    public SafeHouse(Player player) {
        super(player, "SAFE HOUSE");
    }

    @Override
    public boolean onLocation() {
        player.setHealthy(player.getrHealthy());
        System.out.println("Healed up...");
        System.out.println("You are at safe house now");
        return true;
    }
}