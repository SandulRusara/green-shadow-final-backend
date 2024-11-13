package lk.ijse.demo.dto.impl;

import jakarta.persistence.Id;
import lk.ijse.demo.dto.SuperDTO;
import lk.ijse.demo.dto.VehicleStatus;
import lk.ijse.demo.dto.impl.StaffDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO, VehicleStatus {
    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String Name;
    private String category;
    private String fuelType;
    private String remark;
    private StaffDTO staff;
}
