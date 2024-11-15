package lk.ijse.demo.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.demo.dao.FieldDAO;
import lk.ijse.demo.dto.impl.FieldDTO;
import lk.ijse.demo.service.FieldService;
import lk.ijse.demo.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
        @Autowired
        private FieldDAO fieldDAO;

        @Autowired
        private Mapping mapping;


    @Override
    public void saveField(FieldDTO fieldDTO) {

    }
}
