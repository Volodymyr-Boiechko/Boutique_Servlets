package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.AddressDaoImpl;
import com.boiechko.dao.interfaces.AddressDao;
import com.boiechko.entity.Address;
import com.boiechko.service.interfaces.AddressService;
import com.boiechko.service.interfaces.OrderService;

import java.util.ArrayList;
import java.util.List;

public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao = new AddressDaoImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    public List<Address> getAllAddressesOfPerson(final int userID) { return addressDao.getAddressesOfPerson(userID); }

    @Override
    public boolean addAddress(final Address address) { return addressDao.add(address); }

    @Override
    public Address getAddressById(final int idAddress) { return addressDao.getById(idAddress); }

    @Override
    public boolean updateAddress(final Address address) { return addressDao.update(address); }

    @Override
    public boolean deleteAddress(final int idAddress) { return addressDao.delete(idAddress); }

    //todo поміняти can на is
    @Override
    public List<Boolean> isPersonCanDeleteAddress(final List<Address> addresses) {

        List<Boolean> list = new ArrayList<>();
        for (Address address : addresses)
            list.add(orderService.isAddressHasOrder(address.getIdAddress()));

        return list;

    }
}
