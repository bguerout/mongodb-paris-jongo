package org.jongo.session.mongodbparis;

public class Address {

    private String way;
    private String city;
    private String country;

    public Address(String way, String city, String country) {
        this.way = way;
        this.city = city;
        this.country = country;
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getWay() {
        return way;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setWay(String way) {
        this.way = way;
    }
}
