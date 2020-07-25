package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.AddressDaoImpl;
import com.boiechko.dao.interfaces.AddressDao;
import com.boiechko.entity.Address;
import com.boiechko.service.interfaces.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Address> getAddressesOfUser(int userID) { return addressDao.getAddressesOfUser(userID); }

    @Override
    public boolean add(Address address) { return addressDao.add(address); }

    @Override
    public Address getById(int id) { return addressDao.getById(id); }

    @Override
    public List<Address> getAll() { return addressDao.getAll(); }

    @Override
    public boolean update(Address address) { return addressDao.update(address); }

    @Override
    public boolean delete(int id) { return addressDao.delete(id); }
}
