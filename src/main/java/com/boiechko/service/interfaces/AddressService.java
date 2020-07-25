package com.boiechko.service.interfaces;

import com.boiechko.dao.interfaces.Dao;
import com.boiechko.entity.Address;

import java.util.List;

public interface AddressService extends Dao<Address> {

    List<Address> getAddressesOfUser(int userID);

}
