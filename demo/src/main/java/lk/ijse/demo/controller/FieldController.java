package lk.ijse.demo.controller;
import lk.ijse.demo.dto.impl.FieldDTO;
import lk.ijse.demo.exception.DataPersistException;
import lk.ijse.demo.service.FieldService;
import lk.ijse.demo.util.IdGenerate;
import lk.ijse.demo.util.IdListConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
@CrossOrigin
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestPart("name") String fieldName,
            @RequestPart("location") String location,
            @RequestPart("extentSize") String extentSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2,
//            @RequestPart("staffList") List<String> staffList,
//            @RequestPart("cropList") List<String> cropList
             @RequestPart("staffList") String staffList,
            @RequestPart("cropList") String cropList
    ) {
        try {
            List<String> staffCode = new ArrayList<>();
            if (staffList!=null){
                staffCode= IdListConverter.spiltLists(staffList);
            }
            List<String> cropCode = new ArrayList<>();
            if (cropList!=null){
                cropCode=IdListConverter.spiltLists(cropList);
            }

            var fieldDTO = new FieldDTO();
            fieldDTO.setName(fieldName);
            fieldDTO.setLocation(location);
            fieldDTO.setExtentSize(Double.parseDouble(extentSize));
            fieldDTO.setFieldImage1(IdGenerate.imageBase64(fieldImage1.getBytes()));
            fieldDTO.setFieldImage2(IdGenerate.imageBase64(fieldImage2.getBytes()));
//            fieldDTO.setCropCodeList(staffList);
//            fieldDTO.setCropCodeList(cropList);
            fieldDTO.setCropCodeList(staffCode);
            fieldDTO.setCropCodeList(cropCode);
            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
