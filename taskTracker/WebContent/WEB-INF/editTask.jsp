<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css"/>
<title>Edit Task</title>
</head>
<body>
	<div id="box">

		<div id="container">
			<div id="part1">
				<h4 id="title">Task Tracker</h4>
				<a href="task"><button id="close">Close</button></a>
			</div>
			<form class="form-task" method="post" action="edit">
				<div class="row">
					<label for="label-task" class="label-t"> Task </label>
						<input type="text" id="label-task" name="label-task" value="${task.label}" autofocus/>
					
				</div>
				<div class="row">
						<label for="date-task" class="label-t"> Day & Time </label>
						<input type="text" id="date-task" name="date-task" value="${task.date}"/>
						<input type="hidden" name="id" value="${task.id}"/>
						
				</div>
				<div class="row">
					<input id="save" type="submit" value="Modify"/>
				</div>
			</form>
			 
		</div>
	</div>
</body>
</html>