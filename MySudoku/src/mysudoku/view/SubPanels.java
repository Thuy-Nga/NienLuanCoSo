/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import mysudoku.controller.SudokuController;
import mysudoku.model.Game;

/**
 *
 * @author porko
 */
public class SubPanels extends JLabel {

    private int x;
    private int y;

    /**
     *
     * @param x toa do cua x
     * @param y toa do cua y
     */
    public SubPanels(int x, int y) {
        super("", CENTER); // mac dinh khong hien thi text va hien thi chinh giua
        this.x = x;
        this.y = y;

        setPreferredSize(new Dimension(60, 60));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        setOpaque(true);
    }

    /**
     * xét số đầu vào và thiết đặt hiển thi lên bàn cờ
     *
     * @param so
     * @param userInput
     */
    public void setNumber(int so, boolean userInput) {
        setForeground(userInput ? Color.BLUE : Color.BLACK);
        setText(so > 0 ? so + "" : "");
    }

    public int getSubPanelX() {
        return x;
    }

    public int getSubPanelY() {
        return y;
    }

}
