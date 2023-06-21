package com.cefetransport.dto;

import org.springframework.beans.BeanUtils;

import com.cefetransport.enums.Status;
import com.cefetransport.enums.Tipo;
import com.cefetransport.model.Modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalDto {
    
    private Tipo tipo;

    private String registro;

    private Status status;

    public ModalDto(Modal modal) {
        
        BeanUtils.copyProperties(modal, this);

    }

}