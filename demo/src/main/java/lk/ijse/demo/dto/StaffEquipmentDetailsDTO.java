package lk.ijse.demo.dto;

import com.example.demo.dto.StaffEquipmentDetailsStatus;
import com.example.demo.dto.SuperDTO;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffEquipmentDetailsDTO implements SuperDTO , StaffEquipmentDetailsStatus {
    @Id
    private String id;
    private int useEquipmentCount;
    private StaffDTO staffEntity;
    private EquipmentDTO equipmentEntity;
}
