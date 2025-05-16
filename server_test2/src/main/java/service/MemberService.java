package service;

import dao.MemberDao;
import domain.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	//기능
	
	//회원가입
	public void register(Member member) {
		memberDao.insert(member);
	}
	//회원조회
	public Member findBy(String id) {
		return memberDao.selectOne(id);
	}
}
