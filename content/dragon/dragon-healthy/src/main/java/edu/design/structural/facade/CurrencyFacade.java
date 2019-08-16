package edu.design.structural.facade;

import java.util.Currency;
import java.util.List;

/**
 * Created by gwd on 9/2/2016.
 */
public interface CurrencyFacade {
    /** get all currency info**/
    List<Currency> all();

    /**
     * A method to example to finish facade job.
     */
    CurrencyFacade add(String string);

}
