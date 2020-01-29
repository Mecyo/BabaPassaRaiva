/**
 * 
 */
package interfaces;

import java.util.List;

/**
 * @author Emerson Santos
 *
 */
public interface ImplementDAO<E> {

	public E buscarPorId(int id);

	public List<E> listar();

	public boolean existe(Object objeto);
	
	public boolean salvar(E objeto);

	public boolean deletar(E objeto);

}
