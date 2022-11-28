package com.bnp.fr.CompanyTT;

import javax.ws.rs.core.Application;
import com.bnp.fr.CompanyTT.billing.BillingService;
import com.bnp.fr.CompanyTT.billing.impl.BillingServiceImpl;
import com.bnp.fr.CompanyTT.data.input.DayTravel;
import com.bnp.fr.CompanyTT.data.output.Summary;
import com.bnp.fr.CompanyTT.validator.InOutJsonValidateFiles;

import java.io.IOException;

public class App extends Application {
    

	static BillingService calculBilling;
	public static void main(String[] args) throws IOException {

	       if (args[0] == null && args[1]== null) {
	            System.out.println("input and output are missing ");
	        }else if (args[0].trim().isEmpty()) {
	            System.out.println("input file is not defined");
	        }else if (args[1] == null || args[1].trim().isEmpty()) {
	            System.out.println("you should defined the output file");
	        } else {

	              String fileInput = args[0];
	              String fileOutPut=args[1];
	              InOutJsonValidateFiles inputOutFile = new InOutJsonValidateFiles();
	              DayTravel dayTravel = inputOutFile.readAndValidateFile(fileInput);
	              calculBilling = new BillingServiceImpl();
	              Summary summary=calculBilling.calculSummary(dayTravel);
	              new InOutJsonValidateFiles().writeInputFile(fileOutPut,summary);

	       }
	    }
}
