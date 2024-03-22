
public class ReservationDTO {
	private int rid;                //대기번호(자동증가)
	private String rname;           //고객 성함
	private String rtel;            //고객 전화번호
	private int rcount;             //인원 수
	private String rdate;           //예약 날짜
	private String rtime;           //예약 시간
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRtel() {
		return rtel;
	}
	public void setRtel(String rtel) {
		this.rtel = rtel;
	}
	public int getRcount() {
		return rcount;
	}
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	@Override
	public String toString() {
		return "reservationDTO [rid=" + rid + ", rname=" + rname + ", rtel=" + rtel + ", rcount=" + rcount + ", rdate="
				+ rdate + ", rtime=" + rtime + "]";
	}
	
	
}
