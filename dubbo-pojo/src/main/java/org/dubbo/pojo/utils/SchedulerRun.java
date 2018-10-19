package org.dubbo.pojo.utils;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangbin on 2018/6/6.
 */
public class SchedulerRun {

    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();


    /**
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务
     * @param cron             时间设置，参考quartz说明文档
     * @param map:定时任务参数
     * @Description: 添加一个定时任务
     */

    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cron, Map<String,Object> map) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            jobDetail.getJobDataMap().put("parms", map);
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param cron             时间设置，参考quartz说明文档
     * @param map:定时任务参数
     * @Description: 编辑一个定时任务
     */

    public static void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron,  Map<String,Object> map) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
            Class<? extends Job> jobClass = jobDetail.getJobClass();
            removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
            addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @Description: 删除一个定时任务
     */

    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            sched.pauseTrigger(triggerKey);
            sched.unscheduleJob(triggerKey);
            sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    /**
     * 启动所有定时任务
     */

    public static void startJobs() {
        try{
            Scheduler sched = schedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    /**
     * 关闭所有定时任务
     */

    public static void shutdownJobs(){
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 关闭定时任务
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名
     */

    public static void ShutDownJob(String jobName, String jobGroupName){
        try{
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.pauseJob(jobKey);
            System.out.println("关闭定时任务：" + jobName);
        } catch (SchedulerException e){
            e.printStackTrace();
        }

    }

    /**
     * 开启一个定时任务
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名
     */

    public static void StartJob(String jobName, String jobGroupName) {
        try{
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.resumeJob(jobKey);
            System.out.println("开启定时任务：" + jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取定时任务中存在的任务
     *
     * @return
     */

    public static String GetAllExcutingJobs() {
        String jobslist = "定时任务中存在的JobGroups:";
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            List<String> jobgroups = scheduler.getJobGroupNames();
            System.out.println("定时任务中存在的Jobs:" + jobgroups.size());
            for (String jbgroup : jobgroups) {
                jobslist = jobslist + "JobGroup:" + jbgroup;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobslist;

    }


    /**
     * 获取定时任务的状态
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @return
     */

    public static String GetJobStatus(String triggerName, String triggerGroupName)

    {
        String jobstatus = "定时任务" + triggerName + "的状态是：";
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            Trigger.TriggerState state = scheduler.getTriggerState(triggerKey);
            jobstatus = jobstatus + state;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobstatus;
    }

    public static void main(String[] args)  {


    }

}






