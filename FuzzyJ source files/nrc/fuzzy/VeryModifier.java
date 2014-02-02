/*
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 *  http://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2000, 2001, 2002, 2003, 2004, 2005, 2006 National Research Council of Canada 
 * 
 * This software was initially developed at the National Research Council of Canada (NRC).
 *
 * THE NATIONAL RESEARCH COUNCIL OF CANADA MAKES NO REPRESENTATIONS OR
 * WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.
 * THE NATIONAL RESEARCH COUNCIL OF CANADA SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 *
 */


package nrc.fuzzy;

import java.lang.*;
import java.io.*;

/**
 * Concrete implementaion of the <b>very</b> modifier. Consider the following example:
 * <br><br>
 * <table width=640 align=CENTER border=1>
 *      <tr>
 *          <th>Description</th>
 *          <th>Visual Representation</th>
 *      </tr>
 *
 *      <tr>
 *          <td width=300>Unmodified fuzzy value:
 *              <ul>
 *              <li>this is the unmodified fuzzy value to which the modifier is applied.
 *              </ul>
 *          </td>
 *          <td width=340 align=RIGHT valign=MIDDLE>
 *              <img src = "NetGraphics/basicFuzzyValue.gif">
 *          </td>
 *      </tr>
 *          <td>After <b>very</b> modifier applied:
 *              <ul>
 *              <li>the <code>VERY</code> modifier returns the expanded
 *                  fuzzy value passed as its argument, having raised all the
 *                  membership values of the fuzzy value by a factor of 2.
 *              </ul>
 *          </td>
 *          <td width=340 align=RIGHT valign=MIDDLE>
 *              <img src = "NetGraphics/very.gif">
 *          </td>
 *      </tr>
 * </table>
 *
 * @author Bob Orchard
 *
 * @see nrc.fuzzy.ModifierFunction
 * @see nrc.fuzzy.Modifiers
 */
 public class VeryModifier extends ModifierFunction implements Serializable
 {
    public VeryModifier()
    {
        super( "very" );
    }

    public VeryModifier(String s)
    {
        super( s );
    }

    /**
     * Returns a new FuzzyValue object which represents the FuzzyValue argument
     * after the application of the <code>very</code> modifier.  The very modifier
     * raises the membership values of the points in the FuzzyValue to a power of 2.
     *
     * @param fv the FuzzyValue to modify with the <code>very</code> hedge
     * @return a new FuzzyValue object representing FuzzyValue argument after the
     *         application of the <code>very</code> hedge.  This new FuzzyValue
     *         object has the same FuzzyVariable as the FuzzyValue argument.
     * @see ModifierFunction
     */
    public FuzzyValue call(FuzzyValue fv)
    {
        FuzzyValue fvNew = null;
        // executes the 'call' method below for FuzzySet to do the modifier function
        FuzzySet fs = call(fv.getFuzzySet()); 
        try 
         { fvNew = new FuzzyValue(fv.getFuzzyVariable(), fs);
         }
        catch (XValueOutsideUODException e)
         { // we know that the modifers like 'very' do not expand the x value range
           // of the fuzzy set so if get this exception there must be an internal error
           System.err.println("Internal error in Modifier function '"+getName()+
                            "': " + e);
           System.exit(100);
         }

        fvNew.unaryModifyLinguisticExpression(getName(), fv.getLinguisticExpression());
        return(fvNew);
    }

    /**
     * Returns a new FuzzySet object which represents the FuzzySet argument
     * after the application of the <code>very</code> modifier.  The very modifier
     * raises the membership values of the points in the FuzzySet to a power of 2.
     *
     * @param fs the FuzzySet to modify with the <code>very</code> hedge
     * @return a new FuzzySet object representing FuzzySet argument after the
     *         application of the <code>very</code> hedge.
     * @see ModifierFunction
     */
    public FuzzySet call(FuzzySet a){
        return(concentrateDilute(a, 2.0));
    }

}