package com.internshipmanagementsystem.student.mapper;

import com.internshipmanagementsystem.student.dto.AddressRequest;
import com.internshipmanagementsystem.student.dto.AddressResponse;
import com.internshipmanagementsystem.student.model.Address;

public class AddressMapper {

    public static Address toEntity(AddressRequest request) {

        if (request == null) {
            return null;
        }

        return Address.builder()
                .street(request.getStreet())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .pincode(request.getPincode())
                .build();
    }

    public static AddressResponse toResponse(Address address) {

        if (address == null) {
            return null;
        }

        return AddressResponse.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .pincode(address.getPincode())
                .build();
    }
}