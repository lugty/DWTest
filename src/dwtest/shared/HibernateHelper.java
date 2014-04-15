package dwtest.shared;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HibernateHelper {
	protected static SessionFactory sessionFactory;
	protected static ServiceRegistry serviceRegistry;
	
	protected static List<Class> lstClasses = new ArrayList<Class>();
	
	public static void initSessionFactory(Properties properties,Class... mappings){
		if(addMappings(lstClasses,mappings)){
			closeSessionFactory(sessionFactory);
			sessionFactory = createFactory(properties, lstClasses);
		}
	}
	
	public static void initSessionFactory(Class...mappings){
		initSessionFactory(null, mappings);
	}
	
	protected static boolean addMappings(List<Class>list, Class... mappings){
		boolean bNewClass = false;
		
		for (Class mapping : mappings) {
			if(!list.contains(mapping)){
				list.add(mapping);
				bNewClass= true;
			}
		}
		return bNewClass;
	}
	
	public static void closeSessionFactory (SessionFactory factory){
		if(factory!=null){
			factory.close();
		}
	}
	
	public static void closeFactory(){
		closeSessionFactory(sessionFactory);
	}
	
	protected static SessionFactory createFactory(Properties properties,List<Class> list){
		SessionFactory factory = null;
		Configuration cfg = new Configuration();
			
		try {
			if(properties!=null){
				cfg.addProperties(properties);
			}
			
			//configureFromFile(cfg);
			for (Class mapping : list) {
				cfg.addAnnotatedClass(mapping);
			}
			factory = buildFactory(cfg);
		}catch (Exception e) {
			//logger.error("Creacion de fabrica de sessiones fallo.", e);
			closeSessionFactory(factory);
			factory = null;
				
			throw new HibernateException(e);
		}
		
		return factory;
	}
	
	protected  static SessionFactory buildFactory(Configuration configuration) throws Exception {
		SessionFactory factory = null;
		try {
			serviceRegistry = new ServiceRegistryBuilder()
			.applySettings(configuration.getProperties()).buildServiceRegistry();
			factory = configuration.buildSessionFactory(serviceRegistry);
			
		} catch (Exception e) {
			closeSessionFactory(factory);
			factory = null;
			throw e;
		}
		
		return factory;
	}
	
}
