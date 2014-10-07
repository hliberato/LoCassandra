package dao;

import java.util.List;

import model.Actor;

import org.hibernate.Query;
import org.hibernate.classic.Session;

public class ActorDAO {

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<Actor> searchActorByFilmId(Integer dvd_release_id){
		String hql = "select a " +
					 "from Actor a " +
					 "join a.dvdReleases f " +
					 "where f.dvd_release_id = :dvd_release_id ";
					 /*"where d.dvd_release_id = :actorId ";*/
		Query query = session.createQuery(hql);
		query.setParameter("dvd_release_id", dvd_release_id);		
		List<Actor> actors = query.list();
		return actors;
	}
	
	@SuppressWarnings("unchecked")
	public List<Actor> list() {
		return this.session.createCriteria(Actor.class).list();
	}

}
