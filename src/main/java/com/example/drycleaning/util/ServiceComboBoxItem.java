package com.example.drycleaning.util;

import com.example.drycleaning.model.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceComboBoxItem {
    private String serviceName;
    private ServiceEntity service;

    public String toString() {
        return serviceName;
    }
}
