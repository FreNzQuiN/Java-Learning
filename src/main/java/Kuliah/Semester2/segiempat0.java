package Kuliah.Semester2;
import java.util.Scanner;

class segiempat {
    private double x;
    private double y;

    public segiempat(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double hitungLuas() {
        return x * y;
    }
    public double hitungKeliling() {
        return 2 * (x + y);
    }
    public double hitungDiagonal() {
        double res=Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return res;
    }
}

public class segiempat0 {
    public static void main(String[] args) {
        double x, y;
        Scanner scan = new Scanner(System.in);
        while (true) { 
            System.out.print("Masukkan panjang: ");
            x = scan.nextInt();
            System.out.print("Masukkan lebar: ");
            y = scan.nextInt();
            segiempat segiempat1 = new segiempat(x, y);
            double luas = segiempat1.hitungLuas();
            double keliling = segiempat1.hitungKeliling();
            double diagonal = segiempat1.hitungDiagonal();
            System.out.printf("Luas adalah: %.2f\n", luas);
            System.out.printf("Keliling adalah: %.2f\n", keliling);
            System.out.printf("Diagonal adalah: %.2f\n", diagonal);
            System.out.print("Apakah ingin menghitung lagi? (y/n): ");
            String jawaban = scan.next();
            if (jawaban.equals("n")) {break;}   
            scan.close();
        }
    }
}
