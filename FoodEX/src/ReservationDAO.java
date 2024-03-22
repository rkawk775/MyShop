import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO extends Food_DataBase{
	//1
	private static ReservationDAO _dao;
	
	//2 생성자의 은닉화 선언
	private ReservationDAO() {
		// TODO Auto-generated constructor stub
	}
	
	//3 정적영역에서 클래스의 인스턴스를 생성하여 참조필드에 저장
	static {
		_dao=new ReservationDAO();
	}
	
	//4 참조필드에 저장된 인스턴스를 반환하는 메소드 작성
	public static ReservationDAO getDAO() {
		return _dao;
	}
	/*
	//테이블에 새로운 행으로 삽입, 정보 저장
	public int insertReserv(ReservationDTO reservation) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();	
			
			String sql="insert into reservation values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, reservation.getRname());
			pstmt.setString(2, reservation.getRtel());
			pstmt.setInt(3, reservation.getRcount());
			pstmt.setString(4, reservation.getRdate());
			pstmt.setString(5, reservation.getRtime());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertReserv() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		} return rows;
	}	*/
	
	//정보 변경 
	public int updateReserv(ReservationDTO reservation) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			String sql="update reservation set rname=?,rtel=?,rcount=?,rdate=?, rtime=? where rid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, reservation.getRname());
			pstmt.setString(2, reservation.getRtel());
			pstmt.setInt(3, reservation.getRcount());
			pstmt.setString(4, reservation.getRdate());
			pstmt.setString(5, reservation.getRtime());
			pstmt.setInt(6, reservation.getRid());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateReserv() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		} return rows;
	}
	
	//정보 삭제  - 정보삭제할때는 id값 정수로 변환해서 넣기
	public int deleteReserv(int id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		
		try {
			con=getConnection();
			
			String sql="delete from reservation where rid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, id); //
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteReserv() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		} 
		return rows;
	}
	
	
	//id값으로 리스트 뽑아오기(id정수값으로)
	public List<ReservationDTO> selectIdManageList(int id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ReservationDTO> reservationList=new ArrayList<ReservationDTO>();
		try {
			con=getConnection();
			
			String sql="select * from reservation where rid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, id); 
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				ReservationDTO reservation=new ReservationDTO();
				reservation.setRid(rs.getInt("rid"));
				reservation.setRname(rs.getString("rname"));
				reservation.setRtel(rs.getString("rtel"));
			    reservation.setRcount(rs.getInt("rcount"));
				reservation.setRdate(rs.getString("rdate"));
				reservation.setRtime(rs.getString("rtime"));
				reservationList.add(reservation);
			}
			
		} catch (Exception e) {
			System.out.println("[에러] =selectIdManageList() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			try {
				if(rs!=null)
					rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			close(con, pstmt);
		} 
		return reservationList;
	}
	
	//모든 학생 정보를 검색하여 반환하는 메소드 
	//=> 다중행 검색 
	public List<ReservationDTO> selectAllManagerList() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ReservationDTO> reservationList=new ArrayList<ReservationDTO>();
		try {
			con=getConnection();
			
			String sql="select * from reservation order by rid";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				ReservationDTO reservation=new ReservationDTO();
				reservation.setRid(rs.getInt("rid"));
				reservation.setRname(rs.getString("rname"));
				reservation.setRtel(rs.getString("rtel"));
			    reservation.setRcount(rs.getInt("rcount"));
				reservation.setRdate(rs.getString("rdate"));
				reservation.setRtime(rs.getString("rtime"));
				reservationList.add(reservation);
			}
		} catch (SQLException e) {
			System.out.println("[에러] =selectAllManageList() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			try {
				if(rs!=null)
					rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			close(con, pstmt);
		}
		return reservationList; 
	}
}

