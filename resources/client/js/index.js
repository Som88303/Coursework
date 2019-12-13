function pageLoad() {
    let username = Cookies.get("username");
    if (username !== undefined) {
        document.getElementById("logoutLink").style.display = "block";
        document.getElementById("loginLink").style.display = "none";
        document.getElementById("AccountLinkOut").style.display = "block";
        document.getElementById("AccountLinkIn").style.display = "none";
    }

}