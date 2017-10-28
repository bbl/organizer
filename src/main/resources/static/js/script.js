$(document).ready(function() {
	/**
	* Side menu button function-handler
	*/
	$('.side-menu-button').on("click", function()  {
	    this.classList.toggle("change");
	    if ($(this).attr('id') == 'side-menu-open') {
	    	$('#side-menu-open').attr('id', 'side-menu-close');
	    	$(".side-menu-button div").css("background-color", "#818181");
	    	openNav();
	    }
	    else {
	    	$('#side-menu-close').attr('id', 'side-menu-open');
	    	$(".side-menu-button div").css("background-color", "white");
	    	closeNav();
	    }
	});

	/**
	* Opens the side menu
	*/
	function openNav() {
	    $("#side-menu").css("width", "400px");
	};

	/**
	* Closes the side menu
	*/
	function closeNav() {
	    $("#side-menu").css("width", "0");
	    $("body").css("backgroundColor", "#252830");
	};

	/**
	* Login form validation
	*/
	$(function () {
	  $('#loginForm').parsley().on('field:validated', function() {
	    var ok = $('.parsley-error').length === 0;
	    $('.bs-callout-info').toggleClass('hidden', !ok);
	    $('.bs-callout-warning').toggleClass('hidden', ok);
	  });
	});

	/**
	* Create calendar form validation
	*/
	$(function () {
	  $('#createCalendarForm').parsley().on('field:validated', function() {
	    var ok = $('.parsley-error').length === 0;
	    $('.bs-callout-info').toggleClass('hidden', !ok);
	    $('.bs-callout-warning').toggleClass('hidden', ok);
	  });
	});

	/**
	* Edit calendar form validation
	*/
	$(function () {
	  $('#editCalendarForm').parsley().on('field:validated', function() {
	    var ok = $('.parsley-error').length === 0;
	    $('.bs-callout-info').toggleClass('hidden', !ok);
	    $('.bs-callout-warning').toggleClass('hidden', ok);
	  });
	});

		/**
	* Edit calendar form validation
	*/
	$(function () {
	  $('#deleteCalendarForm').parsley().on('field:validated', function() {
	    var ok = $('.parsley-error').length === 0;
	    $('.bs-callout-info').toggleClass('hidden', !ok);
	    $('.bs-callout-warning').toggleClass('hidden', ok);
	  });
	});

	$(document).ready(function(){
	  $('input').iCheck({
  		checkboxClass: 'icheckbox_polaris',
    	radioClass: 'iradio_polaris',
    	increaseArea: '-10%' // optional
	 	});
	});
});