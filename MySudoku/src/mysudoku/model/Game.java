/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 *
 * @author porko
 */
public class Game extends Observable {

    private int[][] phuongan;
    private int[][] game;
    private boolean kiemtra[][];
    private boolean trogiup;
    private int sochon;

    public Game() {
        taoGame();
        kiemtra = new boolean[9][9];
        trogiup = true;
    }

    /**
     * Tạo ra 81 ô hoàn chỉnh và mảng game bỏ 1 số ô
     */
    public void taoGame() {
        phuongan = taoPhuongAn(new int[9][9], 0);
        //print(phuongan);
        ghiFile(phuongan);
        game = taoGame(copy(phuongan));
        setChanged();
        notifyObservers(Update.NEW);

    }

    public void kiemtraGame() {
        sochon = 0;
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                kiemtra[y][x] = game[y][x] == phuongan[y][x];
            }
        }
        setChanged();
        notifyObservers(Update.CHECK);
    }


    private int[][] taoPhuongAn(int[][] game, int index) {
        if (index > 80) {
            return game;
        }

        int x = index % 9;
        int y = index / 9;

        List<Integer> dsso = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) {
            dsso.add(i);
        }
        Collections.shuffle(dsso);

        while (dsso.size() > 0) {
            int so = getSoThichHop(game, x, y, dsso);
            if (so == -1) {
                return null;
            }

            game[y][x] = so;
            int[][] tmpGame = taoPhuongAn(game, index + 1);
            if (tmpGame != null) {
                return tmpGame;
            }
            game[y][x] = 0;
        }

        return null; // không tìm ra được phương án nào
    }

    /**
     * kiểm tra số nhập vào có thỏa không
     */
    private int getSoThichHop(int[][] game, int x, int y, List<Integer> dsSo) {
        while (dsSo.size() > 0) {
            int so = dsSo.remove(0);
            if (ktX(game, y, so) && ktY(game, x, so) && ktBlock(game, x, y, so)) {
                return so;
            }
        }
        return -1;
    }

    // kiểm tra từng ô trong 1 hàng
    private boolean ktX(int[][] game, int y, int so) {
        for (int x = 0; x < 9; x++) {
            if (game[y][x] == so) {
                return false;
            }
        }
        return true;
    }

    // kiểm tra từng ô trong 1 cột
    private boolean ktY(int[][] game, int x, int so) {
        for (int y = 0; y < 9; y++) {
            if (game[y][x] == so) {
                return false;
            }
        }
        return true;
    }

    private boolean ktBlock(int[][] game, int x, int y, int so) {
        int x1 = x < 3 ? 0 : x < 6 ? 3 : 6;
        int y1 = y < 3 ? 0 : y < 6 ? 3 : 6;
        for (int yy = y1; yy < y1 + 3; yy++) {
            for (int xx = x1; xx < x1 + 3; xx++) {
                if (game[yy][xx] == so) {
                    return false;
                }
            }
        }
        return true;
    }

/*    private void print(int[][] game) {
        System.out.println();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                System.out.print(" " + game[y][x]);
            }
            System.out.println();
        }
    }*/

    public void ghiFile(int[][] sudoku) {
        try {
            File f = new File("MySudoku.txt");
            if (!f.exists()) {
                f.createNewFile();                
            }
            else {
                f.delete();
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f, true);
            fw.write("---------- MY Sudoku ------------\n\n");
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    String s = String.valueOf(sudoku[x][y]) + " ";
                    fw.write(s);
                }
                fw.write("\n");
            }
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private int[][] copy(int[][] game) {
        int[][] copy = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                copy[y][x] = game[y][x];
            }
        }
        return copy;
    }

    private int[][] taoGame(int[][] game) {
        List<Integer> dsViTri = new ArrayList<Integer>();
        for (int i = 0; i < 81; i++) {
            dsViTri.add(i);
        }
        Collections.shuffle(dsViTri); // trộn số 
        return taoGame(game, dsViTri);
    }

    // trả ra 1 mảng game khuyết các ô số
    private int[][] taoGame(int[][] game, List<Integer> dsViTri) {
        while (dsViTri.size() > 0) {
            int vt = dsViTri.remove(0);
            int x = vt % 9;
            int y = vt / 9;
            int temp = game[y][x];
            game[y][x] = 0;
            if (!hopLe(game)) // không hợp lệ (có 2 giải pháp cho 1 ô số )
            {
                game[y][x] = temp; // ghi lại số vừa lấy vào vị trí cũ
            }
        }
        return game;
    }

    private boolean hopLe(int[][] game) {
        return hopLe(game, 0, new int[]{0});
    }
    private boolean hopLe(int[][] game, int index, int[] sophuongan) {
        if (index > 80) {
            return ++sophuongan[0] == 1;
        }
        int x = index % 9;
        int y = index / 9;  
        
        if (game[y][x] == 0) { // tim o co gia tri bang 0
            List<Integer> dsso = new ArrayList<Integer>();
            for (int i = 1; i <= 9; i++) {
                dsso.add(i);
            }
            while (dsso.size() > 0) {
                int so = getSoThichHop(game, x, y, dsso);
                if (so == -1) {
                    break;
                }
                game[y][x] = so;

                if (!hopLe(game, index + 1, sophuongan)) {
                    game[y][x] = 0;
                    return false;
                }
                game[y][x] = 0;
            }
        } else if (!hopLe(game, index + 1, sophuongan)) // khi index chay het ma dung se return true 
        {
            return false;
        }
        return true;
    }

    /**
     * lay so tai o x, y
     *
     * @param x toa do x
     * @param y toa do y
     * @return
     */
    public int getSo(int x, int y) {
        return game[y][x];
    }

   /**
    * thiet dat số người dùng đã chọn từ class ButtonController
    */
    public void setSoChon(int sochon) {
        this.sochon = sochon;
        setChanged();
        notifyObservers(Update.SELECTED_NUMBER);
    }

   /**
    *lấy số người dùng chọn
    * 
    */
    public int getSoChon() {
        return sochon;
    }

    /**
     * set số vào vị trí x, y 
     * 
     */
    public void setSo(int x, int y, int number) {
        game[y][x] = number;
    }

    /**
     * trả về gia tri của kiemtra[y][x]
     * trả về true nếu số điển hợp lệ
     * trả về false nếu số điền vào không hợp lệ
     */
    public boolean getHopLe(int x, int y) {
        return kiemtra[y][x]; // Hàm kiemtraGame() 
    }

    
    public void setHelp(boolean help) {
        this.trogiup = help;
        setChanged();
        notifyObservers(Update.HELP);
    }

    public boolean getHelp() {
        return trogiup;
    }

    /**
     * kiểm tra ô ở vt x, y có khả năng điền vào không
     */
    public boolean help_HopLe(int x, int y) {
        return game[y][x] == 0 && ktX(game, y, sochon)
                && ktY(game, x, sochon) && ktBlock(game, x, y, sochon);
    }

    /**
     * Kiểm tra kết quả của bàn cờ 
     */
    public boolean kiemTraWin() {
        int index = 0;
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (kiemtra[y][x]) {
                    if (index == 80) {
                        return true;
                    }
                    index++;
                }
//                index = 80;
            }
        }
       
        return false;
    }
}
