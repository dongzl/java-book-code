/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.wire.protocol;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * OP Code for MongoDB
 * 
 * @see <a href="https://docs.mongodb.com/manual/reference/mongodb-wire-protocol">MongoDB Wire Protocol</a>
 * 
 * @author dongzonglei 
 */
@RequiredArgsConstructor
@Getter
public enum MongoOperationPacketType {

    /**
     * Reply to a client request. responseTo is set.
     */
    OP_REPLY(1),

    /**
     * Update document.
     */
    OP_UPDATE(2001),

    /**
     * Insert new document.
     */
    OP_INSERT(2002),

    /**
     * Formerly used for OP_GET_BY_OID.
     */
    RESERVED(2003),

    /**
     * Query a collection.
     */
    OP_QUERY(2004),

    /**
     * Get more data from a query. See Cursors.
     */
    OP_GET_MORE(2005),

    /**
     * Delete documents.
     */
    OP_DELETE(2006),

    /**
     * Notify database that the client has finished with the cursor.
     */
    OP_KILL_CURSORS(2007),

    /**
     * Send a message using the format introduced in MongoDB 3.6.
     */
    OP_MSG(2013);
    
    private static final Map<Integer, MongoOperationPacketType> MONGODB_OPERATION_CODE_CACHE = new HashMap<Integer, MongoOperationPacketType>() {
        {
            for (MongoOperationPacketType each : MongoOperationPacketType.values()) {
                this.put(each.value, each);
            }
        }
    };

    private final int value;

    /**
     * Value of integer.
     *
     * @param value integer value
     * @return MongoDB OP code enum
     */
    public static MongoOperationPacketType valueOf(final int value) {
        MongoOperationPacketType result = MONGODB_OPERATION_CODE_CACHE.get(value);
        if (null == result) {
            throw new IllegalArgumentException(String.format("Cannot find '%s' in MongoDB OP code", value));
        }
        return result;
    }
}
