package apresentacao;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import negocio.*;
import persistencia.*;

public class Interface {
	
	private static AlunoDAO alunoDAO = new AlunoDAO();
	private static Aluno novoAluno;
	private static ResponsavelDAO respDAO = new ResponsavelDAO();
	private static Responsavel novoResp;
	private static EmpregoDAO empDAO = new EmpregoDAO();
	private static Emprego novoEmp;
	private static ResponsavelAlunoDAO respAlunoDAO = new ResponsavelAlunoDAO();
	private static ResponsavelEmpregoDAO respEmpDAO = new ResponsavelEmpregoDAO();
	
	/*Tela Principal*/
	private static JFrame tela1 = new JFrame();
	private static JPanel panel1 = new JPanel();
	private static JLabel label1;
	private static JButton consultar;
	private static JButton inserir;
	private static JButton editar;
	private static JButton deletar;
	private static ButtonListener listener = new ButtonListener();
	private enum Opcao {DEFAULT, CONSULTAR, INSERIR, EDITAR, DELETAR};
	private enum Campo {DEFAULT, ALUNO, RESPONSAVEL, EMPREGO};
	private static Opcao op = Opcao.DEFAULT;
	private static Campo campo = Campo.DEFAULT;
	
	/*Tela Escolha Campo*/
	private static JFrame tela2 = null;
	private static JPanel panel2;
	private static JLabel label2;
	private static JButton aluno;
	private static JButton responsavel;
	private static JButton emprego;
	
	/*Tela Final*/
	private static JFrame tela3 = null;
	private static JButton pesq;
	private static JButton aplicar;
	private static JButton edit;
	private static JButton aplEdit;
	private static JButton deletBut;
	private static JButton back;
	private static pesqListener pListener = new pesqListener();
	private static applyListener apListener = new applyListener();
	private static editListener eListener = new editListener();
	private static aplEditListener eAplListener = new aplEditListener();
	private static deletListener delListener = new deletListener();
	private static Container contentPane;
	private static SpringLayout layout;
	private static JLabel codigo;
	private static JTextField codText;
	private static JTextField emailText;
	private static JTextField telText;
	private static JTextField cepText;
	private static JTextField endText;
	private static JTextField genText;
	private static JTextField nascText;
	private static JTextField nomeText;
	private static JTextField catText;
	private static JTextField salText;
	private static JLabel info;
	private static JComboBox<String> escolaCB;
	private static JComboBox<String> turmaCB;
	private static JComboBox<String> tipoCB;
	private static JScrollPane respAlunoScroll;
	private static JList<String> respList;
	private static JScrollPane respEmpScroll;
	private static JList<String> empList;
	
	
	/*Tela Consulta/Edicao*/
	private static JFrame tela4 = null;
	
