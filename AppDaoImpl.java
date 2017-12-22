package com.demo.hm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.hm.exception.ValidationException;
import com.demo.hm.model.AppSort;

@Repository
public class AppDaoImpl implements AppDao {

	private static final Logger logger = LoggerFactory.getLogger(AppDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	// Resulting values - Saving Unsorted values, Sorted values, No.of swaps in
	// the AppSort Entity
	// Assuming sorted values = previous result
	@Override
	public AppSort saveUnsortedArray(String genIdStr) throws ValidationException {

		AppSort appSort = new AppSort();
		String[] msgData = null;
		try {

			String[] genIdsArr = genIdStr.split(",");
			List<Integer> genIds = new ArrayList<Integer>();
			for (int i = 0; i < genIdsArr.length; i++) {
				Integer sortedVal = Integer.parseInt(genIdsArr[i]);
				if (sortedVal < 0) {
					throw new ValidationException("It is negative value.. Cannot process the sort");
				}
				genIds.add(sortedVal);
				logger.trace("Unsorted Values :" + genIdsArr[i] + ",");
			}

			// Unsorted values as integer Array -
			int[] genIdAsInArray = genIds.stream().mapToInt(Integer::intValue).toArray();
			appSort.setUnsortedValues(genIdAsInArray);

			applySort(appSort);// Sorting the numerical values and finding the
			// number of swaps

			// Persisting sorted values in DB
			persistInDB(appSort);

		} catch (Exception ex) {

			logger.error("saveunsortedArray, Exception - failed to persist the values : " + genIdStr + " due to : "
					+ ex.getMessage(), ex);

			msgData = new String[1];
			msgData[0] = ex.getMessage();

			if (ex instanceof ValidationException) {
				msgData[0] = ((ValidationException) ex).getErrorMessage();
			} else if (ex instanceof java.lang.RuntimeException) {
				msgData[0] = "Processing Errors";
			}

		}
		appSort.setResponseMessage(msgData[0]); // validationException if
		// any....
		return appSort;
	}

	private void persistInDB(AppSort appSort) {

		Session session = this.sessionFactory.getCurrentSession();
		session.persist(appSort);
		logger.info(
				"\nUnsorted Numbers saved  and Sorted successfully, Unsorted Numbers =" + appSort.getUnsortedValues());
		logger.info("\n Sorted Numbers = " + appSort.getSortedValues());
		logger.info("\n No of swaps to sort = " + appSort.getNoOfSwap());

	}

	
	@Override
	public List<AppSort> displaySortedList() {

		logger.info("Entered displaySortedList");
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<AppSort> query = session.createQuery("FROM APPSORT");
		List<AppSort> result = query.getResultList();
		logger.info("Sorted List::" + result);
		return result;
	}

	public AppSort applySort(AppSort appSort) {

		// Sorting the values and finding the number swaps
		int[] toBeSortedValues = appSort.getUnsortedValues();

		int len = toBeSortedValues.length;
		int temp;
		int noOfSwaps = 0;
		for (int i = 0; i < len - 1; i++) {
			for (int j = 0; j < len - 1; j++) {
				if (toBeSortedValues[j] > toBeSortedValues[j + 1]) {
					temp = toBeSortedValues[j + 1];
					toBeSortedValues[j + 1] = toBeSortedValues[j];
					toBeSortedValues[j] = temp;
					noOfSwaps++;
				}
			}
		}
		logger.trace("Number of swap=" + noOfSwaps);
		logger.trace("Sorted Values" + toBeSortedValues.toString());
		appSort.setSortedValues(toBeSortedValues);// Setting Sorted values
		appSort.setNoOfSwap(noOfSwaps);// Setting no of swaps
		return appSort;

	}

}
