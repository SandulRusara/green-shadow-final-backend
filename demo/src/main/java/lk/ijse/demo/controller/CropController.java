package lk.ijse.demo.controller;

import lk.ijse.demo.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/crops")
@CrossOrigin
public class CropController {
     @Autowired
    private CropService cropService;
}
