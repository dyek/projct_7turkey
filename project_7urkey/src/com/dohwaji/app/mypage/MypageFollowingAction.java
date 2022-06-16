package com.dohwaji.app.mypage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dohwaji.action.Action;
import com.dohwaji.action.ActionForward;
import com.dohwaji.app.member.dao.MemberBean;
import com.dohwaji.app.member.dao.MemberDAO;
import com.dohwaji.app.member.dao.MemberFilesDAO;
import com.dohwaji.app.mypage.dao.FollowDAO;
import com.dohwaji.app.mypage.dao.MypageBean;
import com.dohwaji.app.mypage.dao.MypageDAO;

public class MypageFollowingAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		FollowDAO f_dao = new FollowDAO();
		MypageDAO m_dao = new MypageDAO();
		MemberDAO u_dao = new MemberDAO();
		MemberFilesDAO mf_dao = new MemberFilesDAO();
		
		String id = (String) session.getAttribute("session_id");
		int userNum = u_dao.getimgnum(id);
		
		List<Integer> followingList = f_dao.getFollowingList(userNum);
		List<MypageBean> followingInfoList = new ArrayList<>();
		
		for(int i : followingList) {
			MemberBean user = m_dao.getMemberInfo(i);
			MypageBean temp = new MypageBean();
			temp.setUsernum(i);
			temp.setUserid(user.getUserid());
			temp.setUsername(user.getUsername());
			temp.setUserfileimg(mf_dao.selectFiles(i));
			temp.setF_state(f_dao.checkFollow(userNum, i));
			followingInfoList.add(temp);
		}
		
		request.setAttribute("followingList", followingInfoList);
		
		forward.setRedirect(false);
		forward.setPath("/blue/mypage/following.jsp");
		
		return forward;
	}
}
