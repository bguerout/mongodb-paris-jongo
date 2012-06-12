package org.jongo.session.mongodbparis.helper;

import com.mongodb.*;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.session.mongodbparis.User;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SolvedMarketingUserSelectorTest {

    private DB db;

    @Before
    public void setUp() throws Exception {
        Mongo mongo = new Mongo("127.0.0.1", 27017);
        db = mongo.getDB("mongodb-paris");
    }

    @Test
    //{ $or : [ { age : {$gte:20,$lte:30} } , { age : {$gte:50,$lte:60} } ] }
    public void usingJavaDriver() throws Exception {

        DBCollection users = db.getCollection("users");

        //Recreate json query with a builder
        DBCursor results = users.find(QueryBuilder.start().or(
                QueryBuilder.start("age").greaterThan(20).lessThan(30).get(),
                QueryBuilder.start("age").greaterThan(50).lessThan(60).get()
        ).get());

        //Manually map result from DBObject to User
        assertThat(results.hasNext()).isTrue();
        for (DBObject result : results) {

            User user = new User();
            user.setUsername((String) result.get("username"));
            user.setAge((Integer) result.get("age"));
            user.setAddress(null);

            assertThat(user.getUsername()).isIn("peter", "robert");
        }
    }

    @Test
    //{ $or : [ { age : {$gt:20,$lt:30} } , { age : {$gt:50,$lt:60} } ] }
    public void usingJongo() throws Exception {

        Jongo jongo = new Jongo(db);
        MongoCollection collection = jongo.getCollection("users");

        Iterable<User> users = collection.find("{ $or : [ { age : {$gt:20,$lt:30} } , { age : {$gt:50,$lt:60} } ] }").as(User.class);

        assertThat(users.iterator().hasNext()).isTrue();
        for (User user : users) {
            assertThat(user.getUsername()).isIn("peter", "robert");
            System.out.println("You can send an email to " + user.getUsername());
        }
    }
}
