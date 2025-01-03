
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class WalletFundingServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    private static final double FUNDING_LIMIT=1000.0;
    private final List<FundingTransaction> fundingHistory=new ArrayList<>();
    private static final String DB_URL="jdbc:mysql://localhost:3306/wallet_funding";
    private static final String DB_USER="root";
    private static final String DB_PASSWORD="";
    
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        
        String amount=request.getParameter("amount");
        double amountDouble;
        try{
           amountDouble=Double.parseDouble(amount);
        }catch(NumberFormatException e){
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            out.println("Eroor:Ivalid amount");
            
            return;
            
        }
        
        String paymentMethod=request.getParameter("paymentMethod");
        
        if(amountDouble> FUNDING_LIMIT){
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            out.println("Error:Funding limit exceeded.");
         return;
        }
        try(Connection conn=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD)){
            String query="INSERT INTO funding_transactions(amount payment_method) VALUE(?,?)";
            
            try(PreparedStatement pstmt=conn.prepareStatement(query)){
               pstmt.setDouble(1, amountDouble);
               pstmt.setString(2,paymentMethod);
               
               pstmt.executeUpdate();
            }catch(SQLException e){
                response.setContentType("text/html");
                PrintWriter out=response.getWriter();
                out.println("Error:Unable to process funding request");
            }
           return;
        } catch (SQLException ex) {
            Logger.getLogger(WalletFundingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
        
        //Precess funding request
        FundingTransaction transaction=new FundingTransaction(amountDouble,paymentMethod);
        
        fundingHistory.add(transaction);
        
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("Funding sucessful!");
        
    }
     @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
  
        
     if(request.getParameter("history")!=null){
      try(Connection conn=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD)){
          String query="SELECT * FROM funding_transaction";
          
          try(PreparedStatement pstmt=conn.prepareStatement(query)){
              
          }try(ResultSet rs=pstmt.executeQuery()){
           response.setContentType("text/html");
           PrintWriter out=response.getWriter();
           out.println("<h2>Funding history</h2>");
           out.println("<table>");
           
           out.println("<tr><th>Date</th></th>Amount</th><th>PaymentMethod/th></tr>");
           while(rs.next()){
               out.println("<tr>");
               
               out.println("<td>" + rs.getTimestamp("date")+"</td>");
               out.println("<td>" + rs.getDouble("amount") + "</td>");
               out.println("<td" +rs.getString("payment_method") +"</td>");
               out.println("</tr>");
           }
           out.println("</table");
          }catch(SQLException e){
              response.setContentType("text/html");
           PrintWriter out=response.getWriter();
           out.println("Error:Unable to retrive funding histrory");
          }
      }  catch (SQLException ex) {
             Logger.getLogger(WalletFundingServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
     
     
  }
 }
}