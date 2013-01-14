package org.jongo.session.mongodbparis.helper;

import com.mongodb.*;
import org.fest.assertions.api.Assertions;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.session.mongodbparis.Address;
import org.jongo.session.mongodbparis.User;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class DataInjector {

    private DB db;

    @Before
    public void setUp() throws Exception {
        Mongo mongo = new Mongo("127.0.0.1", 27017);
        db = mongo.getDB("marketing");
        db.dropDatabase();
    }

    @Test
    public void injectUserWithJavaDriver() throws Exception {

        DBCollection collection = db.getCollection("users");
        User user = new User("jdoe", 18, new Address("10 rue des lilas", "Paris", "France"));

        //Creating a DBObject for User
        DBObject dbObject = new BasicDBObject();
        dbObject.put("username", user.getUsername());
        dbObject.put("age", user.getAge());


        //Creating a DBObject for address
        Address address = user.getAddress();
        BasicDBObject dbAddress = new BasicDBObject("way", address.getWay());
        dbAddress.put("city", address.getCity());
        dbAddress.put("country", address.getCountry());
        dbObject.put("address", dbAddress);

        collection.save(dbObject, WriteConcern.SAFE);

        assertThat(collection.count()).isEqualTo(1);
    }

    @Test
    public void injectUsersWithJongo() throws Exception {

        MongoCollection collection = new Jongo(db).getCollection("users");
        User peter = new User("peter", 22, new Address("10 rue des lilas", "Paris", "France"));
        User patrick = new User("patrick", 89, new Address("122 Boulevard Haussmann", "Paris", "France"));
        User boris = new User("boris", 12, new Address("12 allée des coquelicots", "Aix-en-Provence", "France"));
        User robert = new User("robert", 55, new Address("12 allée des paquerettes", "Toulouse", "France"));

        collection.save(peter);
        collection.save(patrick);
        collection.save(boris);
        collection.save(robert);

        assertThat(collection.count("{}")).isGreaterThanOrEqualTo(4);
    }


}
