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
package com.alipay.remoting.rpc.protocol;

import com.alipay.remoting.ProtocolManager;

/**
 * Protocol manager.
 *
 * @author tsui
 * @version $Id: RpcProtocols.java, v 0.1 2018-03-27 19:42 tsui Exp $
 */
public class RpcProtocolManager {
    public static final int DEFAULT_PROTOCOL_CODE_LENGTH = 1;

    public static void initProtocols() {
        ProtocolManager.registerProtocol(new RpcProtocol(), RpcProtocol.PROTOCOL_CODE);
        ProtocolManager.registerProtocol(new RpcProtocolV2(), RpcProtocolV2.PROTOCOL_CODE);
    }
    public static void cleanProtocols() {
        ProtocolManager.unRegisterProtocol(RpcProtocol.PROTOCOL_CODE);
        ProtocolManager.unRegisterProtocol(RpcProtocolV2.PROTOCOL_CODE);
    }
}