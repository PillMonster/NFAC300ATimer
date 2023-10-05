package chien.myweb.NFAC300ATimer.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import chien.myweb.NFAC300ATimer.entity.TestInfo;
import chien.myweb.NFAC300ATimer.entity.TestResult;
import chien.myweb.NFAC300ATimer.entity.Timer;

@Repository
public class TimerDaoImpl implements TimerDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public TestInfo findTestInfo(){
		String sql = "select * from testInfo;";
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
	            TestInfo testInfo = new TestInfo();
	            testInfo.setProductType(rs.getString("productType"));
	            testInfo.setPoleNum(rs.getString("poleNum"));
	            testInfo.setAmmeter(rs.getString("ammeter"));
	            testInfo.setTestPerson(rs.getString("testPerson"));
	            testInfo.setSentMessage(rs.getString("sentMessage"));
	            return testInfo;
        });
	}
	
	@Override
	public int findRunState105() {
		String sql = "select electricCurrent105 from testDateTime;";
		Timer timer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Timer>(Timer.class));
		return timer.getElectricCurrent105();
	}
	
	@Override
	public int findRunState130() {
		String sql = "select electricCurrent130 from testDateTime;";
		Timer timer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Timer>(Timer.class));
		return timer.getElectricCurrent130();
	}
	
	@Override
	public int findRunStopState() {
		String sql = "select electricCurrentStop from testDateTime;";
		Timer timer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Timer>(Timer.class));
		return timer.getElectricCurrentStop();
	}

	@Override
	public Timestamp findCloseDateTime() {
		String sql = "select closeDateTime from testDateTime;";
		Timer timer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Timer>(Timer.class));
		return timer.getCloseDateTime();
	}

	@Override
	public String findCloseSecond() {
		String sql = "select closeSecond from testDateTime;";
		Timer timer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Timer>(Timer.class));
		return timer.getCloseSecond();
	}

	@Override
	public String findCurrentSecond() {
		String sql = "select currentSecond from testDateTime;";
		Timer timer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Timer>(Timer.class));
		return timer.getCurrentSecond();
	}

	@Override
	public List<TestResult> findAll(){
		return this.jdbcTemplate.query("select * from testResult", 
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
