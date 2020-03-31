# Ohjelmistotekniikka, harjoitustyö

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/vaatimusmaarittely.md)  
[Työaikakirjanpito](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Komentorivitoiminnot
### Ohjelman suorittaminen
```
mvn compile exec:java -Dexec.mainClass=gui.Main
```
Joillakin koneilla ohjelman tyyli näkyy väärin ja kaikki on valkoista,
mutta pyrin ratkaisemaan tämän bugin mahdollisimman nopeasti myös bugin
kanssa toiminnallisuus on kuitenkin täysin sama vaikkakin valintoja ei
näe ja exit-nappi näkyy vain hiiren ollessa sen päällä.

### Testaaminen
```
mvn test
```
### Testikattavuus
```
mvn test jacoco:report
```
Generoitunut testikattavuusraportti löytyy avaamalla selaimessa tiedosto target/site/jacoco/index.html