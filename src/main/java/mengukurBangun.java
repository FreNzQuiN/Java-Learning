import java.util.Scanner;

// Kelas abstrak untuk bangun ruang
abstract class BangunRuang {
    protected double volume;
    protected double luasPermukaan;

    public abstract void hitungVolume();
    public abstract void hitungLuasPermukaan();

    public void tampilHasil() {
        System.out.printf("Volume: %.2f\n", volume);
        System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
    }
}

// Kelas untuk Kubus
class Kubus extends BangunRuang {
    private double sisi;

    public Kubus(double sisi) {
        this.sisi = sisi;
    }

    @Override
    public void hitungVolume() {
        volume = Math.pow(sisi, 3);
    }

    @Override
    public void hitungLuasPermukaan() {
        luasPermukaan = 6 * Math.pow(sisi, 2);
    }
}

// Kelas untuk Balok
class Balok extends BangunRuang {
    private double panjang;
    private double lebar;
    private double tinggi;

    public Balok(double panjang, double lebar, double tinggi) {
        this.panjang = panjang;
        this.lebar = lebar;
        this.tinggi = tinggi;
    }

    @Override
    public void hitungVolume() {
        volume = panjang * lebar * tinggi;
    }

    @Override
    public void hitungLuasPermukaan() {
        luasPermukaan = 2 * (panjang * lebar + panjang * tinggi + lebar * tinggi);
    }
}

// Kelas untuk Silinder
class Silinder extends BangunRuang {
    private double jariJari;
    private double tinggi;

    public Silinder(double jariJari, double tinggi) {
        this.jariJari = jariJari;
        this.tinggi = tinggi;
    }

    @Override
    public void hitungVolume() {
        volume = Math.PI * Math.pow(jariJari, 2) * tinggi;
    }

    @Override
    public void hitungLuasPermukaan() {
        luasPermukaan = 2 * Math.PI * jariJari * (jariJari + tinggi);
    }
}

public class mengukurBangun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int kodeBangun;
        String ulang;

        do {
            System.out.println("===========================");
            System.out.println("== Program Hitung Bangun ==");
            System.out.println("===========================");
            System.out.println("1. Kubus");
            System.out.println("2. Balok");
            System.out.println("3. Silinder");
            System.out.println("0. Keluar");
            System.out.print("Ingin menghitung bangun apa? ");
            kodeBangun = scanner.nextInt();

            switch (kodeBangun) {
                case 1:
                    System.out.print("Masukkan panjang sisi kubus: ");
                    double sisiKubus = scanner.nextDouble();
                    Kubus kubus = new Kubus(sisiKubus);
                    kubus.hitungVolume();
                    kubus.hitungLuasPermukaan();
                    kubus.tampilHasil();
                    break;
                case 2:
                    System.out.print("Masukkan panjang, lebar, dan tinggi balok: ");
                    double panjang = scanner.nextDouble();
                    double lebar = scanner.nextDouble();
                    double tinggi = scanner.nextDouble();
                    Balok balok = new Balok(panjang, lebar, tinggi);
                    balok.hitungVolume();
                    balok.hitungLuasPermukaan();
                    balok.tampilHasil();
                    break;
                case 3:
                    System.out.print("Masukkan jari-jari dan tinggi silinder: ");
                    double jariJari = scanner.nextDouble();
                    tinggi = scanner.nextDouble();
                    Silinder silinder = new Silinder(jariJari, tinggi);
                    silinder.hitungVolume();
                    silinder.hitungLuasPermukaan();
                    silinder.tampilHasil();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Input tidak valid (1-3)");
            }

            if (kodeBangun != 0) {
                System.out.print("Ingin mengulang? (Y/N) ");
                ulang = scanner.next();
            } else {
                ulang = "N";
            }

        } while (ulang.equalsIgnoreCase("Y"));

        System.out.println("===========================");
        System.out.println("== TERIMAKASIH^^ SEE YOU ==");
        System.out.println("===========================");
        scanner.close();
    }
}