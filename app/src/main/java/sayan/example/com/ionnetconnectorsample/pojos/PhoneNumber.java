package sayan.example.com.ionnetconnectorsample.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 24-08-2017.
 */

public class PhoneNumber {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("number")
    @Expose
    private String number;

    /**
     * No args constructor for use in serialization
     *
     */
    public PhoneNumber() {
    }

    /**
     *
     * @param number
     * @param type
     */
    public PhoneNumber(String type, String number) {
        super();
        this.type = type;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
