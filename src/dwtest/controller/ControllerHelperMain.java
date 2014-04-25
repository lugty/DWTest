package dwtest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dwtest.model.Alumno;
import dwtest.model.AreaGradoAcademico;
import dwtest.model.Ciclo;
import dwtest.model.Domicilio;
import dwtest.model.Estado;
import dwtest.model.EstadoCivil;
import dwtest.model.Municipio;
import dwtest.model.PadecimientosPersona;
import dwtest.model.Periodos;
import dwtest.model.Persona;
import dwtest.model.SaldoAlumno;
import dwtest.model.Universidad;
import dwtest.model.Usuario;
import dwtest.shared.ButtonMethod;
import dwtest.shared.HelperBase;
import dwtest.shared.HibernateHelper;

public class ControllerHelperMain extends HelperBase{
	//Se declara el data principal del controllerHelper
	protected Persona personaData = new Persona();
	
	protected Municipio municipio = new Municipio();
	protected Estado estado = new Estado();
	protected EstadoCivil estadoCivil = new EstadoCivil();
	protected Domicilio domicilio = new Domicilio();
	protected Alumno alumno = new Alumno();
	protected AreaGradoAcademico acaAreaGradoAcademico = new AreaGradoAcademico();
	protected Ciclo ciclo = new Ciclo();
	protected PadecimientosPersona padecimientosPersona = new PadecimientosPersona();
	protected Periodos periodos = new Periodos();
	protected SaldoAlumno saldoAlumno = new SaldoAlumno();
	protected Universidad universidad = new Universidad();
	
	public ControllerHelperMain(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public Object getData(){
		return personaData;
	}
	
	public void setPersonaData(Persona persona){
		this.personaData = persona;
	}

	@Override
	protected void copyFromSession(Object helper) {
		if (helper.getClass() == this.getClass()) {
            personaData = ((ControllerHelperMain) helper).personaData;
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
		
		request.getRequestDispatcher(address).forward(request, response);
	}
	
	/* espesicicar la ubicacion de los jsp */
	protected String jspLocation(String page){
		return "resources/pages/main/"+page;
	}
	
	@ButtonMethod(buttonName="listAll", isDefault = true)
	public String btnListAll(){
		List personas = HibernateHelper.getListData(personaData.getClass());
		List estaList = HibernateHelper.getListData(estadoCivil.getClass());
		List universidades = HibernateHelper.getListData(universidad.getClass());
		List estados = HibernateHelper.getListData(estado.getClass());
		List municipios = HibernateHelper.getListData(municipio.getClass());
		List grados = HibernateHelper.getListData(acaAreaGradoAcademico.getClass());
		List ciclos = HibernateHelper.getListData(ciclo.getClass());
		request.setAttribute("personas", personas);
		request.setAttribute("estados_civil", estaList);
		request.setAttribute("universidades", universidades);
		request.setAttribute("estados", estados);
		request.setAttribute("grados", grados);
		request.setAttribute("municipios", municipios);
		request.setAttribute("ciclos", ciclos);
		
		System.out.println(" ******* Personas: " + personas.size());
		System.out.println(" ******* Estados Civiles: " + estaList.size());
		System.out.println(" ******* Universidades: " + universidades.size());
		System.out.println(" ******* Etados: " + estados.size());
		System.out.println(" ******* Municipios: " + municipios.size());
		System.out.println(" ******* Grados: " + grados.size());
		System.out.println(" ******* Ciclos: " + ciclos.size());
		
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
	
	/**
	 * Iniciar session factory, las configuraciones de conexion a la base de datos se cargan desde el archivo 
	 */
	static public void initHibernate() {
        HibernateHelper.initSessionFactory(Estado.class, Municipio.class, Domicilio.class, EstadoCivil.class, Persona.class);
    }
}
