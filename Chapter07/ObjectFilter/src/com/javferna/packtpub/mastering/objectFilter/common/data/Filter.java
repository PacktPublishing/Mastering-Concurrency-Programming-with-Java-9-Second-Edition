package com.javferna.packtpub.mastering.objectFilter.common.data;

import java.util.List;

public class Filter {

	public static boolean filter(CensusData data, List<FilterData> filters) {
		boolean result = true;

		for (FilterData filter : filters) {
			result = result && evaluateFilter(data, filter);
			if (!result) {
				return false;
			}
		}

		return true;
	}

	private static boolean evaluateFilter(CensusData data, FilterData filter) {
		switch (filter.getIdField()) {
		case 0:
			if (data.getAge() == Integer.valueOf(filter.getValue())) {
				return true;
			}
			break;
		case 1:
			if (data.getClassOfWorker().equals(filter.getValue())) {
				return true;
			}
			break;
		case 2:
			if (data.getIndustryCode() == Integer.valueOf(filter.getValue())) {
				return true;
			}
			break;
		case 3:
			if (data.getOccupationCode() == Integer.valueOf(filter.getValue())) {
				return true;
			}
			break;
		case 4:
			if (data.getEducation().equals(filter.getValue())) {
				return true;
			}
			break;
		case 5:
			if (data.getWagePerHour() == Integer.valueOf(filter.getValue())) {
				return true;
			}
			break;
		case 6:
			if (data.getEnrolled().equals(filter.getValue())) {
				return true;
			}
			break;
		case 7:
			if (data.getMaritalStatus().equals(filter.getValue())) {
				return true;
			}
			break;
		case 8:
			if (data.getMajorIndustryCode().equals(filter.getValue())) {
				return true;
			}
			break;
		case 9:
			if (data.getMajorOccupationCode().equals(filter.getValue())) {
				return true;

			}
			break;
		case 10:
			if (data.getRace().equals(filter.getValue())) {
				return true;
			}
			break;
		case 11:
			if (data.getHispanicOrigin().equals(filter.getValue())) {
				return true;
			}
			break;
		case 12:
			if (data.getSex().equals(filter.getValue())) {
				return true;
			}
			break;
		case 13:
			if (data.getMemberOfLaborUnion().equals(filter.getValue())) {
				return true;
			}
			break;
		case 14:
			if (data.getReasonForUnemployment().equals(filter.getValue())) {
				return true;
			}
			break;
		case 15:
			if (data.getTimeEmploymentStat().equals(filter.getValue())) {
				return true;
			}
			break;
		case 16:
			if (data.getCapitalGains() == Integer.valueOf(filter.getValue())) {
				return true;
			}
			break;
		case 17:
			if (data.getCapitalLosses() == Integer.valueOf(filter.getValue())) {
				return true;
			}
			break;
		case 18:
			if (data.getDividendsFromStocks() == Integer.valueOf(filter
					.getValue())) {
				return true;
			}
			break;
		case 19:
			if (data.getTaxFilerStatus().equals(filter.getValue())) {
				return true;
			}
			break;
		case 20:
			if (data.getRegionOfPreviousResidence().equals(filter.getValue())) {
				return true;
			}
			break;
		case 21:
			if (data.getStateOfPreviousResidence().equals(filter.getValue())) {
				return true;
			}
			break;
		case 22:
			if (data.getDetailedHousehold().equals(filter.getValue())) {
				return true;
			}
			break;
		case 23:
			if (data.getDetailedHouseholdSummary().equals(filter.getValue())) {
				return true;
			}
			break;
		case 24:
			if (data.getMigrationCodeChange().equals(filter.getValue())) {
				return true;
			}
			break;
		case 25:
			if (data.getMigrationCodeChangeInReg().equals(filter.getValue())) {
				return true;
			}
			break;
		case 26:
			if (data.getMigrationCodeMoveWithinReg().equals(filter.getValue())) {
				return true;
			}
			break;
		case 27:
			if (data.getLiveInThisHouse().equals(filter.getValue())) {
				return true;
			}
			break;
		case 28:
			if (data.getMigrationPrev().equals(filter.getValue())) {
				return true;
			}
			break;
		case 29:
			if (data.getNumPersonsWorked().equals(filter.getValue())) {
				return true;
			}
			break;
		case 30:
			if (data.getFamilyMembersUnder18().equals(filter.getValue())) {
				return true;
			}
			break;
		case 31:
			if (data.getCountryOfBirthFather().equals(filter.getValue())) {
				return true;
			}
			break;
		case 32:
			if (data.getCountryOfBirthMother().equals(filter.getValue())) {
				return true;
			}
			break;
		case 33:
			if (data.getCountryOfBirthSelf().equals(filter.getValue())) {
				return true;
			}
			break;
		case 34:
			if (data.getCitizenship().equals(filter.getValue())) {
				return true;
			}
			break;
		case 35:
			if (data.getOwnBusiness().equals(filter.getValue())) {
				return true;
			}
			break;
		case 36:
			if (data.getFillIncQuestionnaire().equals(filter.getValue())) {
				return true;
			}
			break;
		case 37: 
			if (data.getVeteransBenefits().equals(filter.getValue())) {
				return true;
			}
			break;
		case 38:
			if (data.getWeeksWorkedInYear()==Integer.valueOf(filter.getValue())) {
				return true;
			}
			break;
		case 39:
			if (data.getYear().equals(filter.getValue())) {
				return true;
			}
			break;

		}
		return false;
	}

}
