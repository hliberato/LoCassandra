package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Dvd_release;

import org.hibernate.Query;
import org.hibernate.Session;

import util.ConectaMySQL;

public class Dvd_releaseDAO {
	
	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dvd_release> searchFilmByActorId(Integer actorId){
		String hql = "select d " +
					 "from Dvd_release d " +
					 "join d.actors a " +
					 "where a.actor_id = :actorId ";
					 /*"where d.dvd_release_id = :actorId ";*/
		Query query = session.createQuery(hql);
		query.setParameter("actorId", actorId);		
		List<Dvd_release> dvd_releases = query.list();
		return dvd_releases;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dvd_release> list() {
		return this.session.createCriteria(Dvd_release.class).list();
	}

	public Dvd_release getById(Integer dvd_release_id) {
		return (Dvd_release) this.session.get(Dvd_release.class, dvd_release_id);
	}

	@SuppressWarnings("unchecked")
	public List<Dvd_release> buscarFilmePorNome(String parametroBusca) {
		String hql = "select d from Dvd_release d where d.title like :parametroBusca";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("parametroBusca", "%"+parametroBusca+"%");
		return (List<Dvd_release>) consulta.list();
	}
	
	public List<Dvd_release> searchFilmByActorIdDesnormalizado(Integer actorId) throws SQLException {
		List<Dvd_release> dvd_releases = new ArrayList<Dvd_release>();
		String query = "select dvd_release_id, title, studio, genre " +
				 "from filmesporator "+
				 "where actor_id = "+actorId;
		Connection conexao = new ConectaMySQL().geraConexao();
		PreparedStatement ps = conexao.prepareStatement(query);
		ResultSet resultado = ps.executeQuery();
		while (resultado.next()) {
			Dvd_release dvd = new Dvd_release();
			dvd.setDvd_release_id(resultado.getInt("dvd_release_id"));
			dvd.setTitle(resultado.getString("title"));
			dvd.setStudio(resultado.getString("studio"));
			dvd.setGenre(resultado.getString("genre"));
			dvd_releases.add(dvd);
		}
		return dvd_releases;
	}

}
