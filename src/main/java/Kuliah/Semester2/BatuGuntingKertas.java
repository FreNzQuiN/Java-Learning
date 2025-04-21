package Kuliah.Semester2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


class Player {
    private String nama;
    private int score;
    private String pilihan;
    private Map<String, Double> strategi;

    public Player(String nama) {
        this.nama = nama;
        this.score = 0;
        this.pilihan = "";
        this.strategi = new HashMap<>();
        this.strategi.put("batu", 0.33);
        this.strategi.put("gunting", 0.33);
        this.strategi.put("kertas", 0.34);
    }

    public String getNama() {
        return nama;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

    public String memilih() {
        Random random = new Random();
        double randomNumber = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (Map.Entry<String, Double> entry : this.strategi.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (randomNumber <= cumulativeProbability) {
                this.pilihan = entry.getKey();
                return this.pilihan;
            }
        }
        this.pilihan = "batu"; // Fallback
        return this.pilihan;
    }

    public String getPilihanTerakhir() {
        return pilihan;
    }

    public Map<String, Double> getStrategi() {
        return strategi;
    }

    public void setStrategi(Map<String, Double> strategiBaru) {
        this.strategi = strategiBaru;
    }
}
class Turnamen {
    private List<Player> players;
    private List<List<String>> urutanPermainan;
    private List<List<String>> urutanPenyisihan;
    private Player pemenang;

    public Turnamen() {
        this.players = new ArrayList<>();
        this.urutanPermainan = new ArrayList<>();
        this.urutanPenyisihan = new ArrayList<>();
        this.pemenang = null;
    }

    public void tambahkanPemain(String nama) {
        Player pemainBaru = new Player(nama);
        this.players.add(pemainBaru);
        System.out.println("Pemain '" + nama + "' ditambahkan ke turnamen.");
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<List<String>> getUrutanPermainan() {
        return urutanPermainan;
    }

    public List<List<String>> getUrutanPenyisihan() {
        return urutanPenyisihan;
    }

    public Player getPemenang() {
        return pemenang;
    }

    public void jalankanTurnamen() {
        if (players.size() != 8) {
            System.out.println("Turnamen membutuhkan tepat 8 pemain untuk dimulai.");
            return;
        }

        List<Player> babak8 = acakPemain(new ArrayList<>(players));
        urutanPenyisihan.add(babak8.stream().map(Player::getNama).toList());
        List<Player> babak4 = jalankanBabak(babak8, "Babak 8 Besar");

        if (babak4.size() == 4) {
            List<Player> babak2 = jalankanBabak(babak4, "Babak Semifinal");
            if (babak2.size() == 2) {
                pemenang = jalankanFinal(babak2.get(0), babak2.get(1));
            }
        }
    }

    private List<Player> acakPemain(List<Player> daftarPemain) {
        Collections.shuffle(daftarPemain);
        return daftarPemain;
    }

    private List<Player> jalankanBabak(List<Player> peserta, String namaBabak) {
        System.out.println("\n--- " + namaBabak + " ---");
        List<Player> pemenangBabak = new ArrayList<>();
        List<String> pertandinganBabak = new ArrayList<>();

        for (int i = 0; i < peserta.size(); i += 2) {
            if (i + 1 < peserta.size()) {
                Player pemain1 = peserta.get(i);
                Player pemain2 = peserta.get(i + 1);
                Player pemenang = tanding(pemain1, pemain2);
                pertandinganBabak.add(pemain1.getNama() + " vs " + pemain2.getNama());
                if (pemenang != null) {
                    pemenangBabak.add(pemenang);
                } else {
                    pemenangBabak.add(pemain1); // Jika seri, pemain pertama lolos (aturan bisa diubah)
                    pemenangBabak.add(pemain2);
                }
            }
        }
        urutanPermainan.add(pertandinganBabak);
        return pemenangBabak;
    }

    private Player tanding(Player pemain1, Player pemain2) {
        String pilihan1 = pemain1.memilih();
        String pilihan2 = pemain2.memilih();
        System.out.println(pemain1.getNama() + " (" + formatStrategi(pemain1.getStrategi()) + ") " +
                           "vs " +
                           pemain2.getNama() + " (" + formatStrategi(pemain2.getStrategi()) + ")");
        System.out.println(pemain1.getNama() + " memilih " + pilihan1 + ", " + pemain2.getNama() + " memilih " + pilihan2 + ".");

        if (pilihan1.equals(pilihan2)) {
            System.out.println("Seri!");
            return null;
        } else if ((pilihan1.equals("batu") && pilihan2.equals("gunting")) ||
                   (pilihan1.equals("gunting") && pilihan2.equals("kertas")) ||
                   (pilihan1.equals("kertas") && pilihan2.equals("batu"))) {
            System.out.println(pemain1.getNama() + " menang!");
            pemain1.incrementScore();
            return pemain1;
        } else {
            System.out.println(pemain2.getNama() + " menang!");
            pemain2.incrementScore();
            return pemain2;
        }
    }

    private Player jalankanFinal(Player pemain1, Player pemain2) {
        System.out.println("\n--- Babak Final ---");
        System.out.println(pemain1.getNama() + " vs " + pemain2.getNama());
        Player pemenangFinal = tanding(pemain1, pemain2);
        System.out.println("\n--- Pemenang Turnamen ---");
        if (pemenangFinal != null) {
            System.out.println("Pemenangnya adalah: " + pemenangFinal.getNama() + " dengan skor " + pemenangFinal.getScore() + ".");
            return pemenangFinal;
        } else {
            System.out.println("Pertandingan Final Seri!");
            return null;
        }
    }

    private String formatStrategi(Map<String, Double> strategi) {
        StringBuilder sb = new StringBuilder("{");
        int count = 0;
        for (Map.Entry<String, Double> entry : strategi.entrySet()) {
            sb.append(entry.getKey()).append(":").append(String.format("%.2f", entry.getValue()));
            if (count < strategi.size() - 1) {
                sb.append(", ");
            }
            count++;
        }
        sb.append("}");
        return sb.toString();
    }
}

public class BatuGuntingKertas {
    public static void main(String[] args) {
        Turnamen turnamen = new Turnamen();

        turnamen.tambahkanPemain("Alice");
        turnamen.tambahkanPemain("Bob");
        turnamen.tambahkanPemain("Charlie");
        turnamen.tambahkanPemain("David");
        turnamen.tambahkanPemain("Eve");
        turnamen.tambahkanPemain("Frank");
        turnamen.tambahkanPemain("Grace");
        turnamen.tambahkanPemain("Heidi");

        BatuGuntingKertas simulasi = new BatuGuntingKertas();
        simulasi.mulai(turnamen);
    }

