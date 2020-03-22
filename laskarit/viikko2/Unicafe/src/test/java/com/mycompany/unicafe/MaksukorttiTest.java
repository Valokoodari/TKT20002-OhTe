package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOnAlussaOikea() {
        assertEquals(10, kortti.saldo());
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void kortinSaldoKasvaaOikeinRahaaLadatessa() {
        kortti.lataaRahaa(1440);
        
        assertEquals(1450, kortti.saldo());
        assertEquals("saldo: 14.50", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinKunSaldoRiittaa() {
        kortti.otaRahaa(10);
        
        assertEquals(0, kortti.saldo());
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void saldoEiVaheneKunSaldoEiRiita() {
        kortti.otaRahaa(20);
        
        assertEquals(10, kortti.saldo());
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueKunSaldoRiittaa() {
        assertTrue(kortti.otaRahaa(10));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseKunSaldoEiRiita() {
        assertFalse(kortti.otaRahaa(11));
    }
}
