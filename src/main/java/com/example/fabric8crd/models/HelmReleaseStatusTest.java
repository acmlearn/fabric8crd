package com.example.fabric8crd.models;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import lombok.Data;

@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@Data
public class HelmReleaseStatusTest implements KubernetesResource {
    String phase;
    String releaseStatus;
    Object conditions;
    String lastAttemptedRevision;
    String observedGeneration;
    String releaseName;
    String revision;

}
