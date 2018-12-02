// Register Validation
function numericsonly(ob) 
{
    var invalidChars = /[^0-9]/gi
    if(invalidChars.test(ob.value)) 
    {
        ob.value = ob.value.replace(invalidChars,"");
    }
} //function to allow only numbers ends here

//On page load hide all tool tips
$('.required').next('.tooltip_outer_feedback').hide();
$('.required_feedback').next('.tooltip_outer').hide();
//---

//Onpage load
$(document).ready(function()
{
    //On key up in texbox's hide error messages for required fields
    $('.required').keyup(function()
    {
        $(this).next('.tooltip_outer').hide();
    });
    //On selected item change in dropdownlist hide error messages for required fields
    $('.dpreq').change(function()
    {
        $(this).next('.tooltip_outer').hide();
    });
    //On key up for mobile number avoid non-numeric characters
    $('.mobile').keyup(function()
    {
        $(this).next('.tooltip_outer').hide();
        numericsonly(this); // definition of this function is above
    });
    
    // On button click or submitting values
$('.btn_validate').click(function(e) 
{	
    var empty_count=0; // variable to store error occured status
    $('.required').next('.tooltip_outer').hide();
    $('.required').each(function()
    {
    	
        if($(this).val().length === 0)
        {
        	
        		$(this).after("<div class='tooltip_outer' style='color:red;'><div class='arrow-left'> </div> <div class='tool_tip'>can't be blank </div></div>").show();
                empty_count=1; 
        	  
        }
        else
        {
            $(this).next('.tooltip_outer').hide();
            if($(this).hasClass('mobile'))
            {
                if($(this).val().length != 10)
                {
                    $(this).after('<div class="tooltip_outer"><div class="arrow-left"></div><div class="tool_tip">Mobile should be 10 digits</div></div>').show("slow");
                    empty_count=1; 
                }
                else
                {
                    $(this).next('.tooltip_outer').hide();
                }
            }
            if($(this).hasClass('email'))
            {
                $(this).next('.tooltip_outer').hide();
                var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
                if(filter.test($(this).val()) === false)
                {
                    $(this).after('<div class="tooltip_outer"  style="color:red;"><div class="arrow-left"></div><div class="tool_tip" >Invalid Email </div></div>').show("slow");
                    empty_count=1;
                }
                else
                {
                    $(this).next('.tooltip_outer').hide();
                }
            }
            if($(this).hasClass('password'))
            {
                $(this).next('.tooltip_outer').hide();
                if($(this).val().length < 3)
                {
                    $(this).after("<div class='tooltip_outer' style='color:red;'><div class='arrow-left'> </div> <div class='tool_tip'>length must be 3 and above </div></div>").show();
                    empty_count=1; 
                }
                else
                {
                    $('.confirm_password').next('.tooltip_outer').hide();
                    if($(this).val()===$('.confirm_password').val())
                    {
                        //$('.confirm_password').next('.tooltip_outer').hide();
                        $('.confirm_password').after("<div class='tooltip_outer' style='color:red;'><div class='arrow-left'> </div> <div class='tool_tip'>successfull match </div></div>").show();
             
                    }
                    else
                    {
                       $('.confirm_password').after("<div class='tooltip_outer' style='color:red;'><div class='arrow-left'> </div> <div class='tool_tip'>Password mismatch </div></div>").show();
                       // $(this).after("<div class='tooltip_outer' style='color:red;'><div class='arrow-left'> </div> <div class='tool_tip'>length must be 3 and above </div></div>").show();
                        
                        empty_count=1; 
                    }
                }
            }
           /* if($(this).hasClass('userName'))
            {
                $(this).next('.tooltip_outer').hide();
                if($(this).val().length < 3 || $(this).val().length   )
                {
                    $(this).after("<div class='tooltip_outer' style='color:red;'><div class='arrow-left'> </div> <div class='tool_tip'>length must be 3 and above </div></div>").show();
                    empty_count=1; 
                }
                else
                {
                    $('.confirm_password').next('.tooltip_outer').hide();
                    if($(this).val()===$('.confirm_password').val())
                    {
                        //$('.confirm_password').next('.tooltip_outer').hide();
                        $('.confirm_password').after("<div class='tooltip_outer' style='color:red;'><div class='arrow-left'> </div> <div class='tool_tip'>successfull match </div></div>").show();
             
                    }
                    else
                    {
                       $('.confirm_password').after("<div class='tooltip_outer' style='color:red;'><div class='arrow-left'> </div> <div class='tool_tip'>Password mismatch </div></div>").show();
                       // $(this).after("<div class='tooltip_outer' style='color:red;'><div class='arrow-left'> </div> <div class='tool_tip'>length must be 3 and above </div></div>").show();
                        
                        empty_count=1; 
                    }
                }
            }*/
            
        }
    });
    $('.dpreq').next('.tooltip_outer').hide();
    $('.dpreq').each(function()
    {
        
        $(this).next('.tooltip_outer').hide();
        if($(this).attr("selectedIndex") === 0)
        {
            $(this).after("<div class='tooltip_outer'> <div class='arrow-left'></div><div class='tool_tip'>Please select option </div></div>").show("slow");
            empty_count=1;
        }
        else
        {
            $(this).next('.tooltip_outer').hide();
         }
    });
    if(empty_count===1)
    {
        e.preventDefault();
    }
  else
    {
        $('.tooltip_outer').hide();
        //alert('Successful');
    }
}
);
});
