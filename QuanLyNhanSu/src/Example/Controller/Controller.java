package Example.Controller;


import QuanLyLuong.Model.Luong;
import QuanLyLuong.View.LuongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Luong luong;
    private LuongView luongView;


    public Controller(Luong luong, LuongView luongView) {
        this.luong = luong;
        this.luongView = luongView;

//        luongView.addButtonClickListener(new ButtonClickListener());
    }


    public class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            String input = luongView.getInputText();
//            luong.setData(input);
//            luongView.setButtonText(input);
//            luongView.displayMessage("Data has been updated: " + input);


        }
    }
}

