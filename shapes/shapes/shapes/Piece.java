
public class Piece{
    private Rectangle[] ficha;
    private boolean tomar;
    private boolean reflejo=false;
    private int estado = 0;
    private boolean cuadrado;
    private int mayor;
    public Piece(String color, int [][] points)
    {   
       ficha = new Rectangle[points.length];
       if(points[0][0]<=points[1][0] && points[0][1]<=points[1][1]){cuadrado = true; mayor = 1;}
       else if(points[0][0]>=points[1][0] && points[0][1]>=points[1][1]){cuadrado = true; mayor = 0;}
       for(int i=0; i<points.length; i++){
           ficha[i] = new Rectangle();
           ficha[i].changeSize(points[i][0]*10, points[i][1]*10);
           ficha[i].changeColor(color);
       }
       tomar = true;
       for(int i=0; i<4; i++){
        rotate();
       }
       ficha[0].makeVisible();
       ficha[1].makeVisible();
       if(cuadrado){
           if(mayor==0){ficha[1].makeInvisible();}
           else{ficha[0].makeInvisible();}
        }
    }
    
    public void take(){
        if(!(tomar)){
            tomar=true;
        }
    }
    
    public void put(){
        if(tomar){
            tomar=false;
        }
    }
    
    public void reflect(){
        if(tomar){
           if(cuadrado){return;}
           int maxWidth = Math.max(ficha[0].getWidth(),ficha[1].getWidth());
           if(ficha[0].getPositionx()==ficha[1].getPositionx()){
               if(estado==0){estado=3;}
               if(estado==1){estado=2;}
               ficha[0].moveHorizontal(maxWidth);
               ficha[1].moveHorizontal(maxWidth);
               ficha[0].moveHorizontal(-ficha[0].getWidth());
               ficha[1].moveHorizontal(-ficha[1].getWidth());
            }
           else{
               if(estado==3){estado=0;}
               if(estado==2){estado=1;}
               ficha[0].moveHorizontal(-maxWidth);
               ficha[1].moveHorizontal(-maxWidth);
               ficha[0].moveHorizontal(ficha[0].getWidth());
               ficha[1].moveHorizontal(ficha[1].getWidth());
            }
        }
    }
    
    public void rotate(){
        if (tomar){
            if(cuadrado){ficha[mayor].changeSize(ficha[mayor].getWidth(),ficha[mayor].getHeight()); return;}
            int moverpieza;
            int coorY = Math.min(ficha[0].getPositiony(), ficha[1].getPositiony());
            int coorX = Math.min(ficha[0].getPositionx(), ficha[1].getPositionx());
            int con;
            int sin;
            ficha[0].changeSize(ficha[0].getWidth(),ficha[0].getHeight());
            ficha[1].changeSize(ficha[1].getWidth(),ficha[1].getHeight());
            if(estado==0){
                ficha[0].setCoor(coorX,coorY);
                ficha[1].setCoor(coorX,coorY);
                estado+=1;
            }
            else if(estado==1){
                if(ficha[1].getHeight()>ficha[0].getHeight()){
                    con = 1;
                    sin = 0;
                }
                else{
                    con = 0;
                    sin = 1;
                }
                ficha[con].moveHorizontal(ficha[sin].getWidth()-ficha[con].getWidth());
                estado+=1;
            }
            else if(estado==2){
                if(ficha[1].getHeight()>ficha[0].getHeight()){
                    con = 1;
                    sin = 0;
                }
                else{
                    con = 0;
                    sin = 1;
                }
                ficha[sin].moveVertical(ficha[con].getHeight()-ficha[sin].getHeight());
                ficha[sin].moveHorizontal(ficha[con].getPositionx()-ficha[sin].getPositionx());
                ficha[con].moveHorizontal(ficha[sin].getWidth()-ficha[con].getWidth());
                estado+=1;
            }
            else{
                if(ficha[1].getPositionx()>ficha[0].getPositionx()){
                    con=1;
                    sin=0;
                }
                else{
                    con=0;
                    sin=1;
                }
                ficha[1].setCoor(coorX, coorY);
                ficha[0].setCoor(coorX, coorY);
                ficha[con].moveVertical(ficha[sin].getHeight()-ficha[con].getHeight());
                estado=0;
            }
        }
    }
    
    public void translate(int dx, int dy){
        if(tomar){
            for(int i=0; i<ficha.length; i++){
                ficha[i].moveHorizontal(dx);
                ficha[i].moveVertical(dy);
            }
        }
    
    }
    
    public void resize(int percentage){
        if(tomar){
            for(int i=0; i<2; i++){
            int area = ficha[i].getWidth()*ficha[i].getHeight();
            int proporcion = (area*percentage)/100;
            proporcion = (int) Math.sqrt(proporcion)/2;
            ficha[i].changeSize(ficha[i].getHeight()+proporcion,ficha[i].getWidth()+proporcion);
            }
        }
    }
}
