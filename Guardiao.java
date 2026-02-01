import java.util.Random;

public class Guardiao extends Combatente{
    Random random = new Random();

    Guardiao(String nome) {
        super(nome,120,120);
    }

    @Override
    public int atacar() {
        return 10 + random.nextInt(0,6);
    }
}
