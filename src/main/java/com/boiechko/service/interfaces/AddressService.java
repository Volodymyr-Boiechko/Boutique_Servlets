package com.boiechko.service.interfaces;

import com.boiechko.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddressesOfUser(int userID);

    boolean addAddress(Address address);

    Address getAddressById(int id);

    boolean updateAddress(Address address);

    boolean deleteAddress(int id);

}
