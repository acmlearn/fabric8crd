package com.example.fabric8crd.models;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResourceList;
import lombok.Data;

import java.util.ArrayList;

@Data
public class HelmReleaseListTest extends CustomResourceList<HelmReleaseTest> implements Namespaced {
    ArrayList<HelmReleaseTest> items;
}
