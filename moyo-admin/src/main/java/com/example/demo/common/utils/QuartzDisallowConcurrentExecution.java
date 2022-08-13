package com.example.demo.common.utils;

import com.example.demo.model.SysJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 * 
 * @author moyo
 *
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends com.example.demo.common.utils.AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
