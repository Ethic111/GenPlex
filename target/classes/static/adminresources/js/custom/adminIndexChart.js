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
	var barChartLabels = document.getElementById('barChartLabels').value;
	var barChartData = document.getElementById('barChartData').value;
	
	// Remove the square brackets at the beginning and end
	let stringWithoutBrackets = barChartLabels.slice(1, -1);

	// Split the string into an array using ', ' (comma and space)
	let newbarChartLabels = stringWithoutBrackets.split(',').map(item => item.trim());

// console.log(barChartLabels);
	
// ------------------------------------------------------------------------------------------
	
	// Remove the square brackets at the beginning and end
	  let stringWithoutBrackets2 = barChartData.slice(1, -1);

	  // Split the string into an array using ',' and trim whitespace from
		// each item
	  let stringArray = stringWithoutBrackets2.split(',').map(item => item.trim());

	  // Convert each string in the array to an integer
	  let newbarChartData = stringArray.map(item => parseInt(item, 10));

// console.log(newbarChartData);
	
// --------------------------------------------------------------------------------------
	
// console.log(newbarChartLabels);
// console.log(barChartData);
// console.log(pieChartLabels);
// console.log(pieChartData);

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
				labels : newbarChartLabels ,
				datasets : [ {
					label : "Reports",
					backgroundColor : [ colors.primary, colors.info,
										colors.warning, colors.success, colors.danger ],
					borderColor : colors.cardBg,
					data : newbarChartData
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

	// Line Chart
	if ($('#chartjsLine').length) {
		new Chart($('#chartjsLine'), {
			type : 'line',
			data : {
				labels : [ 1500, 1600, 1700, 1750, 1800, 1850, 1900, 1950,
						1999, 2050 ],
				datasets : [
						{
							data : [ 86, 114, 106, 106, 107, 111, 133, 221,
									783, 2478 ],
							label : "Doctors",
							borderColor : colors.info,
							backgroundColor : "transparent",
							fill : true,
							pointBackgroundColor : colors.cardBg,
							pointBorderWidth : 2,
							pointHoverBorderWidth : 3,
							tension : .3
						},
						{
							data : [ 282, 350, 411, 502, 635, 809, 947, 1402,
									3700, 5267 ],
							label : "Patients",
							borderColor : colors.danger,
							backgroundColor : "transparent",
							fill : true,
							pointBackgroundColor : colors.cardBg,
							pointBorderWidth : 2,
							pointHoverBorderWidth : 3,
							tension : .3
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
});
