// npm package: typeahead.js
// github link: https://github.com/twitter/typeahead.js

$(document).ready(function() {
	'use strict'

	var substringMatcher = function(strs) {
		return function findMatches(q, cb) {
			var matches, substringRegex;

			// an array that will be populated with substring matches
			matches = [];

			// regex used to determine if a string contains the substring `q`
			var substrRegex = new RegExp(q, 'i');

			// iterate through the pool of strings and for any string that
			// contains the substring `q`, add it to the `matches` array
			for (var i = 0; i < strs.length; i++) {
				if (substrRegex.test(strs[i])) {
					matches.push(strs[i]);
				}
			}

			cb(matches);
		};
	};

/*	console.log('I am here......................')*/
	$.ajax({
		url : "http://localhost:7072/doctor/api/patients",
		method : 'GET',
		success : function(response) {
			/*console.log('res>>>>>' , response);*/
			$('#the-basics #patientTypehead').typeahead({
				hint : true,
				highlight : true,
				minLength : 1
			}, {
				name : 'emails',
				source : substringMatcher(response)
			});
		}
	});
});