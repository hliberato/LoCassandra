package loadTests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import util.DAOFactory;
import dao.Dvd_releaseDAO;

public class MysqlLoadTest {
	
	List<Long> results = new ArrayList<Long>();	
	Dvd_releaseDAO dvd_releaseDAO;
	int count = 0;
	
	//Este teste automatizará a carga na busca do ator com maior quantidade de filmes
	// Filmes: 532 Ator: John Wayne Id: 157
	@Test
	public void loadLargerAmount(){
		
		do {			
			//String actor = "John Wayne";
			long startTime = System.nanoTime();
			dvd_releaseDAO = DAOFactory.createDvd_releaseDAO();
			dvd_releaseDAO.searchFilmByActorId(157);
			long endTime = System.nanoTime();
			System.out.println(endTime - startTime);
			count++;
		} while (count<5);
		
		Collections.sort(results);
		System.out.println("Máximo: "+results.get(results.size()-1));
		System.out.println("Minimo: "+results.get(0));
			
	}
}
