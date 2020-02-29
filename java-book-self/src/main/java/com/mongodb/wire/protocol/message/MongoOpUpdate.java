package com.mongodb.wire.protocol.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.Document;

/**
 * @author dongzonglei
 * @description
 * @date 2019-10-19 21:56
 */
@Getter
@Setter
@ToString
public final class MongoOpUpdate extends ClientRequestMessage {

    /**
     * 0 - reserved for future use
     */
    private static final int ZERO = 0;

    /**
     * "dbname.collectionname"
     */
    private String fullCollectionName;

    /**
     * bit vector. see below
     */
    private Integer flags;

    /**
     * the query to select the document
     */
    private Document selector;

    /**
     * specification of the update to perform
     */
    private Document update;
    
}
