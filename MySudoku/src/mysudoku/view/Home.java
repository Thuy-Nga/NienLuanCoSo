/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author porko
 */
public class Home extends JFrame {

    JButton btnBD, btnThoat;

    public Home() {
        super("Game Sudoku");
        addControls(); 
        addEvents();
        showMyWin(); // hiển thị lên màn hình
    }

    private void addEvents() {
        btnBD.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // tắt jframe home 
                new MySudoku();                
            }
        });
    }

    private void addControls() {
        getContentPane().setLayout(new BorderLayout(15, 15));

        JLabel lblTieuDe = new JLabel("SUDOKU");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 50));
        lblTieuDe.setForeground(Color.BLUE);
        lblTieuDe.setHorizontalAlignment((int) CENTER_ALIGNMENT); // canh văn bản theo chiều ngang
        lblTieuDe.setVerticalAlignment((int) CENTER_ALIGNMENT); // theo dọc

        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.PAGE_AXIS));
        JPanel pnimg = new JPanel();

        JLabel lblImg = new JLabel(new ImageIcon("img/sudoku.jpg"));
        lblImg.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblImg.setVerticalAlignment((int) CENTER_ALIGNMENT);

        pnimg.add(lblImg);
        pnCenter.add(pnimg);

        JPanel pnSouth = new JPanel();
        btnBD = new JButton("START");
        btnBD.setAlignmentX(CENTER_ALIGNMENT);
        btnBD.setPreferredSize(new Dimension(100, 50));
        btnBD.setFont(new Font("Arial", Font.BOLD, 20));
        pnSouth.add(btnBD);
        
        add(lblTieuDe, BorderLayout.NORTH);
        add(pnCenter, BorderLayout.CENTER);
        add(pnSouth, BorderLayout.SOUTH);

    }

    private void showMyWin() {
        this.setSize(378, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Home();
    }
}
