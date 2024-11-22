package lk.ijse.demo.util;

import lk.ijse.demo.dto.impl.CropDTO;
import lk.ijse.demo.dto.impl.FieldDTO;
import lk.ijse.demo.entity.impl.CropEntity;
import lk.ijse.demo.entity.impl.FieldEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Mapping {
    @Autowired
    ModelMapper modelMapper;
    public FieldEntity toFieldEntity(FieldDTO fieldDTO){
        return modelMapper.map(fieldDTO,FieldEntity.class);
    }

    public FieldDTO toFieldDTO(FieldEntity fieldEntity){
        return modelMapper.map(fieldEntity,FieldDTO.class);
    }

    public List<FieldDTO> fieldList(List<FieldEntity> fieldDTOS){
        return modelMapper.map(fieldDTOS,new TypeToken<List<FieldDTO>>(){}.getType());
    }




    public CropEntity toCropEntity(CropDTO cropDTO){
        return modelMapper.map(cropDTO,CropEntity.class);
    }

    public CropDTO toCropDTO(CropEntity cropEntity){
        return modelMapper.map(cropEntity,CropDTO.class);
    }

    public List<CropDTO> cropList(List<CropEntity> cropList){
        return modelMapper.map(cropList,new TypeToken<List<CropDTO>>(){}.getType());
    }

}
