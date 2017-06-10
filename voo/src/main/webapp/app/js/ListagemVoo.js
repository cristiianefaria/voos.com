$(document).ready(function(){
    var horarioVoo = $(".horarioVoo");

    $(horarioVoo).each(function(i,data){
        data = $(data);
       dataFormatada =  moment(data.html(), "DD/MM/yyyy HH:mm").format('LLLL');
       data.html(dataFormatada);
    });

});