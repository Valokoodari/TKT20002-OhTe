package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate kassa;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    // Kassan alustaminen
    
    @Test
    public void kassanSaldoOnAlussaOikea() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassanMaukkaidenMaaraOnAlussaOikea() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    public void kassanEdullistenMaaraOnAlussaOikea() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    // Käteisellä ostaminen
    
    @Test
    public void edullisenLounaanOstaminenToimiiKateisella() {
        assertEquals(260, kassa.syoEdullisesti(500));
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaKateisella() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullistaLounastaEiVoiOstaaJosMaksuEiRiitaKateisella() {
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstaminenToimiiKateisella() {
        assertEquals(600, kassa.syoMaukkaasti(1000));
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaKateisella() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukastaLounastaEiVoiOstaaJosMaksuEiRiitaKateisella() {
        assertEquals(399, kassa.syoMaukkaasti(399));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    // Maksukortilla ostaminen
    
    @Test
    public void edullisenLounaanOstaminenToimiiKortilla() {
        Maksukortti kortti = new Maksukortti(500);
        
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(260, kortti.saldo());
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaKortilla() {
        Maksukortti kortti = new Maksukortti(500);
        
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullistaLounastaEiVoiOstaaJosSaldoEiRiitaKortilla() {
        Maksukortti kortti = new Maksukortti(230);
        
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(230, kortti.saldo());
    }
    
    @Test
    public void maukkaanLounaanOstaminenToimiiKortilla() {
        Maksukortti kortti = new Maksukortti(1000);
        
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaKortilla() {
        Maksukortti kortti = new Maksukortti(1000);
        
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukastaLounastaEiVoiOstaaJosSaldoEiRiitaKortilla() {
        Maksukortti kortti = new Maksukortti(399);
        
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(399, kortti.saldo());
    }
    
    @Test
    public void rahanMaaraEiMuutuKortillaOstettaessa() {
        Maksukortti kortti = new Maksukortti(2000);
        
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    // Kortin lataaminen
    
    @Test
    public void rahanLataaminenKortilleKasvattaaKortinSaldoa() {
        Maksukortti kortti = new Maksukortti(500);
        
        kassa.lataaRahaaKortille(kortti, 2000);
        assertEquals(2500, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKortillaKasvattaaRahanMaaraa() {
        Maksukortti kortti = new Maksukortti(20);
        
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }
    
    @Test
    public void negatiivisenSummanLataaminenEiMuutaMitaan() {
        Maksukortti kortti = new Maksukortti(1000);
        
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1000, kortti.saldo());
    }
}