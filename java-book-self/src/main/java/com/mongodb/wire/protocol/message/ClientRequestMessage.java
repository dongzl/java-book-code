package com.mongodb.wire.protocol.message;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dongzonglei
 * @description
 * @date 2019-10-20 18:57
 */
@Setter
@Getter
public class ClientRequestMessage {

    /**
     * standard message header
     */
    private MessageHeader messageHeader;
    
}
