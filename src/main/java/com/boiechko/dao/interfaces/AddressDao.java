package com.boiechko.dao.interfaces;

import com.boiechko.entity.Address;

import java.util.List;

public interface AddressDao extends Dao<Address> {

    List<Address> getAddressesOfPerson(final int idPerson);

}
