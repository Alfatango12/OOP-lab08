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
     * The constructor of the class needed to make a new object.
     */
    public DrawNumberStandardOutputView() {
        /*
         * The constructor doesn't need to initialize anything.
         */
    }

    @Override
    public void setController(final DrawNumberController observer) {
       this.controller = observer;
    }

    @Override
    public void start() {
        /*
         * The output from terminal is always avaliable without external objects.
         */
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription()); //NOPMD
        if (controller != null && (res == DrawResult.YOU_LOST || res == DrawResult.YOU_WON)) {
            controller.resetGame();
        }
    }
}
