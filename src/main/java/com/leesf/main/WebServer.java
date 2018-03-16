package com.leesf.main;

import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServer {
  public static final String CONTEXT = "/";
  private static final Logger LOG = LoggerFactory.getLogger(WebServer.class);
  private static final String DEFAULT_WEBAPP_PATH = "webapps/";
  private Server server;
  private int port;

  public WebServer() {
  }

  public Server createServerInSource() throws UnknownHostException {
    port = 8081;
    server = new Server();
    server.setStopAtShutdown(true);
    SelectChannelConnector connector = new SelectChannelConnector();
    connector.setPort(port);
    connector.setReuseAddress(false);

    connector.setAcceptQueueSize(50);
    connector.setAcceptors(2);
    connector.setThreadPool(
        new ExecutorThreadPool(20,
            40, 0, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(
                16, false)));
    connector.setLowResourcesMaxIdleTime(3000);

    connector.setReuseAddress(true);
    connector.setRequestBufferSize(
        16 * 1024);
    connector.setRequestHeaderSize(
        8 * 1024);

    server.setConnectors(new Connector[] { connector });

    String basePath = "src/main/webapps";
    if (StringUtils.isEmpty(basePath)) {
      basePath = DEFAULT_WEBAPP_PATH;
    }
    WebAppContext webContext = new WebAppContext(basePath, CONTEXT);
    webContext.setContextPath(CONTEXT);
    webContext.setDescriptor(basePath + "/WEB-INF/web.xml");
    System.out.println("-------------web.xml path is " + webContext.getDescriptor()
        + "--------------");
    webContext.setResourceBase(basePath);
    webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
    server.setHandler(webContext);
    return server;
  }

  public void start() throws Exception {
    if (server == null) {
      createServerInSource();
    }
    if (server != null) {
      server.start();
      LOG.info("WebServer has started at port:" + port);
    }
  }

  public void stop() throws Exception {
    if (server != null) {
      server.stop();
    }
  }

  public static void main(String[] args) throws Exception {
    WebServer webServer = new WebServer();
    webServer.start();
  }
}
