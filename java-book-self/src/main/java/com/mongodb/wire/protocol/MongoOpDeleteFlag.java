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
public enum MongoOpDeleteFlag {

    SINGLE_REMOVE(0);
    
    private final int value;
}
