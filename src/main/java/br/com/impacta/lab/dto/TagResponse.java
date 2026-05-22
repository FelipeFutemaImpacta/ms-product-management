package br.com.impacta.lab.dto;

import java.time.LocalDate;

public record TagResponse(Long id, String nome, LocalDate dataAssociacao) {

}
