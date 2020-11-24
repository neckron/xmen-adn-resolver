package com.xmen.dna.exception;

/**
 * It will be trigered when the algorithms are not able to create a NxN matrix
 * @author fr.rodriguez
 */
public class SquareMatrixException extends Exception {

    public SquareMatrixException(String message) {
        super(message);
    }
}
