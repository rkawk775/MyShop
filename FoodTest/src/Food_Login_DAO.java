import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Food_Login_DAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	private final String LOGIN = "select * from shopinfo where sid = ? and spassword = ?";
	private final String INSERT = "insert into shopinfo value(?, ?, ?, ?, ?, ?)";
	private final String LIST = "select * from shopinfo";

	private Food_Login_DAO() {
	}

	private static Food_Login_DAO instance = new Food_Login_DAO();

	public static Food_Login_DAO getInstance() {
		return instance;
	}

	public int idpassword(String id, String password) {

		conn = Food_DataBase.getConnection();

		try {
			stmt = conn.prepareStatement(LOGIN);

			stmt.setString(1, id);
			stmt.setString(2, password);

			rs = stmt.executeQuery();

			if (rs.next()) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int insertinfo(Food_LoginDTO dto) {

		conn = Food_DataBase.getConnection();

		try {
			stmt = conn.prepareStatement(INSERT);

			stmt.setString(1, dto.getName());
			stmt.setString(2, dto.getOwnername());
			stmt.setString(3, dto.getTel());
			stmt.setString(4, dto.getLocation());
			stmt.setString(5, dto.getId());
			stmt.setString(6, dto.getPassword());

			stmt.executeUpdate();
			
			return 1;
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<Food_LoginDTO> sList() {
		conn = Food_DataBase.getConnection();
		List<Food_LoginDTO> list = new ArrayList<Food_LoginDTO>();

		try {
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Food_LoginDTO dto = new Food_LoginDTO();
				dto.setName(rs.getString("sname"));
				dto.setOwnername(rs.getString("ownername"));
				dto.setTel(rs.getString("stel"));
				dto.setLocation(rs.getString("slocation"));
				dto.setId(rs.getString("sid"));
				dto.setPassword(rs.getString("spassword"));
				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
