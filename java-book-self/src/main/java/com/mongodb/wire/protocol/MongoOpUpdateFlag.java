package com.mongodb.wire.protocol;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author dongzonglei
 * @description @see <a href="https://docs.mongodb.com/manual/reference/mongodb-wire-protocol/#client-request-messages">Client Request Messages</a>
 * @date 2019-10-20 10:28
 */
@RequiredArgsConstructor
@Getter
public enum MongoOpUpdateFlag {

    UPSERT(0),

    MULTI_UPDATE(1);

    private final int value;
}
