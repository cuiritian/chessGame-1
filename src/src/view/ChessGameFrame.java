package src.view;
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


import src.controller.GameController;
import src.model.ChessColor;

import javax.swing.*;
import java.awt.*;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public  final int CHESSBOARD_SIZE;
    private static GameController gameController;
    private static JLabel labelPlayer;
    ImageIcon background =new ImageIcon("resource/b9.jpg");
    //URL resource;


    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;


        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addChessboard();
        addLabel();
        addHelloButton();
        addLoadButton();
        addSaveButton();
        addLabel2();
        addLabelPlayer();
        changePlayer();
        addRestartButton();
        addBackgroundLabel();


    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        JLabel statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }

    private void addLabel2(){
        JLabel statusLabel = new JLabel("Current Player:");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10+30);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }

    private void addLabelPlayer(){
        labelPlayer=new JLabel("Black");
        labelPlayer.setLocation(HEIGTH, HEIGTH / 10+60);
        labelPlayer.setSize(200, 60);
        labelPlayer.setFont(new Font("Rockwell", Font.BOLD, 30));
        add(labelPlayer);
    }

    public static void changePlayer(){
        labelPlayer.setText(gameController.getChessboard().getCurrentColor().getName());
        labelPlayer.repaint();
    }


    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton() {
        JButton button = new JButton("Show Hello Here");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "我太难了！"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

    private void addSaveButton() {
        JButton button = new JButton("Save the game");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click save");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.saveGameToFile(path);
        });
    }

    private void addRestartButton() {
        JButton button = new JButton("Restart");
        button.setLocation(HEIGTH, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            String path = "restart";
            gameController.loadGameFromFile("restart");
            //System.out.println("Click save");
            //String path = JOptionPane.showInputDialog(this,"Input Path here");
//            Chessboard chessboard2 = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
//            chessboard2.repaint();
        });
    }
    private void addBackgroundLabel() {//((JFrame)this.getContentPane()).getOpaque(false);//test为当前的类，jpanel_pci.jpg自动添加到当前项目的包中
        //background.setImage(
                //background.getImage().
                        //getScaledInstance(background.getIconWidth(),background.getIconHeight(), Image.SCALE_DEFAULT));
//————————————————
        JLabel picJLabel=new JLabel(background);
        //ImageIcon background =new ImageIcon("resource/dio.webp");
        //System.out.println(background.getIconHeight() + " " + background.getIconWidth());
        picJLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        //picJLabel.setIcon(background);
        add(picJLabel);
        //frame.getContentPane().add(picJLabel);  ;
    }






}
