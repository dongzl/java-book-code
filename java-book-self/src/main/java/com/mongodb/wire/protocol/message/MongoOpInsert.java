package com.mongodb.wire.protocol.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.Document;

import java.util.List;

/**
 * @author dongzonglei
 * @description
 * @date 2019-10-19 21:56
 */
@Getter
@Setter
@ToString
public final class MongoOpInsert extends ClientRequestMessage {

    /**
     * bit vector. see below
     */
    private Integer flags;

    /**
     * "dbname.collectionname"
     */
    private String fullCollectionName;

    /**
     * one or more documents to insert into the collection
     */
    private List<Document> documents;
    
}
