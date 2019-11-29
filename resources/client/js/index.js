function pageLoad() {

    let FoodsHTML = '<table>' +
        '<tr>' +
        '<th>FoodID</th>' +
        '<th>FoodName</th>' +
        '<th>Proteins</th>' +
        '<th>Carbohydrates</th>' +
        '<th>Fats</th>' +
        '<th>CalPerHundredGrams</th>' +
        '<th>HealthyPoints</th>' +
        '<th>PortionSize</th>' +
        '<th class="last">Options</th>' +
        '</tr>';

    fetch('/Foods/list', {method: 'get'}
    ).then(response => response.json()
    ).then(Foods => {
        for (let Food of Foods) {

            FoodsHTML += `<tr>` +
                `<td>${Food.FoodID}</td>` +
                `<td>${Food.FoodName}</td>` +
                `<td class="last">` +
                `<td><img src='/client/img/${Food.Image}' 
                 alt='Picture of ${Food.FoodName}' height='100px'></td>` +


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



