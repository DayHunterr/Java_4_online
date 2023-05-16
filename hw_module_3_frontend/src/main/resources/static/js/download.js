/*
$(document).ready(function() {
    // Назначаем обработчик события клика на кнопку с идентификатором "download"
    $('#download').click(function() {
        // Получаем идентификатор аккаунта со страницы
        var accountId = $('#accountID').val();

        // Формируем URL для GET-запроса с добавлением идентификатора аккаунта
        var url = 'http://localhost:8080/transactions/download/' + accountId;

        // Выполняем GET-запрос
        $.get(url, function(data, status) {
            if (status === 'success') {
                // Если запрос выполнен успешно, можно выполнять дополнительные действия
                console.log('Загрузка файла выполнена успешно.');
            } else {
                // Если произошла ошибка, выводим сообщение об ошибке
                console.log('Ошибка при загрузке файла.');
            }
        });
    });
});*/
/*
$(document).ready(function() {
    $('#download').click(function() {

        var accountID = $('#accountID').val();

        var url = 'http://localhost:8080/transactions/download/' + accountID;


        $.ajax({
            url: url,
            type: 'GET',
            headers: {
                'Access-Control-Allow-Origin': 'http://localhost:8081,http://localhost:8080' ,
                'Access-Control-Allow-Methods': 'GET',
                'Access-Control-Allow-Headers': 'Content-Type'
            },
            success: function(data, status) {
                if (status === 'success') {
                    console.log('Загрузка файла выполнена успешно.');
                } else {
                    console.log('Ошибка при загрузке файла.');
                }
            },
            error: function(xhr, status, error) {
                console.log('Ошибка при загрузке файла.');
            }
        });
    });
});*/
