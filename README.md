# fabric8crd

Project created to report an issue.
1) create a namespace called test in the kubernetes cluster. 
2) Download the files and go to src > helm-release.yaml file location and run the command, kubectl apply -f manifests/helm-release.yaml. This will create a helmrelease object in the test namespace. Run the command, 'kubectl get hr -n test' to confirm for the resource test-1 helmrelease created.
3) run the application using mvn spring-boot:run -Dspring.profiles.active=local. The shared informer sucessfully recognizes the events and also syncs after a minute. Then the log is piled with the below exception for every second.

2020-11-23 15:22:45.598  INFO 12192 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 9090 (http)
2020-11-23 15:22:45.607  INFO 12192 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-11-23 15:22:45.607  INFO 12192 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.39]
2020-11-23 15:22:45.697  INFO 12192 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2020-11-23 15:22:45.697  INFO 12192 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1115 ms
2020-11-23 15:22:46.329  INFO 12192 --- [           main] c.e.f.kubernetes.KubernetesService       : Informer factory initialized
2020-11-23 15:22:46.336  INFO 12192 --- [           main] c.e.f.kubernetes.KubernetesService       : Starting all registered informers
2020-11-23 15:22:46.339  INFO 12192 --- [HelmReleaseTest] i.f.k.client.informers.cache.Controller  : informer#Controller: ready to run resync and reflector runnable
2020-11-23 15:22:46.341  INFO 12192 --- [HelmReleaseTest] i.f.k.client.informers.cache.Reflector   : Started ReflectorRunnable watch for class com.example.fabric8crd.models.HelmReleaseTest
2020-11-23 15:22:46.426  INFO 12192 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-11-23 15:22:46.514  INFO 12192 --- [pool-1-thread-1] c.e.f.kubernetes.KubernetesService       : test-1 hr added
2020-11-23 15:22:46.514  INFO 12192 --- [pool-1-thread-1] c.e.f.kubernetes.KubernetesService       : test-1 hr updated
2020-11-23 15:22:46.542  INFO 12192 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9090 (http) with context path ''
2020-11-23 15:22:46.550  INFO 12192 --- [           main] c.e.fabric8crd.Fabric8crdApplication     : Started Fabric8crdApplication in 2.36 seconds (JVM running for 2.794)
2020-11-23 15:23:46.341  INFO 12192 --- [pool-1-thread-1] c.e.f.kubernetes.KubernetesService       : test-1 hr updated
2020-11-23 15:23:46.539  INFO 12192 --- [pool-1-thread-1] c.e.f.kubernetes.KubernetesService       : test-1 hr updated
2020-11-23 15:24:17.026  WARN 12192 --- [ternal:6443/...] i.f.k.c.d.i.WatchConnectionManager       : Exec Failure

io.fabric8.kubernetes.client.KubernetesClientException: An error has occurred.
        at io.fabric8.kubernetes.client.KubernetesClientException.launderThrowable(KubernetesClientException.java:64) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.KubernetesClientException.launderThrowable(KubernetesClientException.java:53) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:249) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:168) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:153) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.dsl.internal.WatchHTTPManager.readWatchEvent(WatchHTTPManager.java:292) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.dsl.internal.WatchConnectionManager$1.onMessage(WatchConnectionManager.java:228) ~[kubernetes-client-4.12.0.jar:na]
        at okhttp3.internal.ws.RealWebSocket.onReadMessage(RealWebSocket.java:322) ~[okhttp-3.14.9.jar:na]
        at okhttp3.internal.ws.WebSocketReader.readMessageFrame(WebSocketReader.java:219) ~[okhttp-3.14.9.jar:na]
        at okhttp3.internal.ws.WebSocketReader.processNextFrame(WebSocketReader.java:105) ~[okhttp-3.14.9.jar:na]
        at okhttp3.internal.ws.RealWebSocket.loopReader(RealWebSocket.java:273) ~[okhttp-3.14.9.jar:na]
        at okhttp3.internal.ws.RealWebSocket$1.onResponse(RealWebSocket.java:209) ~[okhttp-3.14.9.jar:na]
        at okhttp3.RealCall$AsyncCall.execute(RealCall.java:174) [okhttp-3.14.9.jar:na]
        at okhttp3.internal.NamedRunnable.run(NamedRunnable.java:32) [okhttp-3.14.9.jar:na]
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_241]
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_241]
        at java.lang.Thread.run(Thread.java:748) [na:1.8.0_241]
