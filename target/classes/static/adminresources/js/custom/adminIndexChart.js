$(function() {
	'use strict';

	var colors = {
		primary : "#6571ff",
		secondary : "#7987a1",
		success : "#05a34a",
		info : "#66d1d1",
		warning : "#fbbc06",
		danger : "#ff3366",
		light : "#e9ecef",
		dark : "#060c17",
		muted : "#7987a1",
		gridBorder : "rgba(77, 138, 240, .15)",
		bodyColor : "#000",
		cardBg : "#fff"
	};

	var fontFamily = "'Roboto', Helvetica, sans-serif";


	// Fetch chart data from hidden inputs
	var pieChartLabels = document.getElementById('pieChartLabels').value;
	var pieChartData = document.getElementById('pieChartData').value;
	
// For Pie chat Lables:
	
	// Remove the square brackets at the beginning and end
	let stringWithoutBrackets = pieChartLabels.slice(1, -1);

	// Split the string into an array using ', ' (comma and space)
	let newpieChartLabels = stringWithoutBrackets.split(',').map(item => item.trim());

 console.log(newpieChartLabels);
	
// ------------------------------------------------------------------------------------------
	
 // For Pie Chart Data:
 
	// Remove the square brackets at the beginning and end
	  let stringWithoutBrackets2 = pieChartData.slice(1, -1);

	  // Split the string into an array using ',' and trim whitespace from
		// each item
	  let stringArray = stringWithoutBrackets2.split(',').map(item => item.trim());

	  // Convert each string in the array to an integer
	  let newpieChartData = stringArray.map(item => parseInt(item, 10));

 console.log(newpieChartData);
	
// --------------------------------------------------------------------------------------
	
// console.log(newbarChartLabels);
// console.log(barChartData);
// console.log(pieChartLabels);
// console.log(pieChartData);
 
	// Fetch chart data from hidden inputs
	var barChartLabels = document.getElementById('barChartLabels').value;
	var barChartData = document.getElementById('barChartData').value;
	
// For Bar chat Lables:
	
	// Remove the square brackets at the beginning and end
	let stringWithoutBrackets123 = barChartLabels.slice(1, -1);

	// Split the string into an array using ', ' (comma and space)
	let newbarChartLabels = stringWithoutBrackets123.split(',').map(item => item.trim());

console.log(newbarChartLabels);
	
// ------------------------------------------------------------------------------------------
	
// For bar Chart Data:

	// Remove the square brackets at the beginning and end
	  let stringWithoutBrackets245 = barChartData.slice(1, -1);

	  // Split the string into an array using ',' and trim whitespace from
		// each item
	  let stringArray24 = stringWithoutBrackets245.split(',').map(item => item.trim());

	  // Convert each string in the array to an integer
	  let newbarChartData = stringArray24.map(item => parseInt(item, 10));

console.log(newbarChartData);

	// Bar chart
	if ($('#chartjsBar').length) {
		new Chart($("#chartjsBar"), {
			type : 'bar',
			data : {
				labels : newbarChartLabels ,
				datasets : [ {
					label : "Reports",
					backgroundColor : [ colors.primary, colors.info,
							colors.warning, colors.success, colors.danger ],
					data : newbarChartData,
				} ]
			},
			options : {
				plugins : {
					legend : {
						display : false
					},
				},
				scales : {
					x : {
						display : true,
						grid : {
							display : true,
							color : colors.gridBorder,
							borderColor : colors.gridBorder,
						},
						ticks : {
							color : colors.bodyColor,
							font : {
								size : 12
							}
						}
					},
					y : {
						grid : {
							display : true,
							color : colors.gridBorder,
							borderColor : colors.gridBorder,
						},
						ticks : {
							color : colors.bodyColor,
							font : {
								size : 12
							}
						}
					}
				}
			}
		});
	}

	// Pie Chart
	if ($('#chartjsPie').length) {
		new Chart($('#chartjsPie'), {
			type : 'pie',
			data : {
				labels : newpieChartLabels ,
				datasets : [ {
					label : "Reports",
					backgroundColor : [ colors.primary, colors.info,
										colors.warning, colors.success, colors.danger ],
					borderColor : colors.cardBg,
					data : newpieChartData
				} ]
			},
			options : {
				plugins : {
					legend : {
						display : true,
						labels : {
							color : colors.bodyColor,
							font : {
								size : '13px',
								family : fontFamily
							}
						}
					},
				},
				aspectRatio : 2,
			}
		});
	}

});
