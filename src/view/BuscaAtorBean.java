package view;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputHidden;

import model.Dvd_release;
import util.CassandraDAOFactory;
import util.DAOFactory;
import dao.CassandraDAO;
import dao.Dvd_releaseDAO;


@ManagedBean(name="buscaAtorBean")
@SessionScoped
public class BuscaAtorBean {	
	
	private Dvd_releaseDAO dvd_releaseDAO;
	private CassandraDAO cassandraDAO;
	private List<Dvd_release> filmesPorAtor = null;
	private HtmlInputHidden actor_id = new HtmlInputHidden();
	private HtmlInputHidden first_name = new HtmlInputHidden();
	private HtmlInputHidden last_name = new HtmlInputHidden();
	private String nomeAtor;

	public String buscarAtores(){
		long startTime = System.nanoTime();
		this.setDvd_releaseDAO(DAOFactory.createDvd_releaseDAO());
		this.setFilmesPorAtor(dvd_releaseDAO.searchFilmByActorId((Integer)actor_id.getValue()));
		this.setNomeAtor(this.first_name.getValue() +" "+ this.last_name.getValue());
		long endTime = System.nanoTime();
		System.out.println(endTime - startTime);
		return "filmePorAtor" + "?faces-redirect=true";
	}
	
	public String buscarAtoresCassandra(){
		long startTime = System.nanoTime();
		this.setCassandraDAO(CassandraDAOFactory.createCassandraDAO());
		this.setNomeAtor(this.first_name.getValue() +" "+ this.last_name.getValue());
		this.setFilmesPorAtor(cassandraDAO.getFilmes(nomeAtor));
		long endTime = System.nanoTime();
		System.out.println(endTime - startTime);
		return "filmePorAtor" + "?faces-redirect=true";		
	}
	
	public String buscarAtoresDesnormalizado() throws SQLException{
		long startTime = System.nanoTime();
		this.setDvd_releaseDAO(DAOFactory.createDvd_releaseDAO());
		this.setFilmesPorAtor(dvd_releaseDAO.searchFilmByActorIdDesnormalizado((Integer)actor_id.getValue()));
		this.setNomeAtor(this.first_name.getValue() +" "+ this.last_name.getValue());
		long endTime = System.nanoTime();
		System.out.println(endTime - startTime);
		return "filmePorAtor" + "?faces-redirect=true";		
	}

	public HtmlInputHidden getActor_id() {
		return actor_id;
	}

	public void setActor_id(HtmlInputHidden actor_id) {
		this.actor_id = actor_id;
	}

	public List<Dvd_release> getFilmesPorAtor() {
		return filmesPorAtor;
	}

	public void setFilmesPorAtor(List<Dvd_release> filmesPorAtor) {
		this.filmesPorAtor = filmesPorAtor;
	}

	public Dvd_releaseDAO getDvd_releaseDAO() {
		return dvd_releaseDAO;
	}

	public void setDvd_releaseDAO(Dvd_releaseDAO dvd_releaseDAO) {
		this.dvd_releaseDAO = dvd_releaseDAO;
	}

	public HtmlInputHidden getFirst_name() {
		return first_name;
	}

	public void setFirst_name(HtmlInputHidden first_name) {
		this.first_name = first_name;
	}

	public HtmlInputHidden getLast_name() {
		return last_name;
	}

	public void setLast_name(HtmlInputHidden last_name) {
		this.last_name = last_name;
	}

	public String getNomeAtor() {
		return nomeAtor;
	}

	public void setNomeAtor(String nomeAtor) {
		this.nomeAtor = nomeAtor;
	}

	public CassandraDAO getCassandraDAO() {
		return cassandraDAO;
	}

	public void setCassandraDAO(CassandraDAO cassandraDAO) {
		this.cassandraDAO = cassandraDAO;
	}

}
