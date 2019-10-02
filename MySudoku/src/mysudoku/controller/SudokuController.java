/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import mysudoku.model.Game;
import mysudoku.model.Update;
import mysudoku.view.SubPanels;
import mysudoku.view.SudokuPanel;

/**
 *
 * @author porko
 */
public class SudokuController implements MouseListener {

    private Game game;
    private SudokuPanel sudokupanels;

    public SudokuController(SudokuPanel sudokuPanel, Game game) {
        this.game = game;
        this.sudokupanels = sudokuPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel) e.getSource();
        Component component = panel.getComponentAt(e.getPoint());
        if (component instanceof SubPanels) {
            SubPanels subpanel = (SubPanels) component;
            
            int x = subpanel.getSubPanelX(); // lấy vị trí x của ô nhấp chuột
            int y = subpanel.getSubPanelY(); // lấy trị trí y của ô nhấp chuột

            if ((game.getSo(x, y) == 0  || subpanel.getForeground().equals(Color.BLUE))) {
                int so = game.getSoChon(); // lay so ng dung chon
                if (so == -1) {
                    return;
                }

                game.setSo(x, y, so); // lưu vào mảng game 
                subpanel.setNumber(so, true);  

            }else if (!subpanel.getForeground().equals(Color.BLACK)){
                game.setSo(x, y, 0);
                subpanel.setNumber(0, false);
            }

            sudokupanels.update(game, Update.CANDIDATES);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
