import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

class CartaCombatente extends JPanel {
    private Combatente c;
    private JProgressBar vida;
    private boolean selecionada = false;
    private Color base;

    public CartaCombatente(Combatente c, Color base) {
        this.c = c;
        this.base = base;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 180));
        setBackground(base);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel nome = new JLabel(c.getNome(), SwingConstants.CENTER);
        nome.setForeground(Color.WHITE);

        vida = new JProgressBar(0, c.getPvMax());
        vida.setValue(c.getPv());
        vida.setStringPainted(true);

        add(nome, BorderLayout.NORTH);
        add(vida, BorderLayout.SOUTH);
    }

    public Combatente getCombatente() { return c; }

    public void atualizar() {
        vida.setValue(c.getPv());
        if (!c.vivo()) {
            setBackground(Color.DARK_GRAY);
        }
    }

    public void selecionar(boolean s) {
        selecionada = s;
        setBorder(BorderFactory.createLineBorder(
                s ? Color.YELLOW : Color.BLACK, 3));
    }
}

public class Main {

    private CartaCombatente atacanteSelecionado;
    private CartaCombatente alvoSelecionado;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        JFrame f = new JFrame("Torneio de Cartas");
        f.setLayout(new BorderLayout());

        JPanel jogador = new JPanel();
        JPanel inimigo = new JPanel();

        jogador.setBackground(new Color(80, 0, 120));
        inimigo.setBackground(new Color(90, 0, 0));

        List<CartaCombatente> cartasJogador = criarTime(
                jogador,
                new Color(120, 0, 180),
                List.of(
                        new Guardiao("Guardião"),
                        new Arcanista("Mago"),
                        new Cacador("Caçador")
                ),
                true
        );

        List<CartaCombatente> cartasInimigo = criarTime(
                inimigo,
                new Color(140, 20, 20),
                List.of(
                        new Guardiao("Inimigo A"),
                        new Arcanista("Inimigo B"),
                        new Cacador("Inimigo C")
                ),
                false
        );

        JButton atacar = new JButton("Atacar");
	    atacar.setBackground(Color.BLACK);
	    atacar.setForeground(Color.WHITE);
        atacar.addActionListener(e -> {
            if (atacanteSelecionado != null && alvoSelecionado != null) {
                Combatente a = atacanteSelecionado.getCombatente();
                Combatente b = alvoSelecionado.getCombatente();
                if (a.vivo() && b.vivo()) {
                    b.receberDano(a.atacar());
                    atacanteSelecionado.atualizar();
                    alvoSelecionado.atualizar();
                }
            }
        });

        f.add(jogador, BorderLayout.SOUTH);
        f.add(inimigo, BorderLayout.NORTH);
        f.add(atacar, BorderLayout.CENTER);

        f.setSize(800, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private List<CartaCombatente> criarTime(
            JPanel painel,
            Color cor,
            List<Combatente> combatentes,
            boolean jogador
    ) {
        List<CartaCombatente> cartas = new ArrayList<>();

        for (Combatente c : combatentes) {
            CartaCombatente carta = new CartaCombatente(c, cor);
            cartas.add(carta);

            carta.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (!c.vivo()) return;

                    if (jogador) {
                        if (atacanteSelecionado != null)
                            atacanteSelecionado.selecionar(false);
                        atacanteSelecionado = carta;
                        carta.selecionar(true);
                    } else {
                        if (alvoSelecionado != null)
                            alvoSelecionado.selecionar(false);
                        alvoSelecionado = carta;
                        carta.selecionar(true);
                    }
                }
            });

            painel.add(carta);
        }
        return cartas;
    }
}