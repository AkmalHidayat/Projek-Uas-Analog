package applikasi;


public class tiket {
	String namaF;
	String waktu;
	String tanggal;
	String bangku;
	int Jumlah;
	
	public tiket(String namaF, String waktu, String tanggal,String bangku,int jumlah) {
		this.namaF = namaF;
		this.waktu = waktu;
		this.tanggal = tanggal;
		this.bangku = bangku;
		Jumlah = jumlah;
	}

	public String getNamaF() {
		return namaF;
	}

	public void setNamaF(String namaF) {
		this.namaF = namaF;
	}

	public String getWaktu() {
		return waktu;
	}

	public void setWaktu(String waktu) {
		this.waktu = waktu;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

}
