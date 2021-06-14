package applikasi;


import java.util.ArrayList;

public class Film {
	String namaF;
	int harga;
	
	ArrayList<Tanggal> tanggal = new ArrayList<Tanggal>();

	public Film(String namaF, int harga) {
		this.namaF = namaF;
		this.harga = harga;
	}
	
	public void setTanggal(String Tanggal) {
		tanggal.add(new Tanggal(Tanggal));
	}

	public String getNamaF() {
		return namaF;
	}

	public void setNamaF(String namaF) {
		this.namaF = namaF;
	}

	public int getHarga() {
		return harga;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}
	
}
