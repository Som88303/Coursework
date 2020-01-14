function pageLoad() {

    let UsersHTML = '<table>' +
        '<tr>' +
        '<th>Username</th>' +
        '</tr>'+
        '<th>First Name</th>' +



    fetch('/Users/list', {method: 'get'}
    ).then(response => response.json()
    ).then(Foods => {
        for (let Food of Foods) {

            FoodsHTML += `<tr>` +
                `<td><img src='/client/img/${Food.Image}' 
                 alt='Picture of ${Food.FoodName}' height='100px'></td>` +
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