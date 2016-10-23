requirejs.config({
    //By default load any module IDs from js/lib
    baseUrl: '',
    //except, if the module ID starts with "app",
    //load it from the js/app directory. paths
    //config is relative to the baseUrl, and
    //never includes a ".js" extension since
    //the paths config could be for a directory.
    paths: {
		jquery:'lib/jquery-3.1.0.min',
        bootstrap: '/lib/bootstrap-3.3.7-dist/js/bootstrap.min',
		bootstrapDatepicker: '/lib/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min',
		bootstrapFileinput: '/lib/bootstrap-fileinput/js/fileinput.min'
    }, 
	shim: { "bootstrap": { deps: ["jquery"], exports: ["bootstrapDatepicker","bootstrapFileinput"] } }
});

// Start the main app logic.
requirejs(['jquery','bootstrap','bootstrapDatepicker','bootstrapFileinput'],
function($) {
	console.log("hello requirejs.");
	//$('#datetimepicker').datetimepicker('show');
});