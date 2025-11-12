package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStandardOutputView implements DrawNumberView {
    private DrawNumberController controller;

    @Override
    public void setController(DrawNumberController observer) {
        this.controller = observer;
    }

    @Override
    public void start() {
        
    }

    @Override
    public void result(DrawResult res) {
        if (res == DrawResult.YOURS_HIGH || res == DrawResult.YOURS_LOW) {
            System.out.println(res.getDescription());
            return;
        } else if (res == DrawResult.YOU_WON) {
            System.out.println(res.getDescription() + " a new game starts!");
        } else {
            System.out.println(res.getDescription() + ": Lost!");
        }
        this.controller.resetGame();
    }

}