package org.ingservicios.p1;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class DAOArticulosJDBC implements DAOArticulosInterfaz {
	public JdbcTemplate jdbcTemplate;
	//private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource2) {
	this.jdbcTemplate = new JdbcTemplate(dataSource2);
	}
	
	//Leer Articulos
	public List<DTOArticulo> leeArticulo(){
		String sql = "select * from articulos";
		ArticuloMapper mapper = new ArticuloMapper();
		List<DTOArticulo> arti = this.jdbcTemplate.query(sql,mapper);
		return arti;
	}
	
	//Buscar Articulo
	public DTOArticulo buscaArticulo(int cod){ //Devuelve el articulo buscado en funcion del codigo o null si no existe
		String sql = "select * from articulos where Codigo = ?";
		Object[ ] parametros = {cod}; //Array de objetos
		ArticuloMapper mapper = new ArticuloMapper();
		List<DTOArticulo> art = this.jdbcTemplate.query(sql, parametros, mapper);
		if (art.isEmpty()) return null;
		else return art.get(0);
		}
}
