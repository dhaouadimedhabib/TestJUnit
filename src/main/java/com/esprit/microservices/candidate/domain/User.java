package com.esprit.microservices.candidate.domain;

import io.micrometer.core.lang.Nullable;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private int cin;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private int phone;
    private String address;
    @Nullable
    private String profilepicture;
    @Nullable
    private String companyname;




    public int getUserid() {
        return userid;
    }

    public int getCin() {
        return cin;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Nullable
    public String getProfilepicture() {
        return profilepicture;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProfilepicture(@Nullable String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public void setCompanyname(@Nullable String companyname) {
        this.companyname = companyname;
    }

    public void setLastLoggedIn(LocalDateTime lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    @Nullable
    public String getCompanyname() {
        return companyname;
    }

    public LocalDateTime lastLoggedIn;



    @OneToMany(mappedBy = "planUser")
    @JsonIgnore
    private Set<ContractPlan> planUser;















}