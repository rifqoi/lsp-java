import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConsoleClient {
    private Scanner sc;
    private ArrayList<BukuDipinjam> listBuku;
    private ArrayList<Anggota> listAnggota;

    public ConsoleClient(Scanner sc) {
        this.sc = sc;

        this.listBuku = getBuku();
        this.listAnggota = getAnggota();
    }

    private ArrayList<Anggota> getAnggota() {
        ArrayList<Anggota> listAnggota = new ArrayList<Anggota>();

        Path path = Paths.get("anggota.json");
        try {
            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            JSONArray arrJson = new JSONArray(content);

            for (int i = 0; i < arrJson.length(); i++) {
                JSONObject obj = arrJson.getJSONObject(i);

                int id = obj.getInt("id");
                String nama = obj.getString("nama");
                String ktp = obj.getString("ktp");
                String alamat = obj.getString("alamat");
                String jenisKelamin = obj.getString("jenisKelamin");
                Anggota anggota = new Anggota(id, nama, ktp, alamat, jenisKelamin);
                listAnggota.add(anggota);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return listAnggota;
    }

    private void writeAnggota(Anggota anggota) {
        Path path = Paths.get("anggota.json");
        try {
            File file = path.toFile();
            System.out.println(file.exists());
            if (!file.exists()) {
                file.createNewFile();
            }

            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            JSONArray arrJson;
            if (content.length() == 0) {
                arrJson = new JSONArray();
            } else {
                arrJson = new JSONArray(content);

            }
            JSONObject obj = new JSONObject();

            obj.put("id", anggota.getId());
            obj.put("nama", anggota.getNama());
            obj.put("ktp", anggota.getKtp());
            obj.put("alamat", anggota.getAlamat());
            obj.put("jenisKelamin", anggota.getJenisKelamin());

            System.out.println("masuk2");
            arrJson.put(obj);
            System.out.println(arrJson);

            // Write JSON file
            try (FileWriter fw = new FileWriter(path.toFile())) {
                // We can write any JSONArray or JSONObject instance to the file
                fw.write(arrJson.toString());
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Failed to writing new anggota to anggota.json " + e);
        }

    }

    private ArrayList<BukuDipinjam> getBuku() {
        ArrayList<BukuDipinjam> listBuku = new ArrayList<BukuDipinjam>();

        Path path = Paths.get("buku.json");
        try {
            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            JSONArray arrJson = new JSONArray(content);

            for (int i = 0; i < arrJson.length(); i++) {
                JSONObject obj = arrJson.getJSONObject(i);

                int id = obj.getInt("id");
                String penulis = obj.getString("penulis");
                String judul = obj.getString("judul");
                int stok = obj.getInt("stok");
                int dipinjam = obj.getInt("dipinjam");
                BukuDipinjam buku = new BukuDipinjam(id, penulis, judul, stok, dipinjam);
                listBuku.add(buku);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(listBuku.toString());

        return listBuku;
    }

    private void writeBuku(BukuDipinjam buku) {
        Path path = Paths.get("buku.json");
        try {
            File file = path.toFile();
            System.out.println(file.exists());
            if (!file.exists()) {
                file.createNewFile();
            }

            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            JSONArray arrJson;
            if (content.length() == 0) {
                arrJson = new JSONArray();
            } else {
                arrJson = new JSONArray(content);

            }
            JSONObject obj = new JSONObject();

            obj.put("id", buku.getId());
            obj.put("penulis", buku.getPenulis());
            obj.put("judul", buku.getJudul());
            obj.put("stok", buku.getStok());
            obj.put("dipinjam", buku.getDipinjam());

            arrJson.put(obj);

            // Write JSON file
            try (FileWriter fw = new FileWriter("buku.json")) {
                // We can write any JSONArray or JSONObject instance to the file
                fw.write(arrJson.toString());
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Failed to writing new buku to buku.json");
        }

    }

    public void showMenuPeminjaman() {

        while (true) {
            System.out.println("===== MENU BUKU =====");
            System.out.println("1. Pinjam Buku.");
            System.out.println("2. Lihat semua peminjam.");
            System.out.println("0. Kembali ke menu");
            System.out.print("PILIH [0-1]: ");
            String pilihan = this.sc.nextLine();

            switch (pilihan.strip()) {
                case "1":
                    pinjamBuku();
                    continue;
                case "2":
                    lihatPeminjam();
                    continue;
                case "0":
                    System.out.println("Pindah ke Menu Perpustakaan.");
                    System.out.println();
                    return;
            }

        }
    }

    private void lihatPeminjam() {
        if (this.listBuku.size() < 1) {
            System.out.println("Tidak ada buku yang bisa dipinjam.\n");
            return;
        }

        this.listSemuaBuku();
        int idInt;

        BukuDipinjam buku;
        while (true) {

            System.out.print("Pilih buku yang ingin dipinjam\t: ");

            String id = this.sc.nextLine();
            try {
                idInt = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("Masukkan ID yang benar.");
                continue;
            }

            buku = this.findBukuWithID(idInt);
            if (buku == null) {
                System.out.println("Tidak ada buku dengan ID " + id);
                continue;
            }

            break;
        }

        for (int i = 0; i < buku.getPeminjam().size(); i++) {
            Anggota ang = buku.getPeminjam().get(i);

            int id = i + 1;
            System.out.printf("%d. %s\n", id, ang.getNama());
            // System.out.println(id + ". " + ang.getNama());
        }
        System.out.println();
        System.out.println();

    }

    public void pinjamBuku() {

        if (this.listBuku.size() < 1) {
            System.out.println("Tidak ada buku yang bisa dipinjam.\n");
            return;
        }

        this.listSemuaBuku();
        int idInt;

        BukuDipinjam buku;
        while (true) {

            System.out.print("Pilih buku yang ingin dipinjam\t: ");

            String id = this.sc.nextLine();
            try {
                idInt = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("Masukkan ID yang benar.");
                continue;
            }

            buku = this.findBukuWithID(idInt);
            if (buku == null) {
                System.out.println("Tidak ada buku dengan ID " + id);
                continue;
            }

            break;
        }

        System.out.println();
        if (this.listAnggota.size() < 1) {
            System.out.println("Tidak ada anggota terdaftar.\n");
            return;
        }

        this.listSemuaAnggota();
        Anggota anggota = promptPilihAnggota();

        try {
            buku.addPeminjam(anggota);
            this.listSemuaBuku();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void showMenuAnggota() {

        while (true) {
            System.out.println("===== MENU BUKU =====");
            System.out.println("1. Tambah Anggota.");
            System.out.println("2. Hapus Anggota.");
            System.out.println("3. Lihat Semua Anggota.");
            System.out.println("0. Kembali ke menu");
            System.out.print("PILIH [0-3]: ");
            String pilihan = this.sc.nextLine();

            switch (pilihan.strip()) {
                case "1":
                    showTambahAnggota();
                    continue;
                case "2":
                    this.removeAnggota();
                    continue;
                case "3":
                    listSemuaAnggota();
                    continue;
                case "0":
                    System.out.println("Pindah ke Menu Perpustakaan.");
                    System.out.println();
                    return;
            }

        }
    }

    private Anggota findAnggotaWithID(int id) {
        Anggota anggota = null;
        for (Anggota ang : this.listAnggota) {
            if (ang.getId() == id) {
                anggota = ang;
            }
        }

        return anggota;
    }

    private Anggota promptPilihAnggota() {

        Anggota anggota = null;
        int idInt;
        while (true) {

            System.out.print("Pilih anggota yang ingin dipilih\t: ");

            String id = this.sc.nextLine();
            try {
                idInt = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("Masukkan ID yang benar.");
                continue;
            }

            anggota = this.findAnggotaWithID(idInt);
            if (anggota == null) {
                System.out.println("Tidak ada anggota dengan ID " + id);
                continue;
            }

            break;
        }

        return anggota;
    }

    private void removeAnggota() {
        this.listSemuaAnggota();

        Anggota anggota = this.promptPilihAnggota();
        this.listAnggota.remove(anggota);

    }

    private void showTambahAnggota() {
        System.out.println("===== TAMBAH Anggota =====");
        Anggota anggota = this.inputAnggota();
        listAnggota.add(anggota);
        this.writeAnggota(anggota);

        System.out.printf("\nAnggota %s dengan ID %d berhasil ditambah!\n", anggota.getNama(), anggota.getId());
    }

    private Anggota inputAnggota() {

        String namaAnggota = null;
        String ktp = null;
        String alamat = null;
        String jenisKelamin = null;
        Anggota anggota;

        while (true) {

            System.out.print("Nama Anggota\t\t: ");
            if (namaAnggota != null) {
                System.out.println(namaAnggota);
            } else {
                namaAnggota = this.sc.nextLine();
            }
            System.out.print("Nomor KTP\t\t: ");
            if (ktp != null) {
                System.out.println(ktp);
            } else {
                ktp = this.sc.nextLine();
            }

            System.out.print("Alamat\t\t\t: ");
            if (alamat != null) {
                System.out.println(alamat);
            } else {
                alamat = this.sc.nextLine();
            }

            System.out.print("Jenis Kelamin [L atau P]\t: ");
            if (jenisKelamin != null) {
                System.out.println(jenisKelamin);
            } else {
                jenisKelamin = this.sc.nextLine();

                switch (jenisKelamin) {
                    case "L":
                        break;
                    case "P":
                        break;
                    default:
                        System.out.println(jenisKelamin);
                        System.out.println(jenisKelamin.strip().equals("L"));
                        System.out.println("Masukkan Jenis Kelamin yang sesuai.");
                        jenisKelamin = null;
                        continue;

                }
            }

            int id = listAnggota.size() + 1;

            anggota = new Anggota(id, namaAnggota, ktp, alamat, jenisKelamin);
            break;
        }

        return anggota;
    }

    private void listSemuaAnggota() {
        String leftAlignFormat = "| %-3s | %-40s | %-18s | %-20s | %-15s |%n";

        String line = "+--------------------------------------------------------------------------------------------------------------+";
        System.out.println(line);
        System.out.format(leftAlignFormat, "ID", "Nama", "Alamat", "Nomor KTP", "Jenis Kelamin");
        System.out.println(line);

        for (Anggota anggota : this.listAnggota) {
            System.out.format(leftAlignFormat,
                    anggota.getId(),
                    anggota.getNama(),
                    anggota.getAlamat(),
                    anggota.getKtp(),
                    anggota.getJenisKelamin());
            System.out.println(line);
        }
    }

    public void showMenuBuku() {
        while (true) {
            System.out.println("===== MENU BUKU =====");
            System.out.println("1. Tambah Buku.");
            System.out.println("2. Edit Buku.");
            System.out.println("3. Hapus Buku.");
            System.out.println("4. Lihat Semua Buku.");
            System.out.println("0. Kembali ke menu");
            System.out.print("PILIH [0-4]: ");
            String pilihan = this.sc.nextLine();
            switch (pilihan.strip()) {
                case "1":
                    showTambahBuku();
                    System.out.println();
                    break;
                case "2":
                    showEditBuku();
                    System.out.println();
                    break;
                case "3":
                    deleteBuku();
                    System.out.println();
                    break;
                case "4":
                    listSemuaBuku();
                    System.out.println();
                    break;
                case "0":
                    System.out.println("Pindah ke Menu Perpustakaan.");
                    System.out.println();
                    return;
                default:
                    System.out.println("Silahkan pilih dari angka [0-4]");
                    System.out.println();
            }
        }
    }

    private BukuDipinjam findBukuWithID(int id) {
        BukuDipinjam buku = null;
        for (BukuDipinjam n : this.listBuku) {
            if (id == n.getId()) {
                buku = n;
            }
        }

        return buku;
    }

    private void listSemuaBuku() {
        String leftAlignFormat = "| %-3s | %-40s | %-18s | %-20s | %-15s |%n";

        String line = "+--------------------------------------------------------------------------------------------------------------+";
        System.out.println(line);
        System.out.format(leftAlignFormat, "ID", "Nama Buku", "Nama Penulis", "Stok Buku", "Jumlah Peminjam");
        System.out.println(line);

        for (Buku buku : this.listBuku) {
            System.out.format(leftAlignFormat,
                    buku.getId(),
                    buku.getJudul(),
                    buku.getPenulis(),
                    buku.getStok(),
                    buku.getDipinjam());
            System.out.println(line);
        }
    }

    private BukuDipinjam inputBuku() {

        String namaBuku = null;
        String namaPenulis = null;
        int stokBukuInt = -1;
        BukuDipinjam buku;

        while (true) {

            System.out.print("Nama Buku\t: ");
            if (namaBuku != null) {
                System.out.println(namaBuku);
            } else {
                namaBuku = this.sc.nextLine();
            }

            System.out.print("Nama Penulis\t: ");
            if (namaPenulis != null) {
                System.out.println(namaBuku);
            } else {
                namaPenulis = this.sc.nextLine();
            }

            System.out.print("Jumlah Buku\t: ");
            if (stokBukuInt != -1) {
                System.out.println(stokBukuInt);
            } else {
                String stokBuku = this.sc.nextLine();

                try {
                    stokBukuInt = Integer.parseInt(stokBuku);
                } catch (NumberFormatException e) {
                    System.out.println("Jumlah buku harus merupakan bilangan angka");
                    System.out.println();
                    continue;
                }
            }

            int id = listBuku.size() + 1;

            buku = new BukuDipinjam(id, namaPenulis, namaBuku, stokBukuInt, 0);
            break;
        }

        return buku;
    }

    private void showEditBuku() {
        this.listSemuaBuku();
        int idInt;
        while (true) {

            System.out.print("Pilih buku yang ingin diedit\t: ");

            String id = this.sc.nextLine();
            try {
                idInt = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("Masukkan ID yang benar.");
                continue;
            }

            Buku bukuToEdit = this.findBukuWithID(idInt);
            if (bukuToEdit == null) {
                System.out.println("Tidak ada buku dengan ID " + id);
                continue;
            }

            Buku newBuku = inputBuku();
            bukuToEdit.setJudul(newBuku.getJudul());
            bukuToEdit.setPenulis(newBuku.getPenulis());
            bukuToEdit.setStok(newBuku.getStok());

            break;
        }

    }

    private void showTambahBuku() {
        System.out.println("===== TAMBAH BUKU =====");
        BukuDipinjam buku = this.inputBuku();
        listBuku.add(buku);
        this.writeBuku(buku);

        System.out.printf("\nBuku %s dengan ID %d berhasil ditambah!\n", buku.getJudul(), buku.getId());
    }

    private void deleteBuku() {
        this.listSemuaBuku();
        int idInt;
        while (true) {

            System.out.print("Pilih buku yang ingin dihapus\t: ");

            String id = this.sc.nextLine();
            try {
                idInt = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("Masukkan ID yang benar.");
                continue;
            }

            Buku buku = this.findBukuWithID(idInt);
            if (buku == null) {
                System.out.println("Tidak ada buku dengan ID " + id);
                continue;
            }

            this.listBuku.remove(buku);

            break;
        }
    }
}
