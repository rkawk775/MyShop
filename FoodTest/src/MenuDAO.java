import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuDAO extends Food_DataBase{
	//1
	private static MenuDAO _mdao;
	
	//2 생성자의 은닉화 선언
	private MenuDAO() {
		
	}
	
	//3 정적영역에서 클래스의 인스턴스를 생성하여 참조필드에 저장
	static {
		_mdao=new MenuDAO();
	}
	
	//4 참조필드에 저장된 인스턴스를 반환하는 메소드 작성
	public static MenuDAO getDAO() {
		return _mdao;
	}
	
	//DB테이블에 새로운 행으로 삽입, 정보 저장
	public int insertMenu(MenuDTO menu) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();	
			
			String sql="insert into memu values(?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, menu.getTitle());
			pstmt.setString(2, menu.getImage());
			pstmt.setString(3, menu.getMemo());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertMenu() : "+e.getMessage());
		} finally {
			close(con, pstmt);
		} return rows;
	}	
}
