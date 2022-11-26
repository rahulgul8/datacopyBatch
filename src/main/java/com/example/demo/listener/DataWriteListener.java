package com.example.demo.listener;

import com.example.demo.writer.dto.WriteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

@Slf4j
public class DataWriteListener implements ItemWriteListener<WriteDTO> {


    @Override
    public void beforeWrite(List<? extends WriteDTO> items) {
        log.info("beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends WriteDTO> items) {
        log.info("afterWrite:" + items);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends WriteDTO> items) {
        log.info("onWriteError");
    }
}
