package src.model;

import src.controller.ClickController;
import src.view.ChessboardPoint;
import src.view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BishopChessComponent extends ChessComponent{
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;
    private Image bishopImage;

    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
        }
    }

    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateBishopImage(color);
        if (this.chessColor==ChessColor.BLACK){
            this.name='B';
        }
        if (this.chessColor==ChessColor.WHITE){
            this.name='b';
        }
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int sX=source.getX();
        int sY=source.getY();
        int dX=destination.getX();int dY=destination.getY();
        int a=Math.max(sX,dX)-Math.min(sX,dX);
        int b=Math.max(sY,dY)-Math.min(sY,dY);
        if (chessComponents[dX][dY].getChessColor()!=chessComponents[sX][sY].getChessColor()){
            if (a==b){
                if (dX>sX&&dY>sY){
                    for (int i=1;i<a;i++){
                        if (!(chessComponents[sX+i][sY+i] instanceof EmptySlotComponent)){
                            return false;
                        }
                    }
                }
                if (dX<sX&&dY<sY){
                    for (int i=1;i<a;i++){
                        if (!(chessComponents[sX-i][sY-i] instanceof EmptySlotComponent)){
                            return false;
                        }
                    }
                }
                if (dX>sX&&dY<sY){
                    for (int i=1;i<a;i++){
                        if (!(chessComponents[sX+i][sY-i] instanceof EmptySlotComponent)){
                            return false;
                        }
                    }
                }
                if (dX<sX&&dY>sY){
                    for (int i=1;i<a;i++){
                        if (!(chessComponents[sX-i][sY+i] instanceof EmptySlotComponent)){
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

}
