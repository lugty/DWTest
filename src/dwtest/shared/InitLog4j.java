package dwtest.shared;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 * Servlet implementation class InitLog4j
 */

@WebServlet("/InitLog4j")
public class InitLog4j extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		String initPath = getInitParameter("logPath");
		String logPath = "/WEB-INF/logs/errors.log"; 
		
		if(initPath!=null){
			logPath = initPath;
		}
		
		FileAppender appender = getAppender(logPath);
		if(appender ==null){
			return;
		}
		initLogger(null, appender, Level.ERROR);
		initLogger("org.apache.commons.beanutils", appender, Level.DEBUG);
		
	}
	
	private FileAppender getAppender(String logPath) {
		RollingFileAppender appender = null;
		try {
			appender = new RollingFileAppender(new PatternLayout("[%-5p] %c [%d{dd-MMM-yyyy - HH:mm:ss}] %t - %m%n"), 
					getServletContext().getRealPath(logPath),true);
			appender.setMaxBackupIndex(5);
			appender.setMaxFileSize("1MB");
			
		} catch (Exception e) {
			System.out.println("No se puede crear appender para: "+ logPath+ ":" + e.getMessage());
		}
		System.out.println(getServletContext().getRealPath(logPath));
		return appender;
	}
	
	private void initLogger(String name, FileAppender appender, Level level ){
		Logger logger;
		if (name == null){
			logger = Logger.getRootLogger();
		}else{
			logger = Logger.getLogger(name);
			
		}
		//se le asigna el nivel
		logger.setLevel(level);
		//dise�o su estructura
		logger.addAppender(appender);
		//imprimir en el logger
		logger.info("Iniciando "+logger.getName());
		logger.trace("Mensaje de Trace");
		logger.debug("Mensaje de Debug");
		logger.error("Mensaje de Error");
		logger.fatal("Mensaje de Fatal");
		logger.info("Mensaje de Info"); 
		logger.warn("Mensaje de Warn");
	}

}
