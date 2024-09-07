package com.voufinal.gameservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class EventSchedulerService {
    @Autowired
    private TaskScheduler taskScheduler;

    public void scheduleJob(LocalDateTime eventDate, Runnable job) {
        Date date = Date.from(eventDate.atZone(ZoneId.systemDefault()).toInstant());
        taskScheduler.schedule(job, date);
    }
}
