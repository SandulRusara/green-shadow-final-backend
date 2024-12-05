package lk.ijse.demo.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.ijse.demo.customerStatusCode.SelectedErrorStatus;
import lk.ijse.demo.dto.StaffStatus;
import lk.ijse.demo.dto.impl.StaffDTO;
import lk.ijse.demo.exception.DataPersistException;
import lk.ijse.demo.exception.StaffNotFoundException;
import lk.ijse.demo.service.StaffService;
import lk.ijse.demo.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService staffService;
    @PreAuthorize("hasAnyRole('MANAGER','ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO){
        try{
            if (!Regex.emailValidator(staffDTO.getEmail()).matches()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.saveStaffMember(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // melkata damnna one SCIENTIST mewa danna get all witharai
    @PreAuthorize("hasAnyRole('MANAGER','ADMINISTRATIVE')")
    @PutMapping(value = "/{staffId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaff(@PathVariable ("staffId") String staffId ,@RequestBody StaffDTO staffDTO){
        try{
            staffService.updateStaffMember(staffId,staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('MANAGER','ADMINISTRATIVE')")
    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable ("staffId") String staffId){
        try{
            if (!Regex.idValidator(staffId).matches()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.deleteStaffMember(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('MANAGER','ADMINISTRATIVE','SCIENTIST')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaff(){

        return staffService.getAllStaffMember();
    }
    @GetMapping(value = "/{staffId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus getSelectedStaff(@PathVariable("staffId") String staffId){
        if (!Regex.idValidator(staffId).matches()){
            return new SelectedErrorStatus(1,"Staff Code Not Valid");
        }
        return staffService.getSelectedStaffMember(staffId);
    }
}
