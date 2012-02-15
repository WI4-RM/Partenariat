/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function checkEmail(input){
    var EmailExpression = /^[a-zA-Z0-9\\.\\-]*@mines-saint-etienne.org$/;
    if (!input.value.match(EmailExpression))
        alert("format mail non valide !");

        
}

function checkPassword(input){
    if (!(input.value.length >=8))
        alert("le passe doit avoir 8 caracteres au minimun");
}


function checkName(input){
    var NameExpression = /^[a-zA-Z\\-]*$/;
    if (!input.value.match(NameExpression))
        alert("format de nom non valide");            
}

function checkYear(input){
    var yearExpr = /^[1-2][0-9]{3}$/;
     if (!input.value.match(yearExpr))
        alert("format d'annee non valide");    

}

function checkPhone(input){
    var phoneExpr = /^[0-9]*$/;
         if (!input.value.match(yearExpr))
        alert("format de telephone non valide");   
    
}