var gulp = require('gulp');

//var concat = require('gulp-concat');                            //- 多个文件合并为一个；
//var minifyCss = require('gulp-minify-css');                     //- 压缩CSS为一行；
//var clean = require('gulp-clean-css');							//- 清空css
var sequence = require('gulp-sequence');
//var jsmin = require('gulp-uglify');

gulp.task('copy-bootstrap', function () {
	gulp.src('node_modules/bootstrap/dist/**/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/bootstrap'));
});

gulp.task('copy-font', function () {
	gulp.src('node_modules/font-awesome/**/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/font-awesome'));
});

gulp.task('copy-ionicons', function () {
	gulp.src('node_modules/ionicons/dist/**/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/ionicons'));
});

gulp.task('copy-jquery', function () {
	gulp.src('node_modules/jquery/dist/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/jquery/1.12.4'));
});

gulp.task('copy-btables', function () {
	gulp.src([
		'node_modules/bootstrap-table/dist/*.js',
		'./node_modules/bootstrap-table/dist/*.css',
		'./node_modules/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js'
	])
		.pipe(gulp.dest('src/main/webapp/base/plugins/bootstrap-table'));
});

gulp.task('copy-select2', function () {
	gulp.src('./node_modules/select2/dist/**/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/select2/4.0.3'));
});

gulp.task('copy-echarts', function () {
	gulp.src('./node_modules/echarts/dist/**/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/echarts/3.7.2'));
});

gulp.task('copy-bootstrap-datepicker', function () {
	gulp.src('./node_modules/bootstrap-datepicker/dist/**/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/bootstrap-datepicker'));
});

gulp.task('copy-jquery-ui', function () {
	gulp.src('node_modules/jquery-ui/themes/**/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/jquery-ui/themes'));
	gulp.src('node_modules/jquery-ui/ui/**/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/jquery-ui/ui'));
});

gulp.task('copy-jquery-slimscroll', function () {
	gulp.src('node_modules/jquery-slimscroll/*.js')
		.pipe(gulp.dest('src/main/webapp/base/plugins/jquery-slimscroll'));
});

gulp.task('copy-ueditor', function () {
	gulp.src('node_modules/ueditor/**/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/ueditor'));
});

gulp.task('copy-html5shiv', function () {
	gulp.src('node_modules/html5shiv/dist/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/html5shiv'));
});
gulp.task('copy-respond', function () {
	gulp.src('node_modules/respond.js/dest/*.*')
		.pipe(gulp.dest('src/main/webapp/base/plugins/respond'));
});

gulp.task('copy-base64', function () {
	gulp.src('node_modules/js-base64/*.js')
		.pipe(gulp.dest('src/main/webapp/base/plugins/base64'));
});

//服务器发布任务
gulp.task('copy', sequence(
	'copy-bootstrap',
	'copy-font',
	'copy-ionicons',
	'copy-jquery',
	'copy-btables',
	'copy-select2',
	'copy-echarts',
	'copy-bootstrap-datepicker',
	'copy-jquery-ui',
	'copy-jquery-slimscroll',
	'copy-ueditor',
	'copy-html5shiv',
	'copy-respond',
	'copy-base64'
));
