package chien.myweb.NFAC300ATimer.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import chien.myweb.NFAC300ATimer.entity.TestResult;

@SuppressWarnings("deprecation")
@Repository
public class TestResultDaoImpl implements TestResultDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<String> findProduct() {
		String sql = "select distinct productType from testResult;";
		List<String> productTypeList = jdbcTemplate.queryForList(sql, String.class);
		Collections.sort(productTypeList);
		return productTypeList;
	}
	
	/*public List<String> findPoleNum() {
		String sql = "select distinct poleNum from testResult;";
		List<String> PoleNumList = jdbcTemplate.queryForList(sql, String.class);
		Collections.sort(PoleNumList);
		return PoleNumList;
	}*/
	@Override
	public List<String> findAmmeter() {
		String sql = "select distinct ammeter from testResult;";
		List<String> ammeterList = jdbcTemplate.queryForList(sql, String.class);
		Collections.sort(ammeterList);
		return ammeterList;
	}
	@Override
	public List<String> findTestPerson() {
		String sql = "select distinct testPerson from testResult;";
		List<String> testPersonList = jdbcTemplate.queryForList(sql, String.class);
		Collections.sort(testPersonList);
		return testPersonList;
	}
	
	/*public List<String> findResult() {
		String sql = "select distinct result from testResult;";
		List<String> resultList = jdbcTemplate.queryForList(sql, String.class);
		Collections.sort(resultList);
		return resultList;
	}*/
	@Override
	public String findTestDate() {
		String sql = "SELECT MIN(finishedTestTime) FROM testResult";
		String startTestDate = jdbcTemplate.queryForObject(sql, String.class);

		return startTestDate;
	}
	
	@Override
	public List<TestResult> findByMultiple(String productType, String poleNum, String ammeter, String testPerson, String result, String startDateTime, String endDateTime) {

		String sql = "select * from testResult where productType=? and poleNum=? and ammeter=? and testPerson=? and result=?"
				+ " and finishedTestTime >=? and finishedTestTime <=?";

		return this.jdbcTemplate.query(
				sql,
				new Object[]{productType, poleNum, ammeter, testPerson, result, startDateTime +" 00:00:00", endDateTime +" 23:59:59"},
				(rs, rowNum) -> new TestResult(  // 使用lambda方法
						rs.getInt("id"),
						rs.getString("productType"),
						rs.getString("poleNum"),
						rs.getString("ammeter"),
						rs.getString("testPerson"),
						rs.getString("trip105"),
						rs.getString("trip130"),
						rs.getString("result"),
						rs.getTimestamp("finishedTestTime"),
						rs.getString("testMessage"))
		);
	}
}
