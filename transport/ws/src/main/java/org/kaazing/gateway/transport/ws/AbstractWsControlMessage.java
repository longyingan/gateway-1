/**
 * Copyright (c) 2007-2014 Kaazing Corporation. All rights reserved.
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.kaazing.gateway.transport.ws;

import static org.kaazing.mina.core.buffer.SimpleBufferAllocator.BUFFER_ALLOCATOR;

import org.kaazing.mina.core.buffer.IoBufferEx;

public abstract class AbstractWsControlMessage extends WsMessage {
    public static enum Style { CLIENT, SYSTEM } 

    private static final IoBufferEx EMPTY_BUFFER = BUFFER_ALLOCATOR.wrap(BUFFER_ALLOCATOR.allocate(0));
    
    private Style style = Style.SYSTEM;
    
    public AbstractWsControlMessage() {
        setBytes(EMPTY_BUFFER);
    }

    public AbstractWsControlMessage(IoBufferEx buf) {
		setBytes(buf);
	}
	
	public Style getStyle() {
	    return style;
	}
	
	public void setStyle(Style style) {
	    this.style = style;
	}
	
	public String toString() {
	    return String.format("%s (style %s)", super.toString(), style);
	}

}