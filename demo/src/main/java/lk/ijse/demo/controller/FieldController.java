package lk.ijse.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {
//    @Autowired
//    private FieldService fieldService;
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void>saveFeild(
//            @RequestPart("fieldName") String fieldName,
//            @RequestPart("fieldLocation") String fieldLocation,
//            @RequestPart("fieldSize") String fieldSize,
//            @RequestPart("cropId") String cropId,
//            @RequestPart("staffId") String staffId,
//            @RequestPart("fieldImg1") MultipartFile fieldImg1,
//            @RequestPart("fieldImg2") MultipartFile fieldImg2) {
//
//        try {
//            String fieldId = IdGenerate.generateFieldId();
//            String img1 = PotoEncode.generateProfilePicToBase64(fieldImg1);
//            String img2 = PotoEncode.generateProfilePicToBase64(fieldImg2);
//
//            FieldDTO fieldDTO = new FieldDTO();
//            fieldDTO.setFieldCode(fieldId);
//            fieldDTO.setFieldName(fieldName);
//            fieldDTO.setFieldLocation(fieldLocation);
//            fieldDTO.setExtentSizeOfTheField(fieldSize);
//            fieldDTO.setCrops(cropId);
//            fieldDTO.setStaff(staffId);
//            fieldDTO.setFieldImage1(img1);
//            fieldDTO.setFieldImage2(img2);
//
//            fieldService.saveField(fieldDTO);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } catch (DataPersistException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//    }
}
