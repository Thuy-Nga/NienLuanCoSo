/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import mysudoku.controller.ButtonController;
import mysudoku.model.Update;

/**
 *
 * @author porko
 */
public class ButtonPanels extends JPanel implements Observer {
      
    JCheckBox cbHelp;               
    ButtonGroup bgNumbers;          
    JToggleButton[] btnNumbers;     
    
    public ButtonPanels(){
        super(new BorderLayout());

       JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.NORTH);

        JPanel pnlNumbers = new JPanel();
        pnlNumbers.setLayout(new BoxLayout(pnlNumbers, BoxLayout.PAGE_AXIS));
        pnlNumbers.setBorder(BorderFactory.createTitledBorder(" Numbers "));
        pnlAlign.add(pnlNumbers);

        JPanel pnlNumbersHelp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersHelp);

        cbHelp = new JCheckBox("Help on", true);
        cbHelp.setFocusable(false);
        pnlNumbersHelp.add(cbHelp);

        JPanel pnlNumbersNumbers = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersNumbers);

        bgNumbers = new ButtonGroup();
        btnNumbers = new JToggleButton[9];
        for (int i = 0; i < 9; i++) {
            btnNumbers[i] = new JToggleButton("" + (i + 1));
            btnNumbers[i].setPreferredSize(new Dimension(60, 50));
            btnNumbers[i].setFont(new Font("ARIAL", Font.BOLD, 20));
            btnNumbers[i].setFocusable(false);
            bgNumbers.add(btnNumbers[i]);
            pnlNumbersNumbers.add(btnNumbers[i]);
        }
        
    }

    /**
     * Thêm sự kiện cho 9 btn số và cbHelp
     * @param btn biến lớp sự kiện 
     */
    void setControl(ButtonController btn) {
        cbHelp.addActionListener(btn);
        for(int i=0; i<9; i++)
            btnNumbers[i].addActionListener(btn);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch((Update)arg){
            case NEW: 
                break;
            case CHECK:
                bgNumbers.clearSelection();
                break;
                
        } 
    }
}
