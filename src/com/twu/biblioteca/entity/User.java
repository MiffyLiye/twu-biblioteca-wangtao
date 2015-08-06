package com.twu.biblioteca.entity;

public class User implements Comparable<User>, IHaveID {
    private Integer id;
    private String name;
    private String library_number;
    private String password;
    private String email;
    private String phone;

    public User() {}

    public User(Integer id, String name, String library_number, String password, String email, String phone) {
        this.id = id;
        this.name = name;
        this.library_number = library_number;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public int compareTo(User user) {
        return getId() - user.getId();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibrary_number() {
        return library_number;
    }

    public void setLibrary_number(String library_number) {
        this.library_number = library_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
