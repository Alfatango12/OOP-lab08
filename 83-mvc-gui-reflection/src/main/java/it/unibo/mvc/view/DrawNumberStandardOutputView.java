package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * 
 */
public final class DrawNumberStandardOutputView implements DrawNumberView {
    private DrawNumberController controller;

    /**
     * 
     */
    @Override
    public void setController(final DrawNumberController observer) {
        this.controller = observer;
    }

    /**
     * 
     */
    @Override
    public void start() {

    }

    /**
     * 
     */
    @Override
    public void result(final DrawResult res) {
        if (res == DrawResult.YOURS_HIGH || res == DrawResult.YOURS_LOW) {
            System.out.println(res.getDescription()); //NOPMD
            return;
        } else if (res == DrawResult.YOU_WON) {
            System.out.println(res.getDescription() + " a new game starts!"); //NOPMD
        } else {
            System.out.println(res.getDescription() + ": Lost!"); //NOPMD
        }
        this.controller.resetGame();
    }

}
