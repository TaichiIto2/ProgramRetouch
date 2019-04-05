package ec;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyHistoBeans;
import dao.BuyDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッション開始
		HttpSession session = request.getSession();

		try {

			//パロメーター所得
			String id = request.getParameter("buy_id"); //jspのurlのとこで繋がってる
			int Id = Integer.valueOf(id).intValue();

			BuyHistoBeans gbh = BuyDAO.getBuyhis(Id);
			request.setAttribute("gbh", gbh);

			List <BuyHistoBeans> bude = BuyDAO.buyDetail(Id);
			request.setAttribute("bude", bude);

			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}

	}
}
