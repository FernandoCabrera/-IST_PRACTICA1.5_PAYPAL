package org.ingservicios.p1;

	import java.sql.ResultSet;
	import java.sql.SQLException;

	import org.springframework.jdbc.core.RowMapper;

	public class ArticuloMapper implements RowMapper<DTOArticulo> {
		
		public DTOArticulo mapRow(ResultSet rs,int rowNum) throws SQLException{
			
			DTOArticulo articulo=new DTOArticulo();
			articulo.setCodigo(rs.getInt("Codigo"));
			articulo.setNombre(rs.getString("Nombre"));
			articulo.setPrecio(rs.getFloat("Precio"));
			
			return articulo;
		}
}
