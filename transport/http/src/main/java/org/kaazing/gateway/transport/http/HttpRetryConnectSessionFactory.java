/**
 * Copyright 2007-2016, Kaazing Corporation. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaazing.gateway.transport.http;

import org.apache.mina.core.session.IoSession;
import org.kaazing.mina.core.session.IoSessionEx;

/**
 * Session factory used when HttpConnector is following a redirect
 *
 */
class HttpRetryConnectSessionFactory implements HttpConnectSessionFactory {

    private final DefaultHttpSession httpSession;

    public HttpRetryConnectSessionFactory(DefaultHttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public DefaultHttpSession get(IoSession parent) throws Exception {
        parent.setAttribute(HttpConnector.HTTP_SESSION_KEY, httpSession);
        httpSession.setParent((IoSessionEx) parent);
        HttpConnectProcessor processor = (HttpConnectProcessor) httpSession.getProcessor();
        processor.finishConnect(httpSession);
        return httpSession;
    }

}