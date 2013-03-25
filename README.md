# civi Stripped

A simple application built following the friend-demo. 
It works locally, but throws an error when started on a remote machine:

    openid.clj:92  cemerick.friend.openid/handle-return
    openid.clj:118	cemerick.friend.openid/workflow[fn]
    friend.clj:179	cemerick.friend/authenticate*[fn]
    core.clj:2485	clojure.core/map[fn]
    LazySeq.java:42	clojure.lang.LazySeq.sval
    LazySeq.java:60	clojure.lang.LazySeq.seq
    RT.java:484	clojure.lang.RT.seq
    core.clj:133	clojure.core/seq
    core.clj:2523	clojure.core/filter[fn]
    LazySeq.java:42	clojure.lang.LazySeq.sval
    LazySeq.java:60	clojure.lang.LazySeq.seq
    LazySeq.java:82	clojure.lang.LazySeq.first
    RT.java:577	clojure.lang.RT.first
    core.clj:55	clojure.core/first
    friend.clj:179	cemerick.friend/authenticate*
    friend.clj:207	cemerick.friend/authenticate[fn]
    keyword_params.clj:27	ring.middleware.keyword-params/wrap-keyword-params[fn]
    nested_params.clj:65	ring.middleware.nested-params/wrap-nested-params[fn]
    params.clj:55	ring.middleware.params/wrap-params[fn]
    multipart_params.clj:103	ring.middleware.multipart-params/wrap-multipart-params[fn]
    flash.clj:14	ring.middleware.flash/wrap-flash[fn]
    session.clj:40	ring.middleware.session/wrap-session[fn]
    cookies.clj:160	ring.middleware.cookies/wrap-cookies[fn]
    Var.java:415	clojure.lang.Var.invoke
    reload.clj:18	ring.middleware.reload/wrap-reload[fn]
    stacktrace.clj:15	ring.middleware.stacktrace/wrap-stacktrace-log[fn]
    stacktrace.clj:79	ring.middleware.stacktrace/wrap-stacktrace-web[fn]
    jetty.clj:18	ring.adapter.jetty/proxy-handler[fn]
    (Unknown Source)	ring.adapter.jetty.proxy$org.eclipse.jetty.server.handler.AbstractHandler$0.handle
    HandlerWrapper.java:111	org.eclipse.jetty.server.handler.HandlerWrapper.handle
    Server.java:349	org.eclipse.jetty.server.Server.handle
    AbstractHttpConnection.java:452	org.eclipse.jetty.server.AbstractHttpConnection.handleRequest
    AbstractHttpConnection.java:884	org.eclipse.jetty.server.AbstractHttpConnection.headerComplete
    AbstractHttpConnection.java:938	org.eclipse.jetty.server.AbstractHttpConnection$RequestHandler.headerComplete
    HttpParser.java:634	org.eclipse.jetty.http.HttpParser.parseNext
    HttpParser.java:230	org.eclipse.jetty.http.HttpParser.parseAvailable
    AsyncHttpConnection.java:76	org.eclipse.jetty.server.AsyncHttpConnection.handle
    SelectChannelEndPoint.java:609	org.eclipse.jetty.io.nio.SelectChannelEndPoint.handle
    SelectChannelEndPoint.java:45	org.eclipse.jetty.io.nio.SelectChannelEndPoint$1.run
    QueuedThreadPool.java:599	org.eclipse.jetty.util.thread.QueuedThreadPool.runJob
    QueuedThreadPool.java:534	org.eclipse.jetty.util.thread.QueuedThreadPool$3.run
    Thread.java:679	java.lang.Thread.run
