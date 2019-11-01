// $('#post_related_slide').owlCarousel({
//     loop:false,
//     margin:20,
//     nav:true,
//     dots: false,
//     responsive:{
//         0:{
//             items:1
//         },
//         600:{
//             items:2
//         },
//         1000:{
//             items:3
//         }
//     },
//     navText: ["<i class='fa fa-chevron-left' aria-hidden='true'></i>","<i class='fa fa-chevron-right' aria-hidden='true'></i>"]
// })


$(window).scroll(function() {    
    var scroll = $(window).scrollTop();
    if (scroll >= 160) { 
        $(".header .header-menu").addClass("navbar-fixed-top"); 
    } else { 
        $(".header .header-menu").removeClass("navbar-fixed-top"); 
    }
    if (scroll >= 200) { 
        $('.back-to-top').fadeIn(500);
    } else { 
        $('.back-to-top').fadeOut(500); 
    }
});
if ($('.navigation_post').height()>0) {
    var nav_post = $('.navigation_post').offset().top;
    $(window).scroll(function() {    
        var scroll = $(window).scrollTop();
        if (scroll >= nav_post) { 
            $(".navigation_post").addClass("fix_navbar_post"); 
        } else { 
            $(".navigation_post").removeClass("fix_navbar_post"); 
        }
    });
}

jQuery('.back-to-top').click(function(event) {
    event.preventDefault();
    jQuery('html, body').animate({
        scrollTop: 0
    }, 500);
    return false;
});

$(function () { 
$('.click-to').click(function () {    
    var target = $(this.hash);  
    target = target.length ? target : $('[name=' + this.hash.substr(1) + ']');  
    if (target.length) {    
    $('html,body').animate({    
    scrollTop: target.offset().top - 60 
    }, 1000);   
    return false;   
    }   
    }); 
});

$.fn.setHeightItem = function () { var maxHeight = 0; this .each(function () { maxHeight = Math.max(maxHeight, $(this).height()); }) .height(maxHeight); };
$('.list-product .item .box_pr .entry-cattitle,.list-product .item .box_pr .entry-title').setHeightItem();

var ww = $(window).width();
if (ww < 768) {
    $('#mobile_menu .navbar-nav .menu-item-has-children').append( "<span class='child-menu-toggle'><i class='fa fa-angle-down'></i></span>" );
}
$('.child-menu-toggle').click(function () { 
    $(this).parent().children('.sub-menu').toggleClass('active');
    $(this).toggleClass('active');
});

$(function () {
  $('[data-toggle="offcanvas"]').on('click', function () {
    $('.offcanvas-collapse').toggleClass('open')
  })
})

$('.box-search select').select2({
    matcher: function (params, data) {
        if ($.trim(params.term) === '') {
            return data;
        }
        var _keyword = UnicodeToKoDau(data.text.toLowerCase());
        var _text = UnicodeToKoDau(params.term.toLowerCase());
        if (_keyword.indexOf(_text) == 0) {
            var modifiedData = $.extend({}, data, true);
            modifiedData.text;
            return modifiedData;
        }
        return null;
    }
});

    
$(document).ready(function() {
    if ($('#post-photos-gallery').height()) {
        $('#post-photos-gallery').lightSlider({
            gallery:true,
            item:1,
            loop:true,
            auto: true,
            slideMargin:0,
            speed: 1500,
            pause: 3000,
            enableDrag: false,
            currentPagerPosition:'left',
            onSliderLoad: function(el) {
                el.lightGallery({
                    selector: '#post-photos-gallery .lslide',
                    autoplay: true,
                    thumbnail: true,
                    download: false
                });
            }   
        });  
    }

    $(function () { 
        $('.click-to-post').click(function () {    
            var target = $(this.hash);  
            target = target.length ? target : $('[name=' + this.hash.substr(1) + ']');  
            if (target.length) {    
                $('html,body').animate({    
                    scrollTop: target.offset().top  - 100
                }, 1000);   
                return false;   
            }   
        }); 
    });
});


$(document).ready(function() {
    if ($('#payment_info_sidebar').height()>0) {
        $('#payment_info_sidebar').width($('#payment_info_sidebar').width());
        $('#payment_info_sidebar').height($('#payment_info_sidebar').height());
        var top = $('#payment_info_sidebar').offset().top;
        var f_top = $('.page-footer').offset().top - 50;
        $(window).scroll(function() {    
            var scroll = $(window).scrollTop();
            if (scroll >= top) { 
                $('#payment_info_sidebar').addClass('siderbar-fixed');
                if (scroll >= f_top) {
                    $('#payment_info_sidebar').addClass('siderbar-fixed-bottom');
                }else{
                    $('#payment_info_sidebar').removeClass('siderbar-fixed-bottom');
                }
            } else { 
                $('#payment_info_sidebar').removeClass('siderbar-fixed');
            }
        });
    }


    // preview img
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                $('#img_preview').attr('src', e.target.result);
            }
            
            reader.readAsDataURL(input.files[0]);
        }
    }
    
    $("#image-inputs").change(function(){
        $('#img_preview').show();
        readURL(this);
    });
});