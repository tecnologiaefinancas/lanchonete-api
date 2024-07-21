package com.fiap.tech.fiap_tech_challenge.client.infra.persistence;

import com.fiap.tech.fiap_tech_challenge.categories.domain.CategoryID;
import com.fiap.tech.fiap_tech_challenge.client.domain.Client;
import com.fiap.tech.fiap_tech_challenge.client.domain.ClientID;
import com.fiap.tech.fiap_tech_challenge.product.domain.Product;
import com.fiap.tech.fiap_tech_challenge.product.domain.ProductID;
import com.fiap.tech.fiap_tech_challenge.product.infra.persistense.ProductJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class ClientJpaEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String cpf;

    public ClientJpaEntity(String id, String name, String email, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public ClientJpaEntity() {
    }

    public static ClientJpaEntity from(final Client client) {
        return new ClientJpaEntity(client.getId().getValue(), client.getName(), client.getEmail(), client.getCpf());
    }

    public Client toAggregate() {
        return Client.with(ClientID.from(this.getId()), this.getName(), this.getEmail(), this.getCpf());

    }

}
