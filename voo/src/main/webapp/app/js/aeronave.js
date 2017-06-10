$(document).ready(function(){

    var idAeronaveNoFormPoltronas = $("#aeronave_id");
    var editarPoltrona = $("#poltrona_aeronave_id");
    if($("#poltrona_id").val() != ""){
    	$("#poltrona_quantidade").prop("readonly",true);
    }
    // if(idAeronaveNoFormPoltronas.text() == ""){
    //     $("#gerenciamentoDePoltronas").addClass("hide");
    // }

    if(editarPoltrona.val() == ""){
        $("#gerenciamentoDePoltronas").addClass("hide");
    }

    

});