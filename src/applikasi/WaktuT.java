package applikasi;

import java.util.ArrayList;

public class WaktuT {
	String jam;
	
	ArrayList<Bangku> bangku = new ArrayList<Bangku>();

	public WaktuT(String jam) {
		this.jam = jam;
		preBangku();
	}
	
	public void preBangku() {
		for(int i = 0 ; i < 18; i++) {
			String b = "NO-" + (i + 1);
			bangku.add(new Bangku(b));
		}
	}
}
