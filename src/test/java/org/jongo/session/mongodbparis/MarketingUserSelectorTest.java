package org.jongo.session.mongodbparis;

import com.mongodb.DB;
import com.mongodb.Mongo;
import org.junit.Before;
import org.junit.Test;

public class MarketingUserSelectorTest {

    private DB db;

    @Before
    public void setUp() throws Exception {
        Mongo mongo = new Mongo("127.0.0.1", 27017);
        db = mongo.getDB("jongo-session");
    }

    @Test
    //{ $or : [ { age : {$gt:20,$lt:30} } , { age : {$gt:50,$lt:60} } ] }
    public void canFindUserWithJavaDriver() throws Exception {

    }
}
