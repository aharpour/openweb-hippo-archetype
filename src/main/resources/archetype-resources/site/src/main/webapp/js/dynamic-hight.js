
/*sets the height of embed element to 61% of its width*/
jQuery(document).ready(function(){
	jQuery('.youtube embed').each(function(){
		jQuery(this).height((jQuery(this).width() * 0.5625) + 30);
	});
});