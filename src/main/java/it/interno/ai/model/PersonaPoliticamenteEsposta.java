package it.interno.ai.model;

import java.time.LocalDate;

/**
 * @author mirco.cennamo on 28/02/2025
 * @project spring-into-ai
 */
public class PersonaPoliticamenteEsposta {
    private String nome;
    private String cognome;
    private String idUtente;
    private String idUfficio;
    private LocalDate dataRichiesta;
    private String denominazioneUfficio;

    public PersonaPoliticamenteEsposta() {
    }

    public PersonaPoliticamenteEsposta(String nome, String cognome, String idUtente, String idUfficio, LocalDate dataRichiesta, String denominazioneUfficio) {
        this.nome = nome;
        this.cognome = cognome;
        this.idUtente = idUtente;
        this.idUfficio = idUfficio;
        this.dataRichiesta = dataRichiesta;
        this.denominazioneUfficio = denominazioneUfficio;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public String getDenominazioneUfficio() {
        return denominazioneUfficio;
    }

    public void setDenominazioneUfficio(String denominazioneUfficio) {
        this.denominazioneUfficio = denominazioneUfficio;
    }

    public String getIdUfficio() {
        return idUfficio;
    }

    public void setIdUfficio(String idUfficio) {
        this.idUfficio = idUfficio;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "PersonaPoliticamenteEsposta{" +
                "cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", idUtente='" + idUtente + '\'' +
                ", idUfficio='" + idUfficio + '\'' +
                ", dataRichiesta=" + dataRichiesta +
                ", denominazioneUfficio='" + denominazioneUfficio + '\'' +
                '}';
    }
}
