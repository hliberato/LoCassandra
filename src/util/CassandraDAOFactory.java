package util;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;
import dao.CassandraDAO;



public class CassandraDAOFactory {
	
	public static CassandraDAO createCassandraDAO(){
		Cluster cluster = HFactory.getOrCreateCluster("Test Cluster", "localhost:9160");
	    Keyspace keyspaceOperator = HFactory.createKeyspace("locassandra", cluster);
	    String columnFamily = "filmesporator";
	    CassandraDAO cass = new CassandraDAO();
	    cass.setCluster(cluster);
	    cass.setColumnFamily(columnFamily);
	    cass.setKeyspaceOperator(keyspaceOperator);
		return cass;		
	}
	
}