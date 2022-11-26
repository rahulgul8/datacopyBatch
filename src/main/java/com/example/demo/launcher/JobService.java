package com.example.demo.launcher;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobOperator operator;


    public Long restartJob(Long id) throws Exception {
        return operator.restart(id);
    }

}
