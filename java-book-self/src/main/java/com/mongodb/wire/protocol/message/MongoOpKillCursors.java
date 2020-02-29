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
public final class MongoOpKillCursors extends ClientRequestMessage {

    /**
     * 0 - reserved for future use
     */
    private static final int ZERO = 0;

    /**
     * number of cursorIDs in message
     */
    private Integer numberOfCursorIDs;

    /**
     * sequence of cursorIDs to close
     */
    private Long cursorIDs;
    
}
