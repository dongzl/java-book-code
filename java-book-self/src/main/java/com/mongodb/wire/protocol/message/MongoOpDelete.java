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
public final class MongoOpDelete extends ClientRequestMessage {

    /**
     * 0 - reserved for future use
     */
    private static final int ZERO = 0;

    /**
     * bit vector. see below
     */
    private Integer flags;

    /**
     * "dbname.collectionname"
     */
    private String fullCollectionName;

    /**
     * query object.  See below for details.
     */
    private Document selector;
    
}
