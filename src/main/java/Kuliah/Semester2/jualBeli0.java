package Kuliah.Semester2;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

class Customer {
	private String nama,noHp;
	static int jumlahPerson=1;
	Keranjang troli = new Keranjang();
	public Customer(){
		nama="person"+jumlahPerson;
		noHp="999999999999";
		jumlahPerson++;
	}
	public Customer(String nama, String noHp){
		this.nama = nama;
		this.noHp = noHp;
		jumlahPerson++;
	}
	public void lihatIsi (){
		System.out.printf("==xx %s xx==\n",nama);
		troli.lihatIsi();
		System.out.println();
	}
}

class Keranjang {
	private ArrayList<Produk> daftarBarang = new ArrayList<>();
	public void tambahBarang(Produk barang, int jumlahDibeli){
		daftarBarang.add(barang);
		barang.setJumlahDibeli(jumlahDibeli);
	}
	public double jumlahkanHarga(){
		double hasil=0;
		for(Produk isi : daftarBarang){
			hasil = hasil + isi.getHarga() * isi.getJumlahDibeli();
		}
		return hasil;
	}
	public void lihatIsi(){
		for(Produk isi : daftarBarang){
			isi.tampilkanProduk();
		}
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
		System.out.printf("SUBTOTAL = %s\n", rupiah.format(jumlahkanHarga()));
	}
}

class Produk {
	private String nama;
	private double harga;
	private int jumlahDibeli;
	static int jumlahProduk=1;
	public Produk(){
		nama="barang"+jumlahProduk;
		harga = 10000;
		jumlahDibeli=0;
		jumlahProduk++;
	}
	public Produk(String nama, double harga){
		this.nama = nama;
		this.harga = harga;
		jumlahProduk++;
	}
	public void setJumlahDibeli(int jumlahDibeli){
		this.jumlahDibeli=jumlahDibeli;
	}
	public double getHarga() {
		return harga;
	}
	public int getJumlahDibeli() {
		return jumlahDibeli;
	}
	public void tampilkanProduk(){
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
		System.out.printf("%s : %d x %s\n",nama,jumlahDibeli,rupiah.format(harga));
	}
}

public class jualBeli0 {
	public static void main(String[] args) {
		Produk barang1 = new Produk();
		Produk barang2 = new Produk("Pasta Gigi",30000);
		Produk barang3 = new Produk();
		
		Customer person1 = new Customer("Aldo Barreto","085704026395");
		person1.troli.tambahBarang(barang1, 2);
		person1.troli.tambahBarang(barang2, 5);
		person1.troli.tambahBarang(barang3, 10);
		person1.lihatIsi();
		
		Customer person2 = new Customer("Melda Juani","08210407365");
		person2.troli.tambahBarang(barang2,7);
		person2.lihatIsi();
	}
}
