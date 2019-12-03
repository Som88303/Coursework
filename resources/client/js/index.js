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
            button.addEventListener("click", editFruit);
        }

        let deleteButtons = document.getElementsByClassName("deleteButton");
        for (let button of deleteButtons) {
            button.addEventListener("click", deleteFruit);
        }

    });

    document.getElementById("saveButton").addEventListener("click", saveEditFruit);
    document.getElementById("cancelButton").addEventListener("click", cancelEditFruit);
}



