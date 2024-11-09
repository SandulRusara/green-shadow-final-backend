package lk.ijse.demo.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "monitoringLogServices")
public class MonitoringLogEntity {
    @Id
    private String logCode;

    private Date logDate;
    private String logDetails;

    @Lob
    private String observedImage;

    @OneToMany(mappedBy = "fieldCode")
    private List<FieldEntity> field;

    @OneToMany(mappedBy = "cropCode")
    private List<CropEntity> crop;

    @OneToMany(mappedBy = "staffId")
    private List<StaffEntity> staff;
}
