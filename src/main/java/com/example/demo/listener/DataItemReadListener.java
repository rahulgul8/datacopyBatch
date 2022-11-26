package com.example.demo.listener;


import com.example.demo.read.dto.ReadDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

@Slf4j
public class DataItemReadListener implements ItemReadListener<ReadDTO> {

    @Override
    public void beforeRead() {
        log.info("Before reading an item");
    }

    @Override
    public void afterRead(ReadDTO item) {
        log.info("After reading an item: " + item.toString());
    }

    @Override
    public void onReadError(Exception ex) {
        log.error("Error occurred while reading an item!");
    }
}
