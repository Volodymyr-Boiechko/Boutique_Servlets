package com.boiechko.service.interfaces;

import com.boiechko.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddressesOfUser(final int userID);

    boolean addAddress(final Address address);

    Address getAddressById(final int id);

    boolean updateAddress(final Address address);

    boolean deleteAddress(final int id);

}
