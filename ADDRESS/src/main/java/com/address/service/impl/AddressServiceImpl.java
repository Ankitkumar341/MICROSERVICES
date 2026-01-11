package com.address.service.impl;

import com.address.model.dto.AddressDto;
import com.address.model.dto.AddressRequest;
import com.address.model.dto.AddressRequestDto;
import com.address.model.entity.Address;
import com.address.repository.AddressRepository;
import com.address.service.AddressService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    private final AddressRepository addressRepository ;
    private final ModelMapper modelMapper ;

    Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AddressDto> saveAddress(AddressRequest addressRequest) {
//  check if Employee

        List<Address> addToList= this.saveAndUpdateAddressRequest(addressRequest);
        List<Address> savedAddress  = addressRepository.saveAll(addToList);

        return   savedAddress.stream().map(Address -> modelMapper.map(Address , AddressDto.class)).toList();
    }
    @Override
    public List<AddressDto> updateAddress(AddressRequest addressRequest) {
        
             List<Address> addressByEmpId  = addressRepository.findAllByEmpId(addressRequest.getEmpId());

             if(addressByEmpId.isEmpty()){
                logger.info("Address Is Not Found For this Employee   :{} " , addressByEmpId);
                logger.info(" Create Address  For  this Employee  : {}" , addressByEmpId);
             }

        List<Address> addToList= this.saveAndUpdateAddressRequest(addressRequest);






        return null;
    }

    @Override
    public AddressDto getSingleAddress(Long empId) {
        return null;
    }

    @Override
    public List<AddressDto> getAllAddress() {
        return List.of();
    }

    @Override
    public void deleteAddress(Long empId) {

    }
    public List<Address> saveAndUpdateAddressRequest(AddressRequest addressRequest){


        List<Address> addToList = new ArrayList<>() ;
        for(AddressRequestDto addressRequestDto :addressRequest.getAddressRequestDtoList()){

            Address newAddress =  new Address();
            newAddress.setStreet(addressRequestDto.getStreet());
            newAddress.setPinCode(addressRequestDto.getPinCode());
            newAddress.setCity(addressRequestDto.getCity());
            newAddress.setCountry(addressRequestDto.getCountry());
            newAddress.setAddressType(addressRequestDto.getAddressType());
            newAddress.setEmpId(addressRequest.getEmpId());

            addToList.add(newAddress);
        }
        return addToList ;

    }
}
