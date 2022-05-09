package com.kamiroo.todomanager;

import com.kamiroo.todomanager.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private ToDoService toDoService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void scheduleTaskWithCroneExpression() {
        StopWatch sw = new StopWatch();
        sw.start();
        toDoService.deleteTodoWhereStatusClosed();
        sw.stop();
        logger.info("Tasks deleted successfully: " + toDoService.deleteTodoWhereStatusClosed());
        logger.info("Cron Task :: Executution time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        logger.info("It took " + sw.getTotalTimeSeconds() + " seconds to complete");
    }
}
