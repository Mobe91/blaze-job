/*
 * Copyright 2018 - 2019 Blazebit.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blazebit.job.schedule.cron;

import com.blazebit.apt.service.ServiceProvider;
import com.blazebit.job.JobException;
import com.blazebit.job.Schedule;
import com.blazebit.job.spi.ScheduleFactory;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * A factory for {@link CronSchedule}.
 *
 * @author Christian Beikov
 * @since 1.0.0
 */
@ServiceProvider(ScheduleFactory.class)
public class CronScheduleFactory implements ScheduleFactory {

    private static final DateTimeFormatter CRON_FORMATTER = new DateTimeFormatterBuilder()
        .appendValue(ChronoField.SECOND_OF_MINUTE)
        .appendLiteral(' ')
        .appendValue(ChronoField.MINUTE_OF_HOUR)
        .appendLiteral(' ')
        .appendValue(ChronoField.HOUR_OF_DAY)
        .appendLiteral(' ')
        .appendValue(ChronoField.DAY_OF_MONTH)
        .appendLiteral(' ')
        .appendValue(ChronoField.MONTH_OF_YEAR)
        .appendLiteral(" ? ")
        .appendValue(ChronoField.YEAR)
        .toFormatter();

    @Override
    public String asCronExpression(Instant instant) {
        return CRON_FORMATTER.format(instant.atOffset(ZoneOffset.UTC));
    }

    @Override
    public Schedule createSchedule(String cronExpression) {
        try {
            return new CronSchedule(cronExpression);
        } catch (ParseException ex) {
            throw new JobException(ex);
        }
    }
}
