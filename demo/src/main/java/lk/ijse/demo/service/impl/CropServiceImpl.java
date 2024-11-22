package lk.ijse.demo.service.impl;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lk.ijse.demo.dao.CropDAO;
import lk.ijse.demo.dao.FieldDAO;
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
    @Override
    public void saveCrop(CropDTO cropDTO) {
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
//        for (String logCode:cropDTO.getLogCodeList()){
//            if (logDAO.existsById(logCode)){
//                logEntities.add(logDAO.getReferenceById(logCode));
//            }
//        }
        cropEntity.setFieldList(fieldEntities);
        cropEntity.setLogList(logEntities);
        for (FieldEntity fieldEntity : fieldEntities){
            fieldEntity.getCropList().add(cropEntity);
        }
//        for (LogEntity logEntity:logEntities){
//            logEntity.getCropList().add(cropEntity);
//        }
        cropDAO.save(cropEntity);
        if (cropEntity == null){
            throw new DataPersistException("Crop is not saved.");
        }
    }
}
