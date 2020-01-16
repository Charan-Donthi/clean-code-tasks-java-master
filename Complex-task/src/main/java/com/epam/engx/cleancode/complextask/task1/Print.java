package com.epam.engx.cleancode.complextask.task1;



import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.View;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DatabaseManager;

import java.util.List;


public class Print implements Command {

    private View view;
    private DatabaseManager manager;
    private String tableName;
    private List<DataSet> data;

    
    public Print(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    
    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }

    
    public void process(String input) {
        String[] command = input.split(" ");
        if (command.length != 2) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (command.length - 1));
        }
        tableName = command[1];
        data = manager.getTableData(tableName);
        view.write(getTableString());
    }

    
    private String getTableString() {
        if (getMaxColumnNameLength() == 0) {
            return getEmptyTable(tableName);
        } else {
            return getHeaderOfTheTable() + getStringTableData();
        }
    }

    
    private String getEmptyTable(String tableName) {
    	String tableContent = "║ Table '" + tableName + "' is empty or does not exist ║";
        String construct = "╔";
        construct+=constructColumn(tableContent.length()-2);
        construct += "╗\n";
        construct += tableContent + "\n";
        construct += "╚";
        construct+=constructColumn(tableContent.length()-2);
        construct += "╝\n";
       return construct;
    }
    
    
    private String constructColumn(int columnSize) {
    	String border="";
    		 	for (int i = 0; i < columnSize; i++) {
    		 		border += "═";
    		 		}
    	return border;
    }

    
    private int getmaxColumnSize() {
		int maxColumnSize=getMaxColumnNameLength();
        if(maxColumnSize<getMaxValueLength())
        	maxColumnSize=getMaxValueLength();
		if (maxColumnSize % 2 == 0) {
            return maxColumnSize += 2;
        } else {
            return maxColumnSize += 3;
        }
    }
    
    
    private int getMaxColumnNameLength() {
        int maxLength = 0;
       if (data.size() > 0) {
            List<String> columnNames = data.get(0).getColumnNames();
            for (String columnName : columnNames) {
                if (columnName.length() > maxLength) {
                    maxLength = columnName.length();
                }
            }
        }
        return maxLength;
    }
    
    
    
    private int getMaxValueLength() {
        int maxLength = 0;
       if (data.size() > 0) {
    	   for(int row=0;row<data.size();row++) {
            List<Object> values = data.get(row).getValues();
            for (Object value : values) {
                if (String.valueOf(value).length() > maxLength) {
                    maxLength = String.valueOf(value).length();
                }
            }
    	   }
        }
        return maxLength;
    }
    
	

    
    private String getStringTableData() {
        int rowsCount= data.size();
        int maxColumnSize=getmaxColumnSize();
        int columnCount = getColumnCount();
        String result = "";

        for (int row = 0; row < rowsCount; row++) {
            List<Object> values = data.get(row).getValues();
            result += "║";
            
            for (int column = 0; column < columnCount; column++) {
                int valuesLength = String.valueOf(values.get(column)).length();
                result+=constructSpace((maxColumnSize - valuesLength) / 2);
                result += String.valueOf(values.get(column));
                result+=constructSpace((maxColumnSize - valuesLength) / 2);
                if (valuesLength % 2 != 0)
                	result+=constructSpace(1);
                    result += "║";
            }
            result += "\n";
            
            if (row < rowsCount - 1) {
            	result+=intermediateBorder(maxColumnSize,columnCount);
            }
        }
        
        result+=closeBorder(maxColumnSize,columnCount);
        
        return result;
    }

    
    private int getColumnCount() {
        int result = 0;
        if (data.size() > 0) {
            return data.get(0).getColumnNames().size();
        }
        return result;
    }

    
    private String getHeaderOfTheTable() {
        int maxColumnSize=getmaxColumnSize();
        int columnCount = getColumnCount();
        String result="";
        
        result+=openBorder(maxColumnSize,columnCount);
        
        result+=constructRow(maxColumnSize,columnCount);
        
        if (data.size() > 0) {
        	result+=intermediateBorder(maxColumnSize,columnCount);
        } else {
        	result+=closeBorder(maxColumnSize,columnCount);
        }
        
        
        return result;
    }
    
    
    
    private String openBorder(int maxColumnSize,int columnCount) {
    	String result = "╔";
        for (int column = 1; column < columnCount; column++) {
        	result+=constructColumn(maxColumnSize);
            result += "╦";
        }
        result+=constructColumn(maxColumnSize);
        result += "╗\n";
        
        return result;
    }
    
    
    private String intermediateBorder(int maxColumnSize,int columnCount) {
    	String result="╠";
         for (int column = 1; column < columnCount; column++) {
         	result+=constructColumn(maxColumnSize);
             result += "╬";
         }
         result+=constructColumn(maxColumnSize);
         result += "╣\n";
         
         return result;
    }
    
    
    private String closeBorder(int maxColumnSize,int columnCount) {
    	String result= "╚";
         for (int column = 1; column < columnCount; column++) {
         	result+=constructColumn(maxColumnSize);
             result += "╩";
         }
         result+=constructColumn(maxColumnSize);
         result += "╝\n";
    	return result;
    }
    
    
    
    private String constructRow(int maxColumnSize,int columnCount) {
    	
    	String result="";
    	
    	List<String> columnNames = data.get(0).getColumnNames();
        
        String columnName;
        int columnNameLength=0;
        for (int column = 0; column < columnCount; column++) {
            result += "║";
            columnName=columnNames.get(column);
            columnNameLength=columnName.length();
        	result+=constructSpace((maxColumnSize - columnNameLength) / 2);
            result += columnName;
            result+=constructSpace((maxColumnSize - columnNameLength) / 2);
            if (columnNameLength % 2 != 0) {
            	result+=constructSpace(1);
            }
        }
        result += "║\n";
        
        return result;
    }

    
    
    private String constructSpace(int numberOfSpaces) {
    	String space="";
    	for(int spaceCount=1;spaceCount<=numberOfSpaces;spaceCount++) {
    		space+=" ";
    	}
    	return space;
    }
}