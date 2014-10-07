package dao;

import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.ColumnSliceIterator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.SliceQuery;
import model.Dvd_release;

import com.google.gson.Gson;



public class CassandraDAO {
	
	private Cluster cluster;
	private Keyspace keyspaceOperator;
	private String columnFamily;
	Gson gson = new Gson();
	List<Dvd_release> filmes = null;
	
	public List<Dvd_release> getFilmes(String key){
		//KEY É O NOME DO ATOR + " " + SOBRENOME		
		filmes = new ArrayList<Dvd_release>();
    	SliceQuery<String, String, String> query = HFactory.createSliceQuery(keyspaceOperator, StringSerializer.get(),
    	StringSerializer.get(), StringSerializer.get()).
    	setKey(key).setColumnFamily(columnFamily);
    	ColumnSliceIterator<String, String, String> iterator =
    	new ColumnSliceIterator<String, String, String>(query, null, "\uFFFF", false);
    	while (iterator.hasNext()) {
			HColumn<String, String> filme;
			filme = iterator.next();        		
			//System.out.println(filme.getValue());
			Dvd_release dvd = gson.fromJson(filme.getValue(),Dvd_release.class);
			filmes.add(dvd);
    	}
    	
    	return filmes;
	}
	
	public Cluster getCluster() {
		return cluster;
	}
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	public Keyspace getKeyspaceOperator() {
		return keyspaceOperator;
	}
	public void setKeyspaceOperator(Keyspace keyspaceOperator) {
		this.keyspaceOperator = keyspaceOperator;
	}
	public String getColumnFamily() {
		return columnFamily;
	}
	public void setColumnFamily(String columnFamily) {
		this.columnFamily = columnFamily;
	}

}
