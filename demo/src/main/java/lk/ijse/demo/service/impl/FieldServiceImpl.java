package lk.ijse.demo.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.demo.dto.FieldDTO;
import lk.ijse.demo.service.FieldService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Override
    public void saveField(FieldDTO fieldDTO) {

    }
}
