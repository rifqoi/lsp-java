/**
 * Kelas Anggota merupakan turunan dari kelas Person dan merepresentasikan
 * anggota perpustakaan.
 * Setiap anggota memiliki atribut yang sama dengan kelas Person.
 */
class Anggota extends Person {
    /**
     * Konstruktor untuk objek kelas Anggota.
     *
     * @param id           ID orang.
     * @param nama         Nama orang.
     * @param ktp          KTP.
     * @param alamat       Alamat orang.
     * @param jenisKelamin Jenis kelamin orang.
     */
    public Anggota(int id, String nama, String ktp, String alamat, String jenisKelamin) {
        super(id, nama, ktp, alamat, jenisKelamin);
    }

    /**
     * Menampilkan informasi lengkap mengenai anggota perpustakaan.
     */
    public void printInfoAnggota() {
        System.out.println("ID\t\t\t: " + this.getId());
        System.out.println("Nama\t\t\t: " + this.getNama());
        System.out.println("KTP\t\t\t: " + this.getKtp());
        System.out.println("Alamat\t\t\t: " + this.getAlamat());
        System.out.println("Jenis Kelamin\t\t: " + this.getJenisKelamin());
    }
}
