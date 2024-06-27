package com.lanchonete.tech.core.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIN);
}