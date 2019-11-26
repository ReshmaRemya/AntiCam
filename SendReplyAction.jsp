<%-- 
    Document   : SendReplyAction
    Created on : 30 Oct, 2019, 1:55:35 PM
    Author     : admin
--%>

<%@page import="dbConnection.DbConnection"%>
<%
    DbConnection con = new DbConnection();
    String reply,cid,comp,uid;
    reply = request.getParameter("reply").toString();
    cid = request.getParameter("cid").toString();
    comp = request.getParameter("comp").toString(); 
    uid = request.getParameter("uid").toString(); 

    
    String qry="INSERT INTO `complaint_reply`(`uid`,`cid`,`complaint`,`reply`) VALUES('" + uid + "','" + cid + "','" + comp + "','" + reply + "')";
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
