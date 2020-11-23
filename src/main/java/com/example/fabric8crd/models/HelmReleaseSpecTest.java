package com.example.fabric8crd.models;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import lombok.Data;

@JsonDeserialize(
        using = JsonDeserializer.None.class
)

@Data
public class HelmReleaseSpecTest implements KubernetesResource {

    private Chart chart;
    private Object values;
    private boolean wait = true;
    private int timeout = 300;
    private boolean resetValues = false;
    private boolean forceUpgrade = false;
    private String targetNamespace;
    private String releaseName;
    private int maxHistory = 10;
    private boolean skipCRDs = false;

    @Data
    public static class Chart {
        private String repository;
        private String version;
        private String name;
    }
}
