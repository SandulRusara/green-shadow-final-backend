package lk.ijse.demo.dto.impl;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lk.ijse.demo.dto.FieldStatus;
import lk.ijse.demo.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldStatus {
    @Id
    private String fieldCode;
    private String name;
    private String location;
    private double extentSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<String> equipmentsList;
    private List<String> memberCodeList;
    private List<String> logList;
    private List<String> cropCodeList;
}
