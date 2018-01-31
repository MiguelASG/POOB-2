import java.util.*;
public class PolygonalPuzzle
{
    private Rectangle Tablero; 
    private ArrayList<Piece> Piezas;
    private int fichaActual=-1;
    private int sizePiezas = 0;
    public PolygonalPuzzle(int sizeX, int sizeY)
    {
        Tablero = new Rectangle();
        Tablero.setCoor(0,0);
        Tablero.changeSize(sizeX,sizeY);
        Tablero.changeColor("black");
        Tablero.makeVisible();
        Piezas = new ArrayList<Piece>();
    }
    
    public void CrearPieza(){
        int[][] Posiciones = new int[2][2];
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                int numeroAleatorio = (int) (Math.random()*10+1);
                Posiciones[i][j] = numeroAleatorio;
            }
        }
        sizePiezas+=1;
        fichaActual=sizePiezas-1;
        Piezas.add(new Piece("red", Posiciones));
    }
    public void tomarPieza(int pieza){
        if(pieza<=sizePiezas){fichaActual = pieza-1;}
    }
    public void Rotar(){
        if(fichaActual!=-1){Piezas.get(fichaActual).rotate();}
    }
    public void Reflejar(){
        if(fichaActual!=-1){Piezas.get(fichaActual).reflect();}
    }
    public void Trasladar(int dx, int dy){
        if(fichaActual!=-1){Piezas.get(fichaActual).translate(dx,dy);}
    }
    public void cambiarSize(int percentage){
        if(fichaActual!=-1){Piezas.get(fichaActual).resize(percentage);}
    }
    public void ponerPieza(){
        fichaActual=-1;
    }
}
