package dwtest.controller;

import java.io.IOException;

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
		
		if(address.equals("redirecMain"))
			response.sendRedirect("main");
		else
			request.getRequestDispatcher(address).forward(request, response);
	}
	
	/* acciones principales del servlet */
	@ButtonMethod(isDefault=true)
	public String doLogin(){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username!=null && password!=null){
			Object user = HibernateHelper.getFirstMatch(usuarioData.getClass(), "login", username,"password", password);
			if(user != null){
				usuarioData = (Usuario) user;
				return "redirecMain";
			}else{
				return "";
			}
		}
		return "";
	}
	
	/**
	 * Iniciar session factory, las configuraciones de conexion a la base de datos se cargan desde el archivo 
	 */
	static public void initHibernate() {
        HibernateHelper.initSessionFactory(Usuario.class);
    }

}