	public static void main(String args[]) {
		
		tela1.setTitle("Banco de Dados do Sistema Educacional");
	    tela1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    tela1.setSize(300,230);
	    
	    tela1.addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent we) {
	    		System.exit(0);
      	  	}
	    });
	     
	    panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
	    panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
	    label1 = new JLabel("<html>Escolha uma das opcoes abaixo:<html>");
	    panel1.add(label1); panel1.add(Box.createRigidArea(new Dimension(0, 10)));
	    consultar = new JButton("<html>Consultar<html>");
	    panel1.add(consultar); consultar.addActionListener(listener);
	    panel1.add(Box.createRigidArea(new Dimension(0, 10)));
	    inserir = new JButton("<html>Inserir<html>");
	    panel1.add(inserir); inserir.addActionListener(listener);
	    panel1.add(Box.createRigidArea(new Dimension(0, 10)));
	    editar = new JButton("<html>Editar<html>");
	    panel1.add(editar); editar.addActionListener(listener);
	    panel1.add(Box.createRigidArea(new Dimension(0, 10)));
	    deletar = new JButton("<html>Deletar<html>");
	    panel1.add(deletar); deletar.addActionListener(listener);
        
        tela1.getContentPane().add(panel1, BorderLayout.CENTER);
        tela1.setVisible(true);
	}
	 
	 private static void telaCampo(){
		 tela2 = new JFrame(); panel2 = new JPanel();
		 tela2.setTitle("Banco de Dados do Sistema Educacional");
		 tela2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 tela2.setSize(300,240);
		    
		 tela2.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent we) {
		    	System.exit(0);
	      	}
		 });
		     
		 panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		 panel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		    
		 label2 = new JLabel("<html>Escolha o campo a ser manipulado:<html>");
		 panel2.add(label2); panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		 aluno = new JButton("<html>Aluno<html>");
		 panel2.add(aluno); aluno.addActionListener(listener);
		 panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		 responsavel = new JButton("<html>Responsavel<html>");
		 panel2.add(responsavel); responsavel.addActionListener(listener);
		 panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		 emprego = new JButton("<html>Emprego<html>");
		 panel2.add(emprego); emprego.addActionListener(listener);
		 panel2.add(Box.createRigidArea(new Dimension(0, 20)));
		 back = new JButton("Voltar"); back.addActionListener(listener);
		 panel2.add(back);
		 	        
	     tela2.getContentPane().add(panel2, BorderLayout.CENTER);
	     tela1.setVisible(false); tela2.setVisible(true);		 
	 }
	 
	 private static void telaFinal(){
		 tela3 = new JFrame();
		 tela3.setTitle("Banco de Dados do Sistema Educacional");
		 tela3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 contentPane = tela3.getContentPane();
		 layout = new SpringLayout();
		 contentPane.setLayout(layout);
		 back = new JButton("Voltar"); back.addActionListener(listener);
		    
		 tela3.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent we) {
		    	System.exit(0);
	      	}
		 });
		 
		 switch(op){
		 case CONSULTAR:
			 tela3.setSize(500,150);
			 codText = new JTextField(null, 15);
			 pesq = new JButton("Pesquisar campo");
			 pesq.addActionListener(pListener);
			 
			 switch(campo){
			 case ALUNO:
				 codigo = new JLabel("<html>Escolha a matricula a ser consultada:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 break;
			 case RESPONSAVEL:
				 codigo = new JLabel("<html>Escolha o CPF a ser consultado:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 break;
			 case EMPREGO:
				 codigo = new JLabel("<html>Escolha o ID a ser consultado:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 break;
			 default:
				 break;
			 }
			 
			 contentPane.add(pesq); contentPane.add(back);
			 layout.putConstraint(SpringLayout.WEST, pesq, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, pesq, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.NORTH, back, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.EAST, pesq);
			 
			 info = new JLabel("");
			 contentPane.add(info);
			 layout.putConstraint(SpringLayout.WEST, info, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, info, 35, SpringLayout.NORTH, pesq);
			 
			 break;
		 case INSERIR:
			 codText = new JTextField(null, 15);
			 aplicar = new JButton("Inserir novos dados"); aplicar.addActionListener(apListener);
			
			 switch(campo){
			 case ALUNO:
				 tela3.setSize(600,540);
				 
				 codigo = new JLabel("<html>*Matricula:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 
				 JLabel nome = new JLabel("<html>*Nome:<html>");
				 nomeText = new JTextField(null, 40);
				 contentPane.add(nome); contentPane.add(nomeText);
				 layout.putConstraint(SpringLayout.WEST, nome, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, nome, 25, SpringLayout.NORTH, codigo);
				 layout.putConstraint(SpringLayout.NORTH, nomeText, 25, SpringLayout.NORTH, codigo);
				 layout.putConstraint(SpringLayout.WEST, nomeText, 20, SpringLayout.EAST, nome);
				 
				 JLabel nasc = new JLabel("<html>*Data de Nascimento:<html>");
				 nascText = new JTextField("YYYY-MM-DD", 15);
				 contentPane.add(nasc); contentPane.add(nascText);
				 layout.putConstraint(SpringLayout.WEST, nasc, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, nasc, 25, SpringLayout.NORTH, nome);
				 layout.putConstraint(SpringLayout.NORTH, nascText, 25, SpringLayout.NORTH, nome);
				 layout.putConstraint(SpringLayout.WEST, nascText, 20, SpringLayout.EAST, nasc);
				 
				 JLabel genero = new JLabel("<html>Genero:<html>");
				 genText = new JTextField(null, 15);
				 contentPane.add(genero); contentPane.add(genText);
				 layout.putConstraint(SpringLayout.WEST, genero, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, genero, 25, SpringLayout.NORTH, nasc);
				 layout.putConstraint(SpringLayout.NORTH, genText, 25, SpringLayout.NORTH, nasc);
				 layout.putConstraint(SpringLayout.WEST, genText, 20, SpringLayout.EAST, genero);
				 
				 JLabel endereco = new JLabel("<html>*Endereco:<html>");
				 endText = new JTextField(null, 40);
				 contentPane.add(endereco); contentPane.add(endText);
				 layout.putConstraint(SpringLayout.WEST, endereco, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, endereco, 25, SpringLayout.NORTH, genero);
				 layout.putConstraint(SpringLayout.NORTH, endText, 25, SpringLayout.NORTH, genero);
				 layout.putConstraint(SpringLayout.WEST, endText, 20, SpringLayout.EAST, endereco);
				 
				 JLabel cep = new JLabel("<html>*CEP:<html>");
				 cepText = new JTextField(null, 15);
				 contentPane.add(cep); contentPane.add(cepText);
				 layout.putConstraint(SpringLayout.WEST, cep, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, cep, 25, SpringLayout.NORTH, endereco);
				 layout.putConstraint(SpringLayout.NORTH, cepText, 25, SpringLayout.NORTH, endereco);
				 layout.putConstraint(SpringLayout.WEST, cepText, 20, SpringLayout.EAST, cep);
				 
				 JLabel telefone = new JLabel("<html>Telefone:<html>");
				 telText = new JTextField(null, 15);
				 contentPane.add(telefone); contentPane.add(telText);
				 layout.putConstraint(SpringLayout.WEST, telefone, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, telefone, 25, SpringLayout.NORTH, cep);
				 layout.putConstraint(SpringLayout.NORTH, telText, 25, SpringLayout.NORTH, cep);
				 layout.putConstraint(SpringLayout.WEST, telText, 20, SpringLayout.EAST, telefone);
				 
				 JLabel email = new JLabel("<html>E-mail:<html>");
				 emailText = new JTextField(null, 40);
				 contentPane.add(email); contentPane.add(emailText);
				 layout.putConstraint(SpringLayout.WEST, email, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, email, 25, SpringLayout.NORTH, telefone);
				 layout.putConstraint(SpringLayout.NORTH, emailText, 25, SpringLayout.NORTH, telefone);
				 layout.putConstraint(SpringLayout.WEST, emailText, 20, SpringLayout.EAST, email);
				 
				 ArrayList<String> idEsc = alunoDAO.consultaEscola();
				 String[] escItems = new String[idEsc.size()];
				 escItems = idEsc.toArray(escItems); 
				 escolaCB = new JComboBox<String>(escItems);
				 JLabel escola = new JLabel("<html>ID Escola: <html>");
				 contentPane.add(escola); contentPane.add(escolaCB);
				 layout.putConstraint(SpringLayout.WEST, escola, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, escola, 25, SpringLayout.NORTH, email);
				 layout.putConstraint(SpringLayout.NORTH, escolaCB, 25, SpringLayout.NORTH, email);
				 layout.putConstraint(SpringLayout.WEST, escolaCB, 20, SpringLayout.EAST, escola);
				 
				 ArrayList<String> idTipo = alunoDAO.consultaTipo();
				 String[] tipoItems = new String[idTipo.size()];
				 tipoItems = idTipo.toArray(tipoItems); 
				 tipoCB = new JComboBox<String>(tipoItems);
				 JLabel tipo = new JLabel("<html>ID Tipo Aluno: <html>");
				 contentPane.add(tipo); contentPane.add(tipoCB);
				 layout.putConstraint(SpringLayout.WEST, tipo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, tipo, 30, SpringLayout.NORTH, escola);
				 layout.putConstraint(SpringLayout.NORTH, tipoCB, 30, SpringLayout.NORTH, escola);
				 layout.putConstraint(SpringLayout.WEST, tipoCB, 20, SpringLayout.EAST, tipo);
				 
				 ArrayList<String> idTurma = alunoDAO.consultaTurma();
				 String[] turmaItems = new String[idTurma.size()];
				 turmaItems = idTurma.toArray(turmaItems); 
				 turmaCB = new JComboBox<String>(turmaItems);
				 JLabel turma = new JLabel("<html>Turma: <html>");
				 contentPane.add(turma);  contentPane.add(turmaCB);
				 layout.putConstraint(SpringLayout.WEST, turma, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, turma, 30, SpringLayout.NORTH, tipo);
				 layout.putConstraint(SpringLayout.NORTH, turmaCB, 30, SpringLayout.NORTH, tipo);
				 layout.putConstraint(SpringLayout.WEST, turmaCB, 20, SpringLayout.EAST, turma);
				 
				 ArrayList<Responsavel> auxResp = respDAO.consultaResponsavel();
				 ArrayList<String> arrResp = new ArrayList<String>();
				 for(int k = 0; k < auxResp.size(); k++)
						 arrResp.add(Integer.toString(auxResp.get(k).getCpf()));
				 String[] respItems = new String[arrResp.size()];
				 respItems = arrResp.toArray(respItems); 
				 respList = new JList<String>(respItems);
				 respList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				 respAlunoScroll = new JScrollPane(respList);
				 respAlunoScroll.setPreferredSize(new Dimension(250, 80));
				 JLabel resp = new JLabel("<html>Responsavel: <html>");
				 contentPane.add(resp); contentPane.add(respAlunoScroll);
				 layout.putConstraint(SpringLayout.WEST, resp, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, resp, 30, SpringLayout.NORTH, turma);
				 layout.putConstraint(SpringLayout.NORTH, respAlunoScroll, 30, SpringLayout.NORTH, turma);
				 layout.putConstraint(SpringLayout.WEST, respAlunoScroll, 20, SpringLayout.EAST, resp);
				 
				 contentPane.add(aplicar); contentPane.add(back);
				 layout.putConstraint(SpringLayout.WEST, aplicar, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, aplicar, 90, SpringLayout.NORTH, resp);
				 layout.putConstraint(SpringLayout.NORTH, back, 90, SpringLayout.NORTH, resp);
				 layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.EAST, aplicar);
				 break;
			 case RESPONSAVEL:
				 tela3.setSize(600,450);
				 
				 codigo = new JLabel("<html>*CPF:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 
				 JLabel nome2 = new JLabel("<html>*Nome:<html>");
				 nomeText = new JTextField(null, 40);
				 contentPane.add(nome2); contentPane.add(nomeText);
				 layout.putConstraint(SpringLayout.WEST, nome2, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, nome2, 25, SpringLayout.NORTH, codigo);
				 layout.putConstraint(SpringLayout.NORTH, nomeText, 25, SpringLayout.NORTH, codigo);
				 layout.putConstraint(SpringLayout.WEST, nomeText, 20, SpringLayout.EAST, nome2);
				 
				 JLabel genero2 = new JLabel("<html>*Genero:<html>");
				 genText = new JTextField(null, 15);
				 contentPane.add(genero2); contentPane.add(genText);
				 layout.putConstraint(SpringLayout.WEST, genero2, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, genero2, 25, SpringLayout.NORTH, nome2);
				 layout.putConstraint(SpringLayout.NORTH, genText, 25, SpringLayout.NORTH, nome2);
				 layout.putConstraint(SpringLayout.WEST, genText, 20, SpringLayout.EAST, genero2);
				 
				 JLabel endereco2 = new JLabel("<html>*Endereco:<html>");
				 endText = new JTextField(null, 40);
				 contentPane.add(endereco2); contentPane.add(endText);
				 layout.putConstraint(SpringLayout.WEST, endereco2, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, endereco2, 25, SpringLayout.NORTH, genero2);
				 layout.putConstraint(SpringLayout.NORTH, endText, 25, SpringLayout.NORTH, genero2);
				 layout.putConstraint(SpringLayout.WEST, endText, 20, SpringLayout.EAST, endereco2);
				 
				 JLabel cep2 = new JLabel("<html>*CEP:<html>");
				 cepText = new JTextField(null, 15);
				 contentPane.add(cep2); contentPane.add(cepText);
				 layout.putConstraint(SpringLayout.WEST, cep2, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, cep2, 25, SpringLayout.NORTH, endereco2);
				 layout.putConstraint(SpringLayout.NORTH, cepText, 25, SpringLayout.NORTH, endereco2);
				 layout.putConstraint(SpringLayout.WEST, cepText, 20, SpringLayout.EAST, cep2);
				 
				 JLabel telefone2 = new JLabel("<html>*Telefone:<html>");
				 telText = new JTextField(null, 15);
				 contentPane.add(telefone2); contentPane.add(telText);
				 layout.putConstraint(SpringLayout.WEST, telefone2, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, telefone2, 25, SpringLayout.NORTH, cep2);
				 layout.putConstraint(SpringLayout.NORTH, telText, 25, SpringLayout.NORTH, cep2);
				 layout.putConstraint(SpringLayout.WEST, telText, 20, SpringLayout.EAST, telefone2);
				 
				 JLabel email2 = new JLabel("<html>*E-mail:<html>");
				 emailText = new JTextField(null, 40);
				 contentPane.add(email2); contentPane.add(emailText);
				 layout.putConstraint(SpringLayout.WEST, email2, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, email2, 25, SpringLayout.NORTH, telefone2);
				 layout.putConstraint(SpringLayout.NORTH, emailText, 25, SpringLayout.NORTH, telefone2);
				 layout.putConstraint(SpringLayout.WEST, emailText, 20, SpringLayout.EAST, email2);
				 
				 ArrayList<Emprego> auxEmp = empDAO.consultaEmprego();
				 ArrayList<String> arrEmp = new ArrayList<String>();
				 for(int k = 0; k < auxEmp.size(); k++)
						 arrEmp.add(Integer.toString(auxEmp.get(k).getID()));
				 String[] empItems = new String[arrEmp.size()];
				 empItems = arrEmp.toArray(empItems); 
				 empList = new JList<String>(empItems);
				 empList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				 respEmpScroll = new JScrollPane(empList);
				 respEmpScroll.setPreferredSize(new Dimension(250, 80));
				 JLabel emps = new JLabel("<html>Empregos: <html>");
				 contentPane.add(emps); contentPane.add(respEmpScroll);
				 layout.putConstraint(SpringLayout.WEST, emps, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, emps, 30, SpringLayout.NORTH, email2);
				 layout.putConstraint(SpringLayout.NORTH, respEmpScroll, 30, SpringLayout.NORTH, email2);
				 layout.putConstraint(SpringLayout.WEST, respEmpScroll, 20, SpringLayout.EAST, emps);
				 
				 contentPane.add(aplicar); contentPane.add(back);
				 layout.putConstraint(SpringLayout.WEST, aplicar, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, aplicar, 90, SpringLayout.NORTH, emps);
				 layout.putConstraint(SpringLayout.NORTH, back, 90, SpringLayout.NORTH, emps);
				 layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.EAST, aplicar);
				 break;
			 case EMPREGO:
				 tela3.setSize(600,250);
				 
				 codigo = new JLabel("<html>*ID:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 
				 JLabel categoria = new JLabel("<html>*Categoria:<html>");
				 catText = new JTextField(null, 40);
				 contentPane.add(categoria); contentPane.add(catText);
				 layout.putConstraint(SpringLayout.WEST, categoria, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, categoria, 25, SpringLayout.NORTH, codigo);
				 layout.putConstraint(SpringLayout.NORTH, catText, 25, SpringLayout.NORTH, codigo);
				 layout.putConstraint(SpringLayout.WEST, catText, 20, SpringLayout.EAST, categoria);
				 
				 JLabel salario = new JLabel("<html>*Faixa Salarial:<html>");
				 salText = new JTextField(null, 40);
				 contentPane.add(salario); contentPane.add(salText);
				 layout.putConstraint(SpringLayout.WEST, salario, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, salario, 25, SpringLayout.NORTH, categoria);
				 layout.putConstraint(SpringLayout.NORTH, salText, 25, SpringLayout.NORTH, categoria);
				 layout.putConstraint(SpringLayout.WEST, salText, 20, SpringLayout.EAST, salario);
				 
				 contentPane.add(aplicar); contentPane.add(back);
				 layout.putConstraint(SpringLayout.WEST, aplicar, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, aplicar, 25, SpringLayout.NORTH, salario);
				 layout.putConstraint(SpringLayout.NORTH, back, 25, SpringLayout.NORTH, salario);
				 layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.EAST, aplicar);
				 break;
			 default:
				 break;
			 }
			 
			 info = new JLabel("<html>Preencha todos os campos corretamente.<br>"
					 + "Todos os campos com asterisco s�o obrigat�rios.<br>"
			 		 + "Obs.: n�o utilizar caracteres especiais em campos num�ricos.<html>");
			 contentPane.add(info);
			 layout.putConstraint(SpringLayout.WEST, info, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, info, 35, SpringLayout.NORTH, aplicar);
			 
			 break;
		 case EDITAR:
			 tela3.setSize(450,160);
			 codText = new JTextField(null, 15);
			 edit = new JButton("Editar campo");
			 edit.addActionListener(eListener);
			 
			 switch(campo){
			 case ALUNO:
				 codigo = new JLabel("<html>Escolha a matricula a ser editada:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 break;
			 case RESPONSAVEL:
				 codigo = new JLabel("<html>Escolha o CPF a ser editado:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 break;
			 case EMPREGO:
				 codigo = new JLabel("<html>Escolha o ID a ser editado:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 break;
			 default:
				 break;
			 }
			 
			 contentPane.add(edit); contentPane.add(back);
			 layout.putConstraint(SpringLayout.WEST, edit, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, edit, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.NORTH, back, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.EAST, edit);
			 
			 info = new JLabel("");
			 contentPane.add(info);
			 layout.putConstraint(SpringLayout.WEST, info, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, info, 35, SpringLayout.NORTH, edit);
			 
			 break;
		 case DELETAR:
			 tela3.setSize(300,180);
			 codText = new JTextField(null, 15);
			 deletBut = new JButton("<html>Deletar os dados do<br>campo selecionado<html>");
			 deletBut.addActionListener(delListener);
			 
			 switch(campo){
			 case ALUNO:
				 codigo = new JLabel("<html>Matricula:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 break;
			 case RESPONSAVEL:
				 codigo = new JLabel("<html>CPF:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 break;
			 case EMPREGO:
				 codigo = new JLabel("<html>ID:<html>");
				 contentPane.add(codigo); contentPane.add(codText);
				 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.NORTH, codText, 25, SpringLayout.NORTH, contentPane);
				 layout.putConstraint(SpringLayout.WEST, codText, 20, SpringLayout.EAST, codigo);
				 break;
			 default:
				break;
			 }
			 
			 contentPane.add(deletBut); contentPane.add(back);
			 layout.putConstraint(SpringLayout.WEST, deletBut, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, deletBut, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.NORTH, back, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.EAST, deletBut);
			 
			 info = new JLabel("");
			 contentPane.add(info);
			 layout.putConstraint(SpringLayout.WEST, info, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, info, 50, SpringLayout.NORTH, deletBut);
			 
			 break;
		 default:
		 	break;
		 }
		 
	     tela2.setVisible(false); tela3.setVisible(true);	
	 }
	 
	 private static void telaConsulta(){
		 tela4 = new JFrame();
		 tela4.setTitle("Banco de Dados do Sistema Educacional");
		 tela4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 contentPane = tela4.getContentPane();
		 layout = new SpringLayout();
		 contentPane.setLayout(layout);
		 back = new JButton("Voltar"); back.addActionListener(listener);
		 
		 tela4.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent we) {
		    	System.exit(0);
	      	}
		 });
		
		 switch(campo){
		 case ALUNO:
			 codigo = new JLabel("<html>Matricula: <html>" + novoAluno.getMatricula());
			 contentPane.add(codigo);
			 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
			 JLabel nome = new JLabel("<html>Nome: <html>" + novoAluno.getNome());
			 contentPane.add(nome);
			 layout.putConstraint(SpringLayout.WEST, nome, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, nome, 25, SpringLayout.NORTH, codigo);
			 JLabel nasc = new JLabel("<html>Data de Nascimento: <html>" + novoAluno.getDataNascimento());
			 contentPane.add(nasc);
			 layout.putConstraint(SpringLayout.WEST, nasc, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, nasc, 25, SpringLayout.NORTH, nome);
			 JLabel genero = new JLabel("<html>Genero: <html>" + novoAluno.getGenero());
			 contentPane.add(genero);
			 layout.putConstraint(SpringLayout.WEST, genero, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, genero, 25, SpringLayout.NORTH, nasc);
			 JLabel endereco = new JLabel("<html>Endereco: <html>" + novoAluno.getEndereco());
			 contentPane.add(endereco);
			 layout.putConstraint(SpringLayout.WEST, endereco, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, endereco, 25, SpringLayout.NORTH, genero);
			 JLabel cep = new JLabel("<html>CEP: <html>" + novoAluno.getCEP());
			 contentPane.add(cep);
			 layout.putConstraint(SpringLayout.WEST, cep, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, cep, 25, SpringLayout.NORTH, endereco);
			 JLabel telefone = new JLabel("<html>Telefone: <html>" + novoAluno.getTelefone());
			 contentPane.add(telefone);
			 layout.putConstraint(SpringLayout.WEST, telefone, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, telefone, 25, SpringLayout.NORTH, cep);
			 JLabel email = new JLabel("<html>E-mail: <html>" + novoAluno.getEmail());
			 contentPane.add(email);
			 layout.putConstraint(SpringLayout.WEST, email, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, email, 25, SpringLayout.NORTH, telefone);
			 JLabel escola = new JLabel("<html>ID Escola: <html>" + novoAluno.getEscolaID());
			 contentPane.add(escola);
			 layout.putConstraint(SpringLayout.WEST, escola, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, escola, 25, SpringLayout.NORTH, email);
			 JLabel tipo = new JLabel("<html>ID Tipo Aluno: <html>" + novoAluno.getTipoAlunoID());
			 contentPane.add(tipo);
			 layout.putConstraint(SpringLayout.WEST, tipo, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, tipo, 25, SpringLayout.NORTH, escola);
			 JLabel turma = new JLabel("<html>Turma: <html>" + novoAluno.getTurmaLetra());
			 contentPane.add(turma);
			 layout.putConstraint(SpringLayout.WEST, turma, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, turma, 25, SpringLayout.NORTH, tipo);
			 JLabel resp = new JLabel("<html>Responsaveis: ");
			 ArrayList<String> respAsso = respAlunoDAO.consultaResp(novoAluno.getMatricula());
			 int cont = 0;
			 for(String r : respAsso){
				 resp.setText(resp.getText() + "<br>	" + r);
				 cont++;
			 }
			 resp.setText(resp.getText() + "<html>");
			 contentPane.add(resp);
			 layout.putConstraint(SpringLayout.WEST, resp, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, resp, 25, SpringLayout.NORTH, turma);
			 contentPane.add(back);
			 layout.putConstraint(SpringLayout.WEST, back, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, back, 30+(cont*10), SpringLayout.NORTH, resp);
			 
			 tela4.setSize(600,410+(cont*10));
			 break;
		 case RESPONSAVEL:
			 codigo = new JLabel("<html>CPF: <html>" + novoResp.getCpf());
			 contentPane.add(codigo);
			 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
			 JLabel nome2 = new JLabel("<html>Nome: <html>" + novoResp.getNome());
			 contentPane.add(nome2);
			 layout.putConstraint(SpringLayout.WEST, nome2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, nome2, 25, SpringLayout.NORTH, codigo);
			 JLabel genero2 = new JLabel("<html>Genero: <html>" + novoResp.getGenero());
			 contentPane.add(genero2);
			 layout.putConstraint(SpringLayout.WEST, genero2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, genero2, 25, SpringLayout.NORTH, nome2);
			 JLabel endereco2 = new JLabel("<html>Endereco: <html>" + novoResp.getEndereco());
			 contentPane.add(endereco2);
			 layout.putConstraint(SpringLayout.WEST, endereco2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, endereco2, 25, SpringLayout.NORTH, genero2);
			 JLabel cep2 = new JLabel("<html>CEP: <html>" + novoResp.getCEP());
			 contentPane.add(cep2);
			 layout.putConstraint(SpringLayout.WEST, cep2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, cep2, 25, SpringLayout.NORTH, endereco2);
			 JLabel telefone2 = new JLabel("<html>Telefone: <html>" + novoResp.getTelefone());
			 contentPane.add(telefone2);
			 layout.putConstraint(SpringLayout.WEST, telefone2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, telefone2, 25, SpringLayout.NORTH, cep2);
			 JLabel email2 = new JLabel("<html>E-mail: <html>" + novoResp.getEmail());
			 contentPane.add(email2);
			 layout.putConstraint(SpringLayout.WEST, email2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, email2, 25, SpringLayout.NORTH, telefone2);
			 JLabel emps = new JLabel("<html>Empregos: ");
			 ArrayList<String> empAsso = respEmpDAO.consultaEmprego(novoResp.getCpf());
			 int cont2 = 0;
			 for(String r : empAsso){
				 emps.setText(emps.getText() + "<br>	" + r);
				 cont2++;
			 }
			 emps.setText(emps.getText() + "<html>");
			 contentPane.add(emps);
			 layout.putConstraint(SpringLayout.WEST, emps, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, emps, 25, SpringLayout.NORTH, email2);
			 contentPane.add(back);
			 layout.putConstraint(SpringLayout.WEST, back, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, back, 30+(cont2*10), SpringLayout.NORTH, emps);
			 
			 tela4.setSize(600,300+(cont2*10));
			 break;
		 case EMPREGO:
			 tela4.setSize(600,200);
			 codigo = new JLabel("<html>ID: <html>" + novoEmp.getID());
			 contentPane.add(codigo);
			 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
			 JLabel categoria = new JLabel("<html>Categoria: <html>" + novoEmp.getCategoria());
			 contentPane.add(categoria);
			 layout.putConstraint(SpringLayout.WEST, categoria, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, categoria, 25, SpringLayout.NORTH, codigo);
			 JLabel salario = new JLabel("<html>Faixa Salarial: <html>" + novoEmp.getFaixaSalarial());
			 contentPane.add(salario);
			 layout.putConstraint(SpringLayout.WEST, salario, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, salario, 25, SpringLayout.NORTH, categoria);
			 contentPane.add(back);
			 layout.putConstraint(SpringLayout.WEST, back, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, back, 25, SpringLayout.NORTH, salario);
			 break;
		 default:
			 break;
		 }

		 tela3.setVisible(false); tela4.setVisible(true);
	 }
	 
	 private static void telaEdicao(){
		 tela4 = new JFrame();
		 tela4.setTitle("Banco de Dados do Sistema Educacional");
		 tela4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 contentPane = tela4.getContentPane();
		 layout = new SpringLayout();
		 contentPane.setLayout(layout);
		 aplEdit = new JButton("Aplicar edicao"); aplEdit.addActionListener(eAplListener);
		 back = new JButton("Voltar"); back.addActionListener(listener);
		 
		 tela4.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent we) {
		    	System.exit(0);
	      	}
		 });
		
		 switch(campo){
		 case ALUNO:
			 tela4.setSize(600,540);
			 
			 codigo = new JLabel("<html>Matricula: <html>" + Integer.toString(novoAluno.getMatricula()));
			 contentPane.add(codigo);
			 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
			 
			 JLabel nome = new JLabel("<html>*Nome:<html>");
			 nomeText = new JTextField(novoAluno.getNome(), 40);
			 contentPane.add(nome); contentPane.add(nomeText);
			 layout.putConstraint(SpringLayout.WEST, nome, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, nome, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.NORTH, nomeText, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.WEST, nomeText, 20, SpringLayout.EAST, nome);
			 
			 JLabel nasc = new JLabel("<html>*Data de Nascimento:<html>");
			 DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 nascText = new JTextField(form.format(novoAluno.getDataNascimento()), 15);
			 contentPane.add(nasc); contentPane.add(nascText);
			 layout.putConstraint(SpringLayout.WEST, nasc, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, nasc, 25, SpringLayout.NORTH, nome);
			 layout.putConstraint(SpringLayout.NORTH, nascText, 25, SpringLayout.NORTH, nome);
			 layout.putConstraint(SpringLayout.WEST, nascText, 20, SpringLayout.EAST, nasc);
			 
			 JLabel genero = new JLabel("<html>Genero:<html>");
			 genText = new JTextField(novoAluno.getGenero(), 15);
			 contentPane.add(genero); contentPane.add(genText);
			 layout.putConstraint(SpringLayout.WEST, genero, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, genero, 25, SpringLayout.NORTH, nasc);
			 layout.putConstraint(SpringLayout.NORTH, genText, 25, SpringLayout.NORTH, nasc);
			 layout.putConstraint(SpringLayout.WEST, genText, 20, SpringLayout.EAST, genero);
			 
			 JLabel endereco = new JLabel("<html>*Endereco:<html>");
			 endText = new JTextField(novoAluno.getEndereco(), 40);
			 contentPane.add(endereco); contentPane.add(endText);
			 layout.putConstraint(SpringLayout.WEST, endereco, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, endereco, 25, SpringLayout.NORTH, genero);
			 layout.putConstraint(SpringLayout.NORTH, endText, 25, SpringLayout.NORTH, genero);
			 layout.putConstraint(SpringLayout.WEST, endText, 20, SpringLayout.EAST, endereco);
			 
			 JLabel cep = new JLabel("<html>*CEP:<html>");
			 cepText = new JTextField(Integer.toString(novoAluno.getCEP()), 15);
			 contentPane.add(cep); contentPane.add(cepText);
			 layout.putConstraint(SpringLayout.WEST, cep, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, cep, 25, SpringLayout.NORTH, endereco);
			 layout.putConstraint(SpringLayout.NORTH, cepText, 25, SpringLayout.NORTH, endereco);
			 layout.putConstraint(SpringLayout.WEST, cepText, 20, SpringLayout.EAST, cep);
			 
			 JLabel telefone = new JLabel("<html>Telefone:<html>");
			 telText = new JTextField(Integer.toString(novoAluno.getTelefone()), 15);
			 contentPane.add(telefone); contentPane.add(telText);
			 layout.putConstraint(SpringLayout.WEST, telefone, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, telefone, 25, SpringLayout.NORTH, cep);
			 layout.putConstraint(SpringLayout.NORTH, telText, 25, SpringLayout.NORTH, cep);
			 layout.putConstraint(SpringLayout.WEST, telText, 20, SpringLayout.EAST, telefone);
			 
			 JLabel email = new JLabel("<html>E-mail:<html>");
			 emailText = new JTextField(novoAluno.getEmail(), 40);
			 contentPane.add(email); contentPane.add(emailText);
			 layout.putConstraint(SpringLayout.WEST, email, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, email, 25, SpringLayout.NORTH, telefone);
			 layout.putConstraint(SpringLayout.NORTH, emailText, 25, SpringLayout.NORTH, telefone);
			 layout.putConstraint(SpringLayout.WEST, emailText, 20, SpringLayout.EAST, email);
			 
			 ArrayList<String> idEsc = alunoDAO.consultaEscola();
			 String[] escItems = new String[idEsc.size()];
			 escItems = idEsc.toArray(escItems); 
			 escolaCB = new JComboBox<String>(escItems);
			 if(novoAluno.getEscolaID() != -1 && novoAluno.getEscolaID() != 0)
				 escolaCB.setSelectedItem(Integer.toString(novoAluno.getEscolaID()));
			 JLabel escola = new JLabel("<html>ID Escola: <html>");
			 contentPane.add(escola); contentPane.add(escolaCB);
			 layout.putConstraint(SpringLayout.WEST, escola, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, escola, 25, SpringLayout.NORTH, email);
			 layout.putConstraint(SpringLayout.NORTH, escolaCB, 25, SpringLayout.NORTH, email);
			 layout.putConstraint(SpringLayout.WEST, escolaCB, 20, SpringLayout.EAST, escola);
			 
			 ArrayList<String> idTipo = alunoDAO.consultaTipo();
			 String[] tipoItems = new String[idTipo.size()];
			 tipoItems = idTipo.toArray(tipoItems); 
			 tipoCB = new JComboBox<String>(tipoItems);
			 if(novoAluno.getTipoAlunoID() != -1 && novoAluno.getTipoAlunoID() != 0)
				 tipoCB.setSelectedItem(Integer.toString(novoAluno.getTipoAlunoID()));
			 JLabel tipo = new JLabel("<html>ID Tipo Aluno: <html>");
			 contentPane.add(tipo); contentPane.add(tipoCB);
			 layout.putConstraint(SpringLayout.WEST, tipo, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, tipo, 30, SpringLayout.NORTH, escola);
			 layout.putConstraint(SpringLayout.NORTH, tipoCB, 30, SpringLayout.NORTH, escola);
			 layout.putConstraint(SpringLayout.WEST, tipoCB, 20, SpringLayout.EAST, tipo);
			 
			 ArrayList<String> idTurma = alunoDAO.consultaTurma();
			 String[] turmaItems = new String[idTurma.size()];
			 turmaItems = idTurma.toArray(turmaItems); 
			 turmaCB = new JComboBox<String>(turmaItems);
			 if(novoAluno.getTurmaLetra() != null)
				 turmaCB.setSelectedItem(novoAluno.getTurmaLetra());
			 JLabel turma = new JLabel("<html>Turma: <html>");
			 contentPane.add(turma);  contentPane.add(turmaCB);
			 layout.putConstraint(SpringLayout.WEST, turma, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, turma, 30, SpringLayout.NORTH, tipo);
			 layout.putConstraint(SpringLayout.NORTH, turmaCB, 30, SpringLayout.NORTH, tipo);
			 layout.putConstraint(SpringLayout.WEST, turmaCB, 20, SpringLayout.EAST, turma);
			 
			 ArrayList<Responsavel> auxResp = respDAO.consultaResponsavel();
			 ArrayList<String> arrResp = new ArrayList<String>();
			 for(int k = 0; k < auxResp.size(); k++)
					 arrResp.add(Integer.toString(auxResp.get(k).getCpf()));
			 String[] respItems = new String[arrResp.size()];
			 respItems = arrResp.toArray(respItems); 
			 respList = new JList<String>(respItems);
			 respList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			 ArrayList<String> selectResp = respAlunoDAO.consultaResp(Integer.parseInt(codText.getText()));
			 for(int i = 0; i < auxResp.size(); i++){
				 for(int j = 0; j < selectResp.size(); j++){
					 if(respList.getModel().getElementAt(i).equals(selectResp.get(j)))
						 respList.setSelectedIndex(i);
				 }
			 }
			 respAlunoScroll = new JScrollPane(respList);
			 respAlunoScroll.setPreferredSize(new Dimension(250, 80));
			 JLabel resp = new JLabel("<html>Respons�vel: <html>");
			 contentPane.add(resp); contentPane.add(respAlunoScroll);
			 layout.putConstraint(SpringLayout.WEST, resp, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, resp, 30, SpringLayout.NORTH, turma);
			 layout.putConstraint(SpringLayout.NORTH, respAlunoScroll, 30, SpringLayout.NORTH, turma);
			 layout.putConstraint(SpringLayout.WEST, respAlunoScroll, 20, SpringLayout.EAST, resp);
			 
			 contentPane.add(aplEdit); contentPane.add(back);
			 layout.putConstraint(SpringLayout.WEST, aplEdit, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, aplEdit, 90, SpringLayout.NORTH, resp);
			 layout.putConstraint(SpringLayout.NORTH, back, 90, SpringLayout.NORTH, resp);
			 layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.EAST, aplEdit);
			 break;
		 case RESPONSAVEL:
			 tela4.setSize(600,450);
			 
			 codigo = new JLabel("<html>CPF: <html>" + Integer.toString(novoResp.getCpf()));
			 contentPane.add(codigo);
			 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
			 
			 JLabel nome2 = new JLabel("<html>*Nome:<html>");
			 nomeText = new JTextField(novoResp.getNome(), 40);
			 contentPane.add(nome2); contentPane.add(nomeText);
			 layout.putConstraint(SpringLayout.WEST, nome2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, nome2, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.NORTH, nomeText, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.WEST, nomeText, 20, SpringLayout.EAST, nome2);
			 
			 JLabel genero2 = new JLabel("<html>*Genero:<html>");
			 genText = new JTextField(novoResp.getGenero(), 15);
			 contentPane.add(genero2); contentPane.add(genText);
			 layout.putConstraint(SpringLayout.WEST, genero2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, genero2, 25, SpringLayout.NORTH, nome2);
			 layout.putConstraint(SpringLayout.NORTH, genText, 25, SpringLayout.NORTH, nome2);
			 layout.putConstraint(SpringLayout.WEST, genText, 20, SpringLayout.EAST, genero2);
			 
			 JLabel endereco2 = new JLabel("<html>*Endereco:<html>");
			 endText = new JTextField(novoResp.getEndereco(), 40);
			 contentPane.add(endereco2); contentPane.add(endText);
			 layout.putConstraint(SpringLayout.WEST, endereco2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, endereco2, 25, SpringLayout.NORTH, genero2);
			 layout.putConstraint(SpringLayout.NORTH, endText, 25, SpringLayout.NORTH, genero2);
			 layout.putConstraint(SpringLayout.WEST, endText, 20, SpringLayout.EAST, endereco2);
			 
			 JLabel cep2 = new JLabel("<html>*CEP:<html>");
			 cepText = new JTextField(Integer.toString(novoResp.getCEP()), 15);
			 contentPane.add(cep2); contentPane.add(cepText);
			 layout.putConstraint(SpringLayout.WEST, cep2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, cep2, 25, SpringLayout.NORTH, endereco2);
			 layout.putConstraint(SpringLayout.NORTH, cepText, 25, SpringLayout.NORTH, endereco2);
			 layout.putConstraint(SpringLayout.WEST, cepText, 20, SpringLayout.EAST, cep2);
			 
			 JLabel telefone2 = new JLabel("<html>*Telefone:<html>");
			 telText = new JTextField(Integer.toString(novoResp.getTelefone()), 15);
			 contentPane.add(telefone2); contentPane.add(telText);
			 layout.putConstraint(SpringLayout.WEST, telefone2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, telefone2, 25, SpringLayout.NORTH, cep2);
			 layout.putConstraint(SpringLayout.NORTH, telText, 25, SpringLayout.NORTH, cep2);
			 layout.putConstraint(SpringLayout.WEST, telText, 20, SpringLayout.EAST, telefone2);
			 
			 JLabel email2 = new JLabel("<html>*E-mail:<html>");
			 emailText = new JTextField(novoResp.getEmail(), 40);
			 contentPane.add(email2); contentPane.add(emailText);
			 layout.putConstraint(SpringLayout.WEST, email2, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, email2, 25, SpringLayout.NORTH, telefone2);
			 layout.putConstraint(SpringLayout.NORTH, emailText, 25, SpringLayout.NORTH, telefone2);
			 layout.putConstraint(SpringLayout.WEST, emailText, 20, SpringLayout.EAST, email2);
			 
			 ArrayList<Emprego> auxEmp = empDAO.consultaEmprego();
			 ArrayList<String> arrEmp = new ArrayList<String>();
			 for(int k = 0; k < auxEmp.size(); k++)
					 arrEmp.add(Integer.toString(auxEmp.get(k).getID()));
			 String[] empItems = new String[arrEmp.size()];
			 empItems = arrEmp.toArray(empItems); 
			 empList = new JList<String>(empItems);
			 empList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			 ArrayList<String> selectEmp = respEmpDAO.consultaEmprego(Integer.parseInt(codText.getText()));
			 for(int i = 0; i < auxEmp.size(); i++){
				 for(int j = 0; j < selectEmp.size(); j++){
					 if(empList.getModel().getElementAt(i).equals(selectEmp.get(j)))
						 empList.setSelectedIndex(i);
				 }
			 }
			 respEmpScroll = new JScrollPane(empList);
			 respEmpScroll.setPreferredSize(new Dimension(250, 80));
			 JLabel emp = new JLabel("<html>Empregos: <html>");
			 contentPane.add(emp); contentPane.add(respEmpScroll);
			 layout.putConstraint(SpringLayout.WEST, emp, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, emp, 30, SpringLayout.NORTH, email2);
			 layout.putConstraint(SpringLayout.NORTH, respEmpScroll, 30, SpringLayout.NORTH, email2);
			 layout.putConstraint(SpringLayout.WEST, respEmpScroll, 20, SpringLayout.EAST, emp);
			 
			 contentPane.add(aplEdit); contentPane.add(back);
			 layout.putConstraint(SpringLayout.WEST, aplEdit, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, aplEdit, 90, SpringLayout.NORTH, emp);
			 layout.putConstraint(SpringLayout.NORTH, back, 90, SpringLayout.NORTH, emp);
			 layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.EAST, aplEdit);
			 break;
		 case EMPREGO:
			 tela4.setSize(600,250);
			 codigo = new JLabel("<html>ID: <html>" + Integer.toString(novoEmp.getID()));
			 contentPane.add(codigo);
			 layout.putConstraint(SpringLayout.WEST, codigo, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, codigo, 25, SpringLayout.NORTH, contentPane);
			 
			 JLabel categoria = new JLabel("<html>*Categoria:<html>");
			 catText = new JTextField(novoEmp.getCategoria(), 40);
			 contentPane.add(categoria); contentPane.add(catText);
			 layout.putConstraint(SpringLayout.WEST, categoria, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, categoria, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.NORTH, catText, 25, SpringLayout.NORTH, codigo);
			 layout.putConstraint(SpringLayout.WEST, catText, 20, SpringLayout.EAST, categoria);
			 
			 JLabel salario = new JLabel("<html>*Faixa Salarial:<html>");
			 salText = new JTextField(Float.toString(novoEmp.getFaixaSalarial()), 40);
			 contentPane.add(salario); contentPane.add(salText);
			 layout.putConstraint(SpringLayout.WEST, salario, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, salario, 25, SpringLayout.NORTH, categoria);
			 layout.putConstraint(SpringLayout.NORTH, salText, 25, SpringLayout.NORTH, categoria);
			 layout.putConstraint(SpringLayout.WEST, salText, 20, SpringLayout.EAST, salario);
			 
			 contentPane.add(aplEdit); contentPane.add(back);
			 layout.putConstraint(SpringLayout.WEST, aplEdit, 10, SpringLayout.WEST, contentPane);
			 layout.putConstraint(SpringLayout.NORTH, aplEdit, 25, SpringLayout.NORTH, salario);
			 layout.putConstraint(SpringLayout.NORTH, back, 25, SpringLayout.NORTH, salario);
			 layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.EAST, aplEdit);
			 break;
		 default:
			 break;
		 }
		 
		 info = new JLabel("<html>Preencha todos os campos corretamente.<br>"
				 + "Todos os campos com asterisco s�o obrigat�rios.<br>"
		 		 + "Obs.: n�o utilizar caracteres especiais em campos num�ricos.<html>");
		 contentPane.add(info);
		 layout.putConstraint(SpringLayout.WEST, info, 10, SpringLayout.WEST, contentPane);
		 layout.putConstraint(SpringLayout.NORTH, info, 35, SpringLayout.NORTH, aplEdit);

	     tela3.setVisible(false); tela4.setVisible(true);	
	 }
	 
	 private static void returnMenu(){
		 op = Opcao.DEFAULT;
		 campo = Campo.DEFAULT;
		 if(tela2 != null){
			 tela2.setVisible(false);
			 tela2.dispose();
		 }
		 if(tela3 != null){
			 tela3.setVisible(false);
			 tela3.dispose();
		 }
		 if(tela4 != null){
			 tela4.setVisible(false);
			 tela4.dispose();
		 }
		 tela1.setVisible(true);
	 }
	 
	 private static class ButtonListener implements ActionListener {
			public void actionPerformed (ActionEvent event) {
				if (event.getSource() == consultar) {
					telaCampo();
					op = Opcao.CONSULTAR;
				}
				else if (event.getSource() == inserir) { 
					telaCampo();
					op = Opcao.INSERIR;
				}
				else if (event.getSource() == editar) { 
					telaCampo();
					op = Opcao.EDITAR;
				}
				else if (event.getSource() == deletar) { 
					telaCampo();
					op = Opcao.DELETAR;
				}
				else if (event.getSource() == aluno) {
					campo = Campo.ALUNO;
					telaFinal();
				}
				else if (event.getSource() == responsavel) {
					campo = Campo.RESPONSAVEL;
					telaFinal();
				}
				else if (event.getSource() == emprego) {
					campo = Campo.EMPREGO;
					telaFinal();
				}
				else if (event.getSource() == back) {
					returnMenu();
				}
			}
	 }
	 
	 public static class applyListener implements ActionListener {
		 private int executado = 2;
	 		public void actionPerformed (ActionEvent event) {
	 			switch(campo){
				case ALUNO:
					if(codText.getText().isEmpty() || nomeText.getText().isEmpty() || nascText.getText().isEmpty()
							|| endText.getText().isEmpty() || cepText.getText().isEmpty()){
						executado = -1;
					} else {
						DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate dateNasc = LocalDate.parse(nascText.getText(), format);
						
						int tel;
						if(!telText.getText().isEmpty())
							tel = Integer.parseInt(telText.getText());
						else tel = -1;
						
						int escId = -1;
						if (escolaCB.getSelectedItem() != null) {
				            escId = Integer.parseInt(escolaCB.getSelectedItem().toString());
				        }
						
						int tipoId = -1;
						if (tipoCB.getSelectedItem() != null) {
				            tipoId = Integer.parseInt(tipoCB.getSelectedItem().toString());
				        }
						
						String turmaId = null;
						if (turmaCB.getSelectedItem() != null) {
				            turmaId = turmaCB.getSelectedItem().toString();
				        }
								
						novoAluno = new Aluno(Integer.parseInt(codText.getText()),
								   			  nomeText.getText(),
								   			  dateNasc,
								   			  genText.getText(),
								   			  endText.getText(),
								   			  Integer.parseInt(cepText.getText()),
								   			  tel,
								   			  emailText.getText(),
								   			  escId,
								   			  tipoId,
								   			  turmaId);
						executado = alunoDAO.insereAluno(novoAluno);
						
						List<String> respSelec = respList.getSelectedValuesList();
						for(String re : respSelec){
							ResponsavelAluno novo = new ResponsavelAluno(Integer.parseInt(re), 
									Integer.parseInt(codText.getText()));
							respAlunoDAO.insereRespAluno(novo);
						}
						
					}
					
					break;
				case RESPONSAVEL:
					if(codText.getText().isEmpty() || nomeText.getText().isEmpty() || genText.getText().isEmpty()
							|| endText.getText().isEmpty() || cepText.getText().isEmpty()
							|| telText.getText().isEmpty() || emailText.getText().isEmpty()){
						executado = -1;
					} else {						
						novoResp = new Responsavel(Integer.parseInt(codText.getText()),
												   nomeText.getText(),
												   genText.getText(),
												   endText.getText(),
												   Integer.parseInt(cepText.getText()),
												   Integer.parseInt(telText.getText()),
												   emailText.getText());
						executado = respDAO.insereResponsavel(novoResp);
					}
					
					List<String> empSelec = empList.getSelectedValuesList();
					for(String eS : empSelec){
						ResponsavelEmprego novo = new ResponsavelEmprego(Integer.parseInt(eS), 
								Integer.parseInt(codText.getText()));
						respEmpDAO.insereRespEmp(novo);
					}
					
					break;
				case EMPREGO:
					if(codText.getText().isEmpty() || catText.getText().isEmpty() || salText.getText().isEmpty()){
						executado = -1;
					} else {
						novoEmp = new Emprego(Integer.parseInt(codText.getText()),
											  catText.getText(),
											  Float.parseFloat(salText.getText()));
						executado = empDAO.insereEmprego(novoEmp);
					}
					
					break;
				default:
					break;
	 			}
	 			
	 			if(executado == -1){
	 				info.setText("<html>Preencha todos os campos!<html>");
	 			} else if(executado == 1){
	 				info.setText("<html>ID j� cadastrado.<html>");
	 			} else if(executado == 0){
	 				info.setText("<html>Campo inserido com sucesso!<html>");
	 			} else if(executado == 2){
	 				info.setText("<html>Falha na insercao.<html>");
	 			}
	 		}
	 	}
	 
	 public static class deletListener implements ActionListener {
		 private int deletado;
		 public void actionPerformed (ActionEvent event) {
			 if(!(codText.getText().trim().equals("") || codText.getText() == null)){
	 			switch(campo){
				case ALUNO:
					deletado = alunoDAO.deletaAluno(Integer.parseInt(codText.getText()));
					break;
				case RESPONSAVEL:
					deletado = respDAO.deletaResponsavel(Integer.parseInt(codText.getText()));
					break;
				case EMPREGO:
					deletado = empDAO.deletaEmprego(Integer.parseInt(codText.getText()));
					break;
				default:
					break;
	 			}
	 			
	 			 if (deletado == 0){
	 				 info.setText("<html>Campo deletado com sucesso.<html>");
	 			 }
	 			 else if(deletado == 1){
	 				info.setText("<html>Campo especificado nao existe.<html>");
	 			 }
	 			 else{
	 				 info.setText("<html>Nao foi poss�vel deletar o campo especificado.<html>");
	 			 }
			 }
			 else{
				 info.setText("<html>Nenhum campo foi especificado.<html>");
			 }
		 }
	 }
	 
	 public static class pesqListener implements ActionListener {
		 private boolean encontrado = false;
		 public void actionPerformed (ActionEvent event) {
			if(!(codText.getText().trim().equals("") || codText.getText() == null)){
				switch(campo){
				case ALUNO:
					novoAluno = alunoDAO.consultaAluno(Integer.parseInt(codText.getText()));
					if(novoAluno != null){
						encontrado = true;
					}
					break;
				case RESPONSAVEL:
					novoResp = respDAO.consultaResponsavel(Integer.parseInt(codText.getText()));
					if(novoResp != null){
						encontrado = true;
					}
					break;
				case EMPREGO:
					novoEmp = empDAO.consultaEmprego(Integer.parseInt(codText.getText()));
					if(novoEmp != null){
						encontrado = true;
					}
					break;
				default:
					break;
				}
				
				if(encontrado){
					telaConsulta();
				}
				else{
					info.setText("<html>O campo especificado nao foi encontrado.<html>");
				}
			}
			else{
				info.setText("<html>Nenhum campo foi especificado.<html>");
			}
 		 }
	 }
	 
	 public static class editListener implements ActionListener {
		 private boolean encontrado = false;
		 public void actionPerformed (ActionEvent event) {
			if(!(codText.getText().trim().equals("") || codText.getText() == null)){
				switch(campo){
				case ALUNO:
					novoAluno = alunoDAO.consultaAluno(Integer.parseInt(codText.getText()));
					if(novoAluno != null){
						encontrado = true;
					}
					break;
				case RESPONSAVEL:
					novoResp = respDAO.consultaResponsavel(Integer.parseInt(codText.getText()));
					if(novoResp != null){
						encontrado = true;
					}
					break;
				case EMPREGO:
					novoEmp = empDAO.consultaEmprego(Integer.parseInt(codText.getText()));
					if(novoEmp != null){
						encontrado = true;
					}
					break;
				default:
					break;
				}
				
				if(encontrado){
					telaEdicao();
				}
				else{
					info.setText("<html>O campo especificado nao foi encontrado.<html>");
				}
			}
			else{
				info.setText("<html>Nenhum campo foi especificado.<html>");
			}
 		 }
	 }
	 
	 public static class aplEditListener implements ActionListener {
		 private boolean executado = false;
		 private int execInt = 0;
	 		public void actionPerformed (ActionEvent event) {
	 			switch(campo){
				case ALUNO:
					if(nomeText.getText().isEmpty() || nascText.getText().isEmpty()
							|| endText.getText().isEmpty() || cepText.getText().isEmpty()){
						execInt = -1;
					} else {
						DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate dateNasc = LocalDate.parse(nascText.getText(), format);
						
						int escId = -1;
						if (escolaCB.getSelectedItem() != null) {
				            escId = Integer.parseInt(escolaCB.getSelectedItem().toString());
				        }
						
						int tipoId = -1;
						if (tipoCB.getSelectedItem() != null) {
				            tipoId = Integer.parseInt(tipoCB.getSelectedItem().toString());
				        }
						
						String turmaId = null;
						if (turmaCB.getSelectedItem() != null) {
				            turmaId = turmaCB.getSelectedItem().toString();
				        }
						
						executado = alunoDAO.editaAluno(Integer.parseInt(codText.getText()),
								   			  			nomeText.getText(),
								   			  			dateNasc,
								   			  			genText.getText(),
								   			  			endText.getText(),
								   			  			Integer.parseInt(cepText.getText()),
								   			  			Integer.parseInt(telText.getText()),
								   			  			emailText.getText(),
								   			  			escId,
								   			  			tipoId,
								   			  			turmaId);
					}
					
					List<String> respSelec = respList.getSelectedValuesList();
					for(String re : respSelec){
						ResponsavelAluno novo = new ResponsavelAluno(Integer.parseInt(re), 
								Integer.parseInt(codText.getText()));
						respAlunoDAO.insereRespAluno(novo);
					}
					
					ArrayList<String> respTotal = respAlunoDAO.consultaResp(); 
					for(String rT : respTotal){
						boolean encontrado = false;
						for(String rS : respSelec){
							if(rT.equals(rS))
								encontrado = true;
						}
						if(!encontrado){
							respAlunoDAO.deletaRespAluno(Integer.parseInt(codText.getText()),
									Integer.parseInt(rT));
						}
					}
					
					break;
				case RESPONSAVEL:
					if(nomeText.getText().isEmpty() || genText.getText().isEmpty()
							|| endText.getText().isEmpty() || cepText.getText().isEmpty()
							|| telText.getText().isEmpty() || emailText.getText().isEmpty()){
						execInt = -1;
					} else {
						executado = respDAO.editaResponsavel(Integer.parseInt(codText.getText()),
											   				 nomeText.getText(),
											   				 genText.getText(),
											   				 endText.getText(),
											   				 Integer.parseInt(cepText.getText()),
											   				 Integer.parseInt(telText.getText()),
											   				 emailText.getText());
					}
					
					List<String> empSelec = empList.getSelectedValuesList();
					for(String eS : empSelec){
						ResponsavelEmprego novo = new ResponsavelEmprego(Integer.parseInt(eS), 
								Integer.parseInt(codText.getText()));
						respEmpDAO.insereRespEmp(novo);
					}
					
					ArrayList<String> empTotal = respEmpDAO.consultaEmprego(); 
					for(String eT : empTotal){
						boolean encontrado = false;
						for(String eS : empSelec){
							if(eT.equals(eS))
								encontrado = true;
						}
						if(!encontrado){
							respEmpDAO.deletaRespEmp(Integer.parseInt(eT), 
									Integer.parseInt(codText.getText()));
						}
					}
					
					break;
				case EMPREGO:
					if(catText.getText().isEmpty() || salText.getText().isEmpty()){
						execInt = -1;
					} else {
						executado = empDAO.editaEmprego(Integer.parseInt(codText.getText()),
											  			catText.getText(),
											  			Float.parseFloat(salText.getText()));
					}
					break;
				default:
					break;
	 			}
	 			
	 			if(execInt == -1){
	 				info.setText("<html>Preencha todos os campos!<html>");
	 			} else {
		 			if(executado){
		 				info.setText("<html>Campo editado com sucesso!<html>");
		 			}
		 			else{
		 				info.setText("<html>Falha na edicao.<html>");
		 			}
	 			}
	 		}
 	}
}
