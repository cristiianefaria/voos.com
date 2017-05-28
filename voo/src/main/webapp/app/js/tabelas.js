$(document).ready(function(){

    tabelas = $(".naoExibirSeVazia");

    tabelas.each(function(i,tabela){
        trs = $(tabela).find("tbody tr");
        if(trs.length < 1){
            $(tabela).addClass("hide");
        }
    });

});