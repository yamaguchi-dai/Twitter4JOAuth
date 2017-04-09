package jums;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.auth.RequestToken;

/**
 * twitterdeveloperの方に登録済み
 * callbackURLの方にアドレスを指定済み
 * この２つを必ず先に行ってください。
 * step1-step4を先に終わらせておくのが推奨です。
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Login() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		HttpSession  session = request.getSession();
		TwitterHelper th = new TwitterHelper();
		RequestToken requestToken = th.getRequestToken();
		
		// ユーザーがAPIにアクセスするためのURLを取得
		String URL = requestToken.getAuthorizationURL();
		
		// APIから帰ってきたときに使うので。
		session.setAttribute("requestToken", requestToken);
		
		// 次のページにURLを持っていきリンクを作る
		request.setAttribute("URL", URL);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
