package utils;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

public class DataProviders {

    ExcelUtility reader;

    @DataProvider(name="registerData")
    public Iterator<Object[]> registerData(){

        ArrayList<Object[]> data = new ArrayList<>();

        reader = new ExcelUtility("src/test/resources/testdata.xlsx");

        for(int i=1;i<=reader.getRowCount("Sheet1");i++){

            String name = reader.getCellData("Sheet1",i,0);
            String email = reader.getCellData("Sheet1",i,1);
            String pass = reader.getCellData("Sheet1",i,2);
            String result = reader.getCellData("Sheet1",i,3);

            Object obj[] = {name,email,pass,result};
            data.add(obj);
        }

        return data.iterator();
    }


    @DataProvider(name="loginData")
    public Iterator<Object[]> loginData(){

        ArrayList<Object[]> data = new ArrayList<>();

        reader = new ExcelUtility("src/test/resources/loginData.xlsx");

        for(int i=1;i<=reader.getRowCount("Sheet2");i++){

            String email = reader.getCellData("Sheet2",i,0);
            String pass = reader.getCellData("Sheet2",i,1);
            String result = reader.getCellData("Sheet2",i,2);

            Object obj[] = {email,pass,result};
            data.add(obj);
        }

        return data.iterator();
    }
}