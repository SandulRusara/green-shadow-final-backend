package lk.ijse.demo.controller;

import lk.ijse.demo.dto.impl.CropDTO;
import lk.ijse.demo.dto.impl.FieldDTO;
import lk.ijse.demo.exception.DataPersistException;
import lk.ijse.demo.service.CropService;
import lk.ijse.demo.util.IdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
@CrossOrigin
public class CropController {
     @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("cropName") String cropName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImage") MultipartFile cropImage,
            @RequestPart("fieldList") List<FieldDTO> fieldList
    ) {
        try{
            var cropDTO = new CropDTO();
            cropDTO.setCropName(cropName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);
            cropDTO.setCropImage(IdGenerate.imageBase64(cropImage.getBytes()));
            cropDTO.setFieldList(fieldList);
            cropService.saveCrop(cropDTO);
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
