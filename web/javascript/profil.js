function ModifInfoPerso(nom, prenom, promo){
    document.getElementById('cgauche').innerHTML = "";
    document.getElementById('cgauche').innerHTML += "\
<form action=\"changeInfoPerso\">\n\
<h2>Modifier Mes Informations Personnelles</h2>\n\
<table>\n\
<tr>\n\
<td>Nom</td>\n\
<td><input type=\"text\" name=\"nom\" value="+nom+" /></td>\n\
</tr>\n\
<tr>\n\
<td>Prenom</td>\n\
<td><input type=\"text\" name=\"prenom\" value="+prenom+" /></td>\n\
</tr>\n\
<tr>\n\
<td>Promotion</td>\n\
<td><input type=\"text\" name=\"promo\" value="+promo+" /></td>\n\
</tr>\n\
</table>\n\
<input type=\"button\" value=\"Annuler\" onClick=\"javascript:refreshInfoPerso(\'"+nom+"\',\'"+prenom+"\',\'"+promo+"\');\">\n\
</form>";
}

function refreshInfoPerso(nom, prenom, promo){
    document.getElementById('cgauche').innerHTML = "";
    document.getElementById('cgauche').innerHTML += "\
<table>\n\
<caption>Informations personelles</caption>\n\
<tr>\n\
<td>Nom</td>\n\
<td>"+nom+"</td>\n\
</tr>\n\
<tr>\n\
<td>Prenom</td>\n\
<td>"+prenom+"</td>\n\
</tr>\n\
<tr>\n\
<td>Promotion</td>\n\
<td>"+promo+"</td>\n\
</tr>\n\
</table>\n\
<input type=\"button\" onclick=\"javascript:ModifInfoPerso(\'"+nom+"\',\'"+prenom+"\',\'"+promo+"\');\" value=\"Modifier\" > \n\
"
}

function addDestinationP(){
    document.getElementById('cd').innerHTML = "";
    document.getElementById('cd').innerHTML += "\
<form id='newform' action=\"addDestination\">\n\
<h2>Ajouter une destination</h2>\n\
<table>\n\
<tr>\n\
<td>Ville *</td>\n\
<td><input type=\"text\" id=\"ville\" name=\"ville\" /></td>\n\
</tr>\n\
<tr>\n\
<td>Pays *</td>\n\
<td><input type=\"text\" id=\"pays\" name=\"pays\" /></td>\n\
</tr>\n\
<tr>\n\
<td>Type *</td>\n\
<td><input type=\"radio\" name=\"type\" value=\"Tourisme\">Tourisme\n\
<input type=\"radio\" name=\"type\" value=\"Etudes\">Etudes\n\
<input type=\"radio\" name=\"type\" value=\"Stage\">Stage</td>\n\
</tr>\n\
<tr>\n\
<td>Date de départ *</td>\n\
<td>JJ<input type=\"text\"  name=\"jourd\" style=\"width:20px\" onkeypress:\"checkDate(this.form.JJd,this.form.MMd,this.form.AAAAd)\"/>MM<input type=\"textarea\" name=\"moisd\" style=\"width:20px\"/>AAAA<input type=\"textarea\" name=\"ana\" style=\"width:40px\"/></td>\n\
</tr>\n\
<tr>\n\
<td>Date de retour *</td>\n\
<td>JJ<input type=\"text\" name=\"joura\" style=\"width:20px\" onkeypress:\"checkDate(this.form.JJa,this.form.MMa,this.form.AAAAa)\"/>MM<input type=\"textarea\" name=\"moisa\" style=\"width:20px\"/>AAAA<input type=\"textarea\" name=\"and\" style=\"width:40px\"/></td>\n\
</tr>\n\
<tr>\n\
<td>Commentaire</td>\n\
<td><textarea name=\"com\" style=\"width:400px; height:200px;\"> </textarea></td>\n\
</tr>\n\
</table>\n\
<input type=\"button\" value=\"Annuler\">\n\
<input type=\"button\" value=\"Créer une nouvelle destination\" onclick=\"javascript:checkFormValues(this.form.ville, this.form.pays, this.form.type, this.form.com, this.form.joura,this.form.moisa,this.form.ana, this.form.jourd,this.form.moisd, this.form.and)\" >\n\
<input id = 'x' type=\"hidden\" name=\"x\"></input>\n\
<input id = 'y' type=\"hidden\" name=\"y\"></input>\n\
<input id = 'z' type=\"hidden\" name=\"z\"></input>\n\
</form>";
}

function checkFormValues(ville, pays, type, com, jourA, moisA, anA, jourD, moisD, anD){
    if ((checkNotNull(ville, pays, type)==true)&&(checkDate(jourD, moisD, anD)==true)&&(checkDate(jourA, moisA, anA)==true)){
    var i =0;
    for (i=0; i < type.length; i++)
            {
               if (type[i].checked){
                   break;
               }
            }
    document.getElementById("addmap").style.left = "10%";
    map.resize();
    map.reposition();
}
}


function checkNotNull(ville, pays, type){
    if ((ville.value.length==0)||(pays.value.length==0)){
        alert("Tous les champs obligatoires(*) doivent être remplis");
        return false;
    }
    else{
            for (var i=0; i < type.length; i++)
            {
               if (type[i].checked){
                   return true;
               }
            }
        return false
    }

    return true;
}

function checkDate(jour, mois, an) {
        
        if ((mois.value.length!=2)||(jour.value.length!=2)||(an.value.length!=4)){
            alert("La date n'est pas valide!");
            return false;
        }
            
        if ((mois.value==1)||(mois.value==3)||(mois.value==5)||(mois.value==7)||(mois.value==8)||(mois.value==10)||(mois.value==12)){
            if ((jour.value<1)||(jour.value>31)){
                alert("La date n'est pas valide!");
                return false;
            }
                
        }
        else{
            if ((jour.value<1)||(jour.value>30)){
                alert("La date n'est pas valide!");
                return false;
            }
        }
        
        return true;
        
}