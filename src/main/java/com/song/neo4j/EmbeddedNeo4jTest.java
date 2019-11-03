package com.song.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.util.Map;

public class EmbeddedNeo4jTest {

    public static void main(String[] args) {

        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(new File("data"));

        db.traversalDescription().depthFirst();

//        Node firstNode = db.createNode();
//        firstNode.setProperty("name", "my node");

        try (Transaction ignored = db.beginTx();
             Result result = db.execute("MATCH (n {name: 'my node'}) RETURN n, n.name")) {
//             Result result = db.execute("create (n{name:'my node'}) return n, n.name")) {
            StringBuilder rows = new StringBuilder();
            while (result.hasNext()) {
                Map<String, Object> row = result.next();
                for (Map.Entry<String, Object> column : row.entrySet()) {
                    rows.append(column.getKey()).append(": ").append(column.getValue()).append("; ");
                }
                rows.append("\n");
            }

            System.out.println(rows);
        }

        db.shutdown();
    }
}
