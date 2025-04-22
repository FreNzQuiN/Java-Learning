package Kuliah.Semester2;
import java.util.Scanner;

public class KambingJumboMbek {
    private static final double HARGA_MANDI = 5000;
    private static final double HARGA_GROOMING = 7000;
    private static final double DISKON_GEMUK = 0.15;
    private static final double PAJAK = 0.05;

    private String namaKambing;
    private int berat;
    private String jenisPerawatan;

    public KambingJumboMbek(String nama, int berat, String jenis) {
        this.namaKambing = nama;
        this.berat = berat;
        this.jenisPerawatan = jenis;
    }

    private double hitungBiayaDasar() {
       // Tuliskan Kodemu Disini
       if (jenisPerawatan.equals("mandi")) {
        return HARGA_MANDI*berat;
    } else if (jenisPerawatan.equals("grooming")) {
        return HARGA_GROOMING*berat;
    }
    return 0;
    }

    
    private double hitungDiskon() {
       // Tuliskan Kodemu Disini
       if (berat > 20) {
        return hitungBiayaDasar() * DISKON_GEMUK;
    }
    return 0;
    }

    public static double hitungPajak(double subtotal) {
        // Tuliskan Kodemu Disini
        return subtotal * PAJAK;
    }

    
    public void cetakNota() {
        double dasar=hitungBiayaDasar();
        double diskon=hitungDiskon();
        double subtotal=dasar-diskon;
        double pajak=hitungPajak(subtotal);
        System.out.println("============ NOTA KAMBING JUMBO ============");
        System.out.printf("Nama Kambing: %s\n", namaKambing);// Tuliskan Kodemu Disini);
        System.out.printf("Berat: %d kg\n", berat);// Tuliskan Kodemu Disini);
        System.out.printf("Jenis Perawatan: %s\n", jenisPerawatan);// Tuliskan Kodemu Disini);
        System.out.printf("Biaya Dasar: Rp %.1f\n", dasar);// Tuliskan Kodemu Disini);
        System.out.printf("Diskon: Rp %.1f\n", diskon);// Tuliskan Kodemu Disini);
        System.out.printf("Subtotal: Rp %.1f\n", subtotal);// Tuliskan Kodemu Disini);
        System.out.printf("Pajak: Rp %.1f\n", pajak);// Tuliskan Kodemu Disini);
        System.out.printf("Total Biaya: ");// Tuliskan Kodemu Disini);
        if(namaKambing.equals("Mbek") || namaKambing.equals("Mbeek") || namaKambing.equals("Mbeeek")){
            System.out.printf("Rp %.1f\n", 0.0);
        } else { System.out.printf("Rp %.1f\n", pajak+subtotal);}
        System.out.println("============================================");

        // Pesan unik untuk Mbek
        // Tuliskan Kodemu Disini
        if (namaKambing.equals("Mbek") || namaKambing.equals("Mbeek") || namaKambing.equals("Mbeeek")) {
            System.out.printf("Terima kasih, %s ! Kambing Jumbo selalu gratis, ya!\n", namaKambing);
        } else {
            System.out.printf("Terima kasih, %s ! Nanti kambingmu kami kembalikan... atau jadi menu special\n", namaKambing);
        }
    }
                          
    // DILARANG MENGEDIT KELAS MAIN
    // JIKA MENGEDIT NILAI = 0
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input nama kambing
        String nama = "";
        while (true) {
            nama = scanner.nextLine().trim();
            if (nama.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Mbek! Nama kambing harus pakai huruf, bukan angka!");
            }
        }

        // Input berat
        int berat = 0;
        while (true) {
            try {
                berat = Integer.parseInt(scanner.nextLine().trim());
                if (berat < 1) {
                    System.out.println("Kambing hantu? Masukkan berat yang valid, hooman!");
                } else if (berat > 50) {
                    System.out.println("Wah, ini kambing atau sapi?");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Berat tidak valid! Kambing kami bukan hologram!");
            }
        }

        // Input jenis perawatan
        String jenis = "";
        while (true) {
            jenis = scanner.nextLine().trim().toLowerCase();
            if (jenis.equals("mandi") || jenis.equals("grooming")) {
                break;
            } else {
                System.out.println("Pilih 'mandi' atau 'grooming'! Kambingmu mau jadi artis?");
            }
        }

        // Proses dan cetak nota
        KambingJumboMbek kambing = new KambingJumboMbek(nama, berat, jenis);
        kambing.cetakNota();
    }
}