package com.mongodb.wire.protocol.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dongzonglei
 * @description
 * @date 2019-10-19 21:56
 */
@Getter
@Setter
@ToString
public final class MongoOpGetMore extends ClientRequestMessage {

    /**
     * 0 - reserved for future use
     */
    private static final int ZERO = 0;
    
    /**
     * "dbname.collectionname"
     */
    private String fullCollectionName;
    
    /**
     * number of documents to return
     * in the first OP_REPLY batch
     */
    private Integer numberToReturn;

    /**
     * cursorID from the OP_REPLY
     */
    private Long cursorID;
    
}
