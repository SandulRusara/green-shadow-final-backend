package lk.ijse.demo.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.demo.dao.FieldDAO;
import lk.ijse.demo.dto.impl.FieldDTO;
import lk.ijse.demo.entity.impl.FieldEntity;
import lk.ijse.demo.exception.DataPersistException;
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
        int number = 0;
        FieldEntity field = fieldDAO.findLastRowNative();
        if (field != null){
            String[] parts = field.getFieldCode().split("-");
            number = Integer.parseInt(parts[1]);
        }
        fieldDTO.setFieldCode("FIELD-" + ++number);

        FieldEntity fieldEntity = mapping.toFieldEntity(fieldDTO);
        fieldEntity.setLocation(fieldDTO.getLocation());
        FieldEntity saveField = fieldDAO.save(fieldEntity);
        if (saveField == null){
            throw new DataPersistException("Field is not saved.");
        }
    }
}
