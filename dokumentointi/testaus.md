# Testausdokumentti

## Yksikkö- ja integraatiotestit
Ohjelmaa varten on kirjoitettu automaattisesti suoritettavia yksikkö- ja integraatiotestejä,
joiden tarkoituksena on testata eri luokkien toimintaa yhdessä ja erikseen.

Yksikkötestejä on kirjoitettu kaikista merkittävimmille luokille, joita integraatiotestit eivät ole
kattaneet, kun käyttöliittymää ei ole otettu mukaan automaattiseen testaukseen.

### Testikattavuus
Sovelluksen automatisoidun testauksen rivikattavuus on 91 % ja haaraumakattavuus 84 %.  
![Kuva testikattavuudesta](https://github.com/Valokoodari/tkt-ohte-ht/blob/master/dokumentointi/kuvat/testikattavuus.jpg)

## Järjestelmätestit
Ohjelmaa on testattu manuaalisesti, jotta voitaisiin varmistua ohjelman käyttökelpoisuudesta.  

Ohjelma ei ole osoittautunut toimivaksi uusimmilla versioilla Windowsista tai Mac OS:sta.  

Vaikeustasot 'Easy' ja 'Medium' on pelattu onnistuneesti läpi. Vaikeustasolla 'Hard' peli
saattaa olla mahdoton pelata läpi nykyisillä vaihtoehdoilla vahingoittaa viruksia.

## Sovelluksen nykyiset ongelmat
- Ohjelman napeista piirtyy joillakin järjestelmillä vain tekstit. Ei vaikutusta ohjelman toiminnallisuuteen.  
- Pelin päävalikko ei näy oikein alussa. Korjaantuu, kun peli käynnistetään uudelleen.
- Virukset poistuvat polulta eivätkä etene loppuun saakka. Satunnainen ja harvinainen.  
- Vanha peli jää taustalle, jos päävalikkoon palataan manuaalisesti. (Käynnistä peli uudelleen)  
- Suoritukset päivittyvät päävalikkoon vasta, kun pelin käynnistää uudelleen.