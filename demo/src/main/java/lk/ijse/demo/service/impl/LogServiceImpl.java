package lk.ijse.demo.service.impl;


import lk.ijse.demo.dao.CropDAO;
import lk.ijse.demo.dao.FieldDAO;
import lk.ijse.demo.dao.LogDAO;
import lk.ijse.demo.dao.StaffDAO;
import lk.ijse.demo.dto.LogStatus;
import lk.ijse.demo.dto.impl.LogDTO;
import lk.ijse.demo.entity.impl.CropEntity;
import lk.ijse.demo.entity.impl.FieldEntity;
import lk.ijse.demo.entity.impl.LogEntity;
import lk.ijse.demo.entity.impl.StaffEntity;
import lk.ijse.demo.exception.CropNotFoundException;
import lk.ijse.demo.exception.DataPersistException;
import lk.ijse.demo.service.LogService;
import lk.ijse.demo.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDAO logDAO;
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveLog(LogDTO logDTO) {
        int number = 0;
        LogEntity log = logDAO.findLastRowNative();
        if (log != null){
            String[] parts = log.getLogCode().split("-");
            number = Integer.parseInt(parts[1]);
        }
        logDTO.setLogCode("LOG-" + ++number);
        List<StaffEntity> staffEntities = new ArrayList<>();
        List<FieldEntity> fieldEntities = new ArrayList<>();
        List<CropEntity> cropEntities = new ArrayList<>();
        for (String cropCode :logDTO.getCropList()){
            if (cropDAO.existsById(cropCode)){
                cropEntities.add(cropDAO.getReferenceById(cropCode));
            }
        }
        for (String fieldCode:logDTO.getFieldList()){
            if (fieldDAO.existsById(fieldCode)){
                fieldEntities.add(fieldDAO.getReferenceById(fieldCode));
            }
        }
        for (String staffCode:logDTO.getStaffList()){
            if (staffDAO.existsById(staffCode)){
                staffEntities.add(staffDAO.getReferenceById(staffCode));
            }
        }
        LogEntity logEntity = mapping.toLogEntity(logDTO);
        logEntity.setCropList(cropEntities);
        logEntity.setStaffList(staffEntities);
        logEntity.setFieldList(fieldEntities);
        for (FieldEntity fieldEntity:fieldEntities){
            fieldEntity.getLogList().add(logEntity);
        }
        LogEntity saveLog = logDAO.save(logEntity);
        if (saveLog==null){
            throw new DataPersistException("Field Is Not Saved.");
        }
    }

    @Override
    public List<LogDTO> getAllLog() {
        List<LogDTO> logDTOS = new ArrayList<>();
        for (LogEntity logEntity:logDAO.findAll()){
            List<String> fieldCode = new ArrayList<>();
            List<String> cropCode = new ArrayList<>();
            List<String> staffCode = new ArrayList<>();
            for (FieldEntity fieldEntity:logEntity.getFieldList()){
                fieldCode.add(fieldEntity.getFieldCode());
            }
            for (CropEntity cropEntity:logEntity.getCropList()){
                cropCode.add(cropEntity.getCropCode());
            }
            for (StaffEntity staffEntity:logEntity.getStaffList()){
                staffCode.add(staffEntity.getMemberCode());
            }
            LogDTO logDTO = mapping.toLogGetAll(logEntity);
            logDTO.setFieldList(fieldCode);
            logDTO.setCropList(cropCode);
            logDTO.setStaffList(staffCode);
            logDTOS.add(logDTO);
        }
        return logDTOS;
    }

    @Override
    public void deleteLog(String id) {
        if (logDAO.existsById(id)){
            LogEntity logEntity = logDAO.getReferenceById(id);
            List<FieldEntity> fieldList = logEntity.getFieldList();
            List<CropEntity> cropList = logEntity.getCropList();
            List<StaffEntity> staffList = logEntity.getStaffList();
            for (FieldEntity fieldEntity : fieldList){
                List<LogEntity> logEntities = fieldEntity.getLogList();
                logEntities.remove(logEntity);
            }
            for (CropEntity cropEntity : cropList){
                List<LogEntity> logEntities = cropEntity.getLogList();
                logEntities.remove(logEntity);
            }
            for (StaffEntity staffEntity : staffList){
                List<LogEntity> logEntities = staffEntity.getLogList();
                logEntities.remove(logEntity);
            }
            logEntity.getFieldList().clear();
            logEntity.getStaffList().clear();
            logEntity.getCropList().clear();
            logDAO.delete(logEntity);
        }else {
            throw new CropNotFoundException("Id " + id + "Not Found");
        }
    }

    @Override
    public void updateLog(String id, LogDTO logDTO) {
        Optional<LogEntity> tmpLog = logDAO.findById(id);
        if (tmpLog.isPresent()){
            tmpLog.get().setDate(logDTO.getDate());
            tmpLog.get().setLogDetails(logDTO.getDate());
            tmpLog.get().setObservedImage(logDTO.getObservedImage());

            List<StaffEntity> staffList = tmpLog.get().getStaffList();
            List<CropEntity> cropList= tmpLog.get().getCropList();
            List<FieldEntity> fieldList = tmpLog.get().getFieldList();

           LogEntity referenceById = logDAO.getReferenceById(logDTO.getLogCode());

            for (StaffEntity staff: staffList) {
               List<LogEntity> logList = staff.getLogList();
               logList.remove(referenceById);
            }
            for (CropEntity cropEntity: cropList) {
                List<LogEntity> logList= cropEntity.getLogList();
                logList.remove(referenceById);
            }
            for (FieldEntity fieldEntity:fieldList) {
                List<LogEntity> logList = fieldEntity.getLogList();
                logList.remove(referenceById);

            }
            tmpLog.get().getStaffList();
            tmpLog.get().getCropList();
            tmpLog.get().getFieldList();

            List<StaffEntity> staffEntities = new ArrayList<>();
            List<FieldEntity> fieldEntities = new ArrayList<>();
            List<CropEntity> cropEntities = new ArrayList<>();

            for (String staffId:logDTO.getStaffList() ) {
                if (staffDAO.existsById(staffId)){
                    staffEntities.add(staffDAO.getReferenceById(staffId));
                }
            }
            for (String fieldId:logDTO.getFieldList()) {
                if (fieldDAO.existsById(fieldId)){
                    fieldEntities.add(fieldDAO.getReferenceById(fieldId));
                }
            }
            for (String cropID: logDTO.getCropList()) {
                if (cropDAO.existsById(cropID)){
                    cropEntities.add(cropDAO.getReferenceById(cropID));
                }
            }
            tmpLog.get().getStaffList().addAll(staffEntities);
            tmpLog.get().getFieldList().addAll(fieldEntities);
            tmpLog.get().getCropList().addAll(cropEntities);

//            for (String staffCode :logDTO.getStaffList()){
//                staffEntities.add(staffDAO.getReferenceById(staffCode));
//            }
//            for (String cropCode :logDTO.getCropList()){
//                cropEntities.add(cropDAO.getReferenceById(cropCode));
//            }
//            for (String fieldCode :logDTO.getFieldList()){
//                fieldEntities.add(fieldDAO.getReferenceById(fieldCode));
//            }
//            tmpLog.get().setStaffList(staffEntities);
//            tmpLog.get().setCropList(cropEntities);
//            tmpLog.get().setFieldList(fieldEntities);
        }
    }

    @Override
    public LogStatus getSelectedLog(String logId) {
        return null;
    }
}
