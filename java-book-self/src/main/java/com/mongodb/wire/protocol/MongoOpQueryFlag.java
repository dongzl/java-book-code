package com.mongodb.wire.protocol;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author dongzonglei
 * @description @see <a href="https://docs.mongodb.com/manual/reference/mongodb-wire-protocol/#client-request-messages">Client Request Messages</a>
 * @date 2019-10-19 21:56 
 */
@RequiredArgsConstructor
@Getter
public enum MongoOpQueryFlag {

    TAILABLE_CURSOR(1),

    SLAVE_OK(2),

    OPLOG_REPLAY(3),

    NO_CURSOR_TIMEOUT(4),

    AWAIT_DATA(5),

    EXHAUST(6),

    PARTIAL(7);
    
    private final int value;
}
