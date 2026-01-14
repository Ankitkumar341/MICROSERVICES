package com.address.controller;


import com.address.model.dto.AddressDto;
import com.address.model.dto.AddressRequest;
import com.address.service.impl.AddressServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressServiceImpl addressService ;

    public AddressController(AddressServiceImpl addressService)
    {
        this.addressService = addressService;
    }

    @PostMapping("/save")
    public ResponseEntity<List<AddressDto>> SaveNewAddress(@RequestBody AddressRequest addressRequest){
        if(addressRequest.getEmpId()==null){
            throw new RuntimeException("please Provide EmpID");
        }
        List<AddressDto> response =  addressService.saveAddress(addressRequest);
       return  new ResponseEntity<>(response , HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public  ResponseEntity<List<AddressDto>> updateAddress( @RequestBody AddressRequest addressRequest ){

        List<AddressDto> response =  addressService.saveAddress(addressRequest);
        return  new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @GetMapping("/addressByID/{addressId}")
    public  ResponseEntity<AddressDto> getSingleAddress (@PathVariable Long addressId){
        AddressDto singleAddress = addressService.getSingleAddress(addressId);
        return new ResponseEntity<>(singleAddress,HttpStatus.FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AddressDto>> getAllAddress(){
        List<AddressDto> allAddress = addressService.getAllAddress();
        return new ResponseEntity<>(allAddress,HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity< String> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK);
    }

}
