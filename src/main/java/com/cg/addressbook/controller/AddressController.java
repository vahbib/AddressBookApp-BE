package com.cg.addressbook.controller;

import com.cg.addressbook.exception.ResourceNotFoundException;
import com.cg.addressbook.model.Address;
import com.cg.addressbook.repository.AddressBookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressController {

    @Autowired
    public AddressBookRespository addressBookRespository;
    private ResponseEntity<Address> ok;

    //get all addresses
    @GetMapping("/addresses")
    public List<Address> getAllAddresses(){
        return addressBookRespository.findAll();
    }

    //get address by id
    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getAddressByID(@PathVariable Long id){
        Address address=addressBookRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Address doesn't exist for"+id));
        return ResponseEntity.ok(address);
    }

    //create new address
    @PostMapping("/addresses/create")
    public Address createAddress(@RequestBody Address address){
        return addressBookRespository.save(address);
    }

    //update existing address
    @PutMapping("/addresses/update/{id}")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address_details, @PathVariable Long id){
        Address address=addressBookRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Address doesn't exist for"+id));
        address.setFull_name(address_details.getFull_name());
        address.setAddress(address_details.getAddress());
        address.setState(address_details.getState());
        address.setCity(address_details.getCity());
        address.setZip(address_details.getZip());
        address.setPhone_no(address_details.getPhone_no());

        Address updatedAddress=addressBookRespository.save(address);

        return ResponseEntity.ok(updatedAddress);
    }

    //delete existing address
    @DeleteMapping("/addresses/delete/{id}")
    public void deleteAddress(@PathVariable Long id){
        addressBookRespository.deleteById(id);
    }
}
