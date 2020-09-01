package com.boiechko.service.interfaces;

import com.boiechko.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddressesOfPerson(final int userID);

    boolean addAddress(final Address address);

    Address getAddressById(final int idAddress);

    boolean updateAddress(final Address address);

    boolean deleteAddress(final int idAddress);

    List<Boolean> isPersonCanDeleteAddress(final List<Address> addresses);

}
