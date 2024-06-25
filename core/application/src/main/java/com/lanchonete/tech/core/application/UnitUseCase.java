package com.lanchonete.tech.core.application;

public abstract class UnitUseCase<IN> {

    public abstract void execute(IN anIn);
}