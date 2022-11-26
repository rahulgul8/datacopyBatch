package com.example.demo.convertor;

import com.example.demo.read.dto.ReadDTO;
import com.example.demo.writer.dto.WriteDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class DataConvertor implements ItemProcessor<ReadDTO, WriteDTO> {

    @Override
    public WriteDTO process(ReadDTO readDTO) throws Exception {
        WriteDTO write = new WriteDTO();
        BeanUtils.copyProperties(readDTO, write);
        return write;
    }
}
