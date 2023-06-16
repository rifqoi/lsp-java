import java.util.Scanner;

public class PerpusApp {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        ConsoleClient consoleClient = new ConsoleClient(sc);

        while (true) {
            System.out.println("===== MENU PERPUSTAKAAN =====");
            System.out.println("1. Menu Buku");
            System.out.println("2. Menu Anggota");
            System.out.println("3. Menu Peminjaman");
            System.out.println("0. Keluar dari program");
            System.out.print("Pilih [0-3]: ");

            String pilihan = sc.nextLine();

            int pilihanInt;
            try {
                pilihanInt = Integer.parseInt(pilihan);
            } catch (Exception e) {
                System.out.println("Silahkan pilih menu 0-4");
                continue;

            }

            switch (pilihanInt) {
                case 1:
                    consoleClient.showMenuBuku();
                    break;
                case 2:
                    consoleClient.showMenuAnggota();
                    break;
                case 3:
                    consoleClient.showMenuPeminjaman();
                    break;
                case 0:
                    return;
            }

            if (pilihanInt == 0) {
                break;
            }

        }

        sc.close();

    }

}
