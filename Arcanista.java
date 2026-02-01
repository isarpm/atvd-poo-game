import java.util.Random;

public class Arcanista extends Combatente {
    private int mana;
    private final int manaMaxima = 40;

    public Arcanista(String nome) {
        super(nome, 80, 80);
        this.mana = manaMaxima;
    }

    @Override
    public int atacar() {
        Random rand = new Random();
        
        if (this.mana >= 10) {
            this.mana -= 10;
            return 20 + rand.nextInt(10);
        } else {
            this.mana = Math.min(manaMaxima, this.mana + 5); 
            return 5;
        }
    }

    public String exibirBarraMana() {
        int totalBlocos = 10;
        int blocosPreenchidos = (int) ((double) mana / manaMaxima * totalBlocos);
        
        String barra = "Mana: [";
        for (int i = 0; i < totalBlocos; i++) {
            if (i < blocosPreenchidos) {
                barra += "■";
            } else {
                barra += " ";
            }
        }
        barra += "] " + mana + "/" + manaMaxima;
        return barra;
    }

        public int getMana() {

            return mana;

        }

    

        public int getManaMaxima() {

            return manaMaxima;

        }

    }

    
