package com.example.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
public class DataChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        log.info("beforeChunk");
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        log.info("afterChunk");
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        log.error("afterChunkError");
    }
}