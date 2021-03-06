/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparators;

import java.util.Comparator;
import pojos.ImdbTop1000;

/**
 *
 * @author Somogyi László <proceed step by step>
 */
public class GrossComparator implements Comparator<ImdbTop1000>{

    @Override
    public int compare(ImdbTop1000 o1, ImdbTop1000 o2) {
        return Long.compare(o1.getGross(), o2.getGross());
    }
    
}
