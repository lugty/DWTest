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
		return "resources/pages/main/"+page;
	}
	
	@ButtonMethod(buttonName="listAll", isDefault = true)
	public String btnListAll(){
		List personas= HibernateHelper.getListData(personaData.getClass());
		request.setAttribute("personas", personas);
		
		return jspLocation("gestion_personas.jsp");
	}
	
	@ButtonMethod(buttonName="btnDelete")
	public String deleteButton(){
		int idDelete = Integer.parseInt(request.getParameter("id"));
		Persona personaBorrar = (Persona) HibernateHelper.getKeyData(Persona.class, idDelete);
		if(personaBorrar!=null){
			HibernateHelper.removeDB(personaBorrar);
		}else{
			logger.info("persona no encontrada");
		}
		
		List personas = HibernateHelper.getListData(personaData.getClass());
		request.setAttribute("personas", personas);
		
		return jspLocation("gestion_personas.jsp");
	}
	
	@ButtonMethod (buttonName="btnInsert")
	public String btnConfirm(){
		
		Domicilio dom = new Domicilio();
		EstadoCivil edo= new EstadoCivil();
		personaData.setCve_domicilio(dom);
		personaData.setCve_estado_civil(edo);
		
		fillBeanFromRequest(personaData);
		System.out.println(personaData.getCve_domicilio().getCalle());
		
		String address = "";
		if(isValid(personaData)){
			HibernateHelper.saveDB(edo);
			HibernateHelper.saveDB(dom);
			
			HibernateHelper.saveDB(personaData);
			address= jspLocation("gestion_personas.jsp");
			response.setStatus(200);
		}else{
			response.setStatus(500);
			address= jspLocation("gestion_personas.jsp");
		}
		return address;
	}
	
	static public void initHibernate() {
        HibernateHelper.initSessionFactory(Estado.class, Municipio.class, Domicilio.class, EstadoCivil.class, Persona.class);
    }
}
