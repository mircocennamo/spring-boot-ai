package it.interno.ai.tools;


import it.interno.ai.model.PersonaPoliticamenteEsposta;
import it.interno.ai.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author mirco.cennamo on 28/02/2025
 * @project spring-into-ai
 */
@Component
@EnableAsync
public class PersonaPoliticamenteEspostaGeneratorTools {


  private static final Logger log = LoggerFactory.getLogger(PersonaPoliticamenteEspostaGeneratorTools.class);
  private final ResourceLoader resourceLoader;


  @Autowired
  public PersonaPoliticamenteEspostaGeneratorTools(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }
/*
  @Tool(description = "Restituisce le informazioni di tutte le persone politicamente esposte")
    public  List<PersonaPoliticamenteEsposta> getPPE() throws ParseException {
      log.info("start getPPE: {}");
      List<PersonaPoliticamenteEsposta> persone = new ArrayList<>();
        PersonaPoliticamenteEsposta ppe1 = new PersonaPoliticamenteEsposta();
        ppe1.setNome("Giorgia");
        ppe1.setCognome("Atzori");
        ppe1.setIdUtente("1");
        ppe1.setIdUfficio("ufficio Passaporti");
        ppe1.setDataRichiesta(DateUtil.parseDate("1950-02-18"));
        ppe1.setDenominazioneUfficio("Ufficio Passaporti");
        persone.add(ppe1);

        PersonaPoliticamenteEsposta ppe2 = new PersonaPoliticamenteEsposta();
        ppe2.setNome("Mirco");
        ppe2.setCognome("Cennamo");
        ppe2.setIdUtente("2");
        ppe2.setIdUfficio("ufficio brevetti");
        ppe2.setDataRichiesta(DateUtil.parseDate("1970-04-12"));
        ppe2.setDenominazioneUfficio("Ufficio Brevetti");
        persone.add(ppe2);

        PersonaPoliticamenteEsposta ppe3 = new PersonaPoliticamenteEsposta();
        ppe3.setNome("Andrea");
        ppe3.setCognome("Manzo");
        ppe3.setIdUtente("3");
        ppe3.setIdUfficio("Ufficio sinistri ");
        ppe3.setDataRichiesta(DateUtil.parseDate("1980-12-03"));
        ppe3.setDenominazioneUfficio("Ufficio Sinistri");
        persone.add(ppe3);

        PersonaPoliticamenteEsposta ppe4 = new PersonaPoliticamenteEsposta();
        ppe4.setNome("Paolo");
        ppe4.setCognome("Bonolis");
        ppe4.setIdUtente("4");
        ppe4.setIdUfficio("Ufficio Scomparsi");
        ppe4.setDataRichiesta(DateUtil.parseDate("1948-05-07"));
        ppe4.setDenominazioneUfficio("Ufficio Scomparsi");
        persone.add(ppe4);


        PersonaPoliticamenteEsposta ppe5 = new PersonaPoliticamenteEsposta();
        ppe5.setNome("Paolo");
        ppe5.setCognome("Avallone");
        ppe5.setIdUtente("5");
        ppe5.setIdUfficio("Ufficio Relazioni con il pubblico");
        ppe5.setDataRichiesta(DateUtil.parseDate("1967-08-20"));
        ppe5.setDenominazioneUfficio("Ufficio Relazioni con il pubblico");
        persone.add(ppe5);
      log.info("stop getPPE: {}",persone);

        return persone;
    }
  @Tool(description = "Restituisce le informazioni di tutte le persone politicamente esposte")
  public List<PersonaPoliticamenteEsposta> getAll() throws IOException, ParseException {
    log.info("start getAll: {}");
    List<PersonaPoliticamenteEsposta> persone = new ArrayList<>();
    Resource resource = resourceLoader.getResource("classpath:20250207-ListaCompleta.csv");
    try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
      String line = br.readLine(); // Skip header
      while ((line = br.readLine()) != null) {
        String[] values = line.split(";");
        if (values.length < 8) continue; // Skip invalid lines
        PersonaPoliticamenteEsposta persona = new PersonaPoliticamenteEsposta();
        persona.setCognome(values[0]);
        persona.setNome(values[1]);
        persona.setDataDiNascita(DateUtil.parseDate(values[2]));
        persona.setLuogoDiNascita(values[3]);
        persona.setProvinciaDiNascita(values[4]);
        persona.setDataDiFineControllo(DateUtil.parseDate(values[5])); //dataFineControllo
        persona.setEnte(values[6]);
        persona.setRuolo(values[7]);
        persone.add(persona);
      }
    }
    log.info("stop getAll");
    return persone;
  }

  @Async
  public CompletableFuture<List<PersonaPoliticamenteEsposta>> getAllAsync() throws IOException, ParseException {
    List<PersonaPoliticamenteEsposta> result = getAll();
    return CompletableFuture.completedFuture(result);
  }

 */

  public List<PersonaPoliticamenteEsposta> filterByCriteria(List<PersonaPoliticamenteEsposta> ppeList, String criteria) {
    return ppeList.stream()
            .filter(ppe -> ppe.matchesCriteria(criteria))
            .collect(Collectors.toList());
  }


 /* @Tool(description = "Restituisce le informazioni di una persona politicamente esposta utilizzando il cognome")
  PersonaPoliticamenteEsposta getPersonaPoliticamenteEspostaInfo(@ToolParam(description = "cognome della persona politicamente esposta") String cognome) throws ParseException {
    log.info("start getPersonaPoliticamenteEspostaInfo: {}",cognome);
    switch(cognome){
        case "Atzori":
            return new PersonaPoliticamenteEsposta("Giorgia", "Atzori", "1", "ufficio Passaporti", DateUtil.parseDate("1950-02-18"), "Ufficio Passaporti");
        case "Cennamo":
            return new PersonaPoliticamenteEsposta("Mirco", "Cennamo", "2", "ufficio brevetti", DateUtil.parseDate("1970-04-12"), "Ufficio Brevetti");
        case "Manzo":
            return new PersonaPoliticamenteEsposta("Andrea", "Manzo", "3", "Ufficio sinistri ", DateUtil.parseDate("1980-12-3"), "Ufficio Sinistri");
        case "Bonolis":
            return new PersonaPoliticamenteEsposta("Paolo", "Bonolis", "4", "Ufficio Scomparsi", DateUtil.parseDate("1948-5-7"), "Ufficio Scomparsi");
        case "Avallone":
            return new PersonaPoliticamenteEsposta("Paolo", "Avallone", "5", "Ufficio Relazioni con il pubblico", DateUtil.parseDate("1967-08-20"), "Ufficio Relazioni con il pubblico");
        default:
            return new PersonaPoliticamenteEsposta();
    }
  }
*/


}
