package it.interno.ai.model;

import java.time.LocalDate;

/**
 * @author mirco.cennamo on 28/02/2025
 * @project spring-into-ai
 */
public class PersonaPoliticamenteEsposta {
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String luogoDiNascita;
    private String provinciaDiNascita;
    private LocalDate dataDiFineControllo;
    private String ente;
    private String ruolo;


    public String getEnte() {
        return ente;
    }

    public void setEnte(String ente) {
        this.ente = ente;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public LocalDate getDataDiFineControllo() {
        return dataDiFineControllo;
    }

    public void setDataDiFineControllo(LocalDate dataDiFineControllo) {
        this.dataDiFineControllo = dataDiFineControllo;
    }

    public String getProvinciaDiNascita() {
        return provinciaDiNascita;
    }

    public void setProvinciaDiNascita(String provinciaDiNascita) {
        this.provinciaDiNascita = provinciaDiNascita;
    }

    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public boolean matchesCriteria(String criteria) {
        return criteria.contains(this.nome) ||
                criteria.contains(this.cognome) ||
                criteria.contains(this.luogoDiNascita) ||
                criteria.contains(this.provinciaDiNascita) ||
                criteria.contains(this.ente) ||
                criteria.contains(this.ruolo);
    }

    @Override
    public String toString() {
        return "PersonaPoliticamenteEsposta{" +
                "cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", luogoDiNascita='" + luogoDiNascita + '\'' +
                ", provinciaDiNascita='" + provinciaDiNascita + '\'' +
                ", dataDiFineControllo=" + dataDiFineControllo +
                ", ente='" + ente + '\'' +
                ", ruolo='" + ruolo + '\'' +
                '}';
    }
}
