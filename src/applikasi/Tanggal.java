package applikasi;

import java.util.ArrayList;

public class Tanggal {
	String tanggal;
	
	ArrayList<WaktuT> waktu = new ArrayList<WaktuT>();

	public Tanggal(String tanggal) {
		this.tanggal = tanggal;
		autoTime();
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}
	
	public void autoTime() {
		waktu.add(new WaktuT("15:00"));
		waktu.add(new WaktuT("17:30"));
		waktu.add(new WaktuT("20:00"));
	}
	
}
