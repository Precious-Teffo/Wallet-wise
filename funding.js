

document.getElemntById('fund-button').addEventListener('click',function(){
    const amount=document.getElementById('amount').value;
    const paymentMethod=document.getElementyId('payment-method').value;
    
    fetch('WalletFundingServlet',{
       headers:{
           'Content-Type':'application/json'
       },
       body:JSON.stringify({
           amount:amount,
           paymentMethod:paymentMethod
       })
    })
            .then(response=>response.json());
            .then(data=>{document.getElementById('result').innerHTML='Funding successful! Wallet balance: ${data.balance}';
           getFundingHistoryu();
            getTransactionHistory();
    })
            .catch(error=>{document.getElementById('result').innerHTML=`Error:${error.message}`;
    });
    } 

//Get funding history from backend API
function getFundingHistory(){
    fetch('/api/funding-history')
            .then(response=>response.json())
            .then(data=>{
                document.getElementById('funding-history').innerHTML='';
        data.forEach(transaction=>{
            const transactionDiv=document.creatElement('div');
            trasactionDiv.innerHTMLL=`<P>Date:${transaction.date}</p>
             <p>Payment method:${transaction.paymentMethod}</p>`;
            document.getElementById('funding-history'.appendChild(transactionDiv));
        });
            })
                    .catch(error=>{document.getElementById('funding-history').innerHTML=`Error:${error.message}`;
            });
            }
//get transaction history from backend API
function getTransactionHistory(){
    fetch('/api/ transaction-history')
            .then(response=>response.json())
            .then(data=>{
                document.getElementById('transaction-history').innerHTML='';
             const table=document.createElement('table');
             const thead=document.createElement('thead');
             const tbody=document.createElement('tbody');
             table.appendChild(thead);
             table.appendChild(table);
             
             const row=document.createElement('tr');
             thead.appendChild(row);
             const dateTh=document.createElement('th');
             dateTh.innerHTML='Date';
             row.appendChild(dateTh);
             const amountTh=document.createElement('th');
             amountTh.innerHTML='Amount';
             row.appendChild(amountTh);
             const typeTh=document.createElement('th');
             typeTh.innerHTML='Type';
             row.appendChild(typeTh);
             data.forEach(transaction=>{
                 const row=document.createElement('tr');
                 tbody.appendChild(row);
                 const dateTd=document.createElement('td');
                 dateTd.innerHTML=transacton.date;
                 row.appendChild(dateTd);
                 const amoundTd= document.createElement('td');
                 amoundTd.innerHTML=transaction.amount;
                 row.appendChild(amountTd);
                 const typeId=document.createElement('td');
                 typeId.innerHTML=transaction.type;
                 row.appendChild(typeId);
             });
            })
                    .catch(error=>{
                        document.getElementById('transaction-history').innerHTML=`Error: ${error.message}`;
                    })
    }
};
