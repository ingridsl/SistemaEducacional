package negocio;

public class Emprego {
	private int ID;
	private String categoria;
	private float faixaSalarial;
	
	public Emprego(){}
	
	public Emprego (int ID, String categoria, float faixaSalarial){
		this.ID=ID;
		this.categoria=categoria;
		this.faixaSalarial=faixaSalarial;
	}
	public int getID(){
		return ID;
	}
	public String getCategoria(){
		return categoria;
	}
	public float getFaixaSalarial(){
		return faixaSalarial;
	}
	public void setID(int num){
		num=ID;		
	}
	public void setCategoria(String string){
		string=categoria;		
	}
	public void setFaixaSalarial(float num){
		num=faixaSalarial;		
	}
}
