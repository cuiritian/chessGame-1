package src.model;

import src.controller.ClickController;
import src.view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class QueenChessComponent extends ChessComponent{
    private static Image QUEEN_WHITE;
    private static Image QUEEN_BLACK;
    private Image queenImage;

    public void loadResource() throws IOException {
        if (QUEEN_WHITE == null) {
            QUEEN_WHITE = ImageIO.read(new File("./images/queen-white.png"));
        }

        if (QUEEN_BLACK == null) {
            QUEEN_BLACK = ImageIO.read(new File("./images/queen-black.png"));
        }
    }

    private void initiateQueenImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                queenImage = QUEEN_WHITE;
            } else if (color == ChessColor.BLACK) {
                queenImage = QUEEN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateQueenImage(color);
        if (this.chessColor==ChessColor.BLACK){
            this.name='Q';
        }
        if (this.chessColor==ChessColor.WHITE){
            this.name='q';
        }
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (source.getX() == destination.getX()) {
            int row = source.getX();
            for (int col = Math.min(source.getY(), destination.getY()) + 1;
                 col < Math.max(source.getY(), destination.getY()); col++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
            return true;
        }
        else if (source.getY() == destination.getY()) {
            int col = source.getY();
            for (int row = Math.min(source.getX(), destination.getX()) + 1;
                 row < Math.max(source.getX(), destination.getX()); row++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
            return true;
        }
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
        g.drawImage(queenImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
