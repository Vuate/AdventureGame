public abstract class BattleLoc extends Location {
    protected Obstacle obstacle;
    protected String award;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award) {
        super(player);
        this.obstacle = obstacle;
        this.name = name;
        this.award = award;
    }

    public boolean getLocation() {
        int obsCount = obstacle.count();
        System.out.println("Şuan buradasınız :" + this.getName());
        System.out.println("Dikkatli ol! burada " + obsCount + " tane " + obstacle.getName() + " Yaşıyor!");
        System.out.print("<S>avaş veya <K>aç :");
        String selCase = scan.nextLine();
        selCase = selCase.toUpperCase();
        if (selCase.equals("S")) {
            if (combat(obsCount)) {
                System.out.println(this.getName() + " Bölgesindeki tüm düşmanları temizlediniz !");
                if (this.award.equals("Food") && player.getInv().isFood() == false) {
                    System.out.println(this.award + "Kazandınız!");
                    player.getInv().setFood(true);
                } else if (this.award.equals("Water") && player.getInv().isWater() == false) {
                    System.out.println(this.award + "Kazandınız!");
                    player.getInv().setWater(true);
                } else if (this.award.equals("Firewood") && player.getInv().isFirewood() == false) {
                    System.out.println(this.award + "Kazandınız!");
                    player.getInv().setFirewood(true);

                }
                return true;
            }
            if (player.getHealthy() <= 0) {
                System.out.println("Öldünüz !");
                return false;
            }

        }
        return true;

    }

    public boolean combat(int obsCount) {
        for (int i = 0; i < obsCount; i++) {
            int defObsHealt = obstacle.getHealth();
            playerStats();
            enemyStats();
            while (player.getHealthy() > 0 && obstacle.getHealth() > 0) {
                System.out.print("<V>ur veya <K>aç :");
                String selCase = scan.nextLine();
                selCase = selCase.toUpperCase();
                if (selCase.equals("V")) {
                    System.out.println("Siz vurdunuz !");
                    obstacle.setHealth(obstacle.getHealth() - player.getTotalDamage());
                    afterHit();
                    if (obstacle.getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar size vurdu !");
                        player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInv().getArmor()));
                        afterHit();
                    } else {
                        return true;
                    }
                } else if (selCase.equals("K")) { // Kaçma durumunu kontrol et
                    System.out.println("Savaştan kaçtınız!");
                    return false;  // Savaştan çıkmak için false döndür


                }

            }
            if (obstacle.getHealth() < player.getHealthy()) {
                System.out.println(" Düşmanı yendiniz !");
                player.setMoney(player.getMoney() + obstacle.getAward());
                System.out.println("Güncel paranız : " + player.getMoney());
                obstacle.setHealth(defObsHealt);


            } else {
                return true;
            }
            System.out.println("--------------------");
        }
        return true;
    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri\n------------");
        System.out.println("Can:" + player.getHealthy());
        System.out.println("Hasar:" + player.getTotalDamage());
        System.out.println("Para:" + player.getMoney());
        if (player.getInv().getDamage() > 0) {
            System.out.println("Silah:" + player.getInv().getwName());
        }
        if (player.getInv().getArmor() > 0) {
            System.out.println("Zırh:" + player.getInv().getaName());
        }
    }

    public void enemyStats() {
        System.out.println("\n" + obstacle.getName() + "Değerleri\n------------");
        System.out.println("Can:" + obstacle.getHealth());
        System.out.println("Hasar:" + obstacle.getDamage());
        System.out.println("Ödül:" + obstacle.getAward());

    }

    public void afterHit() {
        System.out.println("Oyuncu canı:" + player.getHealthy());
        System.out.println(obstacle.getName() + "Canı:" + obstacle.getHealth());
        System.out.println();

    }
}