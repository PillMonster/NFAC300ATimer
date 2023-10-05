package chien.myweb.NFAC300ATimer.entity;

import java.sql.Timestamp;

import org.springframework.data.relational.core.mapping.Table;

@Table(name = "testDateTime")
public class Timer {
	private int electricCurrent105;
	private int electricCurrent130;
	private int electricCurrentStop;
	private Timestamp closeDateTime;
	private String closeSecond;
	private String currentSecond;
	
	
	public int getElectricCurrent105() {
		return electricCurrent105;
	}
	public void setElectricCurrent105(int electricCurrent105) {
		this.electricCurrent105 = electricCurrent105;
	}
	public int getElectricCurrent130() {
		return electricCurrent130;
	}
	public void setElectricCurrent130(int electricCurrent130) {
		this.electricCurrent130 = electricCurrent130;
	}
	public int getElectricCurrentStop() {
		return electricCurrentStop;
	}
	public void setElectricCurrentStop(int electricCurrentStop) {
		this.electricCurrentStop = electricCurrentStop;
	}
	public Timestamp getCloseDateTime() {
		return closeDateTime;
	}
	public void setCloseDateTime(Timestamp closeDateTime) {
		this.closeDateTime = closeDateTime;
	}
	public String getCloseSecond() {
		return closeSecond;
	}
	public void setCloseSecond(String closeSecond) {
		this.closeSecond = closeSecond;
	}
	public String getCurrentSecond() {
		return currentSecond;
	}
	public void setCurrentSecond(String currentSecond) {
		this.currentSecond = currentSecond;
	}
	
	
}
