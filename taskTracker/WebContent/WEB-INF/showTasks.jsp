<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css"/>
<title>Task Tracker</title>
   
   <script src="actions.js" defer> </script>
</head>
<body>
	<div id="box">

		<div id="container">
			<div id="part1">
				<h4 id="title">Task Tracker</h4>
				<a href="add"><button id="add">Add</button> </a>
			</div>
			<div id="tasks">
				<c:if  test="${!tasks.isEmpty()}">
						<c:forEach items="${tasks}" var="task">
							<c:set var="idTask" value="task-${task.id}"></c:set>
							<section id="${idTask}" class="task">
								<section class="task-action"> 
									<label class="label-task"><c:out value="${task.label}"/></label>
									<a class="delete" onClick="deleteTask( ${task.id} );"> <img src="x-button.png" title="delete task"/></a>
								</section>
								<section class="task-date"><span><c:out value="${task.date}"/></span></section>
							</section>
						 </c:forEach>
					
					
				
				 </c:if>
			</div>
		</div>
	</div>
</body>
</html>