module.exports = function (grunt) {
    grunt.initConfig({
        jshint: {
            all: ['Gruntfile.js', 'src/main/webapp/src/**/*.js']
        },
        karma: {
            unit: {
                options: {
                    configFile: 'karma.conf.js',
                    autoWatch: true
                }
            }
        },
        html2js: {
            options: {
                base: 'src/main/webapp/src/',
                module: 'build.templates',
                htmlmin: {
                    collapseBooleanAttributes: true,
                    collapseWhitespace: true,
                    removeComments: true
                }
            },
            dist: {
                src: ['src/main/webapp/src/**/*.html'],
                dest: 'tmp/templates.js'
            }
        },
        concat: {
            options: {
                separator: '\n'
            },
            app: {
                src: ['src/main/webapp/src/**/*.js', 'tmp/*.js'],
                dest: 'src/main/webapp/bin/js/app.js'
            },
            vendor: {
                src: ['bower_components/jquery/dist/jquery.js', 'bower_components/angular/angular.js',
                    'bower_components/angular-cookies/angular-cookies.js',
                    'bower_components/ui-router/release/angular-ui-router.js',
                    'bower_components/bootstrap/dist/js/bootstrap.js'],
                dest: 'src/main/webapp/bin/js/vendor.js'
            }
        },
        clean: {
            temp: {
                src: ['tmp']
            }
        },
        uglify: {
            dist: {
                files: [
                    {
                        'src/main/webapp/bin/js/app.min.js': ['src/main/webapp/bin/js/app.js'],
                        'src/main/webapp/bin/js/vendor.min.js': ['src/main/webapp/bin/js/vendor.js']
                    }
                ],
                options: {
                    mangle: false
                }
            }
        },
        concat_css: {
            options: {},
            app: {
                src: ['src/main/webapp/src/**/*.css'],
                dest: 'src/main/webapp/bin/css/app.css'
            },
            vendor: {
                src: ['bower_components/normalize.css/normalize.css',
                    'bower_components/bootstrap/dist/css/bootstrap.css'],
                dest: 'src/main/webapp/bin/css/vendor.css'
            }
        },
        cssmin: {
            target: {
                files: [
                    {
                        expand: true,
                        cwd: 'src/main/webapp/bin/css',
                        src: ['**/*.css'],
                        dest: 'src/main/webapp/bin/css',
                        ext: '.min.css'
                    }]
            }
        },
        sass: {
            dist: {
                options: {
                    style: 'expanded'
                },
                files: {
                    'src/main/webapp/src/sass.css': 'src/main/webapp/src/styles/main.scss'
                }
            }
        },
        watch: {
            scripts: {
                files: 'src/main/webapp/src/**/*',
                tasks: ["jshint", 'html2js:dist', 'concat:app', 'concat:vendor', 'sass:dist', 'concat_css:app', 'concat_css:vendor', 'clean:temp']
            }
        },
        copy: {
            main: {
                files: [
                    {
                        expand: true, flatten: true,
                        src: ['bower_components/bootstrap/fonts/*'],
                        dest: 'src/main/webapp/bin/fonts/',
                        filter: 'isFile'
                    }
                ]
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-compress');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-concat-css');
    grunt.loadNpmTasks('grunt-html2js');
    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-sass');

    grunt.registerTask("install-run", ["jshint", 'html2js:dist', 'concat:app', 'concat:vendor', 'sass:dist', 'concat_css:app', 'concat_css:vendor', 'copy:main', 'clean:temp']);

    grunt.registerTask("dev-run", ["jshint", 'html2js:dist', 'concat:app', 'sass:dist', 'concat_css:app', 'clean:temp', 'watch']);

    grunt.registerTask("prod-run", ["jshint", 'html2js:dist', 'concat:app', 'concat:vendor', 'uglify', 'sass:dist', 'concat_css:app', 'concat_css:vendor', 'cssmin', 'clean:temp']);

};