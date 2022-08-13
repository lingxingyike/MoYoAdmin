package com.example.demo.common.utils;

import com.example.demo.model.SysJob;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author moyo
 *
 */
public class QuartzJobExecution extends com.example.demo.common.utils.AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        com.example.demo.common.utils.JobInvokeUtil.invokeMethod(sysJob);
    }
}
