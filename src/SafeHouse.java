public class SafeHouse extends NormalLoc {


    SafeHouse(Player player) {
        super(player, "Güvenli ev");
    }
    public boolean getLocation(){
    player.setHealthy(player.getrHealthy());
        System.out.println(" İyileştiniz... ");
        System.out.println("Şuan güvenli ev adlı yerdesiniz.");
    return true;
    }
}
