console.log("this is script file...");

const toggleSidebar = () => {

    //using jquery to show/hide sidebar
    if ($(".sidebar").is(":visible")) {
        //true
        //hiding sidebar now

        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%"); //when sidebar place is empty, we are have to make margin 0, so content area shift to left side

    } else{
        //false
        //showing sidebar

        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "20%");
    }
};


//search bar code
const search = () => {
    //console.log("searching...");
    let query = $("#search-input").val(); //rargetting search-input id
    if (query == "") {
        $(".search-result").hide();
      } else {
        //search it
        console.log(query);
        //sending request to server
        let url = `http://localhost:9090/search/${query}`;
        //fetch will return promise, so we will use 'then', we have pass function in 'then' so that response can receive.
        fetch(url)
            .then((response) => {
            return response.json(); //returning response using parsing
            }) //now json which has returned, this response we can be get in another 'then' 
            .then((data) => {
                //to get json we have access data
                //console.log(data);
                //injecting html from user query
                let text = `<div class='list-group'>`;
                //now 'data' is array here, just traverse it.
                data.forEach((contact) => {
                    //appending text to get dynamic data from js.
                    text += `<a href='/user/${contact.cId}/contact' class='list-group-item list-group-item-action'> ${contact.name} </a>`;
                });
                text += `</div>`; //append div to <div class='list-group'>
                //now we got dynamic data from js, next send 'text' to result (search box)
                $(".search-result").html(text); //injecting text to search-result.
                //we will only show result only when we get successful result.
                $(".search-result").show();
            });

        
    }
}

