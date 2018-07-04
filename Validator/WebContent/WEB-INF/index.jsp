<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Validator</title>
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
		<div id="input-form">
			<table>
				<thead>
					<tr>
						<th colspan="2">Source</th>
						<th colspan="2">Target</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Data Source Type:</td>
						<td><select name="sourceType">
								<option value="mysql">MySQL</option>
								<option value="hive2">Hive</option>
								<option value="HDFS">HDFS</option>
						</select></td>
						<td>Data Source Type:</td>
						<td><select name="targetType">
								<option value="mysql">MySQL</option>
								<option value="hive2">Hive</option>
								<option value="HDFS">HDFS</option>
						</select></td>
					</tr>
					<tr>
						<td>Host:</td>
						<td><input type="text" name="sourceHost" id="sourceHost"
							required></td>
						<td>Host:</td>
						<td><input type="text" name="targetHost" id="targetHost"
							required></td>
					</tr>
					<tr>
						<td>Port:</td>
						<td><input type="text" name="sourcePort" id="sourcePort"
							required></td>
						<td>Port:</td>
						<td><input type="text" name="targetPort" id="targetPort"
							required></td>
					</tr>
					<tr>
						<td>Database Name:</td>
						<td><input type="text" name="sourceDb" id="sourceDb" required>
						</td>
						<td>Database Name:</td>
						<td><input type="text" name="targetDb" id="targetDb" required>
						</td>
					</tr>
					<tr>
						<td>User Name:</td>
						<td><input type="text" name="sourceUser" id="sourceUser"
							required></td>
						<td>User Name:</td>
						<td><input type="text" name="targetUser" id="targetUser"
							required></td>
					</tr>
					<tr>
						<td>Database Password:</td>
						<td><input type="password" name="sourcePassword"
							id="sourcePassword" required></td>
						<td>Database Password:</td>
						<td><input type="password" name="targetPassword"
							id="targetPassword" required></td>
					</tr>
					<tr>
						<td>Table Name:</td>
						<td><input type="text" name="sourceTable" id="sourceTable"
							required></td>
						<td>Table Name:</td>
						<td><input type="text" name="targetTable" id="targetTable"
							required></td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;"><a
							href="metadata"><button type="button" name="connector"
									id="connector">Connect to Data Sources</button></a></td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="connectionResult"
								class="alert alert-primary hiddenElement" role="alert"></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>