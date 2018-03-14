/*
 *  Copyright (c) 2014-2018 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.kumuluz.ee.jaxws.cxf;

import com.kumuluz.ee.common.Component;
import com.kumuluz.ee.common.ServletServer;
import com.kumuluz.ee.common.config.EeConfig;
import com.kumuluz.ee.common.dependencies.EeComponentDef;
import com.kumuluz.ee.common.dependencies.EeComponentDependency;
import com.kumuluz.ee.common.dependencies.EeComponentType;
import com.kumuluz.ee.common.runtime.EeRuntime;
import com.kumuluz.ee.common.wrapper.KumuluzServerWrapper;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;


/**
 * @author gpor89
 * @since 3.0.0
 */
@EeComponentDef(name = "CXF", type = EeComponentType.JAX_WS)
@EeComponentDependency(value = EeComponentType.SERVLET)
public class JaxWsComponent implements Component {

    protected static final String JAX_WS_CTX_PATH = "kumuluzee.jax-ws.web-context";

    private static final Logger LOG = Logger.getLogger(JaxWsComponent.class.getSimpleName());

    @Override
    public void init(KumuluzServerWrapper server, EeConfig eeConfig) {

        LOG.info("Initiating CXF");

        // Check if CDI is present in the runtime
        Boolean cdiPresent = EeRuntime.getInstance().getEeComponents().stream().anyMatch(c -> c.getType().equals(EeComponentType.CDI));

        final ServletServer kumuluzServer = (ServletServer) server.getServer();

        final Optional<String> contextPath = ConfigurationUtil.getInstance().get(JAX_WS_CTX_PATH);

        String webContext = contextPath.isPresent() ? contextPath.get() : "";

        final Map<String, String> servletParams = new HashMap<>();
        servletParams.put(KumuluzCXFServlet.CDI_INIT_PARAM, cdiPresent.toString());
        servletParams.put(KumuluzCXFServlet.CONTEXT_ROOT, webContext);

        kumuluzServer.registerServlet(KumuluzCXFServlet.class, webContext + "/*", servletParams);
    }

    @Override
    public void load() {

        LOG.info("CXF Initialized");
    }
}
