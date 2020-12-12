/*
 * Copyright 20120 Dromara.org.
 *
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

package com.msgpack;

/**
 * MessagePackSerializer.
 *
 * @author dongzl
 */
@SuppressWarnings("unchecked")
public class MessagePackSerializer {
    
//    public byte[] serialize(final Object obj) {
//        byte[] result;
//        try {
//            MessagePack msgpack = org.dromara.hmily.serializer.msgpack.MessagePackFactory.getInstance();
//            result = msgpack.write(obj);
//        } catch (IOException e) {
//            throw new HmilySerializerException("MessagePack serialize error " + e.getMessage());
//        }
//        return result;
//    }
//    
//    @Override
//    public <T> T deSerialize(final byte[] param, final Class<T> clazz) throws HmilySerializerException {
//        T obj;
//        try {
//            MessagePack msgpack = org.dromara.hmily.serializer.msgpack.MessagePackFactory.getInstance();
//            obj = msgpack.read(param, clazz);
//        } catch (IOException e) {
//            throw new HmilySerializerException("MessagePack deSerialize error " + e.getMessage());
//        }
//        return obj;
//    }
}
