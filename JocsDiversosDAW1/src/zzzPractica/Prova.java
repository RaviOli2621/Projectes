package zzzPractica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Prova
{
    static JFrame f = new JFrame("Mondongo");

    public static JScrollPane contingut()
    {
        String [] paco = {" X "," X ", " 0 "," X "};

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Serif", Font.PLAIN, 20));

        for (int i = 0; i < paco.length; i++)
        {
            textArea.setText(textArea.getText() + paco[i]);
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        return scrollPane;
    }

    public static void crearFinestra() {
        // Afegir barra de menús, barra d'eines i altres elements
        crearMenu();
        // Configurar i mostrar finestra
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);
        f.setVisible(true);

        f.setContentPane(contingut());
    }


    public static void main(String[] args) {
        crearFinestra();
    }

    private static void crearMenu() {
        // Crear la barra de menús
        JMenuBar menuBar = new JMenuBar();

        // Crear menú Opcions
        JMenu menu = new JMenu("Opcions");
        menu.setMnemonic(KeyEvent.VK_O);

        JMenu tamano = new JMenu("Tamaño");
        tamano.setMnemonic(KeyEvent.VK_3);

        // Afegir items al menú
        menu.add(crearMenuItem("Opció 1", KeyEvent.VK_1, KeyEvent.VK_A, "op1"));
        menu.add(crearMenuItem("Opció 2", KeyEvent.VK_2, KeyEvent.VK_B, "op2"));

        tamano.add(crearMenuItem("Opció 1", KeyEvent.VK_4, KeyEvent.VK_C, "grande"));
        tamano.add(crearMenuItem("Opció 2", KeyEvent.VK_2, KeyEvent.VK_D, "pequeño"));

        // Afegir menú Opcions a la barra de menús
        menuBar.add(menu);
        menuBar.add(tamano);
        // Afegir la barra de menús a la finestra
        f.setJMenuBar(menuBar);
    }

    private static JMenuItem crearMenuItem(String text, int mnemonic, int accelerator, String action) {
        JMenuItem mi = new JMenuItem(text);

        mi.setMnemonic(mnemonic);
        mi.setAccelerator(KeyStroke.getKeyStroke(accelerator, InputEvent.ALT_DOWN_MASK));
        // TODO: configurar acció i afegir controlador
        // mi.setActionCommand(action);
        // mi.addActionListener(optionControl);

        return mi;
    }
}
