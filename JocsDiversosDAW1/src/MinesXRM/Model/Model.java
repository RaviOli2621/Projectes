package MinesXRM.Model;

import MinesXRM.Vista.Vista;

public class Model {
    static String[][] tablero = new String[0][0];
    static String[][] minas = new String[tablero.length][tablero.length];

    static int Files, Columnes, Bombes;
    public static void InicialitzarElJoc(int files, int columnes, int bombes)
    {
        Files = files + 2;
        Columnes = columnes + 2;
        Bombes = bombes;
        GenerarCamp();
        GenerarBombes();
        ComptarBombes();
        //Vista.verCamp(Files, Columnes, minas);
    }
    public static void mostrarCampo()
    {

        System.out.println();
        Vista.verCamp(Files, Columnes, tablero);
    }

    private static void GenerarCamp()
    {

        tablero = new String[Columnes][Files];

        for (int i = 0; i < Columnes; i++) {
            for (int j = 0; j < Files; j++) {
                if (i == 0 || i == Columnes - 1) {
                    if (j == 0 || j == Files - 1) tablero[i][j] = "+";
                    else tablero[i][j] = "---";
                } else {
                    if (j == 0 || j == Files - 1) tablero[i][j] = "|";
                    else tablero[i][j] = " # ";
                }
            }
        }
    }
    private static void GenerarBombes()
    {
        int [] posMinas = new int[2]; // posició probisional de una mina habans de ser colocada
        boolean noRep = true; // si on es vo posar una mina ja hi ha una es true i es torna a probar

        minas = new String[Columnes][Files]; //declarar tamaño minas

        for (int i = 0; i < Columnes; i++)
        {
            for (int j = 0; j < Files; j++)
            {
                minas[i][j] = tablero[i][j];
            }
        }

        for (int i = 0; i < Bombes; i++)
        {
            posMinas[0] = 0;
            posMinas[1] = 0;
            do {
                posMinas[0] = (int)(Math.random()*(Files-1-1)+1);
                posMinas[1] = (int)(Math.random()*(Columnes-1-1)+1);
                if(minas[posMinas[1]][posMinas[0]].equalsIgnoreCase(" º ")) noRep = false;
                else
                {
                    noRep = true;
                    minas[posMinas[1]][posMinas[0]] = " º ";
                }
            }while (!noRep);
        }
        System.out.println();
    }
    private static void ComptarBombes()
    {
        int contador = 0;
        for (int i = 1; i < Columnes-1; i++)//vertical
        {
            for (int j = 1; j < Files-1; j++)//horizontal
            {
                contador = 0;
                if(!minas[i][j].equalsIgnoreCase(" º "))
                {
                    //mirar derecha/izquierda
                    if(minas[i][j+1].equalsIgnoreCase(" º ")) contador++;
                    if(minas[i][j-1].equalsIgnoreCase(" º ")) contador++;
                    //arriba abajo
                    if(minas[i+1][j].equalsIgnoreCase(" º ")) contador++;
                    if(minas[i-1][j].equalsIgnoreCase(" º ")) contador++;
                    //diagonales
                    if(minas[i+1][j+1].equalsIgnoreCase(" º ")) contador++;
                    if(minas[i+1][j-1].equalsIgnoreCase(" º ")) contador++;
                    if(minas[i-1][j+1].equalsIgnoreCase(" º ")) contador++;
                    if(minas[i-1][j-1].equalsIgnoreCase(" º ")) contador++;
                    if(contador == 0) minas[i][j] = "   ";
                    else minas[i][j] = " " + contador + " ";
                }
            }
        }
    }
    public static String [] procesarCoord(String coords)
    {
        String [] coordSep = new String[2];
        String [] numeros = {"0","1","2","3","4","5","6","7","8","9"};
        boolean correcte;
        correcte = false;
        if(coords.length() < 2)
        {
            coordSep[0] = "Error";
            return coordSep;
        }
        for (int i = ('a'); i <= ('z'); i++) //comprobar que el primer valor es una lletra
        {
            if((coords.charAt(0) + "").equalsIgnoreCase((char)(i) + ""))
            {
                coordSep[0] = coords.charAt(0) + "";
                coordSep[0] = procesarLetra(coordSep[0].toLowerCase());
                correcte = true;
                break;
            }
        }
        if(!correcte)
        {
            coordSep[0] = "Error";
            return coordSep;
        }
        coordSep[1] = coords.substring(1);//comprobar que todos lo que queda son numeros
        for (int i = 0; i < coordSep[1].length(); i++)
        {
            correcte = false;
            for (int j = 0; j < 10; j++)
            {
                if((coordSep[1].charAt(i) + "").equals(numeros[j]))
                {
                    correcte = true;
                    break;
                }
            }
            if(!correcte)
            {
                coordSep[1] = "Error";
                return coordSep;
            }
        }
        return coordSep; // falta traducir la letra a numero
    }
    public static String procesarLetra(String letra)
    {
        letra = letra.charAt(0)-'a'+1 + "";
        return letra;
    }
    public static void trepitjar(String [] coord)
    {
        if(tablero[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])].equals(" # "))
        {
            if(!(minas[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])].equals("   "))) // si lo que tocas no es un 0 por asi decirlo
            {
                tablero[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])] = minas[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])];
            }else
            {
                tablero[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])] = minas[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])];
                desbloquear(Integer.parseInt(coord[1]),Integer.parseInt(coord[0]));
            }
        }
    }
    public static void desbloquear(int fila,int columna)
    {
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                if(tablero[columna+i][fila+j].equalsIgnoreCase(" # "))
                {
                    tablero[columna+i][fila+j] = minas[columna+i][fila+j];
                    if(tablero[columna+i][fila+j].equalsIgnoreCase("   ")) desbloquear(fila+j,columna+i);
                }
            }
        }
    }
    public static int bandera(String [] coord,int numBanderas)
    {

        if(tablero[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])].equals(" # "))
        {
            tablero[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])] = " M ";
            numBanderas++;
        }
        else if(tablero[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])].equals(" M "))
        {
            tablero[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])] = " # ";
            numBanderas--;
        }
        return numBanderas;
    }
    public static boolean continuarPartida()
    {
        boolean noGana = false,acabar = false;
        for (int i = 0; i < tablero.length; i++)
        {
            for (int j = 0; j < tablero[0].length; j++)
            {
                if(tablero[i][j].equalsIgnoreCase(minas[i][j])) // si las casillas coinciden
                {
                    if(minas[i][j].equalsIgnoreCase(" º "))// si la casilla que coinciden son bombas
                    {
                        acabar = true;
                        noGana = true;
                        break;
                    }
                }else if(!(minas[i][j]).equalsIgnoreCase(" º ")) noGana = true;
            }
        }
        if(acabar)
        {
            for (int i = 0; i < tablero.length; i++)
            {
                for (int j = 0; j < tablero[0].length; j++)
                {
                    tablero[i][j] = minas[i][j];
                }
            }
            Vista.verCamp(tablero[0].length,tablero.length,tablero);
            System.out.println("Perdiste");
        }
        if(!noGana)
        {
            Vista.verCamp(tablero[0].length,tablero.length,tablero);
            System.out.println("Ganaste");
            acabar = true;
        }
        return acabar;
    }
}