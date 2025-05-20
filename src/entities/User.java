package entities;

import enums.Role;

import java.util.Random;

public class User {
    private String id;
    private String name;
    private String email;
    private String phone_no;
    private Role role;

    public User(String name, String email, String phone_no) {
        this.name = name;
        this.email = email;
        this.phone_no = phone_no;
    }

    public User() {
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPhone_no() {return phone_no;}
    public Role getRole() {return role;}

    public void setId() {
        Random rand = new Random();
        String part_of_name = name.substring(0, 5);
        this.id =  part_of_name + rand.nextInt(500);
    }
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPhone_no(String phone_no) {this.phone_no = phone_no;}
    public void setRole(Role role) {this.role = role;}
}
