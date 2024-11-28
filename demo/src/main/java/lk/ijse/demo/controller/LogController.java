package lk.ijse.demo.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.ijse.demo.dto.LogStatus;
import lk.ijse.demo.dto.impl.LogDTO;
import lk.ijse.demo.exception.DataPersistException;
import lk.ijse.demo.service.LogService;
import lk.ijse.demo.util.IdListConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/logs")
public class LogController {
    @Autowired
    private LogService logService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({"MANAGER","SCIENTIST"})
    public ResponseEntity<Void> saveLog(
            @RequestParam("date") String date,
            @RequestParam("logDetails") String logDetails,
            @RequestParam("observedImage") String observedImage,
            @RequestParam("staffList") String staffList,
            @RequestParam("cropList") String cropList,
            @RequestParam("fieldList") String fieldList
    ){
        try {
            List<String> staffListt = new ArrayList<>();
            if (staffListt != null){
                staffListt = IdListConverter.spiltLists(staffList);
            }
            List<String> cropListt = new ArrayList<>();
            if (cropListt != null){
                cropListt = IdListConverter.spiltLists(cropList);
            }
            List<String> fieldListt = new ArrayList<>();
            if (fieldListt != null){
                fieldListt = IdListConverter.spiltLists(fieldList);
            }
            LogDTO logDTO = new LogDTO();
            logDTO.setDate(date);
            logDTO.setLogDetails(logDetails);
            logDTO.setObservedImage(observedImage);
            logDTO.setStaffList(staffListt);
            logDTO.setCropList(cropListt);
            logDTO.setFieldList(fieldListt);
            logService.saveLog(logDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{logId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({"MANAGER","SCIENTIST"})
    public LogStatus getSelectedLog(@PathVariable("logId") String logId){
        return null;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({"MANAGER","ADMINISTRATIVE","SCIENTIST"})
    public List<LogDTO> getAllLog(){
        return logService.getAllLog();
    }

    @DeleteMapping(value = "/{logId}")
    @RolesAllowed({"MANAGER","SCIENTIST"})
    public ResponseEntity<Void> deleteLog(@PathVariable ("logId") String logId){

        return null;
    }

    @PutMapping(value = "/{logId}")
    @RolesAllowed({"MANAGER","SCIENTIST"})
    public void updateLog(@PathVariable("logId") String logId) throws IOException {

    }
}
