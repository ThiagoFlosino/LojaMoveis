package objetos;
import java.util.List;

/**
 * 
 */

/**Classe base para as criações dos ambientes
 * @author Thiago
 *
 */
public abstract class Comodo {
	public String descricao;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public abstract List<Mobilia> listaMobiliaDisponivel();

}
