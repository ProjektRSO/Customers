package si.fri.rso2021.Customers.models.v1.converters;

import si.fri.rso2021.Customers.lib.v1.Customers;
import si.fri.rso2021.Customers.models.v1.entities.CustomersEntitiy;

public class CustomersConverter {

    public static Customers toDto(CustomersEntitiy entity) {

        Customers dto = new Customers();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAddress(entity.getAddress());
        dto.setBirthDate(entity.getBirthDate());

        return dto;

    }

    public static CustomersEntitiy toEntity(Customers dto) {

        CustomersEntitiy entity = new CustomersEntitiy();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setBirthDate(dto.getBirthDate());

        return entity;

    }

}
