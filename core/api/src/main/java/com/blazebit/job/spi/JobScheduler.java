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
package com.blazebit.job.spi;

import java.util.concurrent.TimeUnit;

/**
 * Interface implemented by the job implementation provider.
 *
 * Schedules job instances for a specific partition based on their schedule.
 * It uses the {@link com.blazebit.job.JobManager} to access the jobs of the partition.
 *
 * @author Christian Beikov
 * @since 1.0.0
 */
public interface JobScheduler {

    /**
     * Start scheduling job instances.
     */
    void start();

    /**
     * Force a refresh of the next schedule.
     */
    default void refreshSchedules() {
        refreshSchedules(0L);
    }

    /**
     * Inform the scheduler about the given new schedule so it can refresh internal schedules.
     *
     * @param earliestNewSchedule The newest schedule
     */
    void refreshSchedules(long earliestNewSchedule);

    /**
     * Stops the job scheduler.
     * After this method finished no further jobs are scheduled but there may still be running jobs.
     */
    void stop();

    /**
     * Stops the job scheduler and waits up to the given amount of time for currently running jobs to finish.
     * After this method finished no further jobs are scheduled.
     *
     * @param timeout The maximum time to wait
     * @param unit The time unit of the timeout argument
     * @throws InterruptedException if interrupted while waiting
     */
    void stop(long timeout, TimeUnit unit) throws InterruptedException;
}
