package lk.ijse.demo.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.demo.customerStatusCode.SelectedErrorStatus;
import lk.ijse.demo.dao.CropDAO;
import lk.ijse.demo.dao.FieldDAO;
import lk.ijse.demo.dao.StaffDAO;
import lk.ijse.demo.dto.FieldStatus;
import lk.ijse.demo.dto.impl.FieldDTO;
import lk.ijse.demo.entity.impl.*;
import lk.ijse.demo.exception.DataPersistException;
import lk.ijse.demo.exception.FieldNotFoundException;
import lk.ijse.demo.service.FieldService;
import lk.ijse.demo.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private CropDAO cropDAO;


    @Override
    public void saveField(FieldDTO fieldDTO) {
//        int number = 0;
//        FieldEntity field = fieldDAO.findLastRowNative();
//        if (field != null) {
//            String[] parts = field.getFieldCode().split("-");
//            number = Integer.parseInt(parts[1]);
//        }
//        fieldDTO.setFieldCode("FIELD-" + ++number);
//        List<StaffEntity> staffEntities = new ArrayList<>();
//        List<CropEntity> cropEntities = new ArrayList<>();
//        if (fieldDTO.getMemberCodeList() != null || fieldDTO.getCropCodeList() != null) {
//            for (String id : fieldDTO.getMemberCodeList()) {
//                if (staffDAO.existsById(id)) {
//                    staffEntities.add(staffDAO.getReferenceById(id));
//                }
//            }
//            for (String id : fieldDTO.getCropCodeList()) {
//                if (cropDAO.existsById(id)) {
//                    cropEntities.add(cropDAO.getReferenceById(id));
//                }
//            }
//        }
//        FieldEntity fieldEntity = mapping.toFieldEntity(fieldDTO);
//        fieldEntity.setLocation(fieldDTO.getLocation());
//        fieldEntity.setStaffList(staffEntities);
//        fieldEntity.setCropList(cropEntities);
//        FieldEntity saveField = fieldDAO.save(fieldEntity);
//        if (saveField == null) {
//            throw new DataPersistException("Field is not saved.");
//        }
//    }
        int number = 0;
        FieldEntity field = fieldDAO.findLastRowNative();
        if (field != null) {
            String[] parts = field.getFieldCode().split("-");
            number = Integer.parseInt(parts[1]);
        }
        fieldDTO.setFieldCode("FIELD-" + ++number);

        // Initialize staffEntities and cropEntities lists
        List<StaffEntity> staffEntities = new ArrayList<>();
        List<CropEntity> cropEntities = new ArrayList<>();

        // Check if MemberCodeList and CropCodeList are not null before iterating
        if (fieldDTO.getMemberCodeList() != null) {
            for (String id : fieldDTO.getMemberCodeList()) {
                if (staffDAO.existsById(id)) {
                    staffEntities.add(staffDAO.getReferenceById(id));
                }
            }
        }

        if (fieldDTO.getCropCodeList() != null) {
            for (String id : fieldDTO.getCropCodeList()) {
                if (cropDAO.existsById(id)) {
                    cropEntities.add(cropDAO.getReferenceById(id));
                }
            }
        }

        // Map FieldDTO to FieldEntity
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldDTO);
        fieldEntity.setLocation(fieldDTO.getLocation());
        fieldEntity.setStaffList(staffEntities);
        fieldEntity.setCropList(cropEntities);

        // Save the entity and handle the result
        FieldEntity savedField = fieldDAO.save(fieldEntity);
        if (savedField == null) {
            throw new DataPersistException("Field is not saved.");
        }
    }


    @Override
    public void updateField(String id, FieldDTO fieldDTO) {
        Optional<FieldEntity> byId = fieldDAO.findById(id);
        if (byId.isPresent()){
            byId.get().setName(fieldDTO.getName());
            byId.get().setLocation(fieldDTO.getLocation());
            byId.get().setExtentSize(fieldDTO.getExtentSize());
            byId.get().setFieldImage1(fieldDTO.getFieldImage1());
            byId.get().setFieldImage2(fieldDTO.getFieldImage2());
            List<CropEntity> cropEntities = new ArrayList<>();
            List<StaffEntity> staffEntities = new ArrayList<>();
            for (String cropCode:fieldDTO.getCropCodeList()){
                cropEntities.add(cropDAO.getReferenceById(cropCode));
            }
            for (String memberCode:fieldDTO.getMemberCodeList()){
                staffEntities.add(staffDAO.getReferenceById(memberCode));
            }
            byId.get().setCropList(cropEntities);
            byId.get().setStaffList(staffEntities);
        }
    }

    @Override
    public void deleteField(String id) throws FileNotFoundException, FieldNotFoundException {
        Optional<FieldEntity> selectedField = fieldDAO.findById(id);
        if (fieldDAO.existsById(id)){
            FieldEntity fieldEntity = fieldDAO.getReferenceById(id);
            List<EquipmentEntity> equipmentEntities = fieldEntity.getEquipmentsList();
            for (EquipmentEntity equipmentEntity:equipmentEntities){
                List<FieldEntity> fields = equipmentEntity.getFieldList();
                fields.remove(fieldEntity);
            }
            fieldEntity.getEquipmentsList().clear();
        }
        if (!selectedField.isPresent()){
            throw new FieldNotFoundException(" id " + id + "not found");
        }else {
            fieldDAO.deleteById(id);
        }
    }

    @Override
    public List<FieldDTO> getAllField() throws IOException, ClassNotFoundException {
        List<FieldDTO> fieldDTOS = new ArrayList<>();
        for (FieldEntity fieldEntity : fieldDAO.findAll()){
            List<String> staffCode = new ArrayList<>();
            List<String> logCode = new ArrayList<>();
            for (StaffEntity staffEntity : fieldEntity.getStaffList()){
                staffCode.add(staffEntity.getMemberCode());
            }
            for (LogEntity logEntity :fieldEntity.getLogList()){
                logCode.add(logEntity.getLogCode());
            }
            FieldDTO fieldDTO = mapping.toGetAllFieldDTO(fieldEntity);
            fieldDTO.setMemberCodeList(staffCode);
            fieldDTO.setLogCodeList(logCode);
            fieldDTOS.add(fieldDTO);
        }
        return fieldDTOS;
    }


    @Override
    public FieldStatus getSelectedField(String fieldId) {
        if (fieldDAO.existsById(fieldId)){
            return mapping.toFieldDTO(fieldDAO.getReferenceById(fieldId));
        }else {
            return new SelectedErrorStatus(2,"Field with Code "+fieldId+" not found");
        }
    }

}
