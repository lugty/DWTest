package dwtest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dwtest.model.Domicilio;
import dwtest.model.Estado;
import dwtest.model.EstadoCivil;
import dwtest.model.Municipio;
import dwtest.model.Persona;
import dwtest.shared.ButtonMethod;
import dwtest.shared.HelperBase;
import dwtest.shared.HibernateHelper;

public class ControllerHelperMain extends HelperBase{
	//Se declara el data principal del controllerHelper
	protected Persona personaData = new Persona();
	
	public ControllerHelperMain(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public Object getData(){
		return personaData;
	}

	@Override
	protected void copyFromSession(Object helper) {
		if (helper.getClass() == this.getClass()) {
            personaData = ((ControllerHelperMain) helper).personaData;
        }
	}

	public void doGet()throws ServletException, IOException{
		addHelperToSession("helper", SessionData.IGNORE);
		
		String address = executeButtonMethod();
		
		request.getRequestDispatcher(address).forward(request, response);
	}
	
	public void doPost()throws ServletException, IOException{
		addHelperToSession("helper", SessionData.READ);
		
		String address = executeButtonMethod();
		
		request.getRequestDispatcher(address).forward(request, response);
	}
	
	/* espesicicar la ubicacion de los jsp */
	protected String jspLocation(String page){
		return "WEB-INF/classes/main/"+page;
	}
	
	@ButtonMethod(buttonName="listAll", isDefault = true)
	public String btnListAll(){
		List cocinas= HibernateHelper.getListData(personaData.getClass());
		request.setAttribute("personas", cocinas);
		
		return jspLocation("listAll.jsp");
	}
	
	@ButtonMethod(buttonName="confirmButton")
	public String confirmButton(){
		//fillBeanFromRequest(data);
		return jspLocation("confirm.jsp");
	}
	
	static public void initHibernate() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        props.setProperty("hibernate.c3p0.min_size", "1");
        props.setProperty("hibernate.c3p0.max_size", "5");
        props.setProperty("hibernate.c3p0.timeout", "300");
        props.setProperty("hibernate.c3p0.max_statements", "50");
        props.setProperty("hibernate.c3p0.idle_test_period", "300");

        //Se establesen los parametros de conexion a la base de datos
        props.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/db_dwtest");
        props.setProperty("hibernate.connection.username", "root");
        props.setProperty("hibernate.connection.password", "root");

        HibernateHelper.initSessionFactory(Estado.class, Municipio.class, Domicilio.class, EstadoCivil.class, Persona.class);
    }
}
