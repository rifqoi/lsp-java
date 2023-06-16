/**
 * Kelas Buku merepresentasikan buku dalam sistem manajemen perpustakaan.
 * Setiap buku memiliki ID unik, judul, penulis, jumlah stok, dan jumlah buku
 * yang sedang dipinjam.
 */
public class Buku {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String penulis;

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    private String judul;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    private int stok;

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    private int dipinjam;

    public int getDipinjam() {
        return this.dipinjam;
    }

    public void setDipinjam(int dipinjam) {
        this.dipinjam = dipinjam;
    }

    /**
     * Konstruktor untuk objek kelas Buku.
     *
     * @param id       ID buku.
     * @param penulis  Nama penulis buku.
     * @param judul    Judul buku.
     * @param stok     Jumlah buku yang tersedia di perpustakaan.
     * @param dipinjam Jumlah buku yang sedang dipinjam oleh anggota perpustakaan.
     */
    public Buku(int id, String penulis, String judul, int stok, int dipinjam) {
        this.id = id;
        this.penulis = penulis;
        this.judul = judul;
        this.stok = stok;
        this.dipinjam = dipinjam;
    }

    /**
     * Mengurangi stok buku dengan 1 saat buku dipinjam oleh anggota.
     */
    public void decreaseStok() throws Exception {
        if (this.stok > 0) {
            this.stok--;
            return;
        }

        throw new Exception("Buku " + this.judul + " tidak ada stok.");
    }

    /**
     * Menambah stok buku dengan 1 saat buku dikembalikan oleh anggota.
     */
    public void increaseStok() {
        this.stok++;
    }

    /**
     * Menambah jumlah buku yang sedang dipinjam oleh anggota dengan 1 saat buku
     * dipinjam.
     */
    public void increaseDipinjam() throws Exception {
        if (this.stok <= 0) {
            throw new Exception("Tidak bisa meminjam. " + "Buku " + this.judul + " tidak ada stok.");
        }

        if (this.dipinjam == this.stok) {
            throw new Exception("Buku " + this.judul + " habis dipinjam.");
        }

        this.dipinjam++;
    }

    /**
     * Mengurangi jumlah buku yang sedang dipinjam oleh anggota dengan 1 saat buku
     * dikembalikan.
     */
    public void decreaseDipinjam() throws Exception {
        if (this.stok <= 0) {
            throw new Exception("Tidak bisa mengembalikan pinjaman. " + "Buku " + this.judul + " tidak ada stok.");
        }

        this.dipinjam--;
    }

    /**
     * Menampilkan informasi lengkap mengenai buku.
     */
    public void printInfoBuku() {
        System.out.println("ID\t\t\t: " + this.id);
        System.out.println("Judul\t\t\t: " + this.judul);
        System.out.println("Penulis\t\t\t: " + this.penulis);
        System.out.println("Stok\t\t\t: " + this.stok);
        System.out.println("Dipinjam\t\t: " + this.dipinjam);
    }

}
