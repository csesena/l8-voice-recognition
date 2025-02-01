<?php
// Replace with real GCM browser / server API key from Google APIs
$apiKey = 'my APIKey';

// Replace with real client registration IDs, most likely stored in your database
$registrationIDs = array( 'myRegistrationID' );

// Set request URL to GCM endpoint
$url = 'https://android.googleapis.com/gcm/send';

// Set request headers (authentication and payload type)
$headers = array( 
                    'Authorization: key=' . $apiKey,
                    'Content-Type: application/json'
                );

if (isset($_POST["accion"])) {

	print("Acción elegida: ".$_POST["accion"]." <br>");
		
	$color = $_POST["color"] ;
	
switch ($_POST["accion"]) {
    case "backled":
        $data = array( 'message' => 'setBackLedColor?color='.$color);
		print("Color elegido: #".$color." <br>");
		break;
    case "matrix":
        $data = array( 'message' => 'setMatrixColor?color='.$color);
		print("Color elegido: #".$color." <br>");
		break;
    case "clear":
        $data = array( 'message' => 'clear' );
		break;
	case "closebt":
        $data = array( 'message' => 'closeBtConnection' );
		break;
	default:
		$data = array( 'message' => 'closeBtConnection' );
		break;
}

	// Set POST variables (device IDs and payload)
	$fields = array(
					'registration_ids'  => $registrationIDs,
					'data'              => $data,
					);
	
	// Open connection
	$ch = curl_init();

	// Set the url
	curl_setopt( $ch, CURLOPT_URL, $url );

	// Set request method to POST
	curl_setopt( $ch, CURLOPT_POST, true );

	// Set custom headers
	curl_setopt( $ch, CURLOPT_HTTPHEADER, $headers);

	// Get response back as string
	curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );

	// Set post data
	curl_setopt( $ch, CURLOPT_POSTFIELDS, json_encode( $fields ) );

	// Send the request
	$result = curl_exec($ch);

	// Close connection
	curl_close($ch);

	// Debug GCM response
	print("Resultado: ".$result." <br>");
	
}

?>

<html>
	<head>
		<meta charset="UTF-8">
		<script type="text/javascript" src="jscolor/jscolor.js"></script>
	</head>
	<body>
		<h2>L8 Smartlight Test</h2>
		<form method="post">
			<input type="radio" name="accion" value="backled" checked ="checked">Back Led<br>
			<input type="radio" name="accion" value="matrix">Matrix<br>
			<input type="radio" name="accion" value="clear">Clear L8<br>
			<input type="radio" name="accion" value="closebt">Close BT Connection
			
			<input name="color" class="color"><br>
			
			<input type="submit" value="Submit">
		</form>
	</body>
</html>