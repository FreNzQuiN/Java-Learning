import java.util.*;

class Player {
    private final String nama;
    private int score = 0;
    private String pilihan = "";
    private Map<String, Double> strategi = new HashMap<>(Map.of(
        "batu", 0.33, "gunting", 0.33, "kertas", 0.34
    ));
    private Map<String, Integer> skill = new HashMap<>(); // future extension
    private static final Random rand = new Random();

    public Player(String nama) {
        this.nama = nama;
    }

    public String getNama() { return nama; }
    public int getScore() { return score; }
    public void incrementScore() { score++; }
    public String getPilihanTerakhir() { return pilihan; }
    public Map<String, Double> getStrategi() { return strategi; }
    public void setStrategi(Map<String, Double> strategiBaru) { this.strategi = strategiBaru; }

    public String memilih() {
        double r = rand.nextDouble(), akumulasi = 0;
        for (var e : strategi.entrySet()) {
            akumulasi += e.getValue();
            if (r <= akumulasi) return pilihan = e.getKey();
        }
        return pilihan = "batu";
    }

    public void setSkill(String namaSkill, int nilai) {
        skill.put(namaSkill, nilai);
    }

    public Map<String, Integer> getSkill() {
        return skill;
    }
}

class Turnamen {
    private List<Player> players = new ArrayList<>();
    private List<List<String>> urutanPertandingan = new ArrayList<>();
    private Player pemenang;
    private static final Map<String, String> menangDari = Map.of(
        "batu", "gunting", "gunting", "kertas", "kertas", "batu"
    );

    public void tambahPemain(String nama) {
        players.add(new Player(nama));
    }

    public void jalankan() {
        if (players.size() != 8) {
            System.out.println("Turnamen membutuhkan tepat 8 pemain.");
            return;
        }
        Collections.shuffle(players);
        System.out.println("\n--- Mulai Turnamen ---");
        pemenang = jalankanEliminasi(players);
        if (pemenang != null) {
            System.out.println("\n--- Pemenang Turnamen: " + pemenang.getNama() + " (Skor: " + pemenang.getScore() + ") ---");
        } else {
            System.out.println("\nFinal seri, tidak ada pemenang.");
        }
    }

    private Player jalankanEliminasi(List<Player> peserta) {
        while (peserta.size() > 1) {
            List<Player> pemenangBabak = new ArrayList<>();
            List<String> log = new ArrayList<>();
            for (int i = 0; i < peserta.size(); i += 2) {
                Player p1 = peserta.get(i), p2 = peserta.get(i + 1);
                Player win = tanding(p1, p2);
                log.add(p1.getNama() + " vs " + p2.getNama());
                pemenangBabak.add(win != null ? win : p1); // jika seri, default p1
            }
            urutanPertandingan.add(log);
            peserta = pemenangBabak;
        }
        return peserta.get(0);
    }

    private Player tanding(Player p1, Player p2) {
        String c1 = p1.memilih(), c2 = p2.memilih();
        System.out.printf("%s [%s] vs %s [%s]\n", p1.getNama(), c1, p2.getNama(), c2);

        if (c1.equals(c2)) {
            System.out.println("Seri!");
            return null;
        }
        Player winner = menangDari.get(c1).equals(c2) ? p1 : p2;
        System.out.println("Pemenang: " + winner.getNama());
        winner.incrementScore();
        return winner;
    }

    public void tampilkanPertandingan() {
        int babak = 8;
        for (List<String> babakLog : urutanPertandingan) {
            System.out.println("\nBabak " + (babak == 2 ? "Final" : babak + " Besar") + ":");
            for (String duel : babakLog) System.out.println("- " + duel);
            babak /= 2;
        }
    }

    public Player getPemenang() {
        return pemenang;
    }
}

public class BatuGuntingKertas {
    public static void main(String[] args) {
        Turnamen t = new Turnamen();
        List<String> namaPemain = List.of("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Heidi");
        namaPemain.forEach(t::tambahPemain);

        t.jalankan();
        t.tampilkanPertandingan();
    }
}
