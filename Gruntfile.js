/**
 * Created by Jerico on 26/08/2015.
 */
/**
 * Created by jerico on 4/17/2015.
 */
module.exports = function (grunt) {
    grunt.initConfig({
            pkg: grunt.file.readJSON("package.json"),
            bower: {
                install: {
                    options: {
                        install: true,
                        copy: false,
                        targetDir: './libs',
                        cleanTargetDir: true
                    }
                }
            },
            jshint: {
                all: ['Gruntfile.js', 'src/main/webapp/app/src/js/**/*.js']
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
                    module: 'spTemplates',
                    htmlmin: {
                        collapseBooleanAttributes: true,
                        collapseWhitespace: true,
                        removeComments: true,
                    }
                },
                dist: {
                    src: ['src/main/webapp/app/src/templates/**/*.html'],
                    dest: 'tmp/templates.js'
                }
            },
            concat: {
                options: {
                    separator: ';'
                },
                dist: {
                    src: ['src/main/webapp/app/src/js/**/*.js', 'tmp/*.js'],
                    dest: 'src/main/webapp/app/dist/js/spring-angular.js'
                }
            },
            uglify: {
                dist: {
                    files: [
                        {
                            'src/main/webapp/app/dist/js/spring-angular.min.js': ['src/main/webapp/app/dist/js/spring-angular.js'],
                        }
                    ],
                    options: {
                        mangle: false
                    }
                }
            },
            cssmin: {
                target: {
                    files: [
                        {
                            expand: true,
                            cwd: 'src/main/webapp/app/dist/css',
                            src: ['**/*.css'],
                            dest: 'src/main/webapp/app/dist/css',
                            ext: '.min.css'
                        }]
                }
            },
            concat_css: {
                options: {},
                all: {
                    src: ["src/main/webapp/app/src/css/**/*.css", "src/main/webapp/app/css/**/*.min.css"],
                    dest: "src/main/webapp/app/dist/css/spring-angular.css"
                }
            },
            clean: {
                temp: {
                    src: ['tmp', 'src/main/webapp/app/dist/css/*.css']
                }
            },
            watch: {
                scss: {
                    files: ["src/main/webapp/app/src/sass/**/*.scss"],
                    tasks: ['sass', 'concat_css', 'cssmin'],
                    options: {
                        atBegin: true
                    }
                },
                dev: {
                    files: ['Gruntfile.js', 'src/main/webapp/app/src/**/*', 'src/main/webapp/app/test/**/*'],
                    tasks: ['html2js:dist', 'concat:dist', 'uglify:dist', 'clean:temp', 'compress:dist', 'sass', 'concat_css', 'cssmin', 'karma'],
                    options: {
                        atBegin: true
                    }
                }
            },
            compress: {
                dist: {
                    files: [{
                        src: ['src/main/webapp/app/dist/**'],
                        dest: 'src/main/webapp/app/dist/'
                    }]
                }
            },
            strip: {
                main: {
                    src: 'src/main/webapp/app/dist/js/spring-angular.js',
                    dest: 'src/main/webapp/app/dist/js/spring-angular.js',
                    nodes: ['console', 'debug', 'info', 'log']
                }
            },
            sass: {
                dist: {
                    options: {
                        style: 'expanded'
                    },
                    files: {
                        'src/main/webapp/app/src/css/sass.css': 'src/main/webapp/app/src/sass/**/*.scss'
                    }
                }
            }

        }
    );

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-compress');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-concat-css');
    grunt.loadNpmTasks('grunt-html2js');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-strip');
    grunt.loadNpmTasks('grunt-contrib-sass');

    grunt.registerTask('dev-package', ['bower', 'html2js:dist', 'concat:dist', 'uglify:dist',
        'clean:temp', 'compress:dist', 'sass', 'concat_css', 'cssmin', 'jshint', 'watch:dev']);
    grunt.registerTask('test-package', ['bower', 'html2js:dist', 'concat:dist', 'uglify:dist',
        'clean:temp', 'compress:dist', 'sass', 'concat_css', 'cssmin', 'karma:unit']);
    grunt.registerTask('package', ['bower', 'html2js:dist', 'concat:dist', 'strip', 'uglify:dist',

        'clean:temp', 'compress:dist', 'sass', 'concat_css', 'cssmin']);
};