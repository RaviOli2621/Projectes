import  java.util.Scanner;
import java.util.SimpleTimeZone;
import MinesXRM.Mines;
import Flota.Undir_la_flota;
import Jack.NigggggggerJack;

public class EscojeJuego
{
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception//comentario para hacer un commit pwkdpoijaehfiuh
    {
        System.out.println("Quin joc vols:");
        System.out.println("1.      Undir la flota");
        System.out.println("2.      Black Jack");
        System.out.println("3.      Busca Minas");

        switch (scan.nextInt())
        {
            case 1:
                Undir_la_flota.jugar();
                break;
            case 2:
                NigggggggerJack.jugar();
                break;
            case 3:
                Mines.jugar();
                break;
        }
    }
}
