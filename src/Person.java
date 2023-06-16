/**
 * Kelas Person merepresentasikan orang dalam sistem manajemen perpustakaan.
 * Setiap orang memiliki ID unik, nama, nomor KTP, alamat, dan jenis kelamin.
 */
class Person {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    private String ktp;

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    private String alamat;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    private String jenisKelamin;

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    /**
     * Konstruktor untuk objek kelas Person.
     *
     * @param id           ID orang.
     * @param nama         Nama orang.
     * @param ktp          KTP.
     * @param alamat       Alamat orang.
     * @param jenisKelamin Jenis kelamin orang.
     */
    public Person(int id, String nama, String ktp, String alamat, String jenisKelamin) {
        this.id = id;
        this.nama = nama;
        this.ktp = ktp;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
    }

}
