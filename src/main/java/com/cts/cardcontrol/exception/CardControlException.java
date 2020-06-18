/**
 * 
 */
package com.cts.cardcontrol.exception;

/**
 * @author 445150
 * 
 * This Exception is design to generate business exceptions
 *
 */
public class CardControlException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardControlException() {
		super();
		
	}

	public CardControlException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public CardControlException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public CardControlException(String arg0) {
		super(arg0);
		
	}

	public CardControlException(Throwable arg0) {
		super(arg0);
		
	}
	
	

}
