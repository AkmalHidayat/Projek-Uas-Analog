package applikasi;

public class Bangku {
	String codeB;
	boolean digunakan;

	public Bangku(String codeB) {
		this.codeB = codeB;
		digunakan = false;
	}
	
	



	public String getCodeB() {
		return codeB;
	}

	public void setCodeB(String codeB) {
		this.codeB = codeB;
	}

	public boolean isDigunakan() {
		return digunakan;
	}

	public void setDigunakan(boolean digunakan) {
		this.digunakan = digunakan;
	}

}
