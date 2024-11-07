package Jack;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NigggggggerJack
{
        static Scanner scan = new Scanner(System.in);
        public static void jugar() throws Exception
        {
            String [][] cartes = Cartas(); //arriba puntos abajo ases
            boolean [] plantados = new boolean[5];
            float [][] puntos = {
                                    {0f,0f,0f,0f,0f}, //puntos sin as
                                    {0f,0f,0f,0f,0f}  //numero de ases
                                };
            String resposta;
            while (!(plantados[1] && plantados[2] && plantados[3] && plantados[4]))
            {
                for (int i = 1; i < 5; i++)
                {
                    if(!plantados[i])
                    {
                        System.out.println("Jugador " + i);
                        RobarCarta(puntos,cartes,i);
                        System.out.println();
                    }
                }
                plantar(plantados,puntos);
            }
            IA(puntos,cartes);
        }
        public static void IA(float[][]puntos,String[][]cartes) throws Exception
        {
            int maxPunts = 0,jugadorMax = 0;
            for (int i = 1; i < puntos[0].length; i++)
            {
                if(maxPunts < puntos[0][i] && puntos[0][i] <= 21)
                {
                    maxPunts = (int)puntosConAs(puntos,i,true);
                    jugadorMax = i;
                }
            }
            while (maxPunts > puntosConAs(puntos,0,true))
            {
                RobarCarta(puntos,cartes,0);
                if(puntosConAs(puntos,0,true) == -1 || puntosConAs(puntos,0,true) > 21)
                {
                    System.out.println("La banca se paso");
                    break;
                }
                TimeUnit.SECONDS.sleep(2);
            }
            if((maxPunts <= puntosConAs(puntos,0,true) && puntosConAs(puntos,0,true) <= 21) || maxPunts == 0)
            {
                System.out.println("La banca gana");
            }else
            {
                for (int i = 1; i < puntos[0].length; i++)
                {
                    if(maxPunts == puntos[0][i])
                    {
                        System.out.println("EL jugador " + jugadorMax + " gana");
                    }
                }
            }
        }
        public static float puntosConAs(float[][]puntos,int jugador,boolean max)
        {
            float punts = puntos[0][jugador], ases = puntos[1][jugador];
            if(max)
            {
                while (punts <= 21 && ases > 0)
                {
                    punts += 11;
                    ases--;
                }
                if(ases != puntos[1][jugador] && punts > 21)
                {
                    punts -= 11;
                    ases++;
                    while (punts <= 21 && ases > 0)
                    {
                        punts += 1;
                        ases--;
                    }
                }
                if(ases > 0) return -1;
                else
                {
                    //System.out.println(punts + " puntos con el as");
                    return punts;
                }
            }
            return puntos[0][jugador];
        }
        public static void plantar(boolean [] plantados,float [][] puntos)
        {
            String[] jugador;
            String respuesta;
            for (int i = 0; i < plantados.length-1; i++)
            {
                if(puntosConAs(puntos,i+1,true) == -1 || puntosConAs(puntos,i+1,true) > 21)
                {
                    plantados[i+1] = true;
                    System.out.println("El jugador " + (int)(i+1) + " se paso");
                }
            }
            System.out.print("Que jugadores se van a plantar (ejem: J1, J2 o 'nadie' si no se plantan): ");
            respuesta = scan.nextLine().trim();
            jugador = respuesta.toLowerCase().split(", ");
            if(!jugador[0].equalsIgnoreCase("nadie"))
            {
                for (int i = 0; i < jugador.length; i++)
                {
                    plantados[Integer.parseInt(jugador[i].charAt(1) + "")] = true;
                }
            }
        }
        public static String[][] Cartas()
    {
        String palos [] = {"Diamantes", "Corazones", "Picas", "Treboles"};
        String figures [] = {"A","2","3","4","5","6","7","8","9","10","Jack","Reina","Rei"};
        String [][] cartes = new String[palos.length][figures.length];
        for (int i = 0; i < palos.length; i++)
        {
            for (int j = 0; j < figures.length; j++)
            {
                cartes[i][j] = palos[i] + " " + figures[j];
            }
        }
        return cartes;
    }
    public static void RobarCarta(float [][] puntos,String[][]cartes ,int jugador)
    {
        String [] carta = {" "," "};
        String cartaFull;
        int [] coordenades = new int[2];
        boolean repetir;
        do {
            repetir = false;
                coordenades[0] = (int)(Math.random()*cartes.length);
                coordenades[1] = (int)(Math.random()*cartes[0].length);
                cartaFull = cartes[coordenades[0]][coordenades[1]];
                if(!cartaFull.equalsIgnoreCase("pacoJuan"))
                {
                    carta = cartaFull.split(" ");
                    if(carta[1].equalsIgnoreCase("Jack") || carta[1].equalsIgnoreCase("Reina") || carta[1].equalsIgnoreCase("Rei"))
                    {
                        carta[1] = "10";
                        puntos[0][jugador] += Float.parseFloat(carta[1]);
                    } else if (carta[1].equalsIgnoreCase("A"))
                    {
                        puntos[1][jugador]++;
                    }else
                    {
                        puntos[0][jugador] += Float.parseFloat(carta[1]);
                    }
                    System.out.println(cartaFull);
                    System.out.println(puntos[0][jugador] + " puntos + " + (int)(puntos[1][jugador]) + " As/es");
                    cartes[coordenades[0]][coordenades[1]] = "pacoJuan";
                }else repetir = true;
        }while (repetir);
    }
}
