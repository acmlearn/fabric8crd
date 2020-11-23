package com.example.fabric8crd.models;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import lombok.Data;

@Data
public class HelmReleaseTest extends CustomResource implements Namespaced {
    private String apiVersion;
    private String kind;
    private ObjectMeta metadata;
    private HelmReleaseSpecTest spec;
    private HelmReleaseStatusTest status;
}
