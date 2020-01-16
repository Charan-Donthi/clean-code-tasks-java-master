package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {


    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) throws WrongAccountNameException, WrongPasswordException{
    	
    	validateAccountName(account.getName());
        validatePassword(account.getPassword());
        

        account.setCreatedDate(new Date());
        
        account.setAddresses(addAddresses(account));
        accountManager.createNewAccount(account);
    }
    
    
    
    public void validateAccountName(String accountName) throws WrongAccountNameException{
    	if (accountName.length() <= 5){
            throw new WrongAccountNameException();
        }
    }
    
    
    
    public void validatePassword(String password) throws WrongPasswordException{
    	if (password.length() <= 8) {
            if (passwordChecker.validate(password) != OK) {
                throw new WrongPasswordException();
            }
        }
    }
    
    
    
    public List<Address> addAddresses(Account account){
    	List<Address> addresses = new ArrayList<Address>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        
        return addresses;
    }


    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }

}
