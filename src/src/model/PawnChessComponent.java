package src.model;

import src.controller.ClickController;
import src.view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PawnChessComponent extends ChessComponent{
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;
    private Image pawnImage;

    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
        if (this.chessColor==ChessColor.BLACK){
            this.name='P';
        }
        if (this.chessColor==ChessColor.WHITE){
            this.name='p';
        }
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int sX=source.getX();
        int sY=source.getY();
        int dX=destination.getX();
        int dY=destination.getY();
        if (chessComponents[sX][sY].getChessColor()==ChessColor.BLACK){
            if (sX==1){
                if (dY==sY&&dX==sX+1){
                    if (chessComponents[dX][dY] instanceof EmptySlotComponent){
                        return true;
                    }
                }
                if (dY==sY&&dX==sX+2){
                    if (chessComponents[sX+1][dY] instanceof EmptySlotComponent&&chessComponents[dX][dY] instanceof EmptySlotComponent){
                        return true;
                    }
                }
                if (dX==sX+1&&dY==sY-1){
                    if (chessComponents[dX][dY].getChessColor()==ChessColor.WHITE){
                        return true;
                    }
                }
                if (dX==sX+1&&dY==sY+1){
                    if (chessComponents[dX][dY].getChessColor()==ChessColor.WHITE){
                        return true;
                    }
                }
                return false;
            }
            else {
                if (dX==sX+1&&dY==sY){
                    if (chessComponents[dX][dY]instanceof EmptySlotComponent){
                        return true;
                    }
                }
                if (dX==sX+1&&dY==sY-1){
                    if (chessComponents[dX][dY].getChessColor()==ChessColor.WHITE){
                        return true;
                    }
                }
                if (dX==sX+1&&dY==sY+1){
                    if (chessComponents[dX][dY].getChessColor()==ChessColor.WHITE){
                        return true;
                    }
                }
                return false;
            }
        }
        if (chessComponents[sX][sY].getChessColor()==ChessColor.WHITE){
            if (sX==6){
                if (dY==sY&&dX==sX-1){
                    if (chessComponents[dX][dY] instanceof EmptySlotComponent){
                        return true;
                    }
                }
                if (dY==sY&&dX==sX-2){
                    if (chessComponents[sX-1][dY] instanceof EmptySlotComponent&&chessComponents[dX][dY] instanceof EmptySlotComponent){
                        return true;
                    }
                }
                if (dX==sX-1&&dY==sY-1){
                    if (chessComponents[dX][dY].getChessColor()==ChessColor.BLACK){
                        return true;
                    }
                }
                if (dX==sX-1&&dY==sY+1){
                    if (chessComponents[dX][dY].getChessColor()==ChessColor.BLACK){
                        return true;
                    }
                }
                return false;
            }
            else {
                if (dX==sX-1&&dY==sY){
                    if (chessComponents[dX][dY]instanceof EmptySlotComponent){
                        return true;
                    }
                }
                if (dX==sX-1&&dY==sY-1){
                    if (chessComponents[dX][dY].getChessColor()==ChessColor.BLACK){
                        return true;
                    }
                }
                if (dX==sX-1&&dY==sY+1){
                    if (chessComponents[dX][dY].getChessColor()==ChessColor.BLACK){
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
