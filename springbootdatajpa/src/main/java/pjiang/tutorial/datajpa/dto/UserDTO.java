package pjiang.tutorial.datajpa.dto;

import lombok.Data;
import pjiang.tutorial.datajpa.repository.entity.Gender;

import java.util.Date;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  13:24 2018/10/24.
 */
@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dob;
    private Gender gender;
}
