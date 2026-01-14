package com.address.service.impl;

import com.address.exception.ResourceNotFoundException;
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
import java.util.Objects;

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

        List<Address> listToUpdate= this.saveAndUpdateAddressRequest(addressRequest);



    List<Long>  upComingNotNullIds =   listToUpdate.stream().map(Address ::getId ).filter(Objects:: nonNull).toList();

    List<Long> existingIds = addressByEmpId.stream().map(Address::getId).toList();

    List<Long> deleteIds = existingIds.stream().filter(id -> !upComingNotNullIds.contains(id)).toList();

        if(deleteIds.isEmpty()){
        addressRepository.deleteAllById(deleteIds);
    }

    List<Address> UpdatedAddress = addressRepository.saveAll(listToUpdate);

        return UpdatedAddress.stream().map(Address -> modelMapper.map(UpdatedAddress , AddressDto.class)).toList();
}

    @Override
    public AddressDto getSingleAddress(Long empId) {
        Address getSingleAddByEmpId = addressRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Address Not Found For this empId : " + empId));
        return  modelMapper.map(getSingleAddByEmpId, AddressDto.class);
    }

    @Override
    public List<AddressDto> getAllAddress() {
        List<Address> allAddresses = addressRepository.findAll();
        if (allAddresses.isEmpty()){
            throw new ResourceNotFoundException("NO Address Available");
        }
        return  allAddresses.stream().map(Address->modelMapper.map(Address, AddressDto.class )).toList();
    }

    @Override
    public void deleteAddress(Long empId) {
        Address deleteAdd = addressRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Emp ID has No Address ! Enter Correct ID : {}" + empId));
         addressRepository.delete(deleteAdd);
    }
    public List<Address> saveAndUpdateAddressRequest(AddressRequest addressRequest){


        List<Address> addToList = new ArrayList<>() ;
        for(AddressRequestDto addressRequestDto :addressRequest.getAddressRequestDtoList()){

            Address newAddress =  new Address();
            newAddress.setId(addressRequestDto.getId() != null ? addressRequestDto.getId() : null);
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
