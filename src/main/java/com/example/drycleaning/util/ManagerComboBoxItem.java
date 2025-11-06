package com.example.drycleaning.util;

import com.example.drycleaning.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerComboBoxItem {
    private Employee manager;
    private String fullname;

    public String toString() {
        return fullname;
    }
}
