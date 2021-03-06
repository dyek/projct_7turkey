package com.dohwaji.app.mypage;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dohwaji.action.Action;
import com.dohwaji.action.ActionForward;
import com.dohwaji.app.bttip.dao.BeautytipBean;
import com.dohwaji.app.bttip.dao.BeautytipDAO;
import com.dohwaji.app.bttip.dao.BeautytipFileBean;
import com.dohwaji.app.bttip.dao.BeautytipFileDao;
import com.dohwaji.app.bttip.dao.BeautytipMultiFileBean;
import com.dohwaji.app.bttip.dao.BeautytipMultiFileDao;
import com.dohwaji.app.bttip.dao.BeautytipMultiTextBean;
import com.dohwaji.app.bttip.dao.BeautytipMultiTextDao;
import com.dohwaji.app.member.dao.MemberBean;
import com.dohwaji.app.member.dao.MemberDAO;
import com.dohwaji.app.member.dao.MemberFilesDAO;
import com.dohwaji.app.mypage.dao.FollowDAO;
import com.dohwaji.app.mypage.dao.MypageDAO;
import com.dohwaji.app.product.dao.ProductReplyBean;
import com.dohwaji.app.product.dao.ProductReplyDAO;
import com.dohwaji.config.ProjectConfig;

public class MypageSignOutAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		BeautytipBean tip_bean = new BeautytipBean();
		BeautytipFileBean file_bean = new BeautytipFileBean();
		BeautytipMultiTextBean multitext_bean = new BeautytipMultiTextBean();
		BeautytipMultiFileBean multifile_bean = new BeautytipMultiFileBean();
		
		BeautytipDAO bt_dao = new BeautytipDAO();
		BeautytipFileDao bf_dao = new BeautytipFileDao();
		BeautytipMultiTextDao bmt_dao = new BeautytipMultiTextDao();
		BeautytipMultiFileDao bmf_dao = new BeautytipMultiFileDao();
		ProductReplyDAO pr_dao = new ProductReplyDAO();
		MypageDAO m_dao = new MypageDAO();
		FollowDAO f_dao = new FollowDAO();
		MemberDAO u_dao = new MemberDAO();
		MemberFilesDAO mf_dao = new MemberFilesDAO();
		
		
		String id = (String)session.getAttribute("session_id");
		int userNum = u_dao.getimgnum(id);
		//TODO
		String usersaveFolder = ProjectConfig.USER_UPLOAD_LOCATION;
		String bttipsaveFolder = ProjectConfig.BTTIP_UPLOAD_LOCATION;
		
		//????????? ????????? ??????
		List<BeautytipBean> myBttip = m_dao.getBttipList(id); 
		for(BeautytipBean i : myBttip) {
			int bttip = i.getBttipNum();
			List<BeautytipMultiFileBean> multifile = bmf_dao.getMultiImgDetail(bttip);
			
			for(BeautytipMultiFileBean temp : multifile) {
				File f = new File(bttipsaveFolder + "\\" + temp.getFilename());
				if(f.exists()) {f.delete();}
			}
			
			File f = new File(bttipsaveFolder + "\\" + bf_dao.getBttipImg(bttip));
			if(f.exists()) {f.delete();}
			bmf_dao.deleteMultiFile(bttip);
			bmt_dao.deleteMultiText(bttip);
			bf_dao.deleteFile(bttip);
			bt_dao.deleteBttip(bttip);
		}
		
		//?????? ??????
		List<Integer> myReview = m_dao.getreviewList(id);
		for(Integer i : myReview) {
			pr_dao.deleteOneReply(i);
		}
		
		//?????????, ????????? ??????
		List<Integer> followerList = f_dao.getFollowerList(userNum);
		for(int i : followerList) {
			int followingNum = m_dao.getMemberInfo(i).getFollowingnum();
			int followerNum = m_dao.getMemberInfo(userNum).getFollowernum();
			f_dao.deleteFollow(i, userNum, followingNum, followerNum);
		}
		List<Integer> followingList = f_dao.getFollowingList(userNum);
		for(int i : followingList) {
			int followingNum = m_dao.getMemberInfo(userNum).getFollowingnum();
			int followerNum = m_dao.getMemberInfo(i).getFollowernum();
			f_dao.deleteFollow(userNum, i, followingNum, followerNum);
		}
		
		
		//savebttip ??????
		String[] save = { "bttipsavenum1", "bttipsavenum2", "bttipsavenum3" };
		int bttipsavenum = 0;
		for (int i = 0; i < save.length; i++) {
			String savenum = request.getParameter(save[i]);
			if (savenum == null) {
				continue;
			}
			bttipsavenum = Integer.parseInt(savenum);
		}
		
		//?????? ??????
		String img = mf_dao.selectFiles(userNum);
		File f = new File(usersaveFolder + "\\" + img);
		if(f.exists()) {f.delete();}
		
		mf_dao.deleteFiles(userNum);
		m_dao.deleteMybttip(bttipsavenum);
		
		if(!m_dao.delectUser(userNum)) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.println("<script>");
			out.println("alert('?????? ?????? ??????. ?????? ??????????????????.'); history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		session.invalidate();
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/index.jsp");
		return forward;
	}
}
