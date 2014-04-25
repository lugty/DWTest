package dwtest.shared;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public abstract class HelperBase {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Logger logger;
	
	protected Method methodDefault = null;
	
	protected Map<String,String> errorMap = new HashMap<String, String>();
	
	public HelperBase(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		this.logger = Logger.getLogger("dwtest.webdes");
		this.logger.setLevel(Level.DEBUG);
	}
	
	protected abstract void copyFromSession(Object helper);
	
	/**
	 * Add helper to session
	 */
	public void addHelperToSession(String name, SessionData state){
		if(SessionData.READ == state){
			Object sessionObjt = request.getSession().getAttribute(name);
			if(sessionObjt != null)
				copyFromSession(sessionObjt);
		}
		request.getSession().setAttribute(name, this);
	}
	
	protected enum SessionData{
		READ,
		IGNORE
	}
	
	protected String executeButtonMethod() 
			throws ServletException, IOException{
		String result = "";
		methodDefault = null;
		Class clazz = this.getClass();
		Class enclosingClass = clazz.getEnclosingClass();
		
		while (enclosingClass!=null){
			clazz = this.getClass();
			enclosingClass = clazz.getEnclosingClass();
		}
		
		try {
			result = executeButtonMethod (clazz, true);
		} catch (Exception e) {
			logger.error("Erro en la busqueda y ejecuacion del metodo solicitado", e);
			return "";
		}
		
		return result;
	}
	
	/**
	 * Metodo que determian la ejecucion y ejecuta el metodo invocado
	 * @param clazz
	 * @param searchForDefault
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected String executeButtonMethod(Class clazz, boolean searchForDefault)
			throws IllegalAccessException, InvocationTargetException{
		Method[] methods = clazz.getMethods();
		String result = "";
		
		for(Method method:methods){
			ButtonMethod annotation = method.getAnnotation(ButtonMethod.class);
			if(annotation != null){
				if(searchForDefault && annotation.isDefault())
					methodDefault = method;
				
				System.out.println(annotation.buttonName());
				System.out.println(request.getParameter(annotation.buttonName()));
				
				if(request.getParameter(annotation.buttonName()) != null){
					System.out.println("****** entre ******");
					result = invokeButtonMethod(method);
					break;
				}
			}
		}
		
		if(result.equals("")){
			if (methodDefault!=null){
				result = invokeButtonMethod(methodDefault);
			}else{
				logger.error("No hay metodo por defecto especificado");
				result = "No hay metodo por defecto especificado";
			}
		}
		
		return result;
	}
	
	/**
	 * Metodo que realiza la invocacion de un metodo con anotasion
	 * @return
	 */
	protected String invokeButtonMethod(Method method)
			throws IllegalAccessException, InvocationTargetException{
		String resultInvoke = "No se pudo realizar la invocacion del metodo";
		System.out.println("********* llegue aqui");
		try{
			resultInvoke = (String) method.invoke(this, (Object[])null);
			System.out.println("********* se ejecuto el metodo" + resultInvoke);
		}catch(InvocationTargetException ie){
			ie.printStackTrace();
			logger.error("InvocationTargetException Error en invocacion del metodo", ie);
		}catch (IllegalAccessException ile) {
			ile.printStackTrace();
			logger.error("IllegalAccessException Error en invocacion del metodo", ile);
		}
		
		return resultInvoke;
	}
	
	/**
	 * fill bean with data sent in a request
	 * @param data
	 */
	public void fillBeanFromRequest(Object data){
		try{
			org.apache.commons.beanutils.BeanUtils.populate(data, request.getParameterMap());
		}catch(IllegalAccessException iae){
			logger.error("Populate Illegal Access", iae);
		}catch(InvocationTargetException ite){
			logger.error("Polupate Invocation Error", ite);
		}
	}
	
	/************************** validation ***************************/
	
	public void setErrors(Object data){
		//InvalidValue[] validationMessages;
	}
	
	public Map getErrors(){
		return errorMap;
	}
	
	public boolean isValid(Object data){
		setErrors(data);
		return errorMap.isEmpty();
	}
	
	
}
