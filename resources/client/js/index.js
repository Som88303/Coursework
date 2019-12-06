function pageLoad() {

    let FoodsHTML = '<table>' +
        '<tr>' +
        '<th>Image</th>' +
        '<th>FoodID</th>' +
        '<th>FoodName</th>' +
        '<th>Proteins</th>' +
        '<th>Carbohydrates</th>' +
        '<th>Fats</th>' +
        '<th>CalPerHundredGrams</th>' +
        '<th>HealthyPoints</th>' +
        '<th class="last">Options</th>' +
        '</tr>';

    fetch('/Foods/list', {method: 'get'}
    ).then(response => response.json()
    ).then(Foods => {
        for (let Food of Foods) {

            FoodsHTML += `<tr>` +
                `<td><img src='/client/img/${Food.Image}' 
                 alt='Picture of ${Food.FoodName}' height='100px'></td>` +
                `<td>${Food.FoodID}</td>` +
                `<td>${Food.FoodName}</td>` +
                `<td>${Food.Proteins}</td>` +
                `<td>${Food.Carbohydrates}</td>` +
                `<td>${Food.Fats}</td>` +
                `<td>${Food.CalPerHundredGrams}</td>` +
                `<td>${Food.HealthyPoints}</td>` +
                `<td class="last">` +
                `<button class='editButton' data-id='${Food.FoodID}'>Edit</button>` +
                `<button class='deleteButton' data-id='${Food.FoodID}'>Delete</button>` +
                `</td>` +
                `</tr>`;

        }
        FoodsHTML += '</table>';

        document.getElementById("listDiv").innerHTML = FoodsHTML;

        let editButtons = document.getElementsByClassName("editButton");
        for (let button of editButtons) {
            button.addEventListener("click", editFood);
        }

        let deleteButtons = document.getElementsByClassName("deleteButton");
        for (let button of deleteButtons) {
            button.addEventListener("click", deleteFood);
        }

        checkLogin();
    });

    document.getElementById("saveButton").addEventListener("click", saveEditFood);
    document.getElementById("cancelButton").addEventListener("click", cancelEditFood);
    document.getElementById("imageUploadForm").addEventListener("submit", uploadImage);
}

function editFood(event) {

    const FoodID = event.target.getAttribute("data-id");

    if (FoodID === null) {

        document.getElementById("editHeading").innerHTML = 'Add new fruit:';

        document.getElementById("FoodID").value = '';
        document.getElementById("FoodName").value = '';
        document.getElementById("Proteins").value = '';
        document.getElementById("Carbohydrates").value = '';
        document.getElementById("Fats").value = '';
        document.getElementById("CalPerHundredGrams").value = '';
        document.getElementById("HealthyPoints").value = '';
        document.getElementById("Image").value = '';

        document.getElementById("listDiv").style.display = 'none';
        document.getElementById("editDiv").style.display = 'block';

    } else {

        fetch('/Foods/get/' + FoodID, {method: 'get'}
        ).then(response => response.json()
        ).then(Food => {

            if (Food.hasOwnProperty('error')) {
                alert(Food.error);
            } else {

                document.getElementById("editHeading").innerHTML = 'Editing ' + Food.FoodName + ':';

                document.getElementById("FoodID").value = FoodID;
                document.getElementById("FoodName").value = Food.FoodName;
                document.getElementById("Proteins").value = Food.Proteins;
                document.getElementById("Carbohydrates").value = Food.Carbohydrates;
                document.getElementById("Fats").value = Food.Fats;
                document.getElementById("CalPerHundredGrams").value = Food.CalPerHundredGrams;
                document.getElementById("HealthyPoints").value = Food.HealthyPoints;
                document.getElementById("Image").value = Food.Image;

                document.getElementById("listDiv").style.display = 'none';
                document.getElementById("editDiv").style.display = 'block';
            }
        });
    }
}

function saveEditFood(event) {

    event.preventDefault();

    if (document.getElementById("FoodName").value.trim() === '') {
        alert("Please provide a Food Name.");
        return;
    }

    if (document.getElementById("Proteins").value.trim() === '') {
        alert("Please provide the amount of proteins(g).");
        return;
    }

    if (document.getElementById("Carbohydrates").value.trim() === '') {
        alert("Please provide the amount of Carbohydrates(g).");
        return;
    }

    if (document.getElementById("Fats").value.trim() === '') {
        alert("Please provide the amount of Fats(g).");
        return;
    }
    if (document.getElementById("CalPerHundredGrams").value.trim() === '') {
        alert("Please provide the amount of calories per hundred grams.");
        return;
    }
    if (document.getElementById("HealthyPoints").value.trim() === '') {
        alert("How healthy is this product? 1-5 (1 is healthiest).");
        return;
    }

    const FoodID = document.getElementById("FoodID").value;
    const form = document.getElementById("FoodForm");
    const formData = new FormData(form);

    let apiPath = '';
    if (FoodID === '') {
        apiPath = '/Foods/add';
    } else {
        apiPath = '/Foods/update';
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

function cancelEditFood(event) {

    event.preventDefault();

    document.getElementById("listDiv").style.display = 'block';
    document.getElementById("editDiv").style.display = 'none';

}

function deleteFood(event) {

    const ok = confirm("Are you sure?");

    if (ok === true) {

        let FoodID = event.target.getAttribute("data-id");
        let formData = new FormData();
        formData.append("FoodID", FoodID);

        fetch('/Foods/delete', {method: 'post', body: formData}
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

        logInHTML = "Logged in as " + username + ". <a href='/client/login.html?logout'>Log out</a>";

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






