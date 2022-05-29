package view;
import java.awt.EventQueue;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import controller.GameController;
import model.ChessColor;

import javax.swing.*;
import java.awt.*;

//import static view.Chessboard.step;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public  final int CHESSBOARD_SIZE ;
    private static GameController gameController;
    private static JLabel labelPlayer;
    ImageIcon background1 =new ImageIcon("resource/b9.jpg");
    ImageIcon background2 =new ImageIcon("resource/b10.jpg");
    private JPanel panel1=new JPanel();
    private JPanel panel2=new JPanel();
    //private Music music;

    private Chessboard chessboard;//=new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
    //URL resource;


    public ChessGameFrame(int width, int height) {
        //this.chessboard=chessboard;
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;


        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        panel1.setSize(WIDTH, HEIGTH);
        panel2.setSize(WIDTH, HEIGTH);
        panel1.setLayout(null);
        panel2.setLayout(null);
        getContentPane().add(panel1);
        //music.player();



        addChessboard(panel2);
        addLabel(panel2);
        addHelloButton(panel2);
        addLoadButton(panel2);
        addSaveButton(panel2);
        addLabel2(panel2);
        addLabelPlayer(panel2);
        changePlayer();
        addRestartButton(panel2);
        addBackChessButton(panel2);
        addBackgroundLabel1(panel2);
        addStartButton(panel1);
        addBackgroundLabel2(panel1);
        addHelloButton2(panel1);

    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard(JPanel panel) {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        panel.add(chessboard);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel(JPanel panel) {
        JLabel statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel);
    }

    private void addLabel2(JPanel panel){
        JLabel statusLabel = new JLabel("Current Player:");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10+30);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel);
    }

    private void addLabelPlayer(JPanel panel){
        labelPlayer=new JLabel("Black");
        labelPlayer.setLocation(HEIGTH, HEIGTH / 10+60);
        labelPlayer.setSize(200, 60);
        labelPlayer.setFont(new Font("Rockwell", Font.BOLD, 30));
        panel.add(labelPlayer);
    }

    public static void changePlayer(){
        labelPlayer.setText(gameController.getChessboard().getCurrentColor().getName());
        labelPlayer.repaint();
    }


    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton(JPanel panel) {
        JButton button = new JButton("Show Hello Here");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "我太难了！"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 110);
        button.setSize(180, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);
    }

    private void addLoadButton(JPanel panel) {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 220);
        button.setSize(180, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

    private void addSaveButton(JPanel panel) {
        JButton button = new JButton("Save the game");
        button.setLocation(HEIGTH, HEIGTH / 10 + 330);
        button.setSize(180, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);
        button.addActionListener(e -> {
            System.out.println("Click save");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.saveGameToFile(path);
        });
    }

    private void addRestartButton(JPanel panel) {
        JButton button = new JButton("Restart");
        button.setLocation(HEIGTH, HEIGTH / 10 + 440);
        button.setSize(180, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);
        button.addActionListener(e -> {
            String path = "restart";
            gameController.loadGameFromFile("restart.txt");
            gameController.getChessboard().setBlackBeAtk(0);
            gameController.getChessboard().setWhiteBeAtk(0);
            //System.out.println("Click save");
            //String path = JOptionPane.showInputDialog(this,"Input Path here");
//            Chessboard chessboard2 = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
//            chessboard2.repaint();
        });
    }

    private void addBackChessButton(JPanel panel) {
        JButton button = new JButton("Back chess");
        button.setLocation(HEIGTH, HEIGTH / 10 + 550);
        button.setSize(180, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);
        button.addActionListener(e -> {
            System.out.println("Click back");
            gameController.getChessboard().loadGame(gameController.getChessboard().getStep().get(gameController.getChessboard().getStep().size()-2));
            gameController.getChessboard().getStep().remove(gameController.getChessboard().getStep().size()-1);

            //String path = JOptionPane.showInputDialog(this,"Input Path here");
            //gameController.saveGameToFile(path);
        });
    }

    private void addStartButton(JPanel panel) {
        JButton button = new JButton("Start game");
        button.setLocation(400, HEIGTH / 10 + 250);
        button.setSize(180, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);
        button.addActionListener(e -> {
            System.out.println("Click start");
            remove(panel1);
            getContentPane().add(panel2);
            panel2.repaint();


            //String path = JOptionPane.showInputDialog(this,"Input Path here");
            //gameController.saveGameToFile(path);
        });
    }



    private void addBackgroundLabel1(JPanel panel) {//((JFrame)this.getContentPane()).getOpaque(false);//test为当前的类，jpanel_pci.jpg自动添加到当前项目的包中
        //background.setImage(
                //background.getImage().
                        //getScaledInstance(background.getIconWidth(),background.getIconHeight(), Image.SCALE_DEFAULT));
//————————————————
        JLabel picJLabel=new JLabel(background1);
        //ImageIcon background =new ImageIcon("resource/dio.webp");
        //System.out.println(background.getIconHeight() + " " + background.getIconWidth());
        picJLabel.setBounds(0, 0, background1.getIconWidth(), background1.getIconHeight());
        //picJLabel.setIcon(background);
        panel.add(picJLabel);
        //frame.getContentPane().add(picJLabel);  ;
    }
    private void addBackgroundLabel2(JPanel panel) {//((JFrame)this.getContentPane()).getOpaque(false);//test为当前的类，jpanel_pci.jpg自动添加到当前项目的包中
        //background.setImage(
        //background.getImage().
        //getScaledInstance(background.getIconWidth(),background.getIconHeight(), Image.SCALE_DEFAULT));
//————————————————
        JLabel picJLabel=new JLabel(background2);
        //ImageIcon background =new ImageIcon("resource/dio.webp");
        //System.out.println(background.getIconHeight() + " " + background.getIconWidth());
        picJLabel.setBounds(0, 0, background2.getIconWidth(), background2.getIconHeight());
        //picJLabel.setIcon(background);
        panel.add(picJLabel);
        //frame.getContentPane().add(picJLabel);
    }
    private void addHelloButton2(JPanel panel) {
        JButton button = new JButton("Super large AI!");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "没时间写了啊啊啊啊！"));
        button.setLocation(400, HEIGTH / 10 + 150);
        button.setSize(180, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);
    }
}
