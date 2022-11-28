package com.bnp.fr.CompanyTT.billing.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bnp.fr.CompanyTT.billing.BillingService;
import com.bnp.fr.CompanyTT.data.input.DayTravel;
import com.bnp.fr.CompanyTT.data.input.Tap;
import com.bnp.fr.CompanyTT.data.output.CustomerSummary;
import com.bnp.fr.CompanyTT.data.output.Summary;
import com.bnp.fr.CompanyTT.data.output.Trip;



public class BillingServiceImpl implements BillingService{

	@Override
	public Summary calculSummary(DayTravel dayTravel) {
		Summary daySummary = Summary.createSummary();
        List<CustomerSummary> customerSummaries =new ArrayList<>();

        List<Tap> taps =sortTapsByCustomerByDate(dayTravel);
        Map<Integer, List<Tap>> dayCustomerTaps = groupCustomerByIdAndTab(taps);

        for (Map.Entry<Integer, List<Tap>> entry : dayCustomerTaps.entrySet()) {
            Integer id = entry.getKey();
            List<Tap> list = entry.getValue();
            CustomerSummary customerSummary = CustomerSummary.createCustomerSummary(id);
            int i = 0;
            while (i < list.size() - 1) {
                Trip trip = Trip.createTrip(list.get(i),list.get(i+1));
                customerSummary.setTotalCostInCents(customerSummary.getTotalCostInCents() + trip.getCostInCents());
                customerSummary.getTrips().add(trip);
                i += 2;
            }
            customerSummaries.add(customerSummary);
        }
        daySummary.setCustomerSummaries(customerSummaries);

        return daySummary;
	}
	
	
	/**
     * sort the journey Taps by Customer Id then by date
     * @param daytravel : customer day
     * @return List of Taps sorted
     */
    private List<Tap> sortTapsByCustomerByDate(DayTravel daytravel) {

        Comparator<Tap> comparator = Comparator.comparing(tap -> tap.getCustomerId());
        comparator = comparator.thenComparing(Comparator.comparing(tap -> tap.getUnixTimestamp()));
            return daytravel.getTaps().stream()
                    .sorted(comparator)
                    .parallel()
                    .collect(Collectors.toList());
    }

    /**
     * Grouping Customers id and after grouping by their taps
     * @param taps : customer day
     * @return Map ID customer and taps
     */
    private Map<Integer, List<Tap>> groupCustomerByIdAndTab(List<Tap> taps){
        Map<Integer, List<Tap>> dayCustomerTaps = new HashMap<>();

        for (Tap tap : taps) {
            dayCustomerTaps.computeIfAbsent(tap.getCustomerId(), k -> new ArrayList<>()).add(tap);
        }
        return dayCustomerTaps;
    }

}
