import java.util.ArrayList;

/**
 * Kelas BukuDipinjam merupakan turunan dari kelas Buku dan merepresentasikan
 * buku yang sedang dipinjam.
 * Selain atribut yang sama dengan kelas Buku, kelas ini memiliki atribut
 * peminjam yang merupakan daftar anggota yang meminjam buku.
 */
public class BukuDipinjam extends Buku {
    private ArrayList<Anggota> peminjam;

    /**
     * Mendapatkan daftar peminjam buku.
     *
     * @return ArrayList yang berisi daftar peminjam buku.
     */
    public ArrayList<Anggota> getPeminjam() {
        return peminjam;
    }

    /**
     * Mengatur daftar peminjam buku.
     *
     * @param peminjam ArrayList yang berisi daftar peminjam buku.
     */
    public void setPeminjam(ArrayList<Anggota> peminjam) {
        this.peminjam = peminjam;
    }

    /**
     * Menambahkan anggota sebagai peminjam buku.
     *
     * @param peminjam Anggota yang akan ditambahkan sebagai peminjam buku.
     * @throws Exception Jika buku sudah dipinjam oleh anggota yang sama sebelumnya.
     */
    public void addPeminjam(Anggota peminjam) throws Exception {
        for (Anggota existingPeminjam : this.peminjam) {
            if (peminjam.getId() == existingPeminjam.getId()) {
                throw new Exception("Buku sudah dipinjam oleh " + peminjam.getNama());
            }
        }

        this.increaseDipinjam();
        this.peminjam.add(peminjam);
    }

    /**
     * Mengurangi anggota dari daftar peminjam buku.
     *
     * @param peminjam Anggota yang akan dihapus dari daftar peminjam buku.
     * @throws Exception Jika anggota tidak ada dalam daftar peminjam buku.
     */
    public void decreasePeminjam(Anggota peminjam) throws Exception {
        this.decreaseDipinjam();
        this.peminjam.remove(peminjam);
    }

    /**
     * Konstruktor untuk objek kelas BukuDipinjam.
     *
     * @param id       ID buku.
     * @param penulis  Nama penulis buku.
     * @param judul    Judul buku.
     * @param stok     Jumlah buku yang tersedia di perpustakaan.
     * @param dipinjam Jumlah buku yang sedang dipinjam oleh anggota perpustakaan.
     */
    public BukuDipinjam(int id, String penulis, String judul, int stok, int dipinjam) {
        super(id, penulis, judul, stok, dipinjam);
        this.peminjam = new ArrayList<Anggota>();
    }

    @Override
    public void printInfoBuku() {
        super.printInfoBuku();

        ArrayList<String> namaPeminjam = new ArrayList<String>();
        this.peminjam.forEach((n) -> namaPeminjam.add(n.getNama()));

        String semuaPeminjam = String.join(", ", namaPeminjam);
        System.out.println("Peminjam\t\t: " + semuaPeminjam);
    }

}
