package jums;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import twitter4j.auth.RequestToken;

/**
 * TwitterAPIのCALLBACKURLにはこのサーブレットを指定する
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = getServletContext();
	
		TwitterHelper th = new TwitterHelper();

		// APIからURLで送られてきた情報を受け取る
		String pin = request.getParameter("oauth_verifier");
		// Loginで保存したRequestTokenを再度呼び出す
		RequestToken requestToken = (RequestToken) sc.getAttribute("requestToken");
		
		//Oauthでえた情報を元になにかしらの操作を行う。今回は名前を取得するメソッド
		String name = th.setApi(requestToken, pin);
		
		
		
		request.setAttribute("name", name);
		request.getRequestDispatcher("result.jsp").forward(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
