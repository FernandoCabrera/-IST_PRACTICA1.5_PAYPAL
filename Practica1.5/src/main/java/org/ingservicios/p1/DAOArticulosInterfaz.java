package org.ingservicios.p1;

import java.util.List;

public interface DAOArticulosInterfaz {
	public List<DTOArticulo> leeArticulo();
	public DTOArticulo buscaArticulo(int cod);

}
