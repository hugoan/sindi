package br.com.sindicato.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.sindicato.dao.AssociadoDAO;
import br.com.sindicato.domain.Associado;
import br.com.sindicato.util.JSFUtil;

@ManagedBean(name = "MBAssociado")
@ViewScoped
public class AssocaidoBean {

	private ListDataModel<Associado> itens;
	private Associado associado;

	public ListDataModel<Associado> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Associado> itens) {
		this.itens = itens;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			AssociadoDAO adao = new AssociadoDAO();
			ArrayList<Associado> lista = adao.listar();
			itens = new ListDataModel<Associado>(lista);
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.addMsgErro(ex.getMessage());

		}
	}

	public void prepararNovo() {
		associado = new Associado();
	}

	public void novo() {
		try {
			AssociadoDAO adao = new AssociadoDAO();
			adao.salvar(associado);
			
			ArrayList<Associado> lista = adao.listar();
			itens = new ListDataModel<Associado>(lista);
			JSFUtil.addMsgSucesso("Assocaido adicionado com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.addMsgErro(ex.getMessage());

		}
	}
	
	public void prepararExcluir(){
		associado = itens.getRowData();
	}
	
	public void excluir(){
		try{
			AssociadoDAO adao = new AssociadoDAO();
			adao.excluir(associado);
			
			ArrayList<Associado> lista = adao.listar();
			itens = new ListDataModel<Associado>(lista);
			JSFUtil.addMsgSucesso("Assocaido excluido com sucesso!");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.addMsgErro(ex.getMessage());
			
		}
	}
	
	public void prepararEditar(){
		associado = itens.getRowData();
	}
	
	public void editar(){
		try{
			AssociadoDAO adao = new AssociadoDAO();
			adao.editar(associado);
			
			ArrayList<Associado> lista = adao.listar();
			itens = new ListDataModel<Associado>(lista);
			JSFUtil.addMsgSucesso("Associado editado com sucesso!");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.addMsgErro(ex.getMessage());
			
		}
	}
}

