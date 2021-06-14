package applikasi;

import java.util.ArrayList;

public class Account {
	String accname;
	String accpassword;
	String acctiket;
	int saldo;
	
	ArrayList<tiket> tikets = new ArrayList<tiket>();

	public Account(String accname, String accpassword, int saldo) {
		this.accname = accname;
		this.accpassword = accpassword;
		this.saldo = saldo;
	}
	
	public Account(String accname, String accpassword, String acctiketname, String acctiketwaktu, String acctikettanggal, String bangku, int Jumlah ) {
		this.accname = accname;
		this.accpassword = accpassword;
		tikets.add(new tiket(acctiketname, acctiketwaktu, acctikettanggal, bangku ,Jumlah));
	}

	public String getAcctiket() {
		return acctiket;
	}

	public void setAcctiket(String acctiket,String acctiketwaktu, String acctikettanggal, String bangku, int Jumlah) {
		tikets.add(new tiket(acctiket, acctiketwaktu, acctikettanggal, bangku, Jumlah));
	}

	public String getAccname() {
		return accname;
	}

	public void setAccname(String accname) {
		this.accname = accname;
	}

	public String getAccpassword() {
		return accpassword;
	}

	public void setAccpassword(String accpassword) {
		this.accpassword = accpassword;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public ArrayList<tiket> getTikets() {
		return tikets;
	}

	public void setTikets(ArrayList<tiket> tikets) {
		this.tikets = tikets;
	}

	public void setAcctiket(String acctiket) {
		this.acctiket = acctiket;
	}
}
