package lk.ijse.demo.entity.impl;


import jakarta.persistence.*;
import lk.ijse.demo.entity.EquipmentStatus;
import lk.ijse.demo.entity.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    @Id
    private String equipmentCode;
    private String Name;
    private String type;
    private String status;
    private int availableCount;
    @OneToMany(mappedBy = "equipmentEntity",cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<StaffEquipmentDetailsEntity> staffEquipmentDetailsList;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "equipment_field_details",
            joinColumns = @JoinColumn(name = "equipmentCode"),
            inverseJoinColumns = @JoinColumn(name = "fieldCode")
    )
    private List<FieldEntity> fieldList;
}
