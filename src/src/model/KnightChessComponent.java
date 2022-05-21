package src.model;

import src.controller.ClickController;
import src.view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class KnightChessComponent extends ChessComponent{
    private static Image KNIGHT_WHITE;
    private static Image KNIGHT_BLACK;
    private Image knightImage;
    public void loadResource() throws IOException {
        if (KNIGHT_WHITE == null) {
            KNIGHT_WHITE = ImageIO.read(new File("./images/knight-white.png"));
        }

        if (KNIGHT_BLACK == null) {
            KNIGHT_BLACK = ImageIO.read(new File("./images/knight-black.png"));
        }
    }

    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                knightImage = KNIGHT_WHITE;
            } else if (color == ChessColor.BLACK) {
                knightImage = KNIGHT_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateKnightImage(color);
        if (this.chessColor==ChessColor.BLACK){
            this.name='N';
        }
        if (this.chessColor==ChessColor.WHITE){
            this.name='n';
        }
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int sX=source.getX(); int sY=source.getY();
        int dX=destination.getX();
        int dY=destination.getY();
        if (dX==sX+1&&dY==sY+2){
            if (chessComponents[dX][dY].getChessColor()!=chessComponents[sX][sY].getChessColor()){
                return true;
            }
        }
        if (dX==sX+1&&dY==sY-2){
            if (chessComponents[dX][dY].getChessColor()!=chessComponents[sX][sY].getChessColor()){
                return true;
            }
        }
        if (dX==sX-1&&dY==sY+2){
            if (chessComponents[dX][dY].getChessColor()!=chessComponents[sX][sY].getChessColor()){
                return true;
            }
        }
        if (dX==sX-1&&dY==sY-2){
            if (chessComponents[dX][dY].getChessColor()!=chessComponents[sX][sY].getChessColor()){
                return true;
            }
        }
        if (dX==sX+2&&dY==sY+1){
            if (chessComponents[dX][dY].getChessColor()!=chessComponents[sX][sY].getChessColor()){
                return true;
            }
        }
        if (dX==sX+2&&dY==sY-1){
            if (chessComponents[dX][dY].getChessColor()!=chessComponents[sX][sY].getChessColor()){
                return true;
            }
        }
        if (dX==sX-2&&dY==sY+1){
            if (chessComponents[dX][dY].getChessColor()!=chessComponents[sX][sY].getChessColor()){
                return true;
            }
        }
        if (dX==sX-2&&dY==sY-1){
            if (chessComponents[dX][dY].getChessColor()!=chessComponents[sX][sY].getChessColor()){
                return true;
            }
        }
    return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(knightImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

}
