import java.util.Scanner;

class BangunDatar {
    private double alas_panjang;
    private double tinggi_lebar;
    private double jari2;
    private double luas;

    public double getLuas() {
        return this.luas;
    }

    public BangunDatar(double alas, double tinggi) { //segitiga
        this.alas_panjang = alas;
        this.tinggi_lebar = tinggi;
        this.luas = alas*tinggi/2;
    }
    public BangunDatar(double sisi) { //persegi
        this.alas_panjang = sisi;
        this.tinggi_lebar = sisi;
        this.luas = sisi*sisi;
    }
    public BangunDatar() { //lingkaran
        setLingkaran();
    }
    private void setLingkaran() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan jari-jari lingkaran: ");
        this.jari2 = scan.nextDouble();
        this.luas = Math.PI * Math.pow(this.jari2, 2);
    }
}


public class tigaBangun { // procedural berbasis pengorganisasian objek
    public static void main(String[] args) {
        // Membuat objek dari class Segitiga
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan alas segitiga: ");
        double alas = scan.nextDouble();
        System.out.println("Masukkan tinggi segitiga: ");
        double tinggi = scan.nextDouble();
        BangunDatar segitiga = new BangunDatar(alas,tinggi);
        System.out.println("Luas Segitiga: " + segitiga.getLuas());
        // Membuat objek dari class Persegi
        System.out.println("Masukkan sisi persegi: ");
        double sisi = scan.nextDouble();
        BangunDatar persegi = new BangunDatar(sisi);
        System.out.println("Luas Persegi: " + persegi.getLuas());
        // Membuat objek dari class Lingkaran
        BangunDatar lingkaran = new BangunDatar();
        System.out.println("Luas Lingkaran: " + lingkaran.getLuas());
    }
}
