package com.address.repository;

import com.address.model.dto.AddressDto;
import com.address.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface AddressRepository extends JpaRepository<Address, Long> {


    List<Address> findAllByEmpId(Long empId);
    

}
