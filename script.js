document.getElementById("create-wallet-btn").addEventListener("click",fuction(event){
    event.preventDefault();
    
    var walletName= document.getByElementById("wallet-name").value;
    var walletDescription=document.getByElementById("wallet-description").value;
    var walletType= document.getByElementById("wallet-type").value;
   var walletCurrency= document.getByElementById("wallet-currency").value;
     
     var walletData={
         "walletName":walletName,
         "walletDescription":walletDescription,
         "walletType":walletType,
         "walletCurrency":walletCurrency
     };
     
     fetch("http://localhost:8080/create-wallet",{
         method:"POST",
         header:{"Content-Type":"application/json"},
         body:JSON.stringify(walletData)
     })
             .then(response=>response.json())
             .then(data=>{document.getByElementById("wallet-created").innerHTML="Wallet created successfully!";document.getByElementById("wallet-form").reset();
     })
             .catch(error=>{document.getByElementById("wallet-created").innerHTML="Error creating wallet!"
     });
});
