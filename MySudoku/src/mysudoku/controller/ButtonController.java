/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import mysudoku.model.Game;
import mysudoku.view.SudokuPanel;


/**
 *
 * @author porko
 */
public class ButtonController implements ActionListener {

    private Game game;
    private SudokuPanel sudokuPanel;

    public ButtonController(Game game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")) {
            game.taoGame();
        } else if (e.getActionCommand().equals("Check")) {
            game.kiemtraGame();           
            if (game.kiemTraWin()) {
                JOptionPane.showMessageDialog(null, "Chiến thắng!");
            }
        } else if (e.getActionCommand().equals("Help on")) {
            game.setHelp(((JCheckBox) e.getSource()).isSelected());
        } else if (e.getActionCommand().equals("Exit")) {
            int ok = JOptionPane.showConfirmDialog(null, "Ban co muon thoat", "Xac thuc thoat", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(ok==0)
                System.exit(0);
        } else {
            game.setSoChon(Integer.parseInt(e.getActionCommand()));
        }

    }
}
