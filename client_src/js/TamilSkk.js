function $(id)
{
  return document.getElementById(id);
}

var Tamil = new function()
{
  var ST = 0;
  var CHARS = 1;
  var CHARDESC = 2;
  var ASAI = 3;
  var SEER = 4;
  var stats = false;
  this.showMyselfDiv = function()
  {
    $("MySelf").style.display="block";
  }

  this.OtherViewsBlocked = function(ids)
  {
    for(var i=0;i<ids.length; i++)
    {
      if($(ids[i]))
      {
        $(ids[i]).style.display = "none";
      }
    }
  }

  this.getAlageetuVaipaduDiv = function()
  {
    this.OtherViewsBlocked(["MySelf"]);
  }

  this.clearBox = function(el)
  {
    if(el.value == "Enter the text")
    {
      el.value = "";
    }
  }

  this.setDefaultVal = function(el)
  {
    if($("search_box").value == "")
    {
      el.value = el.getAttribute("att");
    }
  }

  this.setValueInCharDesc = function(arr,id)
  {
    var st = "";
    st += "<table class=\"tblSt\">"
      st += "<tr><td colspan='3'><b>\u0B8E\u0BB4\u0BC1\u0BA4\u0BCD\u0BA4\u0BC1\u0B95\u0BCD\u0B95\u0BB3\u0BCD</b></td></tr>"; //Ezhuthukkal
    for(var ss in arr)
    {
      if(ss == null) continue;
      st += "<tr>"
      st+= "<td>"+ss+"</td><td>&nbsp;</td><td>"+arr[ss]+"</td>";
      st += "</tr>";
    }
    st += "</table>";
    $(id).innerHTML = st;
    $(id).style.display = "";
  }

  this.setValueInAsaikal = function(arr, id)
  {
    arr = Tamil.trimArray(arr);
    var st = "";
        st += "<table>"
                st += "<tr><td colspan='3'><b>\u0B85\u0B9A\u0BC8\u0B95\u0BB3\u0BCD</b></td></tr>";
    for(var i=0; i<arr.length; i = i+2)
    {
      st += "<tr>"
      st += "<td>"+arr[i] +"</td><td>&nbsp;</td><td>"+arr[i+1]+"</td>";
      st += "</tr>";
    }
    st += "</table>";
    $(id).innerHTML = st;
    $(id).style.display = "";
  }

  this.trimArray = function(arr)
  {
    for(var i=0;i<arr.length; i++)
    {
      if(arr[i] == null)
      {
        arr.splice(i,1);
      }
    }
    return arr;
  }

  this.setValueInSeer = function(seer,id)
  {
    $(id).innerHTML = "<p style=\"white-space: nowrap\"> <b>\u0BB5\u0BBE\u0BAF\u0BCD\u0BAA\u0BCD\u0BAA\u0BBE\u0B9F\u0BC1 : </b>"+seer+"</p>";
    $(id).style.display = "";
  }

  this.handleResponse = function(props)
  {
    if(stats == true)
    {
      Tamil.setValueInCharDesc(props[CHARDESC],"chardesc");
      Tamil.setValueInAsaikal(props[ASAI],"asaikal");
      Tamil.setValueInSeer(props[SEER],"seer");
    }
  }

  this.sendReq = function()
  {
    var inp = $("search_box").value;
    if((inp == "Get Alageedu") || (inp == null))
    {
      alert("Enter the valid character");
      return;
    }
    var url = "";
    var inp_val = $("search_box").value;
    url="tamilword.do";
    Ajax.sendRequest("tamilword.do",Tamil.respMeth,"POST",inp_val);
  }

  this.respMeth = function(respText)
  {
    var prop = eval(respText);
    var stat = prop[0];
    if(stat == "Failure")
    {
      alert("Failure");
    }
    else
    {
      stats = true;
      Tamil.handleResponse(prop);
    }
    //var respArr = eval(respText);

  }
}

var Ajax = new function()
{
  var XMLHttpArray = [
    function() {return new XMLHttpRequest()},
    function() {return new ActiveXObject("Msxml2.XMLHTTP")},
    function() {return new ActiveXObject("Microsoft.XMLHTTP")}
  ];

  this.createXMLHTTPObject = function()
  {
    var xmlhttp = false;
    for(var i=0; i<XMLHttpArray.length; i++)
    {
      try
      {
        xmlhttp = XMLHttpArray[i]();
      }catch(e){
        continue;
      }
      break;
    }
    return xmlhttp;
  }

  this.sendRequest = function(url,callback,method,inp_val)
  {
    var req = this.createXMLHTTPObject();
    req.onreadystatechange= function()
    {
      if(req.readyState != 4) return;
      if(req.status != 200) return;
      callback(req.responseText);
    }
    req.open(method,url);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
    req.setRequestHeader("Content-Length", url.length);
    req.send("search_inp="+inp_val);
  }
}
