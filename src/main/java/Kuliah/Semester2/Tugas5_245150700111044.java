package Kuliah.Semester2;
import java.util.Scanner;

public class Tugas5_245150700111044 {
    public static void main(String[] args) {
        System.out.println("=== Tugas 5 ===\n--- KALKULATOR ---");
        Kalkulator kalkulator = new Kalkulator();

        double hasilPenjumlahan = kalkulator.penjumlahan(5, 3);
        System.out.println("Hasil Penjumlahan: " + hasilPenjumlahan);

        double hasilPengurangan = kalkulator.pengurangan(5, 3);
        System.out.println("Hasil Pengurangan: " + hasilPengurangan);

        double hasilPerkalian = kalkulator.perkalian(5, 3);
        System.out.println("Hasil Perkalian: " + hasilPerkalian);

        double hasilPembagian = kalkulator.pembagian(5, 3);
        System.out.println("Hasil Pembagian: " + hasilPembagian);

        String pecahanSederhana = kalkulator.Sederhana(12, 18);
        System.out.println("Pecahan Sederhana: " + pecahanSederhana);

        System.out.println("\n--- PENJUALAN JAKET ---");
        Scanner scanner = new Scanner(System.in);
        
        final int HARGA_A_FINAL = 100000;
        final int HARGA_B_FINAL = 125000;
        final int HARGA_C_FINAL = 175000;

        int jumlahA, jumlahB, jumlahC;
        double totalHarga = 0;

        System.out.println("================== CV. Labkomdas ==================");
        System.out.println("           Penjualan Jaket dengan Diskon           ");
        System.out.println("--------------------------------------------------");
        System.out.println("Harga Final Jaket:");
        System.out.println("Jaket A: Rp " + HARGA_A_FINAL);
        System.out.println("Jaket B: Rp " + HARGA_B_FINAL);
        System.out.println("Jaket C: Rp " + HARGA_C_FINAL);
        System.out.println("--------------------------------------------------");

        System.out.print("Masukkan jumlah Jaket A yang dibeli: ");
        jumlahA = scanner.nextInt();

        System.out.print("Masukkan jumlah Jaket B yang dibeli: ");
        jumlahB = scanner.nextInt();

        System.out.print("Masukkan jumlah Jaket C yang dibeli: ");
        jumlahC = scanner.nextInt();

        System.out.println("\n================== Rincian Pembelian ==================");

        double hargaA;
        if (jumlahA > 100) {
            hargaA = 95000;
            System.out.println(jumlahA + " Jaket A @ Rp 95.000 = Rp " + (jumlahA * hargaA));
        } else {
            hargaA = HARGA_A_FINAL;
            System.out.println(jumlahA + " Jaket A @ Rp " + HARGA_A_FINAL + " = Rp " + (jumlahA * hargaA));
        }
        totalHarga += (jumlahA * hargaA);

        double hargaB;
        if (jumlahB > 100) {
            hargaB = 120000;
            System.out.println(jumlahB + " Jaket B @ Rp 120.000 = Rp " + (jumlahB * hargaB));
        } else {
            hargaB = HARGA_B_FINAL;
            System.out.println(jumlahB + " Jaket B @ Rp " + HARGA_B_FINAL + " = Rp " + (jumlahB * hargaB));
        }
        totalHarga += (jumlahB * hargaB);

        double hargaC;
        if (jumlahC > 100) {
            hargaC = 160000;
            System.out.println(jumlahC + " Jaket C @ Rp 160.000 = Rp " + (jumlahC * hargaC));
        } else {
            hargaC = HARGA_C_FINAL;
            System.out.println(jumlahC + " Jaket C @ Rp " + HARGA_C_FINAL + " = Rp " + (jumlahC * hargaC));
        }
        totalHarga += (jumlahC * hargaC);

        System.out.println("--------------------------------------------------");
        System.out.println("Total Harga Pembelian: Rp " + totalHarga);
        System.out.println("==================================================");
        System.out.println("Terima kasih telah berbelanja di CV. Labkomdas!");
        System.out.println("==================================================");
        System.out.println("=== Tugas 5 Selesai ===");
        
        scanner.close();
    }
}

class Kalkulator {
    public static double penjumlahan(double angka1, double angka2) {
        return angka1 + angka2;
    }

    public static double pengurangan(double angka1, double angka2) {
        return angka1 - angka2;
    }

    public double perkalian(double angka1, double angka2) {
        return angka1 * angka2;
    }

    public double pembagian(double angka1, double angka2) {
        if (angka2 == 0) {
            System.out.println("Error: Pembagian dengan nol tidak diizinkan!");
            return Double.NaN;
        }
        return angka1 / angka2;
    }

    public String Sederhana(int pembilang, int penyebut) {
        if (penyebut == 0) {
            return "Error: Penyebut tidak boleh nol!";
        }
        int gcd = hitungGCD(Math.abs(pembilang), Math.abs(penyebut));
        int pembilangSederhana = pembilang / gcd;
        int penyebutSederhana = penyebut / gcd;

        return pembilangSederhana + "/" + penyebutSederhana;
    }

    private int hitungGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}