document.addEventListener("DOMContentLoaded",function(){
    const registrationForm=document.getElemetById("registration-form");
    const submitButton=document.querySelector("button [type='sumbit']");
    
    registrationForm.addEventListener("submit",function(event){
        event.preventDefault();
        
        const username=document.getElementById("username").value;
        const email=document.getElementById("email").value;
        const password=document.getElementById("password").value;
        const accoutType=document.getElementById("account-type").value;
        
        //Send a POST request to the server to register user
        fetch('https://localhost:8080/register',{
            method:"POST",
            headers:{"Content-Type":"application/json"
            },
            body: JSON.stringify({
                username:username,
                email:email,
                password:password,
                accountType:accountType
            })
        })
                .then(response=>response.json())
                .then(data=>console.log(data))
                .catch(error=>console.error(error));
    });
    
});
