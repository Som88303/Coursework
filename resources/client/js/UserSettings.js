function pageLoad() {

}

function editUser(event) {

    const UserID = event.target.getAttribute("data-id");

    fetch('/Users/get/' + UserID, {method: 'get'}
    ).then(response => response.json()
    ).then(Users => {

        if (Users.hasOwnProperty('error')) {
            alert(Users.error);
        } else {

            document.getElementById("editHeading").innerHTML = 'Editing ' + Users.Username + ':';

            document.getElementById("UserID").value = UserID;
            document.getElementById("FirstName").value = Users.FirstName;
            document.getElementById("LastName").value = Users.LastName;
            document.getElementById("DateOfBirth").value = Users.DateOfBirth;
            document.getElementById("Gender").value = Users.Gender;
            document.getElementById("Age").value = Users.Age;
            document.getElementById("Username").value = Users.Username;
            document.getElementById("Password").value = Users.Password;

            document.getElementById("listDiv").style.display = 'none';
            document.getElementById("editDiv").style.display = 'block';
        }
    });
}