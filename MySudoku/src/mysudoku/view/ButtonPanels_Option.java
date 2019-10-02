/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
public class ButtonPanels_Option extends JPanel  implements Observer {

    JButton btnNew, btnCheck, btnExit;  
    
    public ButtonPanels_Option(){
        super(new BorderLayout());

        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.NORTH);

        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Options "));
        pnlAlign.add(pnlOptions);

        btnNew = new JButton("New");
        btnNew.setFocusable(false);
        pnlOptions.add(btnNew);

        btnCheck = new JButton("Check");
        btnCheck.setFocusable(false);
        pnlOptions.add(btnCheck);

        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);
        pnlOptions.add(btnExit);
    }
    
    /**
     * Set su kien cho cac btn 
     * @param btn biến lớp sự kiện
     */
     
    void setControl(ButtonController btn) {
        btnNew.addActionListener(btn);
        btnCheck.addActionListener(btn);
        btnExit.addActionListener(btn);
    }
    
    @Override
    public void update(Observable o, Object arg) {
       switch((Update)arg){
            case NEW: 
                break;
            case CHECK:
                break;
        }
    }
}
