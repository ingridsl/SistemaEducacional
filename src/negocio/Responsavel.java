package negocio;

public class Responsavel {
	private int cpf, telefone, CEP;
	private String nome, email, endereco, genero;
	
	public Responsavel(){}
	
	public Responsavel (int cpf, String nome, String genero, String endereco, int CEP, int telefone, String email){
		this.cpf = cpf;
		this.telefone = telefone;
		this.CEP = CEP;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.genero = genero;
	}
	public int getCpf(){
		return cpf;
	}
	public int getTelefone(){
		return telefone;
	}
	public int getCEP(){
		return CEP;
	}
	public String getNome(){
		return nome;
	}
	public String getEmail(){
		return email;
	}
	public String getEndereco(){
		return endereco;
	}
	public String getGenero(){
		return genero;
	}
	public void setCpf(int num){
		num= cpf;		
	}
	public void setTelefone(int num){
		num= telefone;		
	}
	public void setCEP(int num){
		num= CEP;		
	}
	public void setNome(String string){
		string= nome;		
	}
	public void setEmail(String string){
		string=email;		
	}
	public void setEndereco(String string){
		string=endereco;		
	}
	public void setGenero(String string){
		string=genero;		
	}
}
