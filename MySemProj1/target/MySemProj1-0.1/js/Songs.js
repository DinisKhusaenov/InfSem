function addToBasket(productId) {
    $.ajax('/list', {
        method: 'POST',
        success: function( result ) {
            alert("Песня добавлены в списки")
        },
        error : function () {
            alert("Требуется авторизоваться")
        }
    })
}

function addToList(productId) {
    $.ajax('/list', {
        method: 'POST',
        data: {
            action: "addToWishList",
            id: productId
        },
        success: function( result ) {
            alert("Песня добавлены в списки")
        },
        error : function () {
            alert("Требуется авторизоваться")
        }
    })
}

function sorted(type){
    $.ajax('/list', {
        method: 'POST',
        data: {
            type
        },
        success: function (result) {
            alert("Песня добавлены в списки")
        },
        error: function () {
            alert("Требуется авторизоваться")
        }
    })

}