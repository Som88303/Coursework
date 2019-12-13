function pageLoad(){
    document.getElementById("cancelSign-upButton").addEventListener("click", event => {
        event.preventDefault();
        window.location.href = '/client/index.html'
    });


}