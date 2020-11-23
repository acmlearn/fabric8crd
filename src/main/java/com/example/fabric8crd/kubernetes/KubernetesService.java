package com.example.fabric8crd.kubernetes;

import com.example.fabric8crd.models.HelmReleaseListTest;
import com.example.fabric8crd.models.HelmReleaseTest;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import io.fabric8.kubernetes.client.dsl.base.OperationContext;
import io.fabric8.kubernetes.client.informers.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KubernetesService implements InitializingBean {

    private KubernetesClient client;
    private CustomResourceDefinitionContext helmReleaseContext;
    private static final String GROUP="helm.fluxcd.io";
    private static final String VERSION="v1";
    private static final String PLURAL="helmreleases";
    private static final String KIND="HelmRelease";

    @Override
    public void afterPropertiesSet() {
        client = new DefaultKubernetesClient();
        helmReleaseContext = new CustomResourceDefinitionContext
                .Builder()
                .withGroup(GROUP)
                .withScope("Namespaced")
                .withVersion(VERSION)
                .withPlural(PLURAL)
                .build();
        monitorHelmReleaseStatus();
    }


       public void monitorHelmReleaseStatus(){
        SharedInformerFactory sharedInformerFactory = client.informers();
        SharedIndexInformer<HelmReleaseTest> helmReleaseTestInformer = sharedInformerFactory.sharedIndexInformerForCustomResource(
                   helmReleaseContext,
                   HelmReleaseTest.class,
                   HelmReleaseListTest.class,
                   new OperationContext().withNamespace("test"),
                   60*1000L);
           log.info("Informer factory initialized");

        helmReleaseTestInformer.addEventHandler(
                new ResourceEventHandler<HelmReleaseTest>() {
                    @Override
                    public void onAdd(HelmReleaseTest helmReleaseTest) {
                        log.info(helmReleaseTest.getMetadata().getName() + " hr added");
                    }

                    @Override
                    public void onUpdate(HelmReleaseTest helmReleaseTest, HelmReleaseTest t1) {
                        log.info(helmReleaseTest.getMetadata().getName() + " hr updated");
                    }

                    @Override
                    public void onDelete(HelmReleaseTest helmReleaseTest, boolean b) {
                        log.info(helmReleaseTest.getMetadata().getName() + " hr deleted");
                    }
                }
        );

        sharedInformerFactory.addSharedInformerEventListener(new SharedInformerEventListener() {
            @Override
            public void onException(Exception exception) {
                log.info("Exception occurred, but caught" + exception.getLocalizedMessage());
            }
        });

        log.info("Starting all registered informers");
        sharedInformerFactory.startAllRegisteredInformers();

    }
}
