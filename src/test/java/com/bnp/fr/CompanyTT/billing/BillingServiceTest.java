package com.bnp.fr.CompanyTT.billing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.bnp.fr.CompanyTT.billing.impl.BillingServiceImpl;
import com.bnp.fr.CompanyTT.data.enums.Station;
import com.bnp.fr.CompanyTT.data.input.DayTravel;
import com.bnp.fr.CompanyTT.data.input.Tap;
import com.bnp.fr.CompanyTT.data.output.CustomerSummary;
import com.bnp.fr.CompanyTT.data.output.Summary;
import com.bnp.fr.CompanyTT.data.output.Trip;
import com.bnp.fr.CompanyTT.exception.DayValidationException;



public class BillingServiceTest {


	
	private DayTravel get_DayTravel_A_D(){

        DayTravel dayTravelAtoD=new DayTravel();
        List<Tap> taps=new ArrayList<>();
        taps.add(new Tap(1,1, Station.A));
        taps.add(new Tap(2,1,Station.D));

        dayTravelAtoD.setTaps(taps);

        return dayTravelAtoD;
    }
    private Summary get_Summary_A_D(){

        Summary summary=new Summary();
        List<CustomerSummary> customerSummaries =new ArrayList<>();
        CustomerSummary customerSummary=new CustomerSummary();
        customerSummary.setCustomerId(1);
        customerSummary.setTotalCostInCents(240);
        customerSummary.setTrips(Arrays.asList(
                new Trip(Station.A,Station.D,1,240,1,2)));

        customerSummaries.add(customerSummary);
        summary.setCustomerSummaries(customerSummaries);

        return summary;
    }
    @Test
    void check_Summary_A_D() throws IOException {
    	BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
        DayTravel journey = get_DayTravel_A_D();
        Summary expected = get_Summary_A_D();
        Summary summary = billingServiceImpl.calculSummary(journey);
        assertThat(summary).isEqualToComparingFieldByField(expected);
    }
    
    
    
    
    /*customer 2*/
    private DayTravel get_DayTravel_B_C_H_G_D_F(){

        DayTravel dayTravelAtoD=new DayTravel();
        List<Tap> taps=new ArrayList<>();
        taps.add(new Tap(2,2, Station.B));
        taps.add(new Tap(3,2, Station.C));
        taps.add(new Tap(3,2, Station.H));
        taps.add(new Tap(10,2,Station.G));
        taps.add(new Tap(20,2,Station.D));
        taps.add(new Tap(65,2,Station.F));

        dayTravelAtoD.setTaps(taps);

        return dayTravelAtoD;
    }

    private Summary get_Summary_B_C_H_G_D_F(){

        Summary summary=new Summary();
        List<CustomerSummary> customerSummaries =new ArrayList<>();
        CustomerSummary customerSummary=new CustomerSummary();
        customerSummary.setCustomerId(2);
        customerSummary.setTotalCostInCents(720);
        customerSummary.setTrips(Arrays.asList(
                new Trip(Station.B,Station.C,2,240,1,2),
                new Trip(Station.H,Station.G,3,200,4,4),
                new Trip(Station.D,Station.F,20,280,2,3)
        ));
        customerSummaries.add(customerSummary);
        summary.setCustomerSummaries(customerSummaries);

        return summary;
    }


    @Test
    void check_Summary_B_C_H_G_D_F() throws IOException {
    	BillingServiceImpl billingServiceImpl=new BillingServiceImpl();
        DayTravel journey = get_DayTravel_B_C_H_G_D_F();
        Summary expected = get_Summary_B_C_H_G_D_F();
        Summary summary = billingServiceImpl.calculSummary(journey);
        assertEquals(summary,expected);
    }
    
	
}
