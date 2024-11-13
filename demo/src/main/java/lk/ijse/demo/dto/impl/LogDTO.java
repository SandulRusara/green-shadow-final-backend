package lk.ijse.demo.dto.impl;


import jakarta.persistence.Id;
import lk.ijse.demo.dto.LogStatus;
import lk.ijse.demo.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDTO implements SuperDTO, LogStatus {
    @Id
    private String logCode;
    private String date;
    private String logDetails;
    private String observedImage;
    private List<StaffDTO> staffList;
    private List<CropDTO> cropList;
    private List<FieldDTO> fieldList;
}
