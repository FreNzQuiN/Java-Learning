import java.util.*;

/*
+----------------------------------+               
|            Player                |
+----------------------------------+
| - nama: String                   |
| - score: int                     |
| - pilihan: String                |
| - strategi: Map<String, Double>  |
| - premium: boolean               |
| - ignoreUsed: boolean            |
+----------------------------------+
| + Player(nama: String, premium: boolean) |
| + getNama(): String              |
| + getScore(): int                |
| + incrementScore()               |
| + decrementScore()               |
| + memilih(): String              |
| + applyLoss(): void              |
| + getPilihanTerakhir(): String   |
| + getStrategi(): Map<String, Double> |
| + setStrategi(Map<String, Double>): void |
| + isPremium(): boolean           |
| + hasUsedIgnore(): boolean       |
+----------------------------------+

+----------------------------------+
|            Turnamen              |
+----------------------------------+
| - players: List<Player>          |
| - roundsPerMatch: int            |
| - urutanPermainan: List<List<String>> |
| - urutanPenyisihan: List<List<String>> |
| - pemenang: Player               |
+----------------------------------+
| + Turnamen(roundsPerMatch: int)  |
| + tambahkanPemain(nama: String, premium: boolean): void |
| + jalankanTurnamen(): void       |
| + getPlayers(): List<Player>     |
| + getUrutanPermainan(): List<List<String>> |
| + getUrutanPenyisihan(): List<List<String>> |
| + getPemenang(): Player          |
| - acakPemain(List<Player>): List<Player> |
| - jalankanBabak(List<Player>, String): List<Player> |
| - tanding(Player, Player): Player |
| - jalankanFinal(Player, Player): Player |
| - formatStrategi(Map<String, Double>): String |
+----------------------------------+

+----------------------------------+
|         BatuGuntingKertas        |
+----------------------------------+
| + main(args: String[]): void     |
| + mulai(turnamen: Turnamen): void|
| + menampilkanNamaSemuaPlayer(turnamen: Turnamen): void |
| + menampilkanUrutanPermainan(turnamen: Turnamen): void |
| + menampilkanUrutanDiMasing2Penyisihan(turnamen: Turnamen): void |
| + menampilkanPemenang(turnamen: Turnamen): void |
+----------------------------------+
*/

enum PlayerType {
    REGULER, PREMIUM
}

class Player {
    private final String nama;
    private int score;
    private String pilihan;
    private final Map<String, Double> strategi;
    private final PlayerType type;
    private boolean hasIgnoreChance;

    public Player(String nama, PlayerType type) {
        this.nama = nama;
        this.type = type;
        this.score = 0;
        this.pilihan = "";
        this.strategi = defaultStrategi();
        this.hasIgnoreChance = (type == PlayerType.PREMIUM);
    }

    private Map<String, Double> defaultStrategi() {
        Map<String, Double> strategi = new HashMap<>();
        strategi.put("batu", 0.33);
        strategi.put("gunting", 0.33);
        strategi.put("kertas", 0.34);
        return strategi;
    }

    public String getNama() { return nama; }
    public int getScore() { return score; }
    public void tambahScore(int nilai) { this.score += nilai; }
    public PlayerType getType() { return type; }

    public String memilih() {
        double rand = new Random().nextDouble(), cumulative = 0.0;
        for (var entry : strategi.entrySet()) {
            cumulative += entry.getValue();
            if (rand <= cumulative) {
                pilihan = entry.getKey();
                return pilihan;
            }
        }
        return pilihan = "batu";
    }

    public boolean bisaAbaikanKalah() {
        if (type == PlayerType.PREMIUM && hasIgnoreChance) {
            hasIgnoreChance = false;
            return true;
        }
        return false;
    }
}

class Turnamen {
    private final List<Player> players = new ArrayList<>();
    private Player pemenang;
    private final int roundsPerMatch;

    public Turnamen(int roundsPerMatch) {
        this.roundsPerMatch = roundsPerMatch;
    }

    public void tambahkanPemain(String nama, PlayerType type) {
        players.add(new Player(nama, type));
        System.out.println("Pemain '" + nama + "' ditambahkan sebagai " + type);
    }

