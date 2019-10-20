package com.mongodb.wire.protocol;

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
public final class MongoOpMsg {

    /**
     * standard message header
     */
    private MessageHeader header;

    /**
     * data sections
     */
    private Long flagBits;

    /**
     * sequence of cursorIDs to close
     */
    //private Sections[] sections;

    /**
     * optional CRC-32C checksum
     */
    private Long checksum;

}
