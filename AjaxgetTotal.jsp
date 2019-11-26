<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>

<%
    dbConnection.DbConnection obj = new dbConnection.DbConnection();
    String sid=request.getParameter("sid").toString();
    String no=request.getParameter("no").toString();
    String qry = "SELECT `entry_fee` FROM `location_details` WHERE `lid`='"+sid+"'";
    Iterator it = obj.getData(qry).iterator();
    if (it.hasNext()) {
        Vector v = (Vector) it.next();
        int total=Integer.parseInt(no)*Integer.parseInt(v.get(0).toString());
        
%>
Price <input type="text" name="price" value="<%=(v.get(0))%>" class="form-control_edited" readonly=""  required="">
Total <input type="number" name="total" value="<%=total%>" class="form-control_edited" readonly="" style="color: red" required="">

    

<% }
%>

