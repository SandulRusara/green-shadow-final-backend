package lk.ijse.demo.entity.impl;


import jakarta.persistence.*;
import lk.ijse.demo.entity.Gender;
import lk.ijse.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity {
    @Id
    private String staffId;

    private String firstName;
    private String lastName;
    private String designation;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Date joinedDate;
    private Date dob;

    @Column(name = "building_no")
    private String addressLine01;

    @Column(name = "Lane")
    private String addressLine02;

    @Column(name = "city")
    private String addressLine03;

    @Column(name = "main_state")
    private String addressLine04;

    @Column(name = "postal_code")
    private String addressLine05;

    private String contactNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "fieldCode")
    private List<FieldEntity> field;

    @OneToMany(mappedBy = "vehicleCode")
    private List<VehicleEntity> vehicle;
}
