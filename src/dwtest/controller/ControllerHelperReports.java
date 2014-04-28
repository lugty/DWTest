package dwtest.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dwtest.model.Domicilio;
import dwtest.model.Estado;
import dwtest.model.EstadoCivil;
import dwtest.model.Municipio;
import dwtest.model.Persona;
import dwtest.model.Universidad;
import dwtest.model.Usuario;
import dwtest.shared.ButtonMethod;
import dwtest.shared.HelperBase;
import dwtest.shared.HibernateHelper;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ControllerHelperReports extends HelperBase{
	
	protected Persona personaData = new Persona();
	
	public ControllerHelperReports(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@Override
	protected void copyFromSession(Object helper) {
		if (helper.getClass() == this.getClass()) {
            personaData = ((ControllerHelperReports) helper).personaData;
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
	
	@ButtonMethod(buttonName="listPersonas", isDefault = true)
	public String btnListAll(){
		
		List personas= HibernateHelper.getListData(personaData.getClass());
		
		System.out.println("***************** Reporte " + personas.size());
		try{
		
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			
			ServletContext sc = request.getServletContext();
			File reportFile = new File(sc.getRealPath("/WEB-INF/classes/DWTestReport.jrxml"));  
			JasperReport reportJasper = JasperCompileManager.compileReport(reportFile.getAbsolutePath());
			
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(personas);
			parameterMap.put("datasorce", JRdataSource);
		
			//bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), parameterMap, JRdataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameterMap, JRdataSource);
			
			response.addHeader("Content-disposition", "attachment; filename=reportPersonas.pdf");
			ServletOutputStream ouputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
			
		}catch(Exception e){
			e.printStackTrace();  
            return null;
		}
		
		return "listAll.jsp";
	}
	
	@ButtonMethod(buttonName="listUsuarios")
	public String btnListAllUsuarios(){
		
		List usuarios= HibernateHelper.getListData(Usuario.class);
		
		System.out.println("usuarios size ********" + usuarios.size());
		try{
		
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			
			ServletContext sc = request.getServletContext();
			File reportFile = new File(sc.getRealPath("/WEB-INF/classes/DWTestReport2.jrxml"));  
			JasperReport reportJasper = JasperCompileManager.compileReport(reportFile.getAbsolutePath());
			
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(usuarios);
			parameterMap.put("datasorce", JRdataSource);
		
			//bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), parameterMap, JRdataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameterMap, JRdataSource);
			
			response.addHeader("Content-disposition", "attachment; filename=reportUsuarios.pdf");
			ServletOutputStream ouputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
			
		}catch(Exception e){
			e.printStackTrace();  
            return null;
		}
		
		return "listAll.jsp";
	}
	
	@ButtonMethod(buttonName="listUniversidades")
	public String btnListAllUniversidades(){
		
		List universidades = HibernateHelper.getListData(Universidad.class);
		
		System.out.println("universidades size ********" + universidades.size());
		try{
		
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			
			ServletContext sc = request.getServletContext();
			File reportFile = new File(sc.getRealPath("/WEB-INF/classes/DWTestReport3.jrxml"));  
			JasperReport reportJasper = JasperCompileManager.compileReport(reportFile.getAbsolutePath());
			
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(universidades);
			parameterMap.put("datasorce", JRdataSource);
		
			//bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), parameterMap, JRdataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameterMap, JRdataSource);
			
			response.addHeader("Content-disposition", "attachment; filename=reportUniversidades.pdf");
			ServletOutputStream ouputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
			
		}catch(Exception e){
			e.printStackTrace();  
            return null;
		}
		
		return "listAll.jsp";
	}
	
	static public void initHibernate() {
        HibernateHelper.initSessionFactory(Estado.class, Municipio.class, Domicilio.class, EstadoCivil.class, Persona.class, Universidad.class, Usuario.class);
    }
	
}
