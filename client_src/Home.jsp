<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@page pageEncoding="UTF-8"%>
<%
response.setContentType("text/html; charset=UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<html>
  <head>
    <style>
      .HomeDiv { 
        height:100%; 
        width:100%; 
        margin-top:-5px; 
        border: 2px solid #A2A2A2; 
        background: url("") repeat scroll 0 0 transparent position: relative;
        overflow: hidden;
      }
      #Alageedu { width: 100%; height:100%; }
      .search_box { color: #4B4B4B; font-size: 20px; height: 35px; }
      .tblSt{
        font-family : ariel;
        font-size: 17px;
      }
    </style>

  <title>
    <%="\u0BA4\u0BAE\u0BBF\u0BB4\u0BCD \u0B85\u0BB2\u0B95\u0BC0\u0B9F\u0BCD\u0B9F\u0BC1 \u0BB5\u0BBE\u0BAF\u0BCD\u0BAA\u0BCD\u0BAA\u0BBE\u0B9F\u0BC1"%>
  </title>
  </head>
  <body>
    <script src="http://prabha-1145:8585/tamil/client_src/js/TamilSkk.js"></script>


    <div class="HomeDiv">
      <table width="100%" height="100%" border="0">
        <tr>
          <td>
            <div id="Alageedu">
              <div style="width:100%; height: 100px;">
                <div style="margin-top:30px; margin-left: 300px; width: 400px;height: 35px;">
                  <form method="POST" action="tamilword.do" name="search_frm" onsubmit="return false;">
                    <input type="text" value="Enter the text" name="search_inp" class="search_box" att="Enter the text" onfocus="Tamil.clearBox(this)" id="search_box" onblur="Tamil.setDefaultVal(this)" />
                    <input type="submit" value="Get Alageedu" id="inp_box" style="padding-top:5px; padding-bottom: 5px; margin-top: 1px; height: 100%;" onclick="Tamil.sendReq()">
                  </form>
                </div>
              </div>
              <hr size="3" style="background-color:#4B4B4B;" />
              <div style="margin-left: 35%;">
                <div id="chardesc" style="position: relative; width: 150px; border-bottom: 1px solid; display: none;"> </div>
                <br>
                <div id="asaikal" style="position: relative; width: 150px; border-bottom: 1px solid; display: none;"> </div>
                <br>
                <div id="seer" style="position: relative; width: 150px; border-bottom: 1px solid; display: none;"> </div>
              </div>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </body>
</html>
