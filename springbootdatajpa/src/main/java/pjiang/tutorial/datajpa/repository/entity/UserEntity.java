package pjiang.tutorial.datajpa.repository.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  12:38 2018/10/24.
 */
@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="dob")
    private Date dob;
    
    @Column(name = "gender")
    private Gender gender;
    
}
