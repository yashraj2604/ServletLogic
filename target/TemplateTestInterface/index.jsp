<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>News Portal</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="ie.css" /><![endif]-->
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
</head>
<body>



document.getElementById('log').innerHTML += '<br>Some new content!';
<div id="log">initial content</div>



<script>
document.getElementById('log').innerHTML += '<br>Some new content!';
window.onload = api_call_lookalikes;

        function api_call_lookalikes()
        {
            $.ajax({
            type: 'GET',

            url: 'urlname',
            success: get_data
            });   // Ajax Call
        };


        function get_data(data)
        {
        var json = JSON.parse(data);
        console.log(json[0].title);
        }
 </script>
</html>

