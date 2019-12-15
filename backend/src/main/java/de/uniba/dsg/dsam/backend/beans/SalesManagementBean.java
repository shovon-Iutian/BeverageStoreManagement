package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.ReportEntity;
import de.uniba.dsg.dsam.persistence.SalesManagement;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Remote(SalesManagement.class)
public class SalesManagementBean implements SalesManagement {

	@PersistenceContext(type= PersistenceContextType.TRANSACTION)
	private EntityManager em;

	@Override
	public void assignIncentiveToBeverage(Beverage beverage, Incentive incentive) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<ReportEntity> getSummaryReport() {
		List<Object[]> results = em.createNamedQuery("summaryReport").getResultList();
		List<ReportEntity> reportEntityList = new ArrayList<>();
		for(Object[] result : results){
			ReportEntity entity = new ReportEntity();
			entity.setCustomer_order_id((Integer) result[0]);
			entity.setSum((Double) result[1]);
			reportEntityList.add(entity);
		}
		return reportEntityList;
	}

	@Override
	public List<ReportEntity> getReportOnIncentive() {
		List<Object[]> results = em.createNamedQuery("detailedReport").getResultList();
		List<ReportEntity> reportEntityList = new ArrayList<>();
		for(Object[] result : results){
			ReportEntity entity = new ReportEntity();
			String dtype = (String) result[0];
			if(dtype == null){
				entity.setDtype("No Incentive");
			}
			else if(dtype.equals("TrialPackageEntity")){
				entity.setDtype("Trial Package");
			}
			else if(dtype.equals("PromotionalGiftEntity")){
				entity.setDtype("Promotional Gift");
			}
			entity.setSum((Double) result[1]);
			reportEntityList.add(entity);
		}
		return reportEntityList;
	}


}
