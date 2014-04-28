package dwtest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dwtest.model.Usuario;
import dwtest.shared.ButtonMethod;
import dwtest.shared.HelperBase;
import dwtest.shared.HibernateHelper;

public class ControllerHelperUsuario extends HelperBase{
	protected Usuario usuarioData = new Usuario();
	
	public ControllerHelperUsuario(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public Object getData(){
		return usuarioData;
	}
	
	public void setPersonaData(Usuario usuario){
		this.usuarioData = usuario;
	}

	@Override
	protected void copyFromSession(Object helper) {
		if (helper.getClass() == this.getClass()) {
            usuarioData = ((ControllerHelperUsuario) helper).usuarioData;
        }
	}
	
	/**
	 * Get default method
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet()throws ServletException, IOException{
		addHelperToSession("helper", SessionData.IGNORE);
		String address = executeButtonMethod();
		
		request.getRequestDispatcher(address).forward(request, response);
	}
	
	/**
	 * Post default method
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost()throws ServletException, IOException{
		addHelperToSession("helper", SessionData.READ);
		
		String address = executeButtonMethod();
		
		if(address.equals("successLogin"))
			response.sendRedirect("main");
		else
			request.getRequestDispatcher(address).forward(request, response);
	}
	
	/* acciones principales del servlet */
	@ButtonMethod(buttonName="login")
	public String doLogin(){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		
		if(username!=null && password!=null){
			Object user = HibernateHelper.getFirstMatch(usuarioData.getClass(), "login", username,"password", password);
			System.out.println(user);
			if(user != null){
				usuarioData = (Usuario) user;
				return "successLogin";
			}else{
				return "/";
			}
		}
		return "";
	}
	
	@ButtonMethod(buttonName="listAllUsers", isDefault=true)
	public String listAllUsers(){
		List usuarios = HibernateHelper.getListData(Usuario.class);
		request.setAttribute("usuarios", usuarios);
		return jspLocation("gestion_usuarios.jsp");
	}
	
	@ButtonMethod(buttonName="btnDelete")
	public String deleteButton(){
		int idDelete = Integer.parseInt(request.getParameter("id"));
		Usuario usuarioBorrar = (Usuario) HibernateHelper.getKeyData(Usuario.class, idDelete);
		if(usuarioBorrar!=null){
			HibernateHelper.removeDB(usuarioBorrar);
		}else{
			logger.info("Usuario no encontrado");
		}
		
		List usuarios = HibernateHelper.getListData(usuarioData.getClass());
		request.setAttribute("usuarios", usuarios);
		
		return jspLocation("gestion_usuarios.jsp");
	}
	
	@ButtonMethod (buttonName="btnInsert")
	public String btnConfirm(){
		fillBeanFromRequest(usuarioData);
		
		String address = "";
		if(isValid(usuarioData)){
			HibernateHelper.saveDB(usuarioData);
			address= jspLocation("gestion_usuarios.jsp");
			response.setStatus(200);
		}else{
			response.setStatus(500);
			address= jspLocation("gestion_usuarios.jsp");
		}
		return address;
	}
	
	/* espesicicar la ubicacion de los jsp */
	protected String jspLocation(String page){
		return "resources/pages/main/"+page;
	}
	
	/**
	 * Iniciar session factory, las configuraciones de conexion a la base de datos se cargan desde el archivo 
	 */
	static public void initHibernate() {
        HibernateHelper.initSessionFactory(Usuario.class);
    }

}
