# Ohjelmistotekniikka, harjoitustyö

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/vaatimusmaarittely.md)  
[Työaikakirjanpito](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Komentorivitoiminnot
### Ohjelman suorittaminen
```
mvn compile exec:java -Dexec.mainClass=gui.Main
```

### Testaaminen
```
mvn test
```
### Testikattavuus
```
mvn test jacoco:report
```
Generoitunut testikattavuusraportti löytyy avaamalla selaimessa tiedosto target/site/jacoco/index.html

## Tunnetut bugit
- Ohjelman napeista piirtyy joillakin järjestelmillä vain tekstit. Ei vaikutusta ohjelman toiminnallisuuteen.
