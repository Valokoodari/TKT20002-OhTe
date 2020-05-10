# Arkkitehtuurikuvaus

## Rakenne
Ohjelma on jaettu kolmeen pakkaukseen, jotka huolehtivat selvästi erilaisista asioista.  
Käyttöliittymän luokat ovat pakkauksessa 'gui', pelilogiikka pakkauksessa 'logic' ja tiedostojen
lukemiseen ja kirjoittamiseen tarkoitetut luokat pakkauksessa 'dao'.

![Kuva luokkakaaviosta](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/kuvat/luokkakaavio.jpg)

## Käyttöliittymä
Käyttöliittymässä on kaksi erillistä näkymää, joista ensimmäisessä eli päävalikossa valitaan
mitä karttaa halutaan pelata ja millä vaikeustasolla. Lisäksi päävalikko näyttää millä vaikeustasoilla
mitkäkin kartat on jo suoritettu.  
Toisena näkymänä taas on itse pelinäkymä, jossa näkyy itse pelikenttä ja tarvittavat tiedot pelin tilanteesta.  

Käyttöliittymä ei sisällä itse pelilogiikkaa, joten käyttöliittymä voitaisiin helposti vaihtaa toiseen.

## Tiedostot
Pelin onnistuneet suoritukset kirjataan konfiguraatiotiedoston mukaiseen SQL-tietokantaan ja luetaan
sieltä aina pelin käynnistyessä. Jos tietokantaa tai konfiguraatiotiedostoa ei ole vielä olemassa, kun peli käynnistetään niin peli luo itse uudet vakioarvoilla.  

Lisäksi pelikenttä ja kenttäkohtaiset virusaallot on tallennettu pelin suoritettavan tiedoston sisälle pakatuihin
tiedostoihin. Täten on siis helposti mahdollista lisätä peliin uusia karttoja vain lisäämällä tarpeelliset tiedostot ja lisäämällä käyttöliittymään mahdollisuuden kutsua pelilogiikkaa sopivalla arvolla.

## Päätoiminnallisuudet
Kaikki seuraavat toiminnallisuudet ovat huomattavasti yksinkertaisemmista tapauksista kuin mitä pelin aikana
todellisuudessa tapahtuu.

Pelin lataaminen aloitettaessa.  
![Sekvenssikaavio aloituksesta](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/kuvat/sekvenssikaavio-alku.jpg)

Tornin ostaminen pelissä, kun klikattu kohta kartalla on vapaata aluetta.  
![Sekvenssikaavio tornin ostamisesta](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/kuvat/sekvenssikaavio-osto.jpg)

Pelin päivittyminen toivottavasti vähintään 60 kertaa sekunnissa.  
![Sekvenssikaavio pelin tickistä](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/kuvat/sekvenssikaavio-paivitys.jpg)

## Rakenteen heikkoudet
Eri luokkien välillä hyppimistä on selvästi liian paljon. Esimerkiksi käyttöliittymän näkymien vaihtamisen
pitäisi todennäköisesti mieluummin tapahtua päävalikon luokassa hyppimisen vähentämiseksi.

Myös luokkaa Game pitäisi todennäköisesti hajottaa vielä useampiin pienempiin luokkiin.