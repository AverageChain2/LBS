package staffs.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.ToString;

@JsonSerialize
@ToString
@Getter
public class Address extends ValueObject {
    private String houseNameNumber;
    private String street;
    private String town;
    private String postalCode;

    //As I am using Address within the Command objects (rather than defining a separate DTO style object
    //I don't want to use a @NoArgs constructor here as it would by pass the setter validation
    //so I have to use the @JsonProperty to enable mapping from the command objects
    public Address(@JsonProperty("houseNameNumber") String houseNameNumber,
                   @JsonProperty("street") String street,
                   @JsonProperty("town") String town,
                   @JsonProperty("postalCode")String postalCode){
        setHouseNameNumber(houseNameNumber);
        setStreet(street);
        setTown(town);
        setPostCode(postalCode);
    }

    //Shallow copy
    public Address(Address address){
        this(address.houseNameNumber, address.street, address.town, address.postalCode);
    }

    public boolean equals(Object o){
        if (o == null && o.getClass() != this.getClass()){
            return false;
        }
        Address anotherAddress = (Address) o;

        return anotherAddress.houseNameNumber.equals(this.houseNameNumber) &&
               anotherAddress.street.equals(this.street) &&
               anotherAddress.town.equals(this.town) &&
               anotherAddress.postalCode.equals(this.postalCode);
    }

    private void setHouseNameNumber(String houseNameNumber){
        assertArgumentNotEmpty(houseNameNumber,"House name/number cannot be empty");
        this.houseNameNumber = houseNameNumber;
    }

    private void setStreet(String street){
        assertArgumentNotEmpty(street,"Street cannot be empty");
        this.street = street;
    }

    private void setTown(String town){
        assertArgumentNotEmpty(town,"Town cannot be empty");
        this.town = town;
    }

    private void setPostCode(String postalCode){
        assertArgumentNotEmpty(postalCode,"PostCode cannot be empty");
        this.postalCode = postalCode;
    }
    public String houseNameNumber(){
        return this.houseNameNumber;
    }

    public String street(){
        return this.street;
    }

    public String town(){
        return this.town;
    }

    public String postalCode(){ return this.postalCode; }
}
