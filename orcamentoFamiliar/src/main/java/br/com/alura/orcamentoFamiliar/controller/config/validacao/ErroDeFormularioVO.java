package br.com.alura.orcamentoFamiliar.controller.config.validacao;

public class ErroDeFormularioVO {
	
	private String campo;
	private String erro;
	
	public ErroDeFormularioVO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	

}
