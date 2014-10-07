package tests;

import model.Dvd_release;

import org.junit.Test;

import util.CassandraDAOFactory;
import dao.CassandraDAO;


public class GetCassTest {
	
	@Test
	public void get(){
		CassandraDAO cassandraDAO = CassandraDAOFactory.createCassandraDAO();
		for (Dvd_release d: cassandraDAO.getFilmes("Will Smith")){
			System.out.println(d.getTitle());
			
		}
	}

}
