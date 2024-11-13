package lk.ijse.demo.dto;

import com.example.demo.dto.SuperDTO;
import com.example.demo.dto.VehicleStatus;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO , VehicleStatus {
    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String Name;
    private String category;
    private String fuelType;
    private String remark;
    private StaffDTO staff;
}
