package com.bnp.fr.CompanyTT.billing;

import com.bnp.fr.CompanyTT.data.input.DayTravel;
import com.bnp.fr.CompanyTT.data.output.Summary;

public interface BillingService {

	public Summary calculSummary(DayTravel dayTravel);
}
