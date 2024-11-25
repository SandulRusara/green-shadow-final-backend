package lk.ijse.demo.service.impl;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lk.ijse.demo.dao.CropDAO;
import lk.ijse.demo.dao.FieldDAO;
import lk.ijse.demo.dao.LogDAO;
import lk.ijse.demo.dto.CropStatus;
import lk.ijse.demo.dto.impl.CropDTO;
import lk.ijse.demo.entity.impl.CropEntity;
import lk.ijse.demo.entity.impl.FieldEntity;
import lk.ijse.demo.entity.impl.LogEntity;
import lk.ijse.demo.exception.DataPersistException;
import lk.ijse.demo.service.CropService;
import lk.ijse.demo.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private LogDAO logDAO;
    @Override
    public void saveCrop(CropDTO cropDTO) {
//        int number = 0;
//        CropEntity crop = cropDAO.findLastRowNative();
//        if (crop != null){
//            String[] parts = crop.getCropCode().split("-");
//            number = Integer.parseInt(parts[1]);
//        }
//        cropDTO.setCropCode("CROP-" + ++number);
//        CropEntity cropEntity = mapping.toCropEntity(cropDTO);
//        List<FieldEntity> fieldEntities = new ArrayList<>();
//        List<LogEntity> logEntities = new ArrayList<>();
//        for (String id:cropDTO.getFieldCodeList()){
//            if (fieldDAO.existsById(id)){
//                fieldEntities.add(fieldDAO.getReferenceById(id));
//            }
//        }
//
//
//        for (String logCode:cropDTO.getLogCodeList()){
//            if (logDAO.existsById(logCode)){
//                logEntities.add(logDAO.getReferenceById(logCode));
//            }
//        }
//        cropEntity.setFieldList(fieldEntities);
//        cropEntity.setLogList(logEntities);
//        for (FieldEntity fieldEntity : fieldEntities){
//            fieldEntity.getCropList().add(cropEntity);
//        }
//        for (LogEntity logEntity:logEntities){
//            logEntity.getCropList().add(cropEntity);
//        }
//        cropDAO.save(cropEntity);
//        if (cropEntity == null){
//            throw new DataPersistException("Crop is not saved.");
//        }

        int number = 0;
        CropEntity crop = cropDAO.findLastRowNative();
        if (crop != null){
            String[] parts = crop.getCropCode().split("-");
            number = Integer.parseInt(parts[1]);
        }
        cropDTO.setCropCode("CROP-" + ++number);
        CropEntity cropEntity = mapping.toCropEntity(cropDTO);
        List<FieldEntity> fieldEntities = new ArrayList<>();
        List<LogEntity> logEntities = new ArrayList<>();
        for (String fieldCode : cropDTO.getFieldCodeList()){
            if (fieldDAO.existsById(fieldCode)){
                fieldEntities.add(fieldDAO.getReferenceById(fieldCode));
            }
        }
        if (cropDTO.getLogCodeList()!=null) {
            for (String logCode : cropDTO.getLogCodeList()) {
                if (logDAO.existsById(logCode)) {
                    logEntities.add(logDAO.getReferenceById(logCode));
                }
            }
        }
        cropEntity.setFieldList(fieldEntities);
        cropEntity.setLogList(logEntities);
        for (FieldEntity fieldEntity : fieldEntities){
            fieldEntity.getCropList().add(cropEntity);
        }
        for (LogEntity logEntity:logEntities){
            logEntity.getCropList().add(cropEntity);
        }
        cropDAO.save(cropEntity);
        if (cropEntity == null){
            throw new DataPersistException("Crop is not saved.");
        }
    }

    @Override
    public List<CropDTO> getAllCrop() {
        List<CropDTO> cropDTOS = new ArrayList<>();
        for (CropEntity cropEntity:cropDAO.findAll()) {
            List<String> fieldCode = new ArrayList<>();
            List<String> logCodes = new ArrayList<>();
            for (FieldEntity field : cropEntity.getFieldList()){
                fieldCode.add(field.getFieldCode());
            }
            for (LogEntity logs : cropEntity.getLogList()){
                logCodes.add(logs.getLogCode());
            }
            CropDTO cropDTO = mapping.toCropDTO(cropEntity);
            cropDTO.setFieldCodeList(fieldCode);
            cropDTO.setLogCodeList(logCodes);
            cropDTOS.add(cropDTO);
        }
        return cropDTOS;
    }

    @Override
    public void deleteCrop(String id) {

    }

    @Override
    public void updateCrop(String id, CropDTO cropDTO) {

    }

    @Override
    public CropStatus getSelectedCrop(String cropId) {
        return null;
    }
}
