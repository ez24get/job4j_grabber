package ru.job4j.quarz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    public static void main(String[] args) {
        try {
            AlertRabbit alertRabbit = new AlertRabbit();
            Properties properties = alertRabbit.readProperties();
            Connection connection = alertRabbit.initConnection(properties);
            alertRabbit.createTable(connection);
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connection", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(getInterval(properties))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    private Properties readProperties() {
        Properties properties = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("db/rabbit.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static int getInterval(Properties properties) {
        try {
            return Integer.parseInt(properties.getProperty("rabbit.interval"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private Connection initConnection(Properties properties) {
        Connection connection = null;
        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connected = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("username"), properties.getProperty("password"))) {
            connection = connected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void createTable(Connection connection) {
        try (PreparedStatement statement =
                     connection.prepareStatement("CREATE TABLE IF NOT EXISTS rabbit (id serial primary key, created TIMESTAMP);")) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) {
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            try (PreparedStatement ps =
                         connection.prepareStatement("INSERT INTO rabbit (created) VALUES (?)")) {
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

