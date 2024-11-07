package MinesXRM.Controlador;
import MinesXRM.Model.Model;
import MinesXRM.Vista.Vista;

import java.util.Scanner;
public class Controlador
{
    static Scanner scan = new Scanner(System.in);
    static boolean acabarJuego = false;
    static int numBanderas;

    static int [] datos;

    public static void jugar()
    {
        numBanderas = 0;
        datos = tamano();
        String []coord = new String[2];
        Model.InicialitzarElJoc(datos[0],datos[1],datos[2]);
        do {
            Model.mostrarCampo();
            Vista.pedirmodo(numBanderas);
            escojerModo();
        }while (!Model.continuarPartida() && !acabarJuego);
    }
    public static int[] tamano()
    {
        int files = 2, columnes = 1, bombes = 1;
        boolean bandera;
        Vista.pedriTamanoPred();
        do {
            bandera = false;
            switch (scan.next())
            {
                case "1":
                    files = 8;
                    columnes = 8;
                    bombes = 10;
                    break;
                case "2":
                    files = 16;
                    columnes = 16;
                    bombes = 40;
                    break;
                case "3":
                    files = 50;
                    columnes = 26;
                    bombes = 150;
                    break;
                case "4":
                    Vista.pedirTamano(); // texto pidiendo la informacion
                    files = Integer.parseInt(scan.next().replace(","," ").trim());
                    if(files > 50) files = 50;
                    columnes = Integer.parseInt(scan.next().replace(","," ").trim());
                    if(columnes > 26) columnes = 26;
                    bombes = Integer.parseInt(scan.next().replace(","," ").trim());
                    break;
                default:
                    bandera = true;
                    System.out.println("Seleccione un valor valido");
                    break;
            }
        }while (bandera);
        int [] datos = {files, columnes, bombes};
        return datos;
    }
    public static void escojerModo()
    {
        String [] coordSep = new String[2];
        boolean bandera;
        do {
            bandera = false;
            switch (scan.next().toUpperCase())
            {
                case "A":
                    do // el do while es para comprobar que los numeros no se salen de los parametros esperados
                    {
                        Vista.pedirCorrd();
                        coordSep = Model.procesarCoord(scan.next());
                        //System.out.println(coordSep[0] + " " + coordSep[1]);
                    }while (coordSep[0].equalsIgnoreCase("Error") || Integer.parseInt(coordSep[0]) > datos[1] || coordSep[1].equalsIgnoreCase("Error") || Integer.parseInt(coordSep[1]) > datos[0]);
                    Model.trepitjar(coordSep);
                    break;
                case "M":
                    do
                    {
                        Vista.pedirCorrd();
                        coordSep = Model.procesarCoord(scan.next());
                        //System.out.println(coordSep[0] + " " + coordSep[1]);
                    }while (coordSep[0].equalsIgnoreCase("Error") || Integer.parseInt(coordSep[0]) > datos[1] || coordSep[1].equalsIgnoreCase("Error") || Integer.parseInt(coordSep[1]) > datos[0]);
                    numBanderas = Model.bandera(coordSep, numBanderas);
                    break;
                case "R":
                    acabarJuego = true;
                    break;
                default:
                    bandera = true;
                    System.out.println("Introdueix una dada valida");
                    break;
            }
        }while (bandera);
    }

}
