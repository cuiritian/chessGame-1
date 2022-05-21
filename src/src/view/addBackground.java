package src.view;
import javax.swing.*;
import java.awt.*;

public class addBackground {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(500,500);
        jFrame.setContentPane(new background());
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    static class background extends JPanel{
        ImageIcon img = new ImageIcon("resource//dio.webp");

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(img.getImage(),0,0,500,500,null);
            //g.drawImage(图片,x坐标,y坐标,宽,长,null);
        }
    }
}
