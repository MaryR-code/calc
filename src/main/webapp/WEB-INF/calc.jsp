<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <link href="https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,800,900" rel="stylesheet">
    <title>Calculator</title>
</head>
<body>
<div class="w3-display-container" style="height:50%; width:75%">
    <div class="w3-display-middle">
        <form method="post">
            <h2> Do the math: </h2>
            <h1> ${player.num1} + ${player.num2} = </h1>
            <input type="number" name="playerSum" class="w3-input w3-border w3-light-grey" style="width:50%">
            <br/>
            <button type="submit" class="w3-btn w3-green w3-large">Check</button>
        </form>
    </div>
</div>
</body>
</html>

