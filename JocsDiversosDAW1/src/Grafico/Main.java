package Grafico;

import Grafico.graficos.*;
import Grafico.graficos.Canvas;
import Grafico.graficos.Color;
import Grafico.graficos.Rectangle;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class Main
{
    static Ellipse egg = new Ellipse(100,100,40,60);
    //https://horstmann.com/sjsu/graphics/ = guia
    public static void main(String[] args) {
        Rectangle box = new Rectangle(0,0,2000,1000);
        box.setColor(Color.CYAN);
        box.fill();

        egg.setColor(Color.YELLOW);
        egg.fill();

        mover();
    }
    public static void mover()
    {
        try {
            for (int i = 0; i < 10; i++)
            {
                egg.setColor(Color.YELLOW);
                egg.translate(10,10);
                egg.fill();
                Thread.sleep((long)(1));
                Canvas.snapshot();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
