package com.siss.test;

/**
 *
 * @author econtreras
 */
abstract public class Employee {

    protected abstract double getSalesAmount();

    public double getCommision() {
        return getSalesAmount() * 0.15;
    }

}
