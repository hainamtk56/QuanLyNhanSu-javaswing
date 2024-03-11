package QuanLyPhongBan.Controller;

import QuanLyPhongBan.Model.Model;
import QuanLyPhongBan.View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.addButtonClickListener(new ButtonClickListener());
    }


    public class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = view.getInputText();
            model.setData(input);
            view.setButtonText(input);
            view.displayMessage("Data has been updated: " + input);


        }
    }
}

