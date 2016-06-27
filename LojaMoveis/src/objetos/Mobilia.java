package objetos;
/**
 * 
 */

/**Classe que gera as mobilias
 * @author Thiago
 *
 */
public class Mobilia {
	public Long id;
	public String descricao;
	public float custo;
	public int tempoEntrega;
	public String tipoComodo;
	public String tipoMobilia;
	
	
	public String getTipoMobilia() {
		return tipoMobilia;
	}
	public void setTipoMobilia(String tipoMobilia) {
		this.tipoMobilia = tipoMobilia;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getCusto() {
		return custo;
	}
	public void setCusto(float custo) {
		this.custo = custo;
	}
	public int getTempoEntrega() {
		return tempoEntrega;
	}
	public void setTempoEntrega(int tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}
	public String getTipoComodo() {
		return tipoComodo;
	}
	public void setTipoComodo(String tipoComodo) {
		this.tipoComodo = tipoComodo;
	}

}
