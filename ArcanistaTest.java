import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArcanistaTest {

    @Test
    void testarGastoDeMana() {
        Arcanista mago = new Arcanista("Gandalf");

        mago.atacar();

        assertEquals(30, mago.getMana(), "A mana devia ter caído para 30!");
    }

    @Test
    void testarRecuperacaoDeMana() {
        Arcanista mago = new Arcanista("Gandalf");

        mago.atacar(); 
        mago.atacar();
        mago.atacar();
        mago.atacar(); 

        mago.atacar(); 

        assertEquals(5, mago.getMana(), "O mago devia ter recuperado 5 de mana!");
    }
}
