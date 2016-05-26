package negocio;
import java.time.LocalDate;

public class Aluno {
	private LocalDate dataNascimento;
	private int matricula, CEP, telefone, escolaID, tipoAlunoID, turmaSerieID;
	private String endereco, nome, genero, email, turmaLetra;
	
	public Aluno(){}
	
	public Aluno (int matricula, String nome, LocalDate dataNascimento, String genero, String endereco, int CEP, int telefone, String email){
		this.dataNascimento = dataNascimento;
		this.matricula = matricula;
		this.CEP = CEP;
		this.telefone = telefone;
		this.endereco = endereco;
		this.nome = nome;
		this.genero = genero;
		this.email = email;
	}
	
	public Aluno (int matricula, String nome, LocalDate dataNascimento, String genero, String endereco, int CEP, int telefone,
			  String email, int escolaID, int tipoAlunoID, String turmaLetra){
		this.dataNascimento = dataNascimento;
		this.matricula = matricula;
		this.CEP = CEP;
		this.telefone = telefone;
		this.endereco = endereco;
		this.nome = nome;
		this.genero = genero;
		this.email = email;
		this.escolaID = escolaID;
		this.tipoAlunoID = tipoAlunoID;
		this.turmaLetra = turmaLetra;
	}
		
	public Aluno (int matricula, String nome, LocalDate dataNascimento, String genero, String endereco, int CEP, int telefone,
				  String email, int escolaID, int tipoAlunoID, String turmaLetra, int  turmaSerieID){
		this.dataNascimento = dataNascimento;
		this.matricula = matricula;
		this.CEP = CEP;
		this.telefone = telefone;
		this.endereco = endereco;
		this.nome = nome;
		this.genero = genero;
		this.email = email;
		this.escolaID = escolaID;
		this.tipoAlunoID = tipoAlunoID;
		this.turmaSerieID = turmaSerieID;
		this.turmaLetra = turmaLetra;
	}
	
	public int getEscolaID(){
		return escolaID;
	}
	public int getTipoAlunoID(){
		return tipoAlunoID;
	}
	public int getTurmaSerieID(){
		return turmaSerieID;
	}
	public String getTurmaLetra(){
		return turmaLetra;
	}
	public LocalDate getDataNascimento(){
		return dataNascimento;
	}
	public int getMatricula(){
		return matricula;
	}
	public int getCEP(){
		return CEP;
	}
	public int getTelefone(){
		return telefone;
	}
	public String getEndereco(){
		return endereco;
	}
	public String getNome(){
		return nome;
	}
	public String getGenero(){
		return genero;
	}
	public String getEmail(){
		return email;
	}
	public void setDataNascimento(LocalDate date){
		date = dataNascimento;		
	}
	public void setMatricula(int num){
		num= matricula;		
	}
	public void setCEP(int num){
		num= CEP;		
	}
	public void setTelefone(int num){
		num= telefone;		
	}
	public void setEndereco(String string){
		string = endereco;	
	}
	public void setNome(String string){
		string = nome;	
	}
	public void setGenero(String string){
		string = genero;	
	}
	public void setEmail(String string){
		string = email;	
	}
	public void setEscolaID(int num){
		num= escolaID;		
	}
	public void setTipoAlunoID(int num){
		num= tipoAlunoID;		
	}
	public void setTurmaSerieID(int num){
		num= turmaSerieID;		
	}
	public void setTurmaLetra(String val){
		val= turmaLetra;		
	}
}
