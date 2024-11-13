package lk.ijse.demo.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.ijse.demo.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldCode;
    private String name;
    private Point location;
    private double extentSize;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;
    @JsonIgnore  // Ignore during serialization to avoid recursion
    @ManyToMany(mappedBy = "fieldList")
    private List<EquipmentEntity> equipmentsList;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "field_staff_details",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "memberCode")
    )
    private List<StaffEntity> staffList;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "field_log_details",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "logCode")
    )
    private List<LogEntity> logList;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "field_crop_details",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "cropCode")
    )
    private List<CropEntity> cropList;
}