    public void mulai() {
        if (players.size() != 8) {
            System.out.println("Turnamen butuh 8 pemain!");
            return;
        }

        List<Player> peserta = new ArrayList<>(players);
        Collections.shuffle(peserta);

        List<Player> semifinal = jalankanBabak(peserta, "8 Besar");
        List<Player> finalis = jalankanBabak(semifinal, "Semifinal");
        pemenang = jalankanFinal(finalis);
        tampilkanPemenang();
    }

    private List<Player> jalankanBabak(List<Player> peserta, String namaBabak) {
        System.out.println("\n--- " + namaBabak + " ---");
        List<Player> pemenangBabak = new ArrayList<>();

        for (int i = 0; i < peserta.size(); i += 2) {
            Player pemenang = tandingBestOfN(peserta.get(i), peserta.get(i + 1));
            pemenangBabak.add(pemenang);
        }
        return pemenangBabak;
    }

    private Player jalankanFinal(List<Player> finalis) {
        System.out.println("\n--- Final ---");
        return tandingBestOfN(finalis.get(0), finalis.get(1));
    }

    private Player tandingBestOfN(Player p1, Player p2) {
        int skorP1 = 0, skorP2 = 0;
        System.out.println(p1.getNama() + " vs " + p2.getNama());

        for (int round = 1; round <= roundsPerMatch; round++) {
            String pilihan1 = p1.memilih();
            String pilihan2 = p2.memilih();
            System.out.println("Ronde " + round + ": " + p1.getNama() + " memilih " + pilihan1 + ", " + p2.getNama() + " memilih " + pilihan2);

            int hasil = hasilPertandingan(pilihan1, pilihan2);
            if (hasil == 1) skorP1++;
            else if (hasil == -1) skorP2++;
        }

        return prosesHasil(p1, p2, skorP1, skorP2);
    }

    private int hasilPertandingan(String p1, String p2) {
        if (p1.equals(p2)) return 0;
        return (p1.equals("batu") && p2.equals("gunting")) ||
               (p1.equals("gunting") && p2.equals("kertas")) ||
               (p1.equals("kertas") && p2.equals("batu")) ? 1 : -1;
    }

    private Player prosesHasil(Player p1, Player p2, int skorP1, int skorP2) {
        System.out.println("Skor akhir: " + p1.getNama() + " (" + skorP1 + ") - " + p2.getNama() + " (" + skorP2 + ")");
        if (skorP1 == skorP2) {
            System.out.println("Seri, memilih " + p1.getNama() + " sebagai pemenang default.");
            return p1;
        }
        Player menang = skorP1 > skorP2 ? p1 : p2;
        Player kalah = skorP1 > skorP2 ? p2 : p1;

        menang.tambahScore(1);

        if (kalah.getType() == PlayerType.PREMIUM && kalah.bisaAbaikanKalah()) {
            System.out.println(kalah.getNama() + " menggunakan hak ignore kekalahan!");
        } else {
            kalah.tambahScore(-1);
        }

        System.out.println(menang.getNama() + " menang!");
        return menang;
    }

    private void tampilkanPemenang() {
        System.out.println("\n--- Pemenang Turnamen ---");
        if (pemenang != null) System.out.println("Pemenangnya: " + pemenang.getNama() + " dengan skor " + pemenang.getScore());
        else System.out.println("Tidak ada pemenang.");
    }
}

public class BatuGuntingKertas {
    public static void main(String[] args) {
        Turnamen turnamen = new Turnamen(3); // bisa diganti jumlah rounds di sini

        turnamen.tambahkanPemain("Alice", PlayerType.PREMIUM);
        turnamen.tambahkanPemain("Bob", PlayerType.REGULER);
        turnamen.tambahkanPemain("Charlie", PlayerType.REGULER);
        turnamen.tambahkanPemain("David", PlayerType.PREMIUM);
        turnamen.tambahkanPemain("Eve", PlayerType.REGULER);
        turnamen.tambahkanPemain("Frank", PlayerType.PREMIUM);
        turnamen.tambahkanPemain("Grace", PlayerType.REGULER);
        turnamen.tambahkanPemain("Heidi", PlayerType.PREMIUM);

        turnamen.mulai();
    }
}
