<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Metadata Validation</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="js/script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
.container {
	margin: auto;
	width: 80%;
	padding: 10px;
}

table {
	width: 100%;
}

.hiddenElement {
	display: none;
	visibility: hidden;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div id="metadata">
			<table>
				<thead>
					<tr>
						<th colspan="2">MetaData Validation</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" name="matchColCount"
							id="matchColCount"></td>
						<td>Validate Column Count</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="colCountValidationResult"
								class="alert alert-primary hiddenElement" role="alert"></div>
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="matchColDataTypes"
							id="matchColDataTypes"></td>
						<td>Validate Column Data type</td>
					</tr>
					<tr>
						<td colspan="2"><a href="data"><button type="submit">Validate</button></a>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="colDataTypesValidationResult"
								class="alert alert-primary hiddenElement" role="alert"></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>