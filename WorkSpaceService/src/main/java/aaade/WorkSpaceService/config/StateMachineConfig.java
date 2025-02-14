package aaade.WorkSpaceService.config;

import aaade.WorkSpaceService.model.TaskEvent;
import aaade.WorkSpaceService.model.TaskState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<TaskState, TaskEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<TaskState, TaskEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<TaskState, TaskEvent> states) throws Exception {
        states
                .withStates()
                .initial(TaskState.NEW)
                .states(EnumSet.allOf(TaskState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TaskState, TaskEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(TaskState.NEW).target(TaskState.IN_PROGRESS).event(TaskEvent.START)
                .and()
                .withExternal()
                .source(TaskState.IN_PROGRESS).target(TaskState.COMPLETED).event(TaskEvent.COMPLETE)
                .and()
                .withExternal()
                .source(TaskState.COMPLETED).target(TaskState.CLOSED).event(TaskEvent.CLOSE);
    }
}
