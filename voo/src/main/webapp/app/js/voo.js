$(document).ready(function(){

	$("#horario").datetimepicker({
        icons: {
            time: "fa fa-clock-o",
            date: "fa fa-calendar",
            up: "fa fa-arrow-up",
            down: "fa fa-arrow-down",
            previous: 'fa fa-angle-left',
            next: 'fa fa-angle-right'
        },
        locale: 'pt-br'
    });
});