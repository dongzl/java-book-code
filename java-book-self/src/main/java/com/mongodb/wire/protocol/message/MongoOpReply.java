package com.mongodb.wire.protocol.message;

import com.mongodb.wire.protocol.message.MessageHeader;
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
public final class MongoOpReply {

    /**
     * standard message header
     */
    private MessageHeader header;

    /**
     * bit vector - see details below
     */
    private Integer responseFlags;

    /**
     * cursor id if client needs to do get more's
     */
    private Long cursorID;

    /**
     * where in the cursor this reply is starting
     */
    private Integer startingFrom;

    /**
     * number of documents in the reply
     */
    private Integer numberReturned;

    /**
     * documents
     */
    private List<Document> documents;
}
