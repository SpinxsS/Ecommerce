package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Address;
import com.ecommerce.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
    	// Obtener la fecha y hora actual y restar 5 horas
	    LocalDateTime now = LocalDateTime.now();
	    
	    // Establecer las fechas de creación y actualización
	    address.setCreatedAt(now);
	    address.setUpdatedAt(now);
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id));
    }

    public Address updateAddress(Integer id, Address addressDetails) {
        Address address = getAddressById(id);
        address.setStreet(addressDetails.getStreet());
        address.setCountry(addressDetails.getCountry());
        address.setZipCode(addressDetails.getZipCode());
        address.setState(addressDetails.getState());
        address.setCity(addressDetails.getCity());
        address.setUpdatedAt(LocalDateTime.now());
        return addressRepository.save(address);
    }

    public void deleteAddress(Integer id) {
        Address address = getAddressById(id);
        addressRepository.delete(address);
    }
}
