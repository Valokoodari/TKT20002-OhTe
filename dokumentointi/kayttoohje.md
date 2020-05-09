# Käyttöohje
Käyttöohje olettaa, että ohjelma suoritetaan pakattuna .jar-tiedostona

## Konfiguraatio
Ohjelma luo ensimmäisellä käynnistyskerralla tiedoston config.properties ja täyttää sen
käytössä olevalle kokoonpanolle sopivilla oletusarvoilla.

### Arvot
- saveDB: tietokantatiedosto, johon pelin tila tallennetaan
- width: näytön leveys pikseleissä
- height: näytön korkeus pikseleissä

## Päävalikko
Aloittaaksesi pelin valitse ensin vaikeustaso (Easy, Medium, Hard)
ja paina sitten haluamasi kartan painiketta.  

![Kuva päävalikosta](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/kuvat/paavalikko.jpg)  

## Pelaaminen
Tornin lisääminen kentälle tapahtuu klikkaamalla kartalta haluttua kohtaa.  
Tornit vahingoittavat sekunnin välein kaikkia viruksia jotka ovat niiden näköetäisyydellä.  
Tornin näköetäisyys on merkitty harmaalla läpinäkyvällä ympyrällä tornin ympärillä.  
Tornin hinta on sitä korkeampi mitä korkeampi vaikeustaso pelissä on. (100, 150, 200)  

Virukset kulkevat kartalla näkyvää polkua pitkin ja katoavat kuollessaan.  
Jos virus pääsee polun viimeisen laatan keskelle, pelaaja ottaa vahinkoa.  
Viruksen kuollessa pelaaja saa viruksesta hieman lisää rahaa tornien
ostamista tai päivittämistä varten  

![Kuva pelikentästä](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/kuvat/pelikentta.jpg)