package dwtest.shared;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import javax.persistence.SharedCacheMode;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class WebAppListener
 *
 */

@WebListener
public class WebAppListener implements ServletContextListener {


	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        try{
        	Enumeration<Driver> enumer = DriverManager.getDrivers();
        	while(enumer.hasMoreElements()){
        		DriverManager.deregisterDriver(enumer.nextElement());
        	}
        }catch(java.sql.SQLException e){
        	e.printStackTrace();
        }
        
        HibernateHelper.closeFactory();
    }
	
}
