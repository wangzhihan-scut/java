package MongoDB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author: wangzh
 * @Date: 2019/4/17 0017 22:57
 */
public class TestMongodb {
    private static String username = "xiaohaitun";
    private static String password = "xiaohaitun";
    private static String DATABASE = "test";
    private static String SERVERADDRESS = "localhost";
    private static int PORT = 27017;

    private static List<String> collections = new ArrayList<>();
    static{
        collections.add("firstCollection");
        collections.add("secondCollection");
    }


    /**
     * mongodb options
     */
//    private Integer maxWaitTime = 3000;

    public static void main(String[] args){
        ServerAddress serverAddress = new ServerAddress(SERVERADDRESS, PORT);
        List<ServerAddress> serverAddresses = new ArrayList<>();
        serverAddresses.add(serverAddress);

        //mongodb的认证
        MongoCredential mongoCredential = MongoCredential.createScramSha256Credential(username, DATABASE, password.toCharArray());
        /*被弃用
        MongoClient mongoClient = new MongoClient(serverAddresses, Collections.singletonList(mongoCredential), MongoClientOptions.builder().build());
        */
        MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredential, MongoClientOptions.builder().build());

        //spring里面有更好的封装MongoTemplate
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> firstCollection = mongoDatabase.getCollection(TestMongodb.collections.get(0));
        System.out.println(firstCollection);

        //检索查看结果
        FindIterable<Document> documentFindIterable = firstCollection.find();
        MongoCursor<Document> mongoCursor = documentFindIterable.iterator();
        while(mongoCursor.hasNext()){
            System.out.println(mongoCursor.next());
        }
        documentFindIterable = firstCollection.find(Filters.eq("docu","third"));
//        System.out.println(Filters.eq("docu","Third"));
        mongoCursor = documentFindIterable.iterator();
        if(mongoCursor.hasNext()){
            System.out.println("修改third");
            firstCollection.updateMany(Filters.eq("docu","third"), new Document("$set", new Document("docu","Third")));
        }else{//插入文档
            System.out.println("插入");
            firstCollection.insertOne(new Document().append("docu", "third"));
        }
        //删除文档
        System.out.println(firstCollection.deleteMany(Filters.eq("docu","third")));

        //聚合aggreagation
//        List<Document> lists = new ArrayList<>();
//        for(int i = 0;i < 5;i++){
//            lists.add(new Document().append("id",i).append("docu", "docu"+i).
//                    append("doc_doc", new Document().append("doc","doc_doc").append("id", i)));
//        }
//        firstCollection.insertMany(lists);
        List<Document> pipeline = new ArrayList<>();
        pipeline.add(new Document("$match", new Document("id", new Document("$exists",true))));
        pipeline.add(new Document("$group", new Document("_id","$id").append("count" ,new Document("$sum", 1))));

//        mongoCursor = firstCollection.aggregate(pipeline).iterator();
//        while(mongoCursor.hasNext()){
//            System.out.println(mongoCursor.next());
//        }
        AggregateIterable<Document> aggregateIterable = firstCollection.aggregate(pipeline);
        System.out.println(aggregateIterable);
        for(Document document : aggregateIterable){
            System.out.println(document);
        }
    }
}
