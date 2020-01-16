package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {
	

    public static double calculateMonthlyPayment(
            int principalAmount, int termInYears, double rateOfInterest) throws InvalidInputException{
    	
    	validate(principalAmount,termInYears,rateOfInterest);

        double DecimalRateOfInterest =rateOfInterest/ 100.0;

        double termInMonths = termInYears * 12;

        //for zero interest rates
        if(DecimalRateOfInterest==0)  
            return  principalAmount/termInMonths;

        double monthlyRate = DecimalRateOfInterest / 12.0;

        // The Math.pow() method is used calculate values raised to a power
        double monthlyPayment = (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));

        return monthlyPayment;
    }
    
    
    public static void validate(int principalAmount, int termInYears, double rateOfInterest)
    {
    	if (principalAmount < 0 || termInYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }
    }
    
    
}
