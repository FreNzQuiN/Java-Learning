package Kuliah.Semester1;
import java.lang.*;
import java.util.Scanner;

class superindo
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scan = new Scanner(System.in);
		inisiasi();
		int n=3;
		barang buah = new barang();
//buah.input(n);
		String kondisi="y";
		while(true) {
			buah.display(n);
			buah.sell();
			buah.summary();
			System.out.printf("Apakah ingin mengulangi program? (y/n) ");
			kondisi=scan.next();
			System.out.printf("\n");
			if(!kondisi.equalsIgnoreCase("y")) break;
		}
		tutup();
	}
	static void inisiasi() {
		System.out.println("========================");
		System.out.println("== BELANJA SUPERINDO ===");
		System.out.println("========================");
	}
	static void tutup() {
		System.out.println("=======================");
		System.out.println("== TERIMAKASIH  KAK^^ ==");
		System.out.println("=======================");
	}
}

class barang {
	Scanner scan = new Scanner(System.in);
	String nama[]= {"Jeruk","Mangga","Anggur","Selesai"};
	int harga[] = {8000,12000,16000,0};
	int total=0;
	void input(int n) {
		this.nama = new String[n+1];
		this.harga = new int[n+1];
		this.nama[n] = "Selesai";
		this.harga[n] = 0;
		for(int i=0; i<n; i++) {
			this.nama[i] = scan.next();
			this.harga[i] = scan.nextInt();
		}
	}
	void display(int n) {
		System.out.println("Daftar Barang yang Dijual per kg:");
		for(int i=0; i<n; i++) System.out.printf("%d. %s = Rp%d,00\n",i+1,nama[i],harga[i]);
		System.out.printf("%d. %s\n",0,nama[n]);
	}
	void sell() {
		int kode=-1,jumlah;
		while(true) {
			System.out.printf("Beli buah dengan kode dan jumlahnya:");
			kode=scan.nextInt();
			if(kode==0) {
				System.out.println();
				break;
			}
			jumlah=scan.nextInt();
			System.out.println();
			total = total + (harga[kode-1]*jumlah);
		}
	}
	void summary() {
		System.out.printf("TOTAL BELANJA ANDA: Rp%d,00\n",total);
		total=0;
	}
}
