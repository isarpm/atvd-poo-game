import java.util.Random;

public class Cacador extends Combatente {
    Random random = new Random();

    Cacador(String nome) {
        super(nome,70,70);
    }

    @Override
    public int atacar() {
        int dano =  12 + random.nextInt(0,8);
        if(random.nextInt(4) == 0) {
            dano *= 2;
        }
        return dano;
    }
}
