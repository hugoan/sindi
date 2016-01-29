package br.com.sindicato.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.sindicato.domain.Associado;
import br.com.sindicato.factory.ConexaoFactory;

public class AssociadoDAO {
	
	public void salvar(Associado a)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO associado ");
		sql.append("(cnpjTblAssociado, razaoTblAssociado, ativoTblAssociado)  ");
		sql.append("VALUES (?, ?, ?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, a.getCnpj());
		comando.setString(2, a.getRazaoSocial());
		comando.setString(3, a.getAtivo());
		
		comando.executeUpdate();
		
	}
	
	public void excluir(Associado a) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM associado ");
		sql.append("WHERE codigoTblAssociado = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, a.getCodigo());
		
		comando.executeUpdate();
	}
	
	public void editar(Associado a)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE associado ");
		sql.append("SET cnpjTblAssociado = ?, razaoTblAssociado = ?, ativoTblAssociado = ? ");
		sql.append("WHERE codigoTblAssociado = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, a.getCnpj());
		comando.setString(2, a.getRazaoSocial());
		comando.setString(3, a.getAtivo());
		comando.setLong(4, a.getCodigo());
		
		comando.executeUpdate();
	}
	
	public Associado buscarPorCnpj(Associado a) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigoTblAssociado, cnpjTblAssociado, razaoTblAssociado, ativoTblAssociado ");
		sql.append("FROM associado ");
		sql.append("WHERE cnpjTblAssociado = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, a.getCnpj());
		
		ResultSet resultado = comando.executeQuery();
		
		Associado retorno = null;
		
		if(resultado.next()){
			retorno = new Associado();
			retorno.setCodigo(resultado.getLong("codigoTblAssociado"));
			retorno.setCnpj(resultado.getString("cnpjTblAssociado"));
			retorno.setRazaoSocial(resultado.getString("razaoTblAssociado"));
			retorno.setAtivo(resultado.getString("ativoTblAssociado"));
			
		}
		
		return retorno;
	}
	
	public ArrayList<Associado> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigoTblAssociado, cnpjTblAssociado, razaoTblAssociado, ativoTblAssociado ");
		sql.append("FROM associado ");
		sql.append("ORDER BY razaoTblAssociado ASC");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Associado> lista = new ArrayList<Associado>();
		
		while(resultado.next()){
			Associado a = new Associado();
			a.setCodigo(resultado.getLong("codigoTblAssociado"));
			a.setCnpj(resultado.getString("cnpjTblAssociado"));
			a.setRazaoSocial(resultado.getString("razaoTblAssociado"));
			a.setAtivo(resultado.getString("ativoTblAssociado"));
			
			lista.add(a);
		}
		
		return lista;
	}
	
	public ArrayList<Associado> buscarPorRazao(Associado a) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigoTblAssociado, cnpjTblAssociado, razaoTblAssociado, ativoTblAssociado ");
		sql.append("FROM associado ");
		sql.append("WHERE razaoTblAssociado LIKE ? ");
		sql.append("ORDER BY razaoTblAssociado ASC");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + a.getRazaoSocial() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Associado> lista = new ArrayList<Associado>();
		
		while (resultado.next()){
			Associado item = new Associado();
			item.setCodigo(resultado.getLong("codigoTblAssociado"));
			item.setCnpj(resultado.getString("cnpjTblAssociado"));
			item.setRazaoSocial(resultado.getString("razaoTblAssociado"));
			item.setAtivo(resultado.getString("ativoTblAssociado"));
			
			lista.add(item);
		}
		
		return lista;
	}
	
	//Teste buscar por razão
	public static void main(String[] args){
		Associado a1 = new Associado();
		a1.setRazaoSocial("Ins");
		AssociadoDAO adao = new AssociadoDAO();
		
		try{
			ArrayList<Associado> lista = adao.buscarPorRazao(a1);
			for(Associado item : lista){
				System.out.println(item);
			}
		}catch(SQLException ex){
			System.out.println("Ocorreu um erro durante a pesquisa");
			ex.printStackTrace();
			
		}
	}
	
	/* Testar listar
	public static void main(String[] args){
		AssociadoDAO adao = new AssociadoDAO();
		try{
			ArrayList<Associado> lista = adao.listar();
			for(Associado a : lista){
				System.out.println(a);
			}
		}catch(SQLException ex){
			System.out.println("Ocorreu um erro durante a listagem");
			ex.printStackTrace();
			
		}
			
	}*/
	
	
	/*Testar Busca por CNPJ
	public static void main(String[] args){
		Associado a1 = new Associado();
		a1.setCnpj("04.835.601/0001-70");
		
		AssociadoDAO adao = new AssociadoDAO();
		try{
		Associado resposta = adao.buscarPorCnpj(a1);
		System.out.println("A resposta é: " + resposta);
		
		}catch(SQLException ex){
			System.out.println("Erro na consulta!");
			ex.printStackTrace();
			
		}*/
		
	
	/*Teste Editar
	public static void main(String[] args){
		Associado a1 = new Associado();
		a1.setCnpj("04.835.601/0001-70");
		a1.setRazaoSocial("Instituto Sindiatacadista/DF");
		a1.setAtivo("Não");
		a1.setCodigo(1L);
		
		AssociadoDAO adao = new AssociadoDAO();
		try{
			adao.editar(a1);
			System.out.println("Associado alterado com sucesso!");
		}catch(SQLException ex){
			System.out.println("Não foi possivel alterar asssociado!");
			ex.printStackTrace();
		}
		
	}*/
	
	/*Testar excluir
	public static void main(String[] args){
		Associado a1 = new Associado();
		a1.setCodigo(2L);
		
		AssociadoDAO adao = new AssociadoDAO();
		try{
			adao.excluir(a1);
			System.out.println("Associado excluido com sucesso!");
					
		}catch(SQLException ex){
			System.out.println("Não foi possível excluir!");
			ex.printStackTrace();
			
		}
		
	}*/
	
	/*Teste Salvar
	
	public static void main(String[] args){
		Associado a1 = new Associado();
			a1.setCnpj("04.835.601/0001-75");
			a1.setRazaoSocial("Sindicato do Comércio Atacadista do DF");
			a1.setAtivo("Sim");
		
		Associado a2 = new Associado();
			a2.setCnpj("01.123.456/0001-12");
			a2.setRazaoSocial("Empresa Teste 2");
			a2.setAtivo("Não");
			
		AssociadoDAO adao = new AssociadoDAO();
		
		try{
			adao.salvar(a1);
			adao.salvar(a2);
			System.out.println("Associado salvo com sucesso!");
									
		}catch(SQLException ex){
			System.out.println("Erro! Não foi possivel salvar!");
			ex.printStackTrace();
			
		}*/
	} 
	
