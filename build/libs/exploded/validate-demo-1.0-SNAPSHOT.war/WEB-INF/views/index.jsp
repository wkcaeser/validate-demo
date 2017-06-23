<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${title}</title>
  <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
</head>
<body>
<span>${head}</span>
<br>
<strong>输入验证码：</strong><input id="validateText" type="text"><label id="validateStatus" style="display: none"></label>
<div id="validate" style="display: none">
  <img id="validateCode" src=""/><label id="refreshValidateCode">换一张</label>
</div>
<script src="${pageContext.request.contextPath}/static/js/validate/validate.js"></script>
</body>
</html>
