<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customLib"%>

<c:url value="/../resources/css" var="css" />
<c:url value="/../resources/js" var="js" />

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="${css}/bootstrap.min.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="${css}/font-awesome.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="${css}/main.css" />" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<customLib:link m_class="navbar-brand" 
							uri="display_computers" 
							text="Application - Computer Database"/>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="add_computer" method="POST" id="computer_form">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> 
								<input type="text" class="form-control has-feedback" id="computerName"
									   name="computerName" placeholder="Computer name">
							</div>
							
							<div class="form-group">
								<label for="introduced">Introduced date</label> 
								<input type="date" class="form-control has-feedback" id="introduced"
									   name="introduced" placeholder="Introduced date">
							</div>
							
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> 
								<input type="date" class="form-control has-feedback" id="discontinued"
									   name="discontinued" placeholder="Discontinued date">
							</div>
							
							<div class="form-group">
								<label for="companyId">Company</label> 
								<select class="form-control has-feedback" id="companyId" name="companyId">
									<option value="0">--</option>

									<c:forEach items="${companies}" var="companies">
										<option value="${companies.id}">${companies.name}</option>
									</c:forEach>

								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <customLib:link m_class="btn btn-default" uri="display_computers" current_page="${page}" text="Cancel"/>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<script src="<c:url value="${js}/jquery.min.js" />"></script>
	<script src="<c:url value="${js}/jquery.validate.js" />"></script>
	<script src="<c:url value="${js}/jquery.validate.additional.js" />"></script>
	<script src="<c:url value="${js}/validator.js" />"></script>
</body>
</html>