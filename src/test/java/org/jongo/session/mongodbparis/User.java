package org.jongo.session.mongodbparis;

public class User {

    private String username;
    private Address address;
    private int age;

    public User(String username, int age, Address address) {
        this.address = address;
        this.age = age;
        this.username = username;
    }

    public User() {
    }

    public Address getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
