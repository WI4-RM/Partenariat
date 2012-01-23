Ext.onReady(function() {

 setupInfoPerso = true;
 setupParcEcole = true;
 setupParcInter = true;
 
 refreshIP(false);
 refreshPE(false);
 refreshPI(false);
 
});

function refreshIP(setupInfoPerso){
    Ext.QuickTips.init();
 var formInfoPerso = new Ext.FormPanel({
	
	frame: true,
	title: 'Informations personnelles',
	//title: 'inscription',
	width: 400,
	//height: 150,
	items: [{
		 xtype: 'textfield',
	fieldLabel: 'Nom',
		name: 'nom'
    },{
         xtype: 'textfield',
	fieldLabel: 'Prénom',
		name: 'prenom'
    },{
         xtype: 'textfield',
	fieldLabel: 'Adresse e-mail',
		name: 'mail'
    }	
	],
	buttons: [{
		text: 'Sauvegarder',
		handler: function(){
                    document.getElementById('cg').innerHTML = "";
                    refreshIP(false);
		}
	}]
});
var tabInfoPerso = new Ext.Panel({
    	//renderTo: Ext.getBody(),
        frame: true,
	title: 'Informations personelles',
         items: [{
            html: "<table border=0>  <tr> <td>Nom : </td> <td>John</td> </tr> <tr> <td>Prénom : </td> <td>Smith</td> </tr> </table>",
            xtype: "panel"
        }],
		buttons: [{
		text: 'Modifier',
		handler: function(){
                    
                    document.getElementById('cg').innerHTML = "";
                    refreshIP(true);
		}
		}]
		});
if  (setupInfoPerso)
	item1 = formInfoPerso;
else
	item1 = tabInfoPerso;
    
var mainPanel = new Ext.Panel ({
           width:400,
          items: [item1],
  renderTo: Ext.Element.get('cg')
});
}


function refreshPE(setupParcEcole){
    var options = new Ext.data.ArrayStore({
	fields: ['id', 'option_choisie'],
	data : [['1', 'Informatique'],['2', 'Procédés'],['3', 'Mécanique et Matériaux'],['4', 'Environnement'],['3', 'Mathématiques Appliqués'],['4', 'Gestion Industrielle']]
});
var formParcEcole = new Ext.FormPanel({
	//renderTo : Ext.getBody(),
	frame: true,
	title: 'Ecole',
	width: 400,
	//height: 150,
	items: [{
         xtype: 'textfield',
	fieldLabel: 'Promotion',
		name: 'promotion'
    },
	{
         xtype: 'combo',
	hiddenName: 'option',
	fieldLabel: 'Option Suivie',
		  mode: 'local',
		 store: options,
		 displayField: 'option_choisie',
		 valueField: 'id',
		 width: 250
    }	
	],
	buttons: [{
		text: 'Sauvegarder',
		handler: function(){
                    document.getElementById('cdh').innerHTML = "";
                    refreshPE(false);
		}
	}]
});
var tabParcEcole = new Ext.Panel({
    	//renderTo: Ext.getBody(),
        frame: true,
	title: 'Ecole',
         items: [{
            html: "<table border=0>  <tr> <td>Promotion : </td> <td>2009</td> </tr> <tr> <td>Option choisie: </td> <td>Informatique</td> </tr> </table>",
            xtype: "panel"
        }],
		buttons: [{
		text: 'Modifier',
		handler: function(){
                    document.getElementById('cdh').innerHTML = "";
                    refreshPE(true);
		}
		}]
});
                
if  (setupParcEcole)
	item2 = formParcEcole;
else
	item2 = tabParcEcole;
    
var mainPanel = new Ext.Panel ({
          width:400,
          items: [item2],
  renderTo: Ext.Element.get('cdh')
});
}

function refreshPI(setupParcInter){
 
 Ext.QuickTips.init();

var formParcInter = new Ext.FormPanel({
	//renderTo : Ext.getBody(),
	frame: true,
	title: 'International',
	width: 400,
	// height: 400,
	items: [{
			 xtype: 'textfield',
		fieldLabel: 'Ville',
			name: 'ville'
		},
		{
			 xtype: 'textfield',
		fieldLabel: 'Pays',
			name: 'pays'
		},
		{
			 xtype: 'datefield',
		fieldLabel: 'Date d\'Arrivée',
			name: 'dateArr'
		},
		{
			 xtype: 'datefield',
		fieldLabel: 'Date de Départ',
			name: 'dateDep'
		},
		{
			xtype: 'radiogroup',
		   columns: 1,
		fieldLabel: 'Cadre',
			  name: 'cadre',
			 items: [{
			 name : 'tourisme',
		   boxLabel: 'Tourisme',
		inputValue: 'tourisme'
		},
		{
			 name : 'stage',
		   boxLabel: 'Stage',
		inputValue: 'stage'
		},
		{
		 name : 'universite',
		   boxLabel: 'Université',
		inputValue: 'universite'
		}]
		},
		{
		xtype: 'htmleditor',
		name: 'Commentaires',
	//	hideLabel: true,
		height: 100,
		anchor: '100%'
		}
	],
	buttons: [{
		text: 'Sauvegarder',
		handler: function(){
                    //setupInfoPerso = false;
                    document.getElementById('cdb').innerHTML = "";
                    refreshPI(false);
		}
	},
	{
		text: 'Ajouter une destination',
		handler: function(){
                    
		}
	}
	]
});


var tabParcInter = new Ext.Panel({
    	//renderTo: Ext.getBody(),
        frame: true,
	title: 'Parcours international',
         items: [{
            html: "<table border=0>  <tr> <td>Ville : </td> <td>Berlin</td> </tr> <tr> <td>Pays : </td> <td>Allemagne</td> </tr> <tr> <td>Date d'arrivée : </td> <td>10 janvier 2011</td> </tr><tr> <td>Date de départ : </td> <td>15 septembre 2011</td> </tr></table>",
            xtype: "panel"
        }],
		buttons: [{
		text: 'Modifier',
		handler: function(){
                   // setupInfoPerso = false;
                    document.getElementById('cdb').innerHTML = "";
                    refreshPI(true);
                    //alert('You clicked the button!');
		}
		}]
});	
if  (setupParcInter)
	item3 = formParcInter;
else
	item3 = tabParcInter;

var mainPanel = new Ext.Panel ({
          width:400,
          items: [item3],
  renderTo: Ext.Element.get('cdb')
});
}