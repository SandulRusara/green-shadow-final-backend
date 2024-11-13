package lk.ijse.demo.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class CropEntity {
    @Id
    private String cropCode;
    private String cropName;
    private String scientificName;
    private String category;
    private String season;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    @JsonIgnore// Ignore during serialization to avoid recursion
    @ManyToMany(mappedBy = "cropList")
    private List<LogEntity> logList;
    @JsonIgnore
    @ManyToMany(mappedBy = "cropList")
    private List<FieldEntity> fieldList;
}
