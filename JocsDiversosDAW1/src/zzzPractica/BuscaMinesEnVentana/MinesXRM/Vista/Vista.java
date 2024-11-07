package zzzPractica.BuscaMinesEnVentana.MinesXRM.Vista;

import zzzPractica.BuscaMinesEnVentana.MinesXRM.Controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Vista
{
    static JFrame f = new JFrame("BuscaMines");
    static JTextArea textArea = new JTextArea();
    static JScrollPane scrollPane = new JScrollPane(textArea);

    public static void crearFinestra() {
        // Afegir barra de menús, barra d'eines i altres elements
        // Configurar i mostrar finestra
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
        textArea.setFont(new Font("Serif", Font.PLAIN, 20));
        f.setContentPane(scrollPane);
    }

    private static void canviarContingut(String t)
    {
        textArea.setText(t);
        scrollPane.getViewport().add(textArea);
    }













    public static String pedriTamanoPred()
    {
        String texto = ("Seleccione un tamaño:\n1.  8X8 con 10 bombas\n2.  16X16 con 40 bombas\n3.  26X50 con 150 bombas (Tabla más grande)\n4.  Personalizar");
        canviarContingut(texto);
        String tamano = JOptionPane.showInputDialog(
                null,
                null,
                "1"
        );
        System.out.println("Nom: " + tamano);
        return tamano;
    }
    public static String pedirTamano()
    {
        String texto = ("Seleccione el numero de filas, columnas i bombas (separados por ', ')");
        canviarContingut(texto);
        String tamano;
        tamano = JOptionPane.showInputDialog(
                null,
                "Escull el numero de files",
                "1"
        );
        tamano += " " + JOptionPane.showInputDialog(
                null,
                "Escull el numero de columnes",
                "1"
        );
        tamano += " " + JOptionPane.showInputDialog(
                null,
                "Escull el numero de bombes",
                "1"
        );
        System.out.println("tamano: " + tamano);
        return tamano;
    }

    public static void pedirmodo(int numbanderas)
    {
        System.out.println("Seleccione 'A' para atacar");
        System.out.println("Seleccione 'M' para marcar una bomba (" + numbanderas + " banderas colocadas)");
        System.out.println("Seleccione 'R' para rendirte");
    }
    public static void pedirCorrd()
    {
        System.out.println("Indique las coordenadas del ataque (Ej: a1)");
    }
    private static String formatF(String s, int lenght) //dar formato al texto omo en el printf
    {
        if(s.length() < lenght)
        {
            lenght -= s.length();
            for (int i = 0; i < lenght; i++)
            {
                s = " " + s;
            }
        }
        return s;
    }
    public static void verCamp(int files, int columnes, String[][] tablero)
    {
        String t = "";
        for (int i = 0; i < columnes; i++)
        {
            if (i == 0) {
                t += ("  \n");
                System.out.println("  ");
                for (int j = 0; j < files - 1; j++) {
                    if (j != 0)  {t += formatF(j + "",3); System.out.printf("%-3d", j);}
                    else { t += "   ";  System.out.print("   ");}
                }
                t += "\n";
                System.out.println();
                t += "   ";
                System.out.print("   ");
            } else if (i != columnes - 1) {
                t += formatF((char)('a' + i - 1)+" ",3);
                System.out.printf("%-3c", ('a' + i - 1));
            } else {t += "   "; System.out.print("   ");}
            for (int j = 0; j < files; j++) {
                t += tablero[i][j];
                System.out.print(tablero[i][j]);
            }
            t +="\n";
            System.out.println();
        }
        canviarContingut(t);
    }
}
