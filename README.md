# Ohjelmistotekniikka, harjoitustyö

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/vaatimusmaarittely.md)  
[Työaikakirjanpito](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/tyoaikakirjanpito.md)  
[Arkkitehtuuri](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/arkkitehtuuri.md)  
[Käyttöohje](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/kayttoohje.md)

## Releaset
[Viikko 5](https://github.com/Valokoodari/tkt-ohte-ht/releases/tag/v0.5.1)
[Viikko 6](https://github.com/Valokoodari/tkt-ohte-ht/releases/tag/v0.6.1)

## Komentorivitoiminnot
### Ohjelman pakkaaminen suoritettavaksi tiedostoksi
```
mvn package
```
Komento luo suoritettavan tiedoston target/CoronaTowerDefense-1.0-SNAPSHOT.jar

### Ohjelman suorittaminen mavenilla
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
Generoitunut testikattavuusraportti löytyy tiedostosta target/site/jacoco/index.html

### Checkstyle
```
mvn jxr:jxr checkstyle:checkstyle
```
Generoitunut raportti löytyy tiedostosta target/site/checkstyle.html

### Javadoc
```
mvn javadoc:javadoc
```
Generoitua dokumentaatiota pääsee selaamaan avaamalla selaimessa target/site/apidocs/index.html

## Tunnetut bugit
- Ohjelman napeista piirtyy joillakin järjestelmillä vain tekstit. Ei vaikutusta ohjelman toiminnallisuuteen.
- Pelin päävalikko ei näy oikein alussa. Korjaantuu aloittamalla pelin ja palaamalla.
- Virukset poistuvat polulta eivätkä etene loppuun saakka. Satunnainen ja harvinainen.
