package com.mongodb.wire.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dongzonglei
 * @description
 * @date 2019-10-19 21:53
 */
@Getter
@Setter
@ToString
public final class MessageHeader {

    /**
     * total message size, including this
     */
    private Integer messageLength;

    /**
     * identifier for this message
     */
    private Integer requestID;

    /**
     * requestID from the original request
     * (used in responses from db)
     */
    private Integer responseTo;

    /**
     * request type - see table below for details
     */
    private Integer opCode;
    
}
