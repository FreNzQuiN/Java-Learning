package Kuliah.Semester2;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Film {
    private String judul;
    private String genre;
    private LocalTime jamTayang;
    private double hargaTiket;
    
    public Film(String judul, String genre, LocalTime jamTayang, double hargaTiket) {
        this.judul = judul;
        this.genre = genre;
        this.jamTayang = jamTayang;
        this.hargaTiket = hargaTiket;
    }
    public void tampilkanInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String jamTayang = this.jamTayang.format(formatter);
        System.out.println("Judul: " + judul);
        System.out.println("Genre: " + genre);
        System.out.println("Jam Tayang: " + jamTayang);
        System.out.println("Harga Tiket: " + hargaTiket);
    }
}

class Studio {
    private int nomor;
    private int kapasitas;
    private Film film;

    public Studio(int nomor, int kapasitas, Film film) {
        this.nomor = nomor;
        this.kapasitas = kapasitas;
        this.film = film;
    }
    public void tampilkanInfo() {
        System.out.println("Nomor Studio: " + nomor);
        System.out.println("Kapasitas Kursi: " + kapasitas);
        film.tampilkanInfo();
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public int getKapasitas() {
        return kapasitas;
    }
}
class Penonton {
    private String nama;
    
    public Penonton(String nama) {
        this.nama = nama;
    }
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
    }
}
class Tiket {
    int nomor;
    Film film;
    Studio studio;
    int kursi;
    Penonton penonton;

    public Tiket(int nomor, Film film, Studio studio, int kursi, Penonton penonton) {
        this.nomor = nomor;
        this.film = film;
        this.studio = studio;
        if (kursi > studio.getKapasitas()) {
            System.out.println("Kursi Melebihi Kapasitas Studio");
            this.kursi = studio.getKapasitas();
        } else if(kursi < 1) {
            System.out.println("Kursi Tidak Boleh Kurang Dari 1");
            this.kursi = 1;
        } else {
            this.kursi = kursi;
        }
        this.kursi = kursi;
        this.penonton = penonton;
    }
    public void tampilkanInfo() {
        System.out.println("\n+++ Nomor Tiket: " + nomor + " +++");
        studio.tampilkanInfo();
        System.out.println("Kursi: " + kursi);
        penonton.tampilkanInfo();
    }
}

public class bioskop0 {
    public static void main(String[] args) {
        // Instansiasi
        Film film1 = new Film("The Avengers", "Action", LocalTime.of(10,0), 50000);
        Film film2 = new Film("The Conjuring", "Horror", LocalTime.of(13,0), 40000);
        Film film3 = new Film("The Lion King", "Animation", LocalTime.of(15,0), 30000);

        Studio studio1 = new Studio(1, 50, film1);
        Studio studio2 = new Studio(2, 40, film2);
        Studio studio3 = new Studio(3, 30, film3);

        Tiket  tiket1 = new Tiket(1, film1, studio1, 1, new Penonton("Budi"));
        Tiket  tiket2 = new Tiket(2, film2, studio2, 2, new Penonton("Andi"));
        Tiket  tiket3 = new Tiket(3, film3, studio3, 3, new Penonton("Caca"));

        // Menampilkan Informasi Hari Pertama
        System.out.println("====== Informasi Hari Pertama ======");
        tiket1.tampilkanInfo();
        tiket2.tampilkanInfo();
        tiket3.tampilkanInfo();

        // Merubah Film Hari Kedua
        studio1.setFilm(film2);
        studio2.setFilm(film3);
        studio3.setFilm(film1);

        Film film4 = new Film("The Fast and The Furious", "Action", LocalTime.of(12,0), 45000);
        Tiket tiket4 = new Tiket(4, film2, studio1, 4, new Penonton("Dedi"));

        // Menampilkan Informasi Hari Kedua
        System.out.println("\n====== Informasi Hari Kedua ======");
        tiket4.tampilkanInfo();
    }
}
