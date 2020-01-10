function pageLoad() {

    let username = Cookies.get("username");
    if (username !== undefined) {
        document.getElementById("logoutLink").style.display = "block";
        document.getElementById("loginLink").style.display = "none";
        document.getElementById("AccountLinkOut").style.display = "block";
        document.getElementById("AccountLinkIn").style.display = "none";
    }

    let ExercisesHTML = '<table>' +
        '<tr>' +
        '<th>Image</th>' +
        '<th>Exercise ID</th>' +
        '<th>Exercise Name</th>' +
        '<th>Calories Per Hour</th>' +
        '<th class="last">Options</th>' +
        '</tr>';

    fetch('/Exercises/list', {method: 'get'}
    ).then(response => response.json()
    ).then(Exercises => {
        for (let Exercise of Exercises) {

            ExercisesHTML += `<tr>` +
                `<td><img src='/client/img/${Exercise.Image}' 
                 alt='Picture of ${Exercise.ExerciseName}' height='100px'></td>` +
                `<td>${Exercise.ExerciseID}</td>` +
                `<td>${Exercise.CalPerHour}</td>` +
                `<td class="last">` +
                `<button class='editButton' data-id='${Exercise.ExerciseID}'>Edit</button>` +
                `<button class='deleteButton' data-id='${Exercise.ExerciseID}'>Delete</button>` +
                `</td>` +
                `</tr>`;

        }
        ExercisesHTML += '</table>';

        document.getElementById("listDiv").innerHTML = ExercisesHTML;

        let editButtons = document.getElementsByClassName("editButton");
        for (let button of editButtons) {
            button.addEventListener("click", editExercise);
        }

        let deleteButtons = document.getElementsByClassName("deleteButton");
        for (let button of deleteButtons) {
            button.addEventListener("click", deleteExercise);
        }

        checkLogin();
    });

    document.getElementById("saveButton").addEventListener("click", saveEditExercise);
    document.getElementById("cancelButton").addEventListener("click", cancelEditExercise);
    document.getElementById("imageUploadForm").addEventListener("submit", uploadImage);
}

function editExercise(event) {

    const ExerciseID = event.target.getAttribute("data-id");

    if (ExerciseID === null) {

        document.getElementById("editHeading").innerHTML = 'Add new Exercise:';

        document.getElementById("ExerciseID").value = '';
        document.getElementById("ExerciseName").value = '';
        document.getElementById("CalPerHour").value = '';
        document.getElementById("Image").value = '' ;

        document.getElementById("listDiv").style.display = 'none';
        document.getElementById("editDiv").style.display = 'block';

    } else {

        fetch('/Exercises/get/' + ExerciseID, {method: 'get'}
        ).then(response => response.json()
        ).then(Exercise => {

            if (Exercise.hasOwnProperty('error')) {
                alert(Exercise.error);
            } else {

                document.getElementById("editHeading").innerHTML = 'Editing ' + Exercise.ExerciseName + ':';

                document.getElementById("ExerciseId").value = ExerciseID;
                document.getElementById("ExerciseName").value = Exercise.ExerciseName;
                document.getElementById("CalPerHour").value = Exercise.CalPerHour;
                document.getElementById("Image").value = Exercise.Image;

                document.getElementById("listDiv").style.display = 'none';
                document.getElementById("editDiv").style.display = 'block';
            }
        });
    }
}

function saveEditExercise(event) {

    event.preventDefault();

    if (document.getElementById("ExerciseName").value.trim() === '') {
        alert("Please provide an Exercise Name");
        return;
    }

    if (document.getElementById("CalPerHour").value.trim() === '') {
        alert("Please provide the Calories per Hour.");
        return;
    }


    const ExerciseID = document.getElementById("ExerciseID").value;
    const form = document.getElementById("ExerciseForm");
    const formData = new FormData(form);

    let apiPath = '';
    if (ExerciseID === '') {
        apiPath = '/Exercises/add';
    } else {
        apiPath = '/Exercises/update';
    }

    fetch(apiPath, {method: 'post', body: formData}
    ).then(response => response.json()
    ).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            document.getElementById("listDiv").style.display = 'block';
            document.getElementById("editDiv").style.display = 'none';
            pageLoad();
        }
    });

}

function cancelEditExercise(event) {

    event.preventDefault();

    document.getElementById("listDiv").style.display = 'block';
    document.getElementById("editDiv").style.display = 'none';

}

function deleteExercise(event) {

    const ok = confirm("Are you sure?");

    if (ok === true) {

        let ExerciseID = event.target.getAttribute("data-id");
        let formData = new FormData();
        formData.append("ExerciseID", ExerciseID);

        fetch('/Exercises/delete', {method: 'post', body: formData}
        ).then(response => response.json()
        ).then(responseData => {

                if (responseData.hasOwnProperty('error')) {
                    alert(responseData.error);
                } else {
                    pageLoad();
                }
            }
        );
    }
}

function checkLogin() {

    let username = Cookies.get("username");

    let logInHTML = '';

    if (username === undefined) {

        let editButtons = document.getElementsByClassName("editButton");
        for (let button of editButtons) {
            button.style.visibility = "hidden";
        }

        let deleteButtons = document.getElementsByClassName("deleteButton");
        for (let button of deleteButtons) {
            button.style.visibility = "hidden";
        }

        document.getElementById("saveButton").style.visibility = 'hidden';
        document.getElementById("cancelButton").style.visibility = 'hidden';

        logInHTML = "Not logged in. <a href='/client/login.html'>Log in</a>";
    } else {

        let editButtons = document.getElementsByClassName("editButton");
        for (let button of editButtons) {
            button.style.visibility = "visible";
        }

        let deleteButtons = document.getElementsByClassName("deleteButton");
        for (let button of deleteButtons) {
            button.style.visibility = "visible";
        }

        document.getElementById("saveButton").style.visibility = 'visible';
        document.getElementById("cancelButton").style.visibility = 'visible';

        logInHTML = "Logged in as " + username ;

    }

    document.getElementById("loggedInDetails").innerHTML = logInHTML;

}

function uploadImage(event) {

    event.preventDefault();

    const imageUploadForm = document.getElementById('imageUploadForm');

    if (document.getElementById('file').value !== '') {

        imageUploadForm.style.display = 'none';
        document.getElementById('uploading').style.display = 'block';

        let fileData = new FormData(imageUploadForm);

        fetch('/Image/upload', {method: 'post', body: fileData},
        ).then(response => response.json()
        ).then(data => {

                if (data.hasOwnProperty('error')) {
                    alert(data.error);
                } else {
                    document.getElementById('file').value = '';
                }
                imageUploadForm.style.display = 'block';

                document.getElementById('uploading').style.display = 'none';
            }
        );

    } else {

        alert('No file specified');

    }

}