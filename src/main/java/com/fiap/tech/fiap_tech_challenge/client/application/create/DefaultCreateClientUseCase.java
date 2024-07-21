package com.fiap.tech.fiap_tech_challenge.client.application.create;

import com.fiap.tech.fiap_tech_challenge.client.domain.Client;
import com.fiap.tech.fiap_tech_challenge.client.domain.ClientGateway;
import com.fiap.tech.fiap_tech_challenge.client.domain.utils.CpfCnpjUtils;
import com.fiap.tech.fiap_tech_challenge.client.domain.utils.EmailValidator;
import com.fiap.tech.fiap_tech_challenge.common.domain.exceptions.NotificationException;
import com.fiap.tech.fiap_tech_challenge.common.domain.validation.Error;
import com.fiap.tech.fiap_tech_challenge.common.domain.validation.ValidationHandler;
import com.fiap.tech.fiap_tech_challenge.common.domain.validation.handler.Notification;
import com.fiap.tech.fiap_tech_challenge.common.domain.validation.handler.ThrowsValidationHandler;

public class DefaultCreateClientUseCase extends CreateClientUseCase {
    private final ClientGateway clientGateway;

    public DefaultCreateClientUseCase(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }

    @Override
    public CreateClientOutput execute(CreateClientCommand command) {

        final var name = command.name();
        final var email = command.email();
        final var cpf = command.cpf();

        final var notification = Notification.create();

        notification.append(validateCpf(cpf));
        notification.append(validateEmail(email));

        final var client = notification.validate(() -> Client.newClient(name, email, cpf));

        if (notification.hasErrors()) {
            throw new NotificationException("Could not create Aggregate Client", notification);
        }

        return CreateClientOutput.from(this.clientGateway.create(client));
    }

    private ValidationHandler validateEmail(String email) {
        if(email == null) {
            return Notification.create(new Error("'email' should not be null"));
        }
        if(email.trim().isBlank()) {
            return Notification.create(new Error("'email' should not be empty"));
        }
        if(!EmailValidator.isEmailValid(email)) {
            return Notification.create(new Error("'email' should not be invalid"));
        }
        if(clientGateway.existsByEmail(email)) {
            return Notification.create(new Error("'email' already exists"));
        }
        return Notification.create();
    }

    private ValidationHandler validateCpf(String cpf) {
        if(cpf == null) {
            return Notification.create(new Error("'cpf' should not be null"));
        }
        if(cpf.trim().isBlank()) {
            return Notification.create(new Error("'cpf' should not be empty"));
        }

        if(!CpfCnpjUtils.isValid(cpf)) {
            return Notification.create(new Error("'cpf' should not be invalid"));
        }
        if(clientGateway.existsByCpf(cpf)) {
            return Notification.create(new Error("'cpf' already exists"));
        }
        return Notification.create();
    }
}
