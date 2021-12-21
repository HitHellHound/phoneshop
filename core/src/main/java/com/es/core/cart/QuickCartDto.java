package com.es.core.cart;

import java.util.List;

public class QuickCartDto {
    private List<String> models;
    private List<String> quantities;

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }

    public List<String> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<String> quantities) {
        this.quantities = quantities;
    }
}
