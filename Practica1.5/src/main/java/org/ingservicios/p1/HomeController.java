package org.ingservicios.p1;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	//Al marcarlo con @Autowired, se inyectará, como una instancia de dao, 
	//un bean de una clase que implemente el interfaz DAOUsuariosInterfaz
	@Autowired
	private DAOUsuariosInterfaz dao;
	@Autowired
	private DAOArticulosInterfaz daoA;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method ={RequestMethod.POST,RequestMethod.GET})
	public String home(HttpServletRequest request, Model model, HttpServletResponse response) {
		
		
		//Variables 
		String cookieName ="user";
		String cookieValue = "";//var a obtener
		String cookie2pass ="pass";
		String cookie2Value = "";//var a obener
		Cookie[ ] cookies = request.getCookies( ); //Obtenemos cookies de la peticion
		
		
		//Asignar valor a las cookies
		if(cookies==null) {
			return "home";
		}else {
		for (Cookie cookie: cookies){
			if (cookieName.equals(cookie.getName())) {
			cookieValue = cookie.getValue();
			//usuario
			if(cookie2pass.equals(cookie.getName())) {
			//contaseña	
			cookie2Value=cookie.getValue();
			}
			}
		}
		//Comprobamos si el cookievalue es el del administrador o no
		
		if(cookieValue.equals("Admin")&& cookie2Value.equals("12345")) {
			
			List <DTOUsuarios> lista = dao.leeUsuarios();
			model.addAttribute("lista", lista);
			return "usuario";
			
		}else {
			//Si el valor de las cookies no es el anterior significa que el usuario se ha autenticado para acceder a los
			//articulos por ello mostramos articulo.jsp, no hace falta autenticar porque ya lo ha echo antes
			
			List <DTOArticulo> list = daoA.leeArticulo();
			model.addAttribute("list", list);
			return"articulo";


			}
		
			}
		
		
	}
	//Servlet gestiona datos usuario
	@RequestMapping(value = "/Servlet1", method = {RequestMethod.GET,RequestMethod.POST})
	public String servlet1 (HttpServletRequest request, Model model, HttpServletResponse response) {
		//Para cookie necesitamos usar response para enviarla en ella
		
		String usuario = request.getParameter("username");
		String pass = request.getParameter("pass");
	
		//url a asignar dependiendo de si es administrador o no.
		String url="";
		
		List <DTOUsuarios> lista = dao.leeUsuarios();
		
		
		Cookie c1=null;
		Cookie c2=null;
		if(dao.buscaAdmin(usuario, pass)!=null) {
			c1 = new Cookie("user", usuario);
			c2 = new Cookie ("pass", pass);
			c1.setPath("/");
			response.addCookie(c1);
			c2.setPath("/");
			response.addCookie(c2);
			
        			model.addAttribute("lista",lista);
        			url="usuario";
        	
			
			//Hay que ponerlo asi porque el servlet.context esta puesto prefix /WEB-INF/views/
        	//Sufifix .jsp
			
			
				
		}else if(dao.buscaUsuario(usuario, pass)!=null){
				
				
			 c1 = new Cookie("user", usuario);
			 c2 = new Cookie("pass", pass);
				c1.setPath("/");
				response.addCookie(c1);
				
				c2.setPath("/");
				response.addCookie(c2);
				
			
				

				List <DTOArticulo> list = daoA.leeArticulo();
				
	        			model.addAttribute("list",list);
	        			url="articulo";
					
				
				
			}
			
		
		
		//Significa que el usuario no existe
		if(!url.equals("usuario") && !url.equals("articulo")) {
			url="registro";
		}
		
		

		return url;
	}
	
	
	
}