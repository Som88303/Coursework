function pageLoad(){
    document.getElementById("cancelSign-upButton").addEventListener("click", event => {
        event.preventDefault();
        window.location.href = '/client/index.html'
    });
    document.getElementById("Sign-upButton").addEventListener("click", saveEditUser);
}


function saveEditUser(event) {

    event.preventDefault();

    if (document.getElementById("FirstName").value.trim() === '') {
        alert("Please provide your FirstName.");
        return;
    }

    if (document.getElementById("LastName").value.trim() === '') {
        alert("Please provide your LastName.");
        return;
    }

    if (document.getElementById("DateOfBirth").value.trim() === '') {
        alert("Please provide your Date Of Birth.");
        return;
    }

    if (document.getElementById("Gender").value.trim() === '') {
        alert("Please provide your Gender.");
        return;
    }
    if (document.getElementById("Age").value.trim() === '') {
        alert("Please provide your Age.");
        return;
    }
    if (document.getElementById("Username").value.trim() === '') {
        alert("Please provide your LastName.");
        return;
    }
    if (document.getElementById("Password").value.trim() === '') {
        alert("Please provide your Password.");
        return;
    }

    const UserID = document.getElementById("UserID").value;
    const form = document.getElementById("RegisterForm");
    const formData = new FormData(form);

    let apiPath = '';
    if (UserID === '') {
        apiPath = '/Users/add';
        window.location.href = '/client/login.html'
    } else {
        apiPath = '/Users/update';
    }

    fetch(apiPath, {method: 'post', body: formData}
    ).then(response => response.json()
    ).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            document.getElementById("listDiv").style.display = 'block';
            document.getElementById("editDiv").style.display = 'none';
        }
    });

}

