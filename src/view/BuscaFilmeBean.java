package view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Dvd_release;
import util.DAOFactory;
import dao.ActorDAO;
import dao.Dvd_releaseDAO;

@ManagedBean(name="buscaFilmeBean")
@SessionScoped
public class BuscaFilmeBean {

	private List<Dvd_release> filmes = null;
	private Dvd_releaseDAO	dvd_releaseDAO;
	private ActorDAO actorDAO;
	private String parametroBusca;
	private Dvd_release dvdSelecionado;
		
	public String exibeFilme(){
		this.setActorDAO(DAOFactory.createActorDAO());
		this.dvdSelecionado.setActors(this.actorDAO.searchActorByFilmId(this.dvdSelecionado.getDvd_release_id()));
		return "exibeFilme" + "?faces-redirect=true";
	}
	
	public String buscarFilme(){
		this.setDvd_releaseDAO(DAOFactory.createDvd_releaseDAO());
		this.setFilmes(dvd_releaseDAO.buscarFilmePorNome(parametroBusca));
		return "buscaFilme" + "?faces-redirect=true";
	}

	public List<Dvd_release> getFilmes() {return filmes;}
	public void setFilmes(List<Dvd_release> filmes) {this.filmes = filmes;}
	public Dvd_releaseDAO getDvd_releaseDAO() {return dvd_releaseDAO;}
	public void setDvd_releaseDAO(Dvd_releaseDAO dvd_releaseDAO) {this.dvd_releaseDAO = dvd_releaseDAO;}
	public String getParametroBusca() {return parametroBusca;}
	public void setParametroBusca(String parametroBusca) {this.parametroBusca = parametroBusca;}
	public Dvd_release getDvdSelecionado() {return dvdSelecionado;}
	public void setDvdSelecionado(Dvd_release dvdSelecionado) {this.dvdSelecionado = dvdSelecionado;}
	public ActorDAO getActorDAO() {return actorDAO;}
	public void setActorDAO(ActorDAO actorDAO) {this.actorDAO = actorDAO;}

}
