<%-- 
    Document   : AddLocationAction
    Created on : 30 Sep, 2019, 5:57:26 PM
    Author     : admin
--%>

<%@page import="Utility.ImageEncode"%>
<%@page import="dbConnection.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.servlet.*" %>
<%@page import="org.apache.commons.fileupload.disk.*" %>
<%@page import="java.io.FileInputStream"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.File"%>


<%
//declaring a fileitem variable
    FileItem f_item = null;
    String file_name = "";
    String co = "";
    String encodedfile = "";
    DbConnection con = new DbConnection();
    String name = "", description = "", restriction = "", top_things = "", fee = "", contact = "", lattitude = "", longittude = "", intime = "", outtime = "",
            file = "",district="",place="",holiday="" ;
//checking if request cotains multipart data
    System.out.println("111");
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);

    if (isMultipart) {

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        //declaring a list of form fields
        List items_list = null;

        //assigning fields to list 'items_list'
        try {
            items_list = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            out.println(ex);
        }

        //declaring iterator
        Iterator itr = items_list.iterator();

        //iterating through the list 'items_list'
        if (itr.hasNext()) {
            //typecasting next element in items_list as fileitem
            f_item = (FileItem) itr.next();

            //checking if 'f_item' contains a formfield(common controls like textbox,dropdown,radio buttonetc)
            while (f_item.isFormField()) {
                //getting fieldname and value

                String field = f_item.getFieldName();
                String value = f_item.getString();

                if (field.equalsIgnoreCase("name")) {
                    name = value;
                }
                if (field.equalsIgnoreCase("description")) {
                    description = value;
                }
                if (field.equalsIgnoreCase("restriction")) {
                    restriction = value;
                }
                if (field.equalsIgnoreCase("top_things")) {
                    top_things = value;
                }
                if (field.equalsIgnoreCase("fee")) {
                    fee = value;
                }
                if (field.equalsIgnoreCase("contact")) {
                    contact = value;
                }
                if (field.equalsIgnoreCase("lattitude")) {
                    lattitude = value;
                }
                if (field.equalsIgnoreCase("longittude")) {
                    longittude = value;
                }
                if (field.equalsIgnoreCase("intime")) {
                    intime = value;
                }
                if (field.equalsIgnoreCase("outtime")) {
                    outtime = value;
                }
                 if (field.equalsIgnoreCase("district")) {
                    district = value;
                }
                if (field.equalsIgnoreCase("place")) {
                    place = value;
                }
                if (field.equalsIgnoreCase("holiday")) {
                    holiday = value;
                }

                f_item = (FileItem) itr.next();
            }

            //else part does the image upload
            file_name = f_item.getName();

            //setting path to store image
            File proj_path = new File(config.getServletContext().getRealPath("/"));
            String file_path = proj_path.getParentFile().getParentFile().getPath() + "\\web\\loc_images\\";

            //creating a file object
            File savedFile = new File(file_path + file_name);

            try {
                //writing the file object
                f_item.write(savedFile);
            } catch (Exception ex) {
                out.println(ex);
            }

        }

    }

    System.out.println("111");
    if (!file_name.equals("")) {

        File f = new File(ImageEncode.IMAGE_PATH + file_name);
        BufferedImage image = ImageIO.read(f);

        encodedfile = ImageEncode.encodeToString(image, "jpeg");

        String str = "INSERT INTO `location_details` (`name`,`description`,`district`,`place`,`cam_restrict`,`top_things`,`entry_fee`,`contact`,`lattitude`,`longittude`,`in`,`out`,img_name,`image`,`holiday`)VALUES"
                + "('" + name + "','" + description + "','" + district + "','" + place + "','" + restriction + "','" + top_things + "','" + fee + "','" + contact + "','" + lattitude + "','" + longittude + "','" + intime + "','" + outtime + "','" + file_name + "','" + encodedfile + "','" + holiday + "')";
        System.out.println("str  : " + str);
        if (con.putData(str) > 0) {


%>
<script>
    alert("Succesfully added");
    window.location = "../Admin/AdminHome.jsp";
</script>
<%        } else {
%>
<script>
    alert("Failed ");
    window.location = "../Admin/AdminHome.jsp";
</script>
<%
        }
    }


%>
