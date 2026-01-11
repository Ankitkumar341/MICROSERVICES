package com.address.model.dto;

import com.address.model.enums.AddressType;

public class AddressDto {
    private  Long id ;
    private  Long empId ;
    private  String street ;
    private  String pinCode  ;
    private  String city ;
    private  String country ;

    private AddressType addressType ;

    public AddressDto() {
    }

    public AddressDto(AddressType addressType, String country, String city, String pinCode, String street, Long empId, Long id) {
        this.addressType = addressType;
        this.country = country;
        this.city = city;
        this.pinCode = pinCode;
        this.street = street;
        this.empId = empId;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", empId=" + empId +
                ", street='" + street + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", addressType=" + addressType +
                '}';
    }

}
