import java.util.Scanner;

public class Game {
    Player player;
    Location location;
    Scanner scan = new Scanner(System.in);


    public void login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Macera oyununa hoşgeldiniz !");
        System.out.println("Oyuna başlamadan önce isminizi giriniz : a ");
        //  String playerName = scan.nextLine();
        player = new Player("a");
        player.selectCha();
        start();
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("===========================================");
            System.out.println();
            System.out.println("Eylem gerçekleştirmek için bir yer seçiniz : ");
            System.out.println("1. Güvenli ev --> Size ait güvenli bir mekan, düşman yok !");
            System.out.println("2. Mağara --> Karşınıza belki zombi çıkabilir !");
            System.out.println("3. Orman --> Karşınıza belki vampir çıkabilir !");
            System.out.println("4. Nehir --> Karşnıza belki ayı çıkabilir !");
            System.out.println("5. Mağaza --> Silah veya Zırh alabilirsiniz !");
            System.out.print("Gitmek istediğiniz yer : ");
            int selLoc = scan.nextInt();
            while (selLoc < 0 || selLoc > 5) {
                System.out.print("Lütfen geçerli bir yer seçiniz : ");
                selLoc = scan.nextInt();    
            }
            switch (selLoc) {
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new Cave(player);
                    break;
                case 3:
                    location = new Forest(player);
                    break;
                case 4:
                    location = new River(player);
                    break;
                case 5:
                    location = new ToolStore(player);
                    break;
                default:
                    location = new SafeHouse(player);

            }
            if (location.getClass().getName().equals("Safe House")) {
                if (player.getInv().isFirewood() && player.getInv().isFood() && player.getInv().isWater()) {
                    System.out.println("Tebrikler oyunu kazandınız !");
                    break;
                }
            }
            if (location.getLocation()) {
                System.out.println("devam");
                System.out.println("---------------------");


            } else {
                System.out.println("Oyun bitti");
                break;
            }
        }
    }
}
