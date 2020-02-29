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
public final class MongoOpQuery extends ClientRequestMessage {

    /**
     * bit vector. see below
     */
    private Integer flags;

    /**
     * "dbname.collectionname"
     */
    private String fullCollectionName;

    /**
     * number of documents to skip
     */
    private Integer numberToSkip;

    /**
     * number of documents to return
     * in the first OP_REPLY batch
     */
    private Integer numberToReturn;

    /**
     * query object.  See below for details.
     */
    private Document query;

    /**
     * Optional. Selector indicating the fields
     * to return.  See below for details.
     */
    private Document returnFieldsSelector;
    
}
