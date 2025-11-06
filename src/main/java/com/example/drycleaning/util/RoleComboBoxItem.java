package com.example.drycleaning.util;

import com.example.drycleaning.model.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleComboBoxItem {
    private String roleName;
    private Role role;

    public String toString() {
        return roleName;
    }
}
