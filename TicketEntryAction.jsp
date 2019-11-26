<%-- 
    Document   : TicketEntryAction
    Created on : 30 Oct, 2019, 10:55:13 AM
    Author     : admin
--%>

<%@page import="dbConnection.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    DbConnection con = new DbConnection();
    String name, no, spot, price, total,ticketno;
    name = request.getParameter("name").toString();
    no = request.getParameter("no").toString();
    spot = request.getParameter("spot").toString();
    price = request.getParameter("price").toString();
    total=request.getParameter("total").toString();
    ticketno=request.getParameter("ticketno").toString();
    
    String qry="INSERT INTO `ticket_entry` (`ticketno`,`name`,`no`,`spot`,`price`,`total`) VALUES('" + ticketno + "','" + name + "','" + no + "','" + spot + "','" + price + "','" + total + "')";
     System.out.println("qry  : "+qry);
    
       if (con.putData(qry) > 0) {
     
%>
<script>
    alert("Succesfully added");
    window.location = "../Admin/AdminHome.jsp";
</script>

<%
      }else {

%>
<script>
    alert("Failed ");
    window.location = "../Admin/AdminHome.jsp";
</script>
<%
        }
    
%>
