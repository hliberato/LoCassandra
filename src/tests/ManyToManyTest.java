package tests;


import org.junit.Test;

import util.DAOFactory;

import dao.Dvd_releaseDAO;

public class ManyToManyTest {
	
	@Test
	public void teste(){
		Dvd_releaseDAO d = new Dvd_releaseDAO();
		d = DAOFactory.createDvd_releaseDAO();
		System.out.println(d.getById(10).getGenre());
	}

}
