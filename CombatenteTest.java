import static org.junit.jupiter.api.Assertions.*;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	class CombatenteTest {

	    private Combatente combatente;

	    
	    private static class CombatenteMock extends Combatente {
	        public CombatenteMock(String nome, int pv, int pvMax) {
	            super(nome, pv, pvMax);
	        }

	        public int atacar() {
	            return 10; 
	        }
	    }

	    @BeforeEach
	    void setUp() {
	        combatente = new CombatenteMock("Guerreiro", 100, 100);
	    }

	    @Test
	    void diminuirPvAoReceberDano() {
	        combatente.receberDano(30);
	        assertEquals(70, combatente.getPv(), "O PV deveria ser 70 após receber 30 de dano.");
	    }

	    @Test
	    void naoDeveTerPvNegativo() {
	        combatente.receberDano(150);
	        assertEquals(0, combatente.getPv(), "O PV não deve ficar abaixo de zero.");
	    }

	    @Test
	    void deveRetornarVivoEnquantoPvMaiorQueZero() {
	        assertTrue(combatente.vivo());
	        combatente.receberDano(50);
	        assertTrue(combatente.vivo());
	    }

	    @Test
	    void deveRetornarMortoQuandoPvForZero() {
	        combatente.receberDano(100);
	        assertFalse(combatente.vivo(), "O combatente deveria estar morto com 0 de PV.");
	    }

	    @Test
	    void deveRetornarNomeCorretamente() {
	        assertEquals("Guerreiro", combatente.getNome());
	    }
	}
