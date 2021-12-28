package main.java.si.fri.rso2021.Customers.models.v1.converters;

import si.fri.rso2021.Customers.lib.v1.Customers;
import si.fri.rso2021.Customers.models.v1.entities.CustomersEntity;

public class CustomersConverter {

    public static Customers toDto(CustomersEntity entity) {

        Customers dto = new Customers();
        dto.setCustomerId(entity.getCustomerId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setStreetAddress(entity.getStreetAddress());
        dto.setPostcode(entity.getPostcode());
        dto.setBirthDate(entity.getBirthDate());

        return dto;

    }

    public static CustomersEntity toEntity(Customers dto) {

        CustomersEntity entity = new CustomersEntity();
        entity.setCustomerId(dto.getCustomerId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setStreetAddress(dto.getStreetAddress());
        entity.setPostcode(dto.getPostcode());
        entity.setBirthDate(dto.getBirthDate());

        return entity;

    }

}
