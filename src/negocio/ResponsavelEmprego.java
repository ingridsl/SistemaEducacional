package negocio;

public class ResponsavelEmprego {
	private int ID,cpf;
	
	public ResponsavelEmprego(){ }
	
	public ResponsavelEmprego(int ID, int cpf){
		this.ID = ID;
		this.cpf = cpf;
	}
	
	public int getID(){
		return ID;
	}
	
	public int getCpf(){
		return cpf;
	}
	
	public void setID(int num){
		num = ID;		
	}
	
	public void setCpf(int num){
		num = cpf;		
	}
}
