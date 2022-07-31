/*
 * Copyright 2022 Thoughtworks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.contrib.plugins.configrepo.groovy.dsl;

import cd.go.contrib.plugins.configrepo.groovy.dsl.util.RunInstanceCount;
import com.fasterxml.jackson.annotation.JsonProperty;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.SimpleType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

import static groovy.lang.Closure.DELEGATE_ONLY;
import static lombok.AccessLevel.NONE;

/**
 * Represents a job.
 * <p>
 * {@includeCode job-with-simple-task.groovy}
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Job extends HasEnvironmentVariables<Job> {

    /**
     * The number of agents this job should run on.
     * Defaults to {@code 1}. Set to string {@code "all"}
     * to run on all agents.
     */
    @JsonProperty("run_instance_count")
    @RunInstanceCount
    private Object runInstanceCount;

    /**
     * The job hung timeout (in minutes). If there is no console output for within this
     * interval of time, the job is assumed to be jung and will be terminated by GoCD.
     * <p>
     * Defaults to {@code 0} to never timeout.
     */
    @JsonProperty("timeout")
    @PositiveOrZero
    private Integer timeout;

    /**
     * A job can be configured to run on an elastic agent by specifying this attribute, which maps to the id of an
     * existing {@code <profile>} defined in the main config xml.
     *
     * <strong>MUST NOT</strong> be specified along with {@link Job#resources}
     */
    @JsonProperty("elastic_profile_id")
    private String elasticProfileId;

    /**
     * Specifies the list of resources that will be matched against jobs.
     * An Agent must have all the Resources specified for a Job to be able to run this job.
     *
     * <strong>MUST NOT</strong> be specified along with {@link Job#elasticProfileId}
     */
    @JsonProperty("resources")
    private List<String> resources = new ArrayList<>();

    @Getter(value = NONE)
    @Setter(value = NONE)
    @JsonProperty("tabs")
    @Valid
    private Tabs tabs = new Tabs();

    @Getter(value = NONE)
    @Setter(value = NONE)
    @JsonProperty("artifacts")
    @Valid
    private Artifacts artifacts = new Artifacts();

    @Getter(value = NONE)
    @Setter(value = NONE)
    @JsonProperty("tasks")
    @Valid
    private Tasks tasks = new Tasks();

    @Getter(value = NONE)
    @Setter(value = NONE)
    @JsonProperty("properties")
    @Valid
    private Properties properties = new Properties();

    public Job() {
        this(null);
    }

    public Job(String name) {
        this(name, null);
    }

    public Job(String name, @DelegatesTo(value = Job.class, strategy = DELEGATE_ONLY) @ClosureParams(value = SimpleType.class, options = "cd.go.contrib.plugins.configrepo.groovy.dsl.Job") Closure cl) {
        super(name);
        configure(cl);
    }

    /**
     * The list of artifacts generated by this job.
     * <p>
     * {@includeCode artifacts.big.groovy}
     */
    public Artifacts artifacts(@DelegatesTo(value = Artifacts.class, strategy = DELEGATE_ONLY) @ClosureParams(value = SimpleType.class, options = "cd.go.contrib.plugins.configrepo.groovy.dsl.Artifacts") Closure cl) {
        artifacts.configure(cl);
        return artifacts;
    }

    /**
     * The sequence of tasks executed by this job.
     * <p>
     * {@includeCode job-with-tasks.groovy}
     */
    public Tasks tasks(@DelegatesTo(value = Tasks.class, strategy = DELEGATE_ONLY) @ClosureParams(value = SimpleType.class, options = "cd.go.contrib.plugins.configrepo.groovy.dsl.Tasks") Closure cl) {
        tasks.configure(cl);
        return tasks;
    }

    /**
     * The list of tabs displayed on the job detail page.
     * <p>
     * {@includeCode job-with-tabs.groovy}
     */
    public Tabs tabs(@DelegatesTo(value = Tabs.class, strategy = DELEGATE_ONLY) @ClosureParams(value = SimpleType.class, options = "cd.go.contrib.plugins.configrepo.groovy.dsl.Tabs") Closure cl) {
        tabs.configure(cl);
        return tabs;
    }

    /**
     * The list of properties published by this job
     * <p>
     * {@includeCode job-with-properties.groovy}
     */
    public Properties properties(@DelegatesTo(value = Properties.class, strategy = DELEGATE_ONLY) @ClosureParams(value = SimpleType.class, options = "cd.go.contrib.plugins.configrepo.groovy.dsl.Properties") Closure cl) {
        properties.configure(cl);
        return properties;
    }
}
