/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku.view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import mysudoku.controller.SudokuController;
import mysudoku.model.Game;
import mysudoku.model.Update;

/**
 *
 * @author porko
 */
public class SudokuPanel extends JPanel implements Observer {

    private static final Color COLOR_CANDIDATE = new Color(102, 153, 255);
    private SubPanels[][] subPanels;
    private JPanel[][] panels;

    /**
     * tao ban co va them cac o vao ban co
     */
    public SudokuPanel() {
        super(new GridLayout(3, 3));

        panels = new JPanel[3][3]; // tạo ma trận 3x3 panel

        // chạy 9 ô lớn
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                panels[y][x] = new JPanel(new GridLayout(3, 3)); // mỗi ô lớn tạo panel là 1 grid 3x3 ô
                panels[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                add(panels[y][x]); // thêm block vào JPanel Sudoku
            }
        }

        // chạy qua các ô lớn 
        subPanels = new SubPanels[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                subPanels[y][x] = new SubPanels(x, y);
                panels[y / 3][x / 3].add(subPanels[y][x]); // thêm 1 ô vào Block
            }
        }
    }

    /**
     * hiện thị số trong mảng game lên bàn cờ 
     */
    public void setGame(Game game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                subPanels[y][x].setBackground(Color.WHITE);
                subPanels[y][x].setNumber(game.getSo(x, y), false);
            }
        }
    }

    /**
     * Thu hien khi model thong bao cap nhat
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        switch ((Update) arg) {
            case NEW:
                setGame((Game) o);
                break;
            case CHECK:
                setGameCheck((Game) o);
                break;
            case SELECTED_NUMBER:
            case CANDIDATES:
            case HELP:
                setCandidates((Game) o); // thiết đặt màu ô hợp lệ
                break;
        }
    }

    /**
     * thêm sự kiện cho bàn cờ
     */
    public void setController(SudokuController sudokuController) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                panels[x][y].addMouseListener(sudokuController);
            }
        }
    }

    /**
     * kiểm tra số đầu vào và hiển thị lên bàn cờ cho btn Check
     */
    private void setGameCheck(Game game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                subPanels[y][x].setBackground(Color.WHITE);
                if (subPanels[y][x].getForeground().equals(Color.BLUE)) {
                    subPanels[y][x].setForeground(game.getHopLe(x, y) ? Color.GREEN : Color.RED);

                }
            }

        }
    }

    /**
     *thiết lập màu cho ô gợi ý
     */
    private void setCandidates(Game game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                subPanels[y][x].setBackground(Color.WHITE);
                if (game.getHelp() && game.help_HopLe(x, y)) {
                    subPanels[y][x].setBackground(COLOR_CANDIDATE);
                }
            }
        }
    }


}
