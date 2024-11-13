package lk.ijse.demo.dto;

import com.example.demo.dto.StaffStatus;
import com.example.demo.dto.SuperDTO;
import com.example.demo.entity.Gender;
import com.example.demo.entity.Role;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO , StaffStatus {
    @Id
    private String memberCode;
    private String firstName;
    private String lastName;
    private LocalDate joinedDate;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String designation;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private Role role;
    private List<StaffEquipmentDetailsDTO> staffEquipmentDetailsList;
    private List<VehicleDTO> vehicleList;
    private List<FieldDTO> fieldList;
    private List<LogDTO> logList;
}
