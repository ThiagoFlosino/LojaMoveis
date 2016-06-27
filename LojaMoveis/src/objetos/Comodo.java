package objetos;
import java.util.List;

/**
 * 
 */

/**Classe base para as cria��es dos ambientes
 * @author Thiago
 *
 */
public abstract class Comodo {
	public String descricao;
	public String tipo;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public abstract List<Mobilia> listaMobiliaDisponivel();

}
