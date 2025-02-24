package Kuliah.Semester2;
import java.util.Scanner;
import java.lang.Math;

class lingkaran {
    private double jariJari;

    public lingkaran(double jariJari) {
        this.jariJari = jariJari;
    }

    public double getJariJari() {
        return jariJari;
    }

    public void setJariJari(double jariJari) {
        this.jariJari = jariJari;
    }

    public double hitungLuas() {
        return Math.PI * Math.pow(jariJari, 2);
    }

    public double hitungKeliling() {
        return 2 * Math.PI * jariJari;
    }
}

public class lingkaran01 {
    public static void main(String[] args) {
        // lingkaran lingkaran1 = new lingkaran(14);
        // System.out.printf("Luas adalah: %.2f", lingkaran1.hitungLuas());
        Scanner scan = new Scanner(System.in);
        while (true) { 
            System.out.print("Masukkan jari-jari: ");
            double jariJari = scan.nextDouble();
            lingkaran lingkaran1 = new lingkaran(jariJari);
            System.out.printf("Luas adalah: %.2f\n", lingkaran1.hitungLuas());
            System.out.printf("Keliling adalah: %.2f\n", lingkaran1.hitungKeliling());
            System.out.print("Apakah ingin menghitung lagi? (y/n): ");
            String jawaban = scan.next();
            if (jawaban.equals("n")) {break;}   
        }
        scan.close();
    }
}
