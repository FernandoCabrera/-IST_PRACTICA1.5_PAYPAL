package org.ingservicios.p1;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method ={RequestMethod.POST,RequestMethod.GET})
	public String home(HttpServletRequest request, Model model, HttpServletResponse response) {
	
		Cookie[ ] cookies = request.getCookies( );
		
		String cookieName ="Nombre";
		String cookieValue = "";
		String cookie2pass ="Contraseña";
		String cookie2Value = "";
		if(cookies==null) {
			return "home";
		}else {
		for (Cookie cookie: cookies){
			if (cookieName.equals(cookie.getName())) {
			cookieValue = cookie.getValue();
			if(cookie2pass.equals(cookie.getName())) {
			cookie2Value=cookie.getValue();
			}
			}
		}
		//Comproamos si el cookievalue es el del administrador o no
		
		if(cookieValue.equals("Admin")&& cookie2Value.equals("12345")) {
			
			List <DTOUsuarios> lista = dao.leeUsuarios();
			model.addAttribute("lista", lista);
			
			
			return "usuariodatos";
		}else {
			//Si el valor de las cookies no es el anterior significa que el usuario se ha autenticado para acceder a los
			//articulos por ello mostramos articulo.jsp, no hace falta autenticar porque ya lo ha echo antes
			
			return"articulo";


			}
		
			}
		
		
	}
	
	@RequestMapping(value = "/Servlet1", method = {RequestMethod.GET,RequestMethod.POST})
	public String servlet1 (HttpServletRequest request, Model model, HttpServletResponse response) {
		//Para cookie necesitamos usar response para enviarla en ella
		
		String usuario = request.getParameter("username");
		String pass = request.getParameter("pass");
		//url a asignar dependiendo de si es administrador o no.
		String url="";
		
		List <DTOUsuarios> lista = dao.leeUsuarios();
		
		//Creamos  cookie
		String answer = request.getParameter("Nombre");
		String answer1 = request.getParameter("Contraseña");
		
		
		if(dao.buscaAdmin(usuario, pass)!=null) {
			
							url="usuario";

				
		}else if(dao.buscaUsuario(usuario, pass)!=null){
				DTOUsuarios dto = new DTOUsuarios();
				dto=dao.buscaUsuario(usuario, pass);
				model.addAttribute("dto", dto);
				
				Cookie c = new Cookie("Nombre", answer);
				Cookie c1 = new Cookie("Contraseña", answer1);
				c.setPath("/");
				response.addCookie(c);
				
				c1.setPath("/");
				response.addCookie(c1);
				
				c.setMaxAge(50);//Expire cookie
				c1.setMaxAge(50);//Expire cookie
				
				url="usuariodatos";
				
			}
			
		
		
		//Significa que el usuario no existe
		if(!url.equals("usuario") && !url.equals("usuariodatos")) {
			url="registro";
		}
		
		model.addAttribute("lista", lista);

		return url;
	}
	
	
	@RequestMapping(value = "/Servlet2", method = {RequestMethod.GET,RequestMethod.POST})
	public String servlet2 (HttpServletRequest request, Model model) {

		//Parameter(...) es del html 
				String usuario = request.getParameter("username");
				//Lo añadimos al model
				model.addAttribute("Nombre", usuario);
				
				String password = request.getParameter("pass");
				//Lo añadimos al model
				model.addAttribute("Password", password);
				
				String email = request.getParameter("email");
				//Lo añadimos al model
				model.addAttribute("Email", email);	
				
				String dni = request.getParameter("dni");
				//Lo añadimos al model
				model.addAttribute("DNI", dni);
				DTOUsuarios usuarioDTO = new DTOUsuarios(usuario,password,email,dni);
			
			
				
				boolean variable=false;
				String url="";
				
				
				if(dao.buscaUsuario(usuario, email, dni)==true) {//Busca usuario a través de correo,user,dni
						
					    url="usuarioYaRegistrado";
						variable=true;
				}
					
				if(variable==false) { 
					boolean variable2=false;
					
						if(dao.buscaUsuario(dni)!=null) {
						
						url="usuarioYaRegistrado";
					variable2=true;
						}
					
					if(variable2==false) {
						dao.addUsuario(usuarioDTO);
						url="usuarioRegistrado";
					}
				}
				return url;
	}
	
	
}
