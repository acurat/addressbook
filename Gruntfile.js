module.exports = function(grunt) {

	// Project configuration.
	grunt.initConfig({
		pkg : grunt.file.readJSON('package.json'),

		clean : {
			js : [ "src/main/webapp/resources/js/main*.js" ]
		},
		concat : {
			dist : {
				src : 'src/main/webapp/resources/js/*.js',
				dest : 'src/main/webapp/resources/js/main.js'
			}
		},
		uglify : {
			build : {
				src : [ 'src/main/webapp/resources/js/main.js' ],
				dest : 'src/main/webapp/resources/js/main.min.js'
			},
			options : {
				mangle : true
			}
		},

		jshint : {
			src : [ 'Gruntfile.js', 'src/main/webapp/resources/js/*.js' ]

		},

		cssmin : {
			target : {
				files : [ {
					expand : true,
					src : 'src/main/webapp/resources/css/*.css',
					ext : '.min.css'
				} ]
			}
		}
	});

	// Load the plugin that provides the "uglify" task.
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-cssmin');

	// Default task(s).
	grunt.registerTask('default', [ 'cssmin', 'clean', 'jshint', 'concat',
			'uglify', 'cssmin' ]);

};