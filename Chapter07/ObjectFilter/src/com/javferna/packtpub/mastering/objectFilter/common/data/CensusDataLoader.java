package com.javferna.packtpub.mastering.objectFilter.common.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CensusDataLoader {

	public static CensusData[] load(Path path) {
		List<CensusData> list = new ArrayList<CensusData>();

		try (InputStream in = Files.newInputStream(path);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				CensusData item = processItem(line);
				list.add(item);
			}
		} catch (IOException x) {
			x.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		CensusData ret[] = new CensusData[list.size()];
		return list.toArray(ret);

	}

	private static CensusData processItem(String line) {
		CensusData ret = new CensusData();
		String tokens[] = line.split(",");

		ret.setAge(Integer.valueOf(tokens[0].trim()));
		ret.setClassOfWorker(tokens[1].trim());
		ret.setIndustryCode(Integer.valueOf(tokens[2].trim()));
		ret.setOccupationCode(Integer.valueOf(tokens[3].trim()));
		ret.setEducation(tokens[4].trim());
		ret.setWagePerHour(Integer.valueOf(tokens[5].trim()));
		ret.setEnrolled(tokens[6].trim());
		ret.setMaritalStatus(tokens[7].trim());
		ret.setMajorIndustryCode(tokens[8].trim());
		ret.setMajorOccupationCode(tokens[9].trim());
		ret.setRace(tokens[10].trim());
		ret.setHispanicOrigin(tokens[11].trim());
		ret.setSex(tokens[12].trim());
		ret.setMemberOfLaborUnion(tokens[13].trim());
		ret.setReasonForUnemployment(tokens[14].trim());
		ret.setTimeEmploymentStat(tokens[15].trim());
		ret.setCapitalGains(Integer.valueOf(tokens[16].trim()));
		ret.setCapitalLosses(Integer.valueOf(tokens[17].trim()));
		ret.setDividendsFromStocks(Integer.valueOf(tokens[18].trim()));
		ret.setTaxFilerStatus(tokens[19].trim());
		ret.setRegionOfPreviousResidence(tokens[20].trim());
		ret.setStateOfPreviousResidence(tokens[21].trim());
		ret.setDetailedHousehold(tokens[22].trim());
		ret.setDetailedHouseholdSummary(tokens[24].trim());
		ret.setMigrationCodeChange(tokens[25].trim());
		ret.setMigrationCodeChangeInReg(tokens[26].trim());
		ret.setMigrationCodeMoveWithinReg(tokens[27].trim());
		ret.setLiveInThisHouse(tokens[28].trim());
		ret.setMigrationPrev(tokens[29].trim());
		ret.setNumPersonsWorked(tokens[30].trim());
		ret.setFamilyMembersUnder18(tokens[31].trim());
		ret.setCountryOfBirthFather(tokens[32].trim());
		ret.setCountryOfBirthMother(tokens[33].trim());
		ret.setCountryOfBirthSelf(tokens[34].trim());
		ret.setCitizenship(tokens[35].trim());
		ret.setOwnBusiness(tokens[36].trim());
		ret.setFillIncQuestionnaire(tokens[37].trim());
		ret.setVeteransBenefits(tokens[38].trim());
		ret.setWeeksWorkedInYear(Integer.valueOf(tokens[39].trim()));
		ret.setYear(tokens[40].trim());

		return ret;
	}

}
