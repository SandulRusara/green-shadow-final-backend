package lk.ijse.demo.controller;

import lk.ijse.demo.dto.impl.VehicleDTO;
import lk.ijse.demo.exception.DataPersistException;
import lk.ijse.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
@CrossOrigin
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        System.out.println(vehicleDTO);
        try{
            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles(){
        return vehicleService.getAllVehicle();
    }

    @PutMapping(value = "/{vehicleId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@PathVariable ("vehicleId") String vehicleId ,@RequestBody VehicleDTO vehicleDTO){
        try{
            vehicleService.updateVehicle(vehicleId,vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
