package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import com.ecommerce.project.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthUtil authUtil;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        Address address = modelMapper.map(addressDTO, Address.class);
        address.setUser(user);
        List<Address> addressesList = user.getAddresses();
        addressesList.add(address);
        user.setAddresses(addressesList);
        Address savedAddress = addressRepo.save(address);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addresses = addressRepo.findAll();
        return addresses.stream().map(address -> modelMapper
                .map(address, AddressDTO.class)).toList();
    }

    @Override
    public AddressDTO getAddressesById(Long addressId) {
        Address address = addressRepo.findById(addressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Address", "id", addressId));
        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddresses(User user) {
        List<Address> addresses = user.getAddresses();
        return addresses.stream().map(address -> modelMapper
                .map(address, AddressDTO.class)).toList();
    }

    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address addressFromDatabase = addressRepo.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address", "id", addressId));

        addressFromDatabase.setStreet(addressDTO.getStreet());
        addressFromDatabase.setCity(addressDTO.getCity());
        addressFromDatabase.setBuildingName(addressDTO.getBuildingName());
        addressFromDatabase.setState(addressDTO.getState());
        addressFromDatabase.setCountry(addressDTO.getCountry());
        addressFromDatabase.setPincode(addressDTO.getPincode());

        Address updatedAddress = addressRepo.save(addressFromDatabase);

        User user=addressFromDatabase.getUser();

        user.getAddresses()
                .removeIf(address -> address.getAddressId()
                        .equals(addressId));
        user.getAddresses().add(addressFromDatabase);
        userRepo.save(user);
        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address addressFromDatabase = addressRepo.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address", "id", addressId));

        User user=addressFromDatabase.getUser();
        user.getAddresses()
                .removeIf(address -> address.getAddressId()
                        .equals(addressId));
        userRepo.save(user);

        addressRepo.delete(addressFromDatabase);

        return "Address deleted successfully with id: " + addressId;
    }

}
