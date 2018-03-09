package com.weather.config;

import com.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quartz 配置
 * @author yyh
 * @date 2018/3/8 16:40
 */
@Configuration
public class QuartzConfiguration {
	/**
	 * TIME 更新频率
	 */
	private static final int TIME = 1800;

	/**
	 * JobDetail
	 * @return JobDetail
	 */
	@Bean
	public JobDetail weatherDataSyncJobDetail() {
		return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob")
				.storeDurably().build();
	}

	/**
	 * Trigger
	 * @return Trigger
	 */
	@Bean
	public Trigger weatherDataSyncTrigger() {

		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(TIME).repeatForever();

		return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
				.withIdentity("weatherDataSyncTrigger").withSchedule(schedBuilder).build();
	}
}
