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
package com.blazebit.job;

/**
 * A processor for job triggers.
 *
 * @param <T> The job trigger type
 * @author Christian Beikov
 * @since 1.0.0
 */
public interface JobProcessor<T extends JobTrigger> extends JobInstanceProcessor<Object, T> {

    @Override
    default Object process(T jobInstance, JobInstanceProcessingContext<Object> context) {
        process(jobInstance, context.getJobContext());
        return null;
    }

    /**
     * Processes the job trigger with the given job context.
     *
     * @param jobTrigger The job trigger to process
     * @param context The job context
     */
    void process(T jobTrigger, JobContext context);

}
