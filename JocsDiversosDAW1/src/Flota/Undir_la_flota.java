package Flota;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Undir_la_flota
{
    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> coorUsadas = new ArrayList<String>();
    static ArrayList<String> coorPerdidJ1 = new ArrayList<String>();
    static ArrayList<String> coorPerdidJ2 = new ArrayList<String>();


    public static void jugar() throws Exception// juego
    {
        boolean estadoPartida = true;
        String iconos [] = {"1","2"};

        iconos[0] = iconos(iconos[0]);
        iconos[1] = iconos(iconos[1]);
        barcos(); // del J1
        barcos(); // del J2
        while (estadoPartida)
        {
            printTablero(iconos);
            barcosUndidos();
            ataque();
            estadoPartida = seguirPartida(estadoPartida);
        }
    }
    public static String iconos(String icono) throws Exception
    {
        String respuesta;
        String pred [];
        boolean jaEstaIcon = false;

        System.out.print("Quieres un icono predeterminado? ");
        respuesta = scan.nextLine().trim().toLowerCase();
        pred = leer();

        if(respuesta.equals("si"))
        {
            for (int i = 0; i < pred.length; i++) System.out.print(pred[i] + " ");
            System.out.println();
            if (icono.equals("1")) System.out.print("Escriu el numero de l'icone pel J1: ");
            else System.out.print("Escriu el numero de  l'icone pel J2: ");
            icono = scan.next().trim();
            icono = pred[Integer.parseInt(icono)-1];
            scan.nextLine();
        }else
        {
            if (icono.equals("1")) System.out.print("Escriu l'icone pel J1: ");
            else System.out.print("Escriu l'icone pel J2: ");
            icono = scan.nextLine().trim();
            icono = icono.charAt(0) + "";

            for (int i = 0; i < pred.length; i++)
            {
                if (icono.equals(pred[i])) jaEstaIcon = true;
            }
            if(!jaEstaIcon) reescribir(icono, pred);
        }
        return icono;
    }
    public static String [] leer() throws Exception
    {
       String s [];
       File carpeta = new File ("UndirFlotaDocs");
       File arxiu = new File ("UndirFlotaDocs/Iconos.txt");
        if(carpeta.mkdirs())
       {
           System.out.println("creado con exito");
       }
       if (!arxiu.exists())
       {
           PrintStream writer = new PrintStream(arxiu);
           arxiu.createNewFile();
           writer.println("X,W,ඞ");
       }
       if(arxiu.canRead()) System.out.println("Se puede leer");
       else System.out.println("No se pudo leer");

       Scanner reader = new Scanner(arxiu);
       if(!(reader.hasNext()))
        {
            PrintStream writer = new PrintStream(arxiu);
            writer.println("X,W,ඞ");
            s = "X,W,ඞ".split(",");
        }else s = reader.nextLine().trim().split(",");

       reader.close();
       return s;
    }
    public static void reescribir(String icono, String prede[]) throws Exception
    {
        File f = new File("UndirFlotaDocs/Iconos.txt");
        PrintStream writer = new PrintStream(f);
        String s;
        System.out.print("Lo quieres guardar como predeterminado?");
        s = scan.next().trim().toLowerCase();
        scan.nextLine();
        if(s.equals("si"))
        {
            for(int i = 0; i < prede.length; i++) writer.print(prede[i] + ",");
            writer.print(icono);
            writer.close();
        }
    }
    public static void barcosUndidos()
    {
        int barcosResJ1 = 5;
        int barcosResJ2 = 5;
        int conteoJ1 = 0;
        int conteoJ2 = 0;
        boolean tirandoBarco =false;

        for (int i = 0; i < coorUsadas.size() && !coorPerdidJ1.isEmpty();i++)
        {

            if(tirandoBarco) conteoJ1++;

            for (int j = 0; j < coorPerdidJ1.size(); j++)
                {
                    if(coorUsadas.get(i).equals(coorPerdidJ1.get(j)))
                    {
                        conteoJ1--;
                        tirandoBarco = true;
                    }
                }
            if(coorUsadas.get(i).equals("/") && tirandoBarco)
                {
                    tirandoBarco =false;
                    if(conteoJ1 == 0)
                    {
                        barcosResJ1--;
                    }
                    conteoJ1 = 0;
                }

        }
        for (int i = 0; i < coorUsadas.size() && !coorPerdidJ2.isEmpty();i++)
        {

            if(tirandoBarco) conteoJ2++;

            for (int j = 0; j < coorPerdidJ2.size(); j++)
                {
                    if(coorUsadas.get(i).equals(coorPerdidJ2.get(j)))
                    {
                        conteoJ2--;
                        tirandoBarco = true;
                    }
                }
            if(coorUsadas.get(i).equals("/") && tirandoBarco)
                {
                    tirandoBarco =false;
                    if(conteoJ2 == 0)
                    {
                        barcosResJ2--;
                    }
                    conteoJ2 = 0;
                }
        }
        System.out.println("Barcos J1 = " + barcosResJ1 + "/5");
        System.out.println("Barcos J2 = " + barcosResJ2 + "/5");
    }
    public static void printTablero(String iconos [])
    {
        String letras [] = {" ","a","b","c","d","e","f","g","h","i","j"};
        String casilla, casillaMedio; // casilaMedio es para poder modificar casilla teniendo un respaldo
        System.out.println();
        for (int j = 0; j < 11; j++)
        {
            for (int i = 0; i < 11; i++)
            {
                if(i == 0) System.out.printf("%-3s",letras[j]);
                else
                {
                    casilla = letras[j] + i;
                    casillaMedio = casilla;
                    for (int k = 0; k < coorPerdidJ2.size(); k++)
                    {
                        if(coorPerdidJ2.get(k).equals(casilla)) casillaMedio = " " + iconos[0];
                    }
                    for (int k = 0; k < coorPerdidJ1.size(); k++)
                    {
                        if(coorPerdidJ1.get(k).equals(casilla)) casillaMedio = " " + iconos[1];
                    }
                    casilla = casillaMedio;
                    System.out.printf("%-3s",casilla);
                }
            }
            System.out.println();
        }
    }

    public static void ataque()
    {
        String ataque [] = {"", ""};

        System.out.print("Introduzca las coordenadas de ataque del J1: ");
        ataque [0] = scan.nextLine().trim();
        if(ataque[0].equals("/")) ataque[0] = "soyRetrasado";

        System.out.print("Introduzca las coordenadas de ataque del J2: ");
        ataque [1] = scan.nextLine().trim();
        if(ataque[1].equals("/")) ataque[1] = "soyRetrasado";

        for(int i = 0; i < coorUsadas.size()-1; i++)
        {
            if(coorPerdidJ2.isEmpty())
            {
                if(ataque[0].equals(coorUsadas.get(i)) && i >=(coorUsadas.size()/2))
                {
                    coorPerdidJ2.add(ataque[0]);
                    System.out.println(ataque[0] + " de J1 a acertado");
                }
            }else
            {
                for (int j = 0; j < coorPerdidJ2.size(); j++)
                {
                    if((ataque[0].equals(coorPerdidJ2.get(j)))) j = coorPerdidJ2.size();
                    if(ataque[0].equals(coorUsadas.get(i)) && i >=(coorUsadas.size()/2) && j == (coorPerdidJ2.size()-1))
                    {
                        coorPerdidJ2.add(ataque[0]);
                        System.out.println(ataque[0] + " de J1 a acertado");
                    }
                }
            } // J1 tumbando al J2
            if(coorPerdidJ1.isEmpty())
            {
                if(ataque[1].equals(coorUsadas.get(i)) && i <=(coorUsadas.size()/2))
                {
                    coorPerdidJ1.add(ataque[1]);
                    System.out.println(ataque[1] + " de J2 a acertado");
                }
            }else
            {
                for (int j = 0; j < coorPerdidJ1.size(); j++)
                {
                    if((ataque[1].equals(coorPerdidJ1.get(j)))) j = coorPerdidJ1.size();
                    if(ataque[1].equals(coorUsadas.get(i)) && i <=(coorUsadas.size()/2) && j == (coorPerdidJ1.size()-1))
                    {
                        coorPerdidJ1.add(ataque[1]);
                        System.out.println(ataque[1] + " de J2 a acertado");
                    }
                }
            }
        }
    }
    public static void barcos()
    {
        String submarino [] = {"","","","1"}; //1 = HORIZONTAL
        String destructor [] = {"","", "0"}; //0 = VERTICAL
        String acorazado [] = {"","","","1"}; //1 = HORIZONTAL
        String fragata [] = {"", "0"}; //0 = VERTICAL
        String portaviones [] = {"","","","", "0"}; //0 = VERTICAL

        if (coorUsadas.size() > 2) coorUsadas.add("/"); //la doble barra dividira los barcos del J1 y los del J2

        submarino = Randombarcos(submarino);
        for(int i = 0; i < submarino.length-1; i++)
        {
            System.out.println(submarino[i]);
        }
        System.out.println("submarino");

        destructor = Randombarcos(destructor);
        for(int i = 0; i < destructor.length-1; i++)
        {
            System.out.println(destructor[i]);
        }
        System.out.println("destructor");

        acorazado = Randombarcos(acorazado);
        for(int i = 0; i < acorazado.length-1; i++)
        {
            System.out.println(acorazado[i]);
        }
        System.out.println("acorazado");

        fragata = Randombarcos(fragata);
        for(int i = 0; i < fragata.length-1; i++)
        {
            System.out.println(fragata[i]);
        }
        System.out.println("fragata");

        portaviones = Randombarcos(portaviones);
        for(int i = 0; i < portaviones.length-1; i++)
        {
            System.out.println(portaviones[i]);
        }
        System.out.println("portaviones");
        for(int i = 0; i <= coorUsadas.size()-1; i++)
        {
            System.out.print(coorUsadas.get(i) + " ");
        }
    }
    public static String[] Randombarcos(String barco [])
    {
        boolean actuando = true;
        String letras [] = {"a","b","c","d","e","f","g","h","i","j"};
        String copiaSeg [] = barco;
        String letra;
        int num, posLetra = 0;
        while (actuando)
        {
            barco = copiaSeg;
            actuando = false;
            if(barco[barco.length-1].equals("1"))
            {
                barco[0] = (int)Math.floor(Math.random() * (10-1)+1) + "";
                if(Integer.parseInt(barco[0]) + barco.length-2 > 10) //ves si se va a salir
                {
                    actuando = true;
                }
                if(!actuando)
                {
                    letra = letras[(int)Math.floor(Math.random() * (10))];
                    num = Integer.parseInt(barco[0]);
                    for(int i = 0; i < barco.length-1; i++)
                    {
                        barco[i] = letra + (num + i);
                    }
                }
            }else
            {
                barco[0] = letras[(int)Math.floor(Math.random() * (10))];
                for (int i = 9; i > (9-(barco.length-2)); i--)
                {
                    if(barco[0].equals(letras[i])) actuando = true;
                }
                if(!actuando)
                {
                    for(int i = 0; i < 10; i++)
                    {
                        if(barco[0].equals(letras[i]))
                        {
                            posLetra = i;
                            i = 11;
                        }
                    }
                    num = (int)(Math.random() * (10 - 1 + 1) + 1);
                    for(int i = 0; i < barco.length-1; i++)
                    {
                        barco[i] = letras[posLetra+i] + num;
                    }
                }
            }
            if(coorUsadas.size() == 0)
            {
                for (int i = 0; i < barco.length-1;i++)
                {
                    coorUsadas.add(barco[i]);
                }
            }else
            {
                for (int i = 0; i < coorUsadas.size()-1; i++)
                {
                    for (int j = 0; j < barco.length-1; j++)
                    {
                        if(barco[j].equals(coorUsadas.get(i)))
                        {
                            actuando = true;
                        }
                    }
                }
                if (!actuando)
                {
                    for (int i = 0; i < barco.length-1;i++)
                    {
                        coorUsadas.add(barco[i]);
                    }

                }
            }
        }
        coorUsadas.add("/");
        return barco;
    }
    public static boolean seguirPartida(boolean estadoPartida)
    {
        final int longitud = 13;
        int ganador = 0;
        String ganadorS [] = {"J2 es el ganador", "J1 es el ganador","", "Empate" };
        if(coorPerdidJ2.size() >= longitud)
        {
            estadoPartida = false;
            ganador = 1;
        }
        if (coorPerdidJ1.size() >= longitud)
        {
            estadoPartida = false;
            ganador *= 2;
        }
        if(!estadoPartida) System.out.println("\n" + ganadorS[ganador]);
        return estadoPartida;
    }
}