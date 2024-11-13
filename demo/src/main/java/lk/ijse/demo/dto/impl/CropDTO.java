package lk.ijse.demo.dto.impl;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lk.ijse.demo.dto.CropStatus;
import lk.ijse.demo.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperDTO, CropStatus {
    @Id
    private String cropCode;
    private String cropName;
    private String scientificName;
    private String category;
    private String season;
    private String cropImage;
    @JsonIgnore
    private List<LogDTO> logList;
    @JsonIgnore
    private List<FieldDTO> fieldList;
}
