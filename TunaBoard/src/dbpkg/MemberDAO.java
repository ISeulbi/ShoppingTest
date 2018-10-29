package dbpkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	public static void insert(MemberVO vo){
		String query = " INSERT INTO MEMBER_TBL_02 "
				+ " (  custno, custname, phone, address, joindate, grade, city ) "
				+ " VALUES "
				+ " ( ?, ?, ?, ?, ?, ?, ? ) ";
		try{
			conn = DBConn.getConnecion();
			ps = conn.prepareStatement(query);
			ps.setInt(1, vo.getCustno());
			ps.setString(2, vo.getCustname());
			ps.setString(3, vo.getPhone());
			ps.setString(4, vo.getAddress());
			ps.setString(5, vo.getJoindate());
			ps.setString(6, vo.getGrade());
			ps.setString(7, vo.getCity());
			ps.executeQuery();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBConn.close(conn, ps, rs);
		}
	}
	
	public static MemberVO getMaxCustno(){
		MemberVO vo = new MemberVO();
		String sql = " select nvl(max(custno), 100000) + 1 "
				+ " , to_char(sysdate, 'YYYYMMDD' ) from member_tbl_02 ";
		try {
			conn = DBConn.getConnecion();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				vo.setCustno(rs.getInt(1));
				vo.setJoindate(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, ps, rs);
		}
		return vo;
	}
	
	public static List<MemberVO> getList(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		String sql = " select custno, custname, phone, address, to_char(joindate, 'YYYY-MM-DD') as joindate, "
				+ " ( decode(grade, 'A', 'VIP', 'B','일반' , 'C', '직원')) as grade, city from MEMBER_TBL_02 order by custno ";
		
		try {
			conn = DBConn.getConnecion();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				MemberVO vo = new MemberVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setJoindate(rs.getString("joindate"));
				vo.setGrade(rs.getString("grade"));
				vo.setCity(rs.getString("city"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConn.close(conn, ps, rs);
		}
		
		return list;
	}
	
	public static MemberVO getMember(int custno){
		MemberVO vo = new MemberVO();
		String sql = " select custno, custname, phone, address, to_char(joindate, 'YYYYMMDD') as joindate, "
				+ " grade, city from MEMBER_TBL_02 "
				+ " where custno=? ";
		try {
			conn = DBConn.getConnecion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, custno);
			rs = ps.executeQuery();
			
			while(rs.next()){
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setJoindate(rs.getString("joindate"));
				vo.setGrade(rs.getString("grade"));
				vo.setCity(rs.getString("city"));
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			DBConn.close(conn, ps, rs);
		}
		
		return vo;
	}
	
	public static void setUpdate(MemberVO vo){
		String sql = " UPDATE MEMBER_TBL_02 SET custname = ?, phone = ?, address = ?, joindate = ?, grade = ?,  city = ? "
				+ " where CUSTNO = ? ";
		try {
			conn = DBConn.getConnecion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getCustname());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddress());
			ps.setString(4, vo.getJoindate());
			ps.setString(5, vo.getGrade());
			ps.setString(6, vo.getCity());
			ps.setInt(7, vo.getCustno());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, ps, rs);
		}
	}
	
	public static List<MemberVO> getSales(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		String sql = " select a.CUSTNO, a.CUSTNAME, (decode(a.grade, 'A', 'VIP', 'B','일반', 'C', '직원')) as grade, SUM(b.PRICE) as price "
				+ " from MEMBER_TBL_02 a inner join MONEY_TBL_02 b on a.CUSTNO=b.CUSTNO "
				+ " group by b.CUSTNO, a.CUSTNO, a.CUSTNAME, a.GRADE, (decode(a.grade, 'A', 'VIP', 'B','일반', 'C', '직원')) "
				+ " order by price DESC ";
		try {
			conn = DBConn.getConnecion();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				MemberVO vo = new MemberVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setGrade(rs.getString("grade"));
				vo.setSales(rs.getInt("price"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConn.close(conn, ps, rs);
		}	
		return list;
	}
}
