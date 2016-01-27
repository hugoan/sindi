package br.com.sindicato.domain;

public class Associado {
	private Long codigo;
	private String cnpj;
	private String razaoSocial;
	private String ativo;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getAtivo(){
		return ativo;
	}
	
	public void setAtivo(String ativo){
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {
		String saida = codigo + " | " + cnpj + " | " + razaoSocial + " | " + ativo;
		return saida;
	}
	
}
