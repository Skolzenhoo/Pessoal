package bo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import model.Usuario;

public class ManipulacaoCustos {
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	private static SessionFactory buildSessionFactory() {
		//Creating configuration instance & passing hibernate configuration file
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		
		//Since hibernate version 4.x, ServiceRegistry is being used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
		
		//Creating hibernate SessionFactory instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}
	
	public static void main (String[] args) {
		System.out.println("Hibernate Maven Example");
		sessionObj = buildSessionFactory().openSession();
		sessionObj.beginTransaction();
		
		try {
			Usuario usuario = new Usuario();
			usuario.setId(1);
			usuario.setNome("Antonio");
			usuario.setEmail("teste.hotmail.com");
			sessionObj.save(usuario);
			
			System.out.println("Registros inseridos com sucesso");
		}
		catch(Exception e){
			sessionObj.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
