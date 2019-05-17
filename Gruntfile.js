// AdminLTE Gruntfile
module.exports = function (grunt) { // jshint ignore:line
    'use strict';

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        watch: {
            less: {
                // 编译保存less 文件
                files: ['src/main/webapp/base/build/**/*.less'],
                tasks: ['less:development', 'less:production', 'replace', 'notify:less']
            },
            js: {
                // 处理js文件任务
                files: ['src/main/webapp/base/build/js/*.js'],
                tasks: ['js', 'notify:js']
            },
            skins: {
                // 处理 skin 任务
                files: ['src/main/webapp/base/build/less/skins/*.less'],
                tasks: ['less:skins', 'less:minifiedSkins', 'notify:less']
            }
        },
        // 任务完成提示信息
        notify: {
            less: {
                options: {
                    title: 'GOD',
                    message: 'LESS finished running'
                }
            },
            js: {
                options: {
                    title: 'GOD',
                    message: 'JS bundler finished running'
                }
            }
        },
        // 'less'-task configuration
        // This task will compile all less files upon saving to create both AdminLTE.css and AdminLTE.min.css
        less: {
            // Development not compressed
            development: {
                options: {
                    //sourceMap: true,
                    //compress: true,
                    //cleancss: false

                },
                files: {
                    // compilation.css  :  source.less
                    'src/main/webapp/base/dist/css/adminlte.css': 'src/main/webapp/base/build/less/AdminLTE.less',
                    // AdminLTE without plugins
                    'src/main/webapp/base/dist/css/alt/adminlte-without-plugins.css': 'src/main/webapp/base/build/less/AdminLTE-without-plugins.less',
                    // Separate plugins
                    'src/main/webapp/base/dist/css/alt/adminlte-select2.css': 'src/main/webapp/base/build/less/select2.less',
                    'src/main/webapp/base/dist/css/alt/adminlte-fullcalendar.css': 'src/main/webapp/base/build/less/fullcalendar.less',
                    'src/main/webapp/base/dist/css/alt/adminlte-bootstrap-social.css': 'src/main/webapp/base/build/less/bootstrap-social.less',
                    //login.less
                    'src/main/webapp/base/dist/css/login.css': 'src/main/webapp/base/build/less/login.less',
                    'src/main/webapp/base/dist/css/lc.css': 'src/main/webapp/base/build/business-less/lc.less'
                }
            },
            // Production compressed version
            production: {
                options: {
                    compress: true
                    //cleancss: true
                },
                files: {
                    // compilation.css  :  source.less
                    'src/main/webapp/base/dist/css/adminlte.min.css': 'src/main/webapp/base/build/less/AdminLTE.less',
                    // AdminLTE without plugins
                    'src/main/webapp/base/dist/css/alt/adminlte-without-plugins.min.css': 'src/main/webapp/base/build/less/AdminLTE-without-plugins.less',
                    // Separate plugins
                    'src/main/webapp/base/dist/css/alt/adminlte-select2.min.css': 'src/main/webapp/base/build/less/select2.less',
                    'src/main/webapp/base/dist/css/alt/adminlte-fullcalendar.min.css': 'src/main/webapp/base/build/less/fullcalendar.less',
                    'src/main/webapp/base/dist/css/alt/adminlte-bootstrap-social.min.css': 'src/main/webapp/base/build/less/bootstrap-social.less',
                    'src/main/webapp/base/dist/css/login.min.css': 'src/main/webapp/base/build/less/login.less',
                    'src/main/webapp/base/dist/css/lc.min.css': 'src/main/webapp/base/build/business-less/lc.less'
                }
            },
            // Non minified skin files
            skins: {
                files: {
                    'src/main/webapp/base/dist/css/skins/skin-blue.css': 'src/main/webapp/base/build/less/skins/skin-blue.less',
                    'src/main/webapp/base/dist/css/skins/skin-black.css': 'src/main/webapp/base/build/less/skins/skin-black.less',
                    'src/main/webapp/base/dist/css/skins/skin-yellow.css': 'src/main/webapp/base/build/less/skins/skin-yellow.less',
                    'src/main/webapp/base/dist/css/skins/skin-green.css': 'src/main/webapp/base/build/less/skins/skin-green.less',
                    'src/main/webapp/base/dist/css/skins/skin-red.css': 'src/main/webapp/base/build/less/skins/skin-red.less',
                    'src/main/webapp/base/dist/css/skins/skin-purple.css': 'src/main/webapp/base/build/less/skins/skin-purple.less',
                    'src/main/webapp/base/dist/css/skins/skin-blue-light.css': 'src/main/webapp/base/build/less/skins/skin-blue-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-black-light.css': 'src/main/webapp/base/build/less/skins/skin-black-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-yellow-light.css': 'src/main/webapp/base/build/less/skins/skin-yellow-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-green-light.css': 'src/main/webapp/base/build/less/skins/skin-green-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-red-light.css': 'src/main/webapp/base/build/less/skins/skin-red-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-purple-light.css': 'src/main/webapp/base/build/less/skins/skin-purple-light.less',
                    'src/main/webapp/base/dist/css/skins/_all-skins.css': 'src/main/webapp/base/build/less/skins/_all-skins.less'
                }
            },
            // Skins minified
            minifiedSkins: {
                options: {
                    compress: true
                },
                files: {
                    'src/main/webapp/base/dist/css/skins/skin-blue.min.css': 'src/main/webapp/base/build/less/skins/skin-blue.less',
                    'src/main/webapp/base/dist/css/skins/skin-black.min.css': 'src/main/webapp/base/build/less/skins/skin-black.less',
                    'src/main/webapp/base/dist/css/skins/skin-yellow.min.css': 'src/main/webapp/base/build/less/skins/skin-yellow.less',
                    'src/main/webapp/base/dist/css/skins/skin-green.min.css': 'src/main/webapp/base/build/less/skins/skin-green.less',
                    'src/main/webapp/base/dist/css/skins/skin-red.min.css': 'src/main/webapp/base/build/less/skins/skin-red.less',
                    'src/main/webapp/base/dist/css/skins/skin-purple.min.css': 'src/main/webapp/base/build/less/skins/skin-purple.less',
                    'src/main/webapp/base/dist/css/skins/skin-blue-light.min.css': 'src/main/webapp/base/build/less/skins/skin-blue-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-black-light.min.css': 'src/main/webapp/base/build/less/skins/skin-black-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-yellow-light.min.css': 'src/main/webapp/base/build/less/skins/skin-yellow-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-green-light.min.css': 'src/main/webapp/base/build/less/skins/skin-green-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-red-light.min.css': 'src/main/webapp/base/build/less/skins/skin-red-light.less',
                    'src/main/webapp/base/dist/css/skins/skin-purple-light.min.css': 'src/main/webapp/base/build/less/skins/skin-purple-light.less',
                    'src/main/webapp/base/dist/css/skins/_all-skins.min.css': 'src/main/webapp/base/build/less/skins/_all-skins.less'
                }
            }

        },

        // Uglify task info. Compress the js files.
        uglify: {
            options: {
                mangle: true,
                preserveComments: false
            },
            production: {
                files: {
                    'src/main/webapp/base/dist/js/adminlte.min.js': ['src/main/webapp/base/dist/js/adminlte.js'],
                    'src/main/webapp/base/dist/js/tools.min.js': ['src/main/webapp/base/dist/js/tools.js'],
                    // login.js
                    'src/main/webapp/base/dist/js/login/login.min.js': ['src/main/webapp/base/dist/js/login/login.js']
                }
            }
        },

        // Concatenate JS Files

        concat: {
            options: {
                separator: '\n\n',
                banner: '/*! GOD SERVER \n*/\n\n'
                + 'if (typeof jQuery === \'undefined\') {\n'
                + 'throw new Error(\'需要引入jquery\');\n'
                + '}\n\n'
            },
            dist: {
                src: [
                    'src/main/webapp/base/build/js/BoxRefresh.js',
                    'src/main/webapp/base/build/js/BoxWidget.js',
                    'src/main/webapp/base/build/js/ControlSidebar.js',
                    'src/main/webapp/base/build/js/DirectChat.js',
                    'src/main/webapp/base/build/js/Layout.js',
                    'src/main/webapp/base/build/js/PushMenu.js',
                    'src/main/webapp/base/build/js/TodoList.js',
                    'src/main/webapp/base/build/js/Tree.js'
                ],
                dest: 'src/main/webapp/base/dist/js/adminlte.js'
            },
            distcommon: {
                src: [
                    'src/main/webapp/base/build/js/common/tools.js',
                    'src/main/webapp/base/build/js/common/common.js',
                    'src/main/webapp/base/build/js/common/menus.js',


                ],
                dest: 'src/main/webapp/base/dist/js/tools.js'
            }


        },

        // Replace image paths in AdminLTE without plugins
        replace: {
            withoutPlugins: {
                src: ['src/main/webapp/base/dist/css/alt/adminlte-without-plugins.css'],
                dest: 'src/main/webapp/base/dist/css/alt/adminlte-without-plugins.css',
                replacements: [
                    {
                        from: '../img',
                        to: '../../img'
                    }
                ]
            },
            withoutPluginsMin: {
                src: ['src/main/webapp/base/dist/css/alt/adminlte-without-plugins.min.css'],
                dest: 'src/main/webapp/base/dist/css/alt/adminlte-without-plugins.min.css',
                replacements: [
                    {
                        from: '../img',
                        to: '../../img'
                    }
                ]
            }
        },

        // Build the documentation files
        // includes: {
        // 	build: {
        // 		src: ['*.html'], // Source files
        // 		dest: 'documentation/', // Destination directory
        // 		flatten: true,
        // 		cwd: 'documentation/build',
        // 		options: {
        // 			silent: true,
        // 			includePath: 'documentation/build/include'
        // 		}
        // 	}
        // },

        // Optimize images
        image: {
            dynamic: {
                files: [
                    {
                        expand: true,
                        cwd: 'src/main/webapp/base/build/img/',
                        src: ['**/*.{png,jpg,gif,svg,jpeg}'],
                        dest: 'src/main/webapp/base/dist/img/'
                    }
                ]
            }
        },

        // Validate JS code
        jshint: {
            options: {
                jshintrc: 'src/main/webapp/base/build/js/.jshintrc'
            },
            grunt: {
                options: {
                    jshintrc: 'src/main/webapp/base/build/grunt/.jshintrc'
                },
                src: 'Gruntfile.js'
            },
            core: {
                src: 'src/main/webapp/base/build/js/*.js'
            }
            // demo: {
            // 	src: 'src/main/webapp/base/dist/js/demo.js'
            // },
            // pages: {
            // 	src: 'src/main/webapp/base/dist/js/pages/*.js'
            // }
        },

        jscs: {
            options: {
                config: 'src/main/webapp/base/build/js/.jscsrc'
            },
            core: {
                src: '<%= jshint.core.src %>'
            },
            pages: {
                src: '<%= jshint.pages.src %>'
            }
        },

        // Validate CSS files
        csslint: {
            options: {
                csslintrc: 'src/main/webapp/base/build/less/.csslintrc'
            },
            dist: [
                'src/main/webapp/base/dist/css/adminlte.css'
            ]
        }

        // Validate Bootstrap HTML
        // bootlint: {
        // 	options: {
        // 		relaxerror: ['W005']
        // 	},
        // 	files: ['pages/**/*.html', '*.html']
        // },

        // Delete images in build directory
        // After compressing the images in the build/img dir, there is no need
        // for them
        // clean: {
        // 	build: ['build/img/*']
        // }
    });

    // Load all grunt tasks

    // LESS Compiler
    grunt.loadNpmTasks('grunt-contrib-less');
    // Watch File Changes
    grunt.loadNpmTasks('grunt-contrib-watch');
    // Compress JS Files
    grunt.loadNpmTasks('grunt-contrib-uglify');
    // Include Files Within HTML
    grunt.loadNpmTasks('grunt-includes');
    // Optimize images
    grunt.loadNpmTasks('grunt-image');
    // Validate JS code
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-jscs');
    // Delete not needed files
    grunt.loadNpmTasks('grunt-contrib-clean');
    // Lint CSS
    grunt.loadNpmTasks('grunt-contrib-csslint');
    // Lint Bootstrap
    grunt.loadNpmTasks('grunt-bootlint');
    // Concatenate JS files
    grunt.loadNpmTasks('grunt-contrib-concat');
    // Notify
    grunt.loadNpmTasks('grunt-notify');
    // Replace
    grunt.loadNpmTasks('grunt-text-replace');

    // Linting task
    grunt.registerTask('lint', ['jshint', 'csslint', 'bootlint']);
    // JS task
    grunt.registerTask('js', ['concat', 'uglify']);
    // CSS Task
    grunt.registerTask('css', ['less:development', 'less:production', 'replace']);

    // The default task (running 'grunt' in console) is 'watch'
    grunt.registerTask('default', ['watch']);
};
