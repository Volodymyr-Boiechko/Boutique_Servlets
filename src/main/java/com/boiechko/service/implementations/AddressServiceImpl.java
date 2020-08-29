package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.AddressDaoImpl;
import com.boiechko.dao.interfaces.AddressDao;
import com.boiechko.entity.Address;
import com.boiechko.service.interfaces.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Address> getAddressesOfUser(final int userID) { return addressDao.getAddressesOfUser(userID); }

    @Override
    public boolean addAddress(final Address address) { return addressDao.add(address); }

    @Override
    public Address getAddressById(final int id) { return addressDao.getById(id); }

    @Override
    public boolean updateAddress(final Address address) { return addressDao.update(address); }

    @Override
    public boolean deleteAddress(final int id) { return addressDao.delete(id); }
}
