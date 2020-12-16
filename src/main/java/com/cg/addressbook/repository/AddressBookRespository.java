package com.cg.addressbook.repository;

import com.cg.addressbook.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRespository extends JpaRepository<Address, Long> {
}
