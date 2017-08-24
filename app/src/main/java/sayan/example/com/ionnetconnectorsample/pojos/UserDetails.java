package sayan.example.com.ionnetconnectorsample.pojos;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 24-08-2017.
 */

public class UserDetails {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("address")
    @Expose
    private JsonObject address;
    @SerializedName("phoneNumbers")
    @Expose
    private JsonArray phoneNumbers = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserDetails() {
    }

    /**
     * @param firstName
     * @param lastName
     * @param gender
     * @param age
     * @param address
     * @param phoneNumbers
     */
    public UserDetails(String firstName, String lastName, String gender, Integer age, JsonObject address, JsonArray phoneNumbers) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public JsonObject getAddress() {
        return address;
    }

    public void setAddress(JsonObject address) {
        this.address = address;
    }

    public JsonArray getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(JsonArray phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

}