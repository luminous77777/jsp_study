package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Member;
import util.DBUtil;

//DAO
//Data Access Object
public class MemberDao {

//	public static void main(String[] args) {
//		new MemberDao().insert(Member.builder().id("sample").pw("1234").name("개똥이").build());
//		System.out.println(new MemberDao().selectOne("sample"));
//		System.out.println(new MemberDao().selectOne("jae"));
//		System.out.println(new MemberDao().selectOne(1l));
//		System.out.println(new MemberDao().selectOne(2l));
//	}

	// insert
	// select

	public void insert(Member member) {
		// 1.접속객체 취득 2.문장생성 3. 실행 후 처리
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into member (id, pw, name) values (?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());

			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(String id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("select * from member where id =?");
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = Member.builder().num(rs.getLong("num")).id(rs.getString("id")).pw(rs.getString("pw"))
						.name(rs.getString("name")).regDate(rs.getDate("regdate")).build();
				return member;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	public Member selectOne(Long num) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement("select * from member where num = ?");
			pstmt.setLong(1, num);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = Member.builder().num(rs.getLong("num")).id(rs.getString("id")).pw(rs.getString("pw"))
						.name(rs.getString("name")).regDate(rs.getDate("regdate")).build();
				return member;
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return null;
	}
}
