package com.example.library.model;

import com.example.library.model.states.New;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.mapstruct.InheritConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.Set;

public abstract class ReservationState {

    protected String stateName;

    static void entry() {
    }

    public String getStateName() {
        return stateName;
    }

    ReservationState action() {
        return new New();
    }


}
