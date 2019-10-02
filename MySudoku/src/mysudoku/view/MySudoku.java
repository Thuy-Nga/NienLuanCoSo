/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import mysudoku.controller.ButtonController;
import mysudoku.controller.SudokuController;
import mysudoku.model.Game;

/**
 *
 * @author porko
 * Class chua giao dien chinh
 */
public class MySudoku extends JFrame {

    /**
     * @param args the command line arguments
     */
    
    public MySudoku()
    {
        super("Game Sudoku");
        getContentPane().setLayout(new BorderLayout());
        
        Game game = new Game();
        
        // btnController được sử dụng chung cho btntop và btnPanels
        ButtonController btnController = new ButtonController(game);
        ButtonPanels_Option btnpnop = new ButtonPanels_Option();
        btnpnop.setControl(btnController); // them sự kiện cho btn tùy chọn
        add(btnpnop, BorderLayout.NORTH);
        
        ButtonPanels btnPanles = new ButtonPanels();
        btnPanles.setControl(btnController); // thêm sự kiện cho panel btn số
        add(btnPanles, BorderLayout.SOUTH);
        
        // ----- tạo ra bàn cờ khuyết ô hiển thị lên gd
        SudokuPanel sudokuPanel = new SudokuPanel(); // tạo ra bàn cờ 81 ô
        SudokuController sudokucontroller = new SudokuController(sudokuPanel,game);
        sudokuPanel.setGame(game); // hiển thị số lên bàn cờ đầu tiên
        sudokuPanel.setController(sudokucontroller);
        add(sudokuPanel, BorderLayout.CENTER);
        // ------------------------------------------------
        
        game.addObserver(btnpnop);
        game.addObserver(btnPanles);        
        game.addObserver(sudokuPanel);
        
        pack();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
}
