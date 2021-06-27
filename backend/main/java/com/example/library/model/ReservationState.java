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

@Entity
public abstract class ReservationState {
    @Id
    protected String stateName;

    private transient Reservation context;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setContext(Reservation context) {
        this.context = context;
    }

    public abstract void entry();
}
