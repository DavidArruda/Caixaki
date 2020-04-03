package br.com.project.util.all;

public enum StatusPersistencia {

	ERRO("Erro"), SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Erro objeto n�o pode ser apagado por possuir refer�ncias ao mesmo.");

	private String name;

	private StatusPersistencia(String s) {
		this.name = s;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
