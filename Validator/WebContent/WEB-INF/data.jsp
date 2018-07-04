<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Data Validation</title>
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
		<div id="dataValidation">
			<table>
				<thead>
					<tr>
						<th colspan="2">Data Validation</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" name="rowByRowValidation"
							id="rowByRowValidation"></td>
						<td>Row by Row Validation (MD5)</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="rowByRowValidationResult"
								class="alert alert-primary hiddenElement" role="alert"></div>
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="sampleValidation"
							id="sampleValidation"></td>
						<td>Validation of Samples || No. of Samples: <input
							type="text" name="sampleLimit" id="sampleLimit">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="sampleValidationResult"
								class="alert alert-primary hiddenElement" role="alert"></div>
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="customQueryValidation"
							id="customQueryValidation"></td>
						<td>Validate Custom Query</td>
					</tr>
					<tr>
						<td></td>
						<td><textarea rows="3" cols="30" name="sourceCustomQuery"
								id="sourceCustomQuery"></textarea> <textarea rows="3" cols="30"
								name="targetCustomQuery" id="targetCustomQuery"></textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="checkbox" name="outputValidation"
							id="outputValidation"> Validate Output</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="customQueryOutputValidationResult"
								class="alert alert-primary hiddenElement" role="alert"></div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="checkbox" name="assertValueValidation"
							id="assertValueValidation"> Validate by Assert Value ||
							Assert Value: <input type="text" name="assertValue"
							id="assertValue"></td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="customQueryAssertValueValidationResult"
								class="alert alert-primary hiddenElement" role="alert"></div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="checkbox" name="resultCountValidation"
							id="resultCountValidation"> Validate Result Count</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="customQueryResultCountValidationResult"
								class="alert alert-primary hiddenElement" role="alert"></div>
						</td>
					</tr>
					<tr>
						<td colspan="2"><a href="report"><button type="submit">Validate</button></a></td>
					</tr>
			</table>
		</div>
	</div>
</body>
</html>