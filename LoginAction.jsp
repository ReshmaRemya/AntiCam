<%-- 
    Document   : LoginAction
    Created on : 30 Sep, 2019, 11:59:49 AM
    Author     : admin
--%>

        <%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%
            
            String uname,pass;
            uname=request.getParameter("uname");
             pass=request.getParameter("pass");
             String qry="SELECT `uid`,`type` FROM `login` WHERE `username`='"+uname+"' AND `password`='"+pass+"'";
             System.out.println("qry : "+qry);
              dbConnection.DbConnection ob=new dbConnection.DbConnection();
              Iterator it=ob.getData(qry).iterator();
             if(it.hasNext())
            {
             
                Vector v=(Vector)it.next();
                session.setAttribute("logid",v.get(0));
                
                if(v.get(1).equals("admin"))
                {
                %>
                <script>
                    alert("Login success");
                    window.location="../Admin/AdminHome.jsp";
                    </script>
            <%
              }
       
            else{

                    %>
                    <script>
                    alert("Login failed");
                    window.location="../Login.jsp";
                    </script>    
                

                <% }
    }

else{

                    %>
                    <script>
                    alert("Login failed");
                    window.location="../Login.jsp";
                    </script>    
                

                <% }
%>