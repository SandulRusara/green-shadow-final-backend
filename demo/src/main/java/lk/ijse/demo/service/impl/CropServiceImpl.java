package lk.ijse.demo.service.impl;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lk.ijse.demo.dto.impl.CropDTO;
import lk.ijse.demo.service.CropService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Override
    public void saveCrop(CropDTO cropDTO) {

    }
}