    public void mulai(Turnamen turnamen) {
        System.out.println("--- Simulasi Turnamen Batu-Gunting-Kertas ---");
        menampilkanNamaSemuaPlayer(turnamen);
        System.out.println("\n--- Urutan Permainan ---");
        turnamen.jalankanTurnamen();
        menampilkanUrutanPermainan(turnamen);
        menampilkanUrutanDiMasing2Penyisihan(turnamen);
        menampilkanPemenang(turnamen);
        System.out.println("--- Simulasi Selesai ---");
    }

    public void menampilkanNamaSemuaPlayer(Turnamen turnamen) {
        System.out.println("\nDaftar Semua Pemain:");
        for (Player player : turnamen.getPlayers()) {
            System.out.println("- " + player.getNama());
        }
    }

    public void menampilkanUrutanPermainan(Turnamen turnamen) {
        if (turnamen.getUrutanPermainan().isEmpty()) {
            System.out.println("Belum ada pertandingan yang dimainkan.");
            return;
        }
        System.out.println("\nUrutan Pertandingan di Setiap Babak:");
        int babak = 8;
        for (List<String> pertandingan : turnamen.getUrutanPermainan()) {
            System.out.println("Babak " + babak + " Besar:");
            for (String match : pertandingan) {
                System.out.println("- " + match);
            }
            babak /= 2;
        }
        System.out.println("Babak Final:");
        if (turnamen.getPemenang() != null) {
            // Urutan final tidak disimpan terpisah, informasinya ada di jalankanFinal
        } else {
            // Informasi final juga ada di jalankanFinal
        }
    }

    public void menampilkanUrutanDiMasing2Penyisihan(Turnamen turnamen) {
        if (turnamen.getUrutanPenyisihan().isEmpty()) {
            System.out.println("Belum ada penyisihan pemain.");
            return;
        }
        System.out.println("\nUrutan Pemain di Awal Penyisihan:");
        int babak = 8;
        for (List<String> urutan : turnamen.getUrutanPenyisihan()) {
            System.out.println("Babak " + babak + " Besar:");
            for (String nama : urutan) {
                System.out.println("- " + nama);
            }
            babak /= 2; // Ini tidak benar untuk penyisihan awal, hanya satu babak awal
            break; // Hanya menampilkan urutan awal 8 pemain
        }
    }

    public void menampilkanPemenang(Turnamen turnamen) {
        if (turnamen.getPemenang() != null) {
            System.out.println("\nPemenang Turnamen: " + turnamen.getPemenang().getNama());
        } else {
            System.out.println("\nBelum ada pemenang turnamen.");
        }
    }
}