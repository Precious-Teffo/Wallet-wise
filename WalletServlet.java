
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WalletServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
       resp.setContentType("application/json"); 
       PrintWriter out=resp.getWriter();
       String walletName=req.getParameter("walletName");
       String walletDescription=req.getParameter("walletDescription");
       String walletType=req.getParameter("walletType");
       String walletCurrency=req.getParameter("walletCurrency");
       
       JSONObject walletData=new JSONObject();
       walletData.put("walletName", walletName);
       walletData.put("walletDescription", walletDescription);
       walletData.put("walletType", walletType);
       walletData.put("walletCurrency", walletCurrency);
       
       //Create a new in database
       
       Wallet wallet=Wallet(walletData);
       WalletDAO walletDAO=new WalletDAO();
       walletDAO.createWallet(wallet);
       out.println(walletData.toString());
    }

    private Wallet Wallet(JSONObject walletData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}