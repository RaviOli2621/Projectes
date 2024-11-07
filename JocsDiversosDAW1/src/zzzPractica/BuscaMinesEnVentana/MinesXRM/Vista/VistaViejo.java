package zzzPractica.BuscaMinesEnVentana.MinesXRM.Vista;

public class VistaViejo
{
    public static void pedriTamanoPred()
    {
        System.out.println("Seleccione un tamaño:");
        System.out.println("1.  8X8 con 10 bombas");
        System.out.println("2.  16X16 con 40 bombas");
        System.out.println("3.  26X50 con 150 bombas (Tabla más grande)");
        System.out.println("4.  Personalizar");
    }
    public static void pedirTamano()
    {
        System.out.println("Seleccione el numero de filas, columnas i bombas (separados por ', ')");
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
    public static void verCamp(int files, int columnes, String[][] tablero)
    {
        for (int i = 0; i < columnes; i++)
        {
            if (i == 0) {
                System.out.print("  ");
                for (int j = 0; j < files - 1; j++) {
                    if (j != 0) System.out.printf("%-3d", j);
                    else System.out.print("   ");
                }
                System.out.println();
                System.out.print("   ");
            } else if (i != columnes - 1) {
                System.out.printf("%-3c", ('a' + i - 1));
            } else System.out.print("   ");
            for (int j = 0; j < files; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }
}