Caused by: com.fasterxml.jackson.databind.JsonMappingException: No resource type found for:helm.fluxcd.io/v1#HelmRelease
 at [Source: (BufferedInputStream); line: 1, column: 2973] (through reference chain: io.fabric8.kubernetes.api.model.WatchEvent["object"])
        at com.fasterxml.jackson.databind.JsonMappingException.from(JsonMappingException.java:274) ~[jackson-databind-2.11.3.jar:2.11.3]
        at io.fabric8.kubernetes.internal.KubernetesDeserializer.fromObjectNode(KubernetesDeserializer.java:85) ~[kubernetes-model-core-4.12.0.jar:4.12.0]
        at io.fabric8.kubernetes.internal.KubernetesDeserializer.deserialize(KubernetesDeserializer.java:57) ~[kubernetes-model-core-4.12.0.jar:4.12.0]
        at io.fabric8.kubernetes.internal.KubernetesDeserializer.deserialize(KubernetesDeserializer.java:46) ~[kubernetes-model-core-4.12.0.jar:4.12.0]
        at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:129) ~[jackson-databind-2.11.3.jar:2.11.3]
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:293) ~[jackson-databind-2.11.3.jar:2.11.3]
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:156) ~[jackson-databind-2.11.3.jar:2.11.3]
        at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4526) ~[jackson-databind-2.11.3.jar:2.11.3]
        at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3513) ~[jackson-databind-2.11.3.jar:2.11.3]
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:247) ~[kubernetes-client-4.12.0.jar:na]
        ... 14 common frames omitted

2020-11-23 15:24:18.037  WARN 12192 --- [ternal:6443/...] i.f.k.c.d.i.WatchConnectionManager       : Exec Failure

io.fabric8.kubernetes.client.KubernetesClientException: An error has occurred.
        at io.fabric8.kubernetes.client.KubernetesClientException.launderThrowable(KubernetesClientException.java:64) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.KubernetesClientException.launderThrowable(KubernetesClientException.java:53) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:249) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:168) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:153) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.dsl.internal.WatchHTTPManager.readWatchEvent(WatchHTTPManager.java:292) ~[kubernetes-client-4.12.0.jar:na]
        at io.fabric8.kubernetes.client.dsl.internal.WatchConnectionManager$1.onMessage(WatchConnectionManager.java:228) ~[kubernetes-client-4.12.0.jar:na]
        at okhttp3.internal.ws.RealWebSocket.onReadMessage(RealWebSocket.java:322) ~[okhttp-3.14.9.jar:na]
        at okhttp3.internal.ws.WebSocketReader.readMessageFrame(WebSocketReader.java:219) ~[okhttp-3.14.9.jar:na]
        at okhttp3.internal.ws.WebSocketReader.processNextFrame(WebSocketReader.java:105) ~[okhttp-3.14.9.jar:na]
        at okhttp3.internal.ws.RealWebSocket.loopReader(RealWebSocket.java:273) ~[okhttp-3.14.9.jar:na]
        at okhttp3.internal.ws.RealWebSocket$1.onResponse(RealWebSocket.java:209) ~[okhttp-3.14.9.jar:na]
        at okhttp3.RealCall$AsyncCall.execute(RealCall.java:174) [okhttp-3.14.9.jar:na]
        at okhttp3.internal.NamedRunnable.run(NamedRunnable.java:32) [okhttp-3.14.9.jar:na]
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_241]
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_241]
        at java.lang.Thread.run(Thread.java:748) [na:1.8.0_241]
Caused by: com.fasterxml.jackson.databind.JsonMappingException: No resource type found for:helm.fluxcd.io/v1#HelmRelease
 at [Source: (BufferedInputStream); line: 1, column: 2973] (through reference chain: io.fabric8.kubernetes.api.model.WatchEvent["object"])
        at com.fasterxml.jackson.databind.JsonMappingException.from(JsonMappingException.java:274) ~[jackson-databind-2.11.3.jar:2.11.3]
        at io.fabric8.kubernetes.internal.KubernetesDeserializer.fromObjectNode(KubernetesDeserializer.java:85) ~[kubernetes-model-core-4.12.0.jar:4.12.0]
        at io.fabric8.kubernetes.internal.KubernetesDeserializer.deserialize(KubernetesDeserializer.java:57) ~[kubernetes-model-core-4.12.0.jar:4.12.0]
        at io.fabric8.kubernetes.internal.KubernetesDeserializer.deserialize(KubernetesDeserializer.java:46) ~[kubernetes-model-core-4.12.0.jar:4.12.0]
        at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:129) ~[jackson-databind-2.11.3.jar:2.11.3]
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:293) ~[jackson-databind-2.11.3.jar:2.11.3]
        at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:156) ~[jackson-databind-2.11.3.jar:2.11.3]
        at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4526) ~[jackson-databind-2.11.3.jar:2.11.3]
        at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3513) ~[jackson-databind-2.11.3.jar:2.11.3]
        at io.fabric8.kubernetes.client.utils.Serialization.unmarshal(Serialization.java:247) ~[kubernetes-client-4.12.0.jar:na]
        ... 14 common frames omitted

2020-11-23 15:24:19.071  WARN 12192 --- [ternal:6443/...] i.f.k.c.d.i.WatchConnectionManager       : Exec Failure


