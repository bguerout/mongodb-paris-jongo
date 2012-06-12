package org.jongo.session.mongodbparis;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Address {

    private String way;
    private String city;
    private String country;

    @JsonCreator
    public Address(@JsonProperty("way") String way, @JsonProperty("city") String city, @JsonProperty("country") String country) {
        this.way = way;
        this.city = city;
        this.country = country;
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

    @Override
    public String toString() {
        return "Address{" +
                "way='" + way + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
