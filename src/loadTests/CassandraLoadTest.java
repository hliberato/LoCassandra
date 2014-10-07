package loadTests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import dao.CassandraDAO;

import util.CassandraDAOFactory;

public class CassandraLoadTest {
	
	List<Long> results = new ArrayList<Long>();	
	CassandraDAO cassandraDAO;
	int count = 0;
	
	//Este teste automatizará a carga na busca do ator com maior quantidade de filmes
	// Filmes: 532 Ator: John Wayne
	@Test
	public void loadLargerAmount(){
		
		do {			
			String actor = "John Wayne";
			long startTime = System.nanoTime();
			this.cassandraDAO = CassandraDAOFactory.createCassandraDAO();
			cassandraDAO.getFilmes(actor);
			long endTime = System.nanoTime();
			results.add(endTime - startTime);
			count++;
		} while (count<5);
		
		Collections.sort(results);
		System.out.println("Máximo: "+results.get(results.size()-1));
		System.out.println("Minimo: "+results.get(0));
			
	}
}
