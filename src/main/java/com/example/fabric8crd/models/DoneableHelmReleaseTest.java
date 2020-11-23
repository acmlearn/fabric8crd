package com.example.fabric8crd.models;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

public class DoneableHelmReleaseTest extends CustomResourceDoneable<HelmReleaseTest> {
    public DoneableHelmReleaseTest(HelmReleaseTest resource, Function function) { super(resource, function); };
}
