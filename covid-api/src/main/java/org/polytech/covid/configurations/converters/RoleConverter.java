package org.polytech.covid.configurations.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.polytech.covid.entities.Role;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        switch(role) {
            case Administrator:
                return "Administrator";
            case SuperAdministrator:
                return "SuperAdministrator";
            case Doctor:
                return "Doctor";
            default:
                return "";
        }
    }

    @Override
    public Role convertToEntityAttribute(String role) {
        try {
            return Role.valueOf(role);
        } catch (Exception e){
            return null;
        }
    }
}