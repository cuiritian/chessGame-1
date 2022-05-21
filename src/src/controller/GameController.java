package src.controller;

import src.model.ChessComponent;
import src.view.Chessboard;
import src.view.Chessboard;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GameController {
    private Chessboard chessboard;

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public List<String> saveGameToFile(String path){
        String res=chessboard.getChessboardGraph();
        FileWriter fwriter = null;
        //String savefile = "E:\\test.txt";
        try {
            fwriter = new FileWriter(path,true);
            fwriter.write(res);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

//————————————————
        //版权声明：本文为CSDN博主「samoyan」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/baoyan2015/article/details/53203370



        return null;
    }

    public List<String> loadGameFromFile(String path) {
        try {
            List<String> chessData = Files.readAllLines(Path.of(path));
            chessboard.loadGame(chessData);
            return chessData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Chessboard getChessboard() {
        return this.chessboard;
    }
}
