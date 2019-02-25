<!DOCTYPE html>
<html>
<head lang="en">
    <title>Spring Boot - FreeMarker</title>
    <script type="text/javascript" src="webjars/jquery/jquery.js"></script>
    <#assign jsurl=urls.getForLookupPath("/js/classpath.js")>
    <script type="text/javascript" src="${request.contextPath}${jsurl}"></script>
    <script type="text/javascript">
        $(function() {
            $('.a').click(function() {
                alert('hello world');
            });
        })
    </script>
    <style type="text/css">
        h2{color:red}
    </style>
</head>
<body>
<h2>首页<h2>

    <font>
        <#list userList as item>
            ${item!}<br />
        </#list>
    </font>

    <button class="a"> click me</button>
</body>
</html>