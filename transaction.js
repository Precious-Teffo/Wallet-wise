const transactionHistoryElement=document.getElementById('transaction-history');
const filterFormElement=document.getElementById('filter-form');
const exportFormElement=document.getElementById('export-form');

//Get transaction history from backend API
function getTransactionHistory(){
     fetch('/api/ transaction-history')
            .then(response=>response.json())
            .then(data=>{
                document.getElementById('transaction-history').innerHTML='';
        data.forEach(transaction=>{
            const transactionElement=document.createElement('div');
            transactionElement.innerHTML=`<p>Date:${transaction.date}</p>
                                        <p>Amount:${transaction.amount}</p>
                                       <p>Type:${transaction.type}</p>`;
           
          transactionHistoryElement.appendChild(transactionElement);  
        });
    })
            .catch(error=>(
            console.error(error)
            ));
    }
    //Export transaction to CSV
    function exportTransactionToCSV(){
        fetch('/api/ transaction-history/CVS')
                .then(response=>response.blob())
                .then(blob=>{const url=window.URL.createObjectURL(blob);
            const a=document.createElement('a');
            a.href=url;
            a.download='transactions.csv';
            a.click();
        })
                .catch(error=>{console.error(error);
        });
    }
     //Export transaction to excel
    function exportTransactionToExcel(){
        fetch('/api/ transaction-history/CVS')
                .then(response=>response.blob())
                .then(blob=>{const url=window.URL.createObjectURL(blob);
            const a=document.createElement('a');
            a.href=url;
            a.download='transactions.csv';
            a.click();
        })
                .catch(error=>{console.error(error);
        });
    }
    //add event listeners
    filterFormElement.addEventListener('submit',(event)=>{
        event.preventDefault();
        filterTransactions();
        
    });
    
    exportFormElement.addEventListener('submit',(event)=>{
         event.preventDefault();
         exportTransactionToCSV();
         exportTransactionToExcel();
    });
    
    //get transaction historynon page load
    gettransactionHistory();