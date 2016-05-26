package negocio;

public class ResponsavelAluno {
	private int cpf, matricula;
	
	public ResponsavelAluno(){ }
	
	public ResponsavelAluno(int cpf, int matricula){
		this.cpf = cpf;
		this.matricula=matricula;
	}
	
	public int getCpf(){
		return cpf;
	}
	
	public int getMatricula(){
		return matricula;
	}
	
	public void setCpf(int num){
		num = cpf;		
	}
	
	public void setMatricula(int num){
		num = matricula;		
	}
}
