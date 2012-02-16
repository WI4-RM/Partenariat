dojo.require("esri.map");      
      
var map;

function init() {
	var initExtent = new esri.geometry.Extent({"xmin":-15086986.872212615,"ymin":-176042.86884174153,"xmax":11486193.13706525,"ymax":12073449.536024196,"spatialReference":{"wkid":4326}});
	map = new esri.Map("map",{extent:initExtent});
	var basemap = new esri.layers.ArcGISTiledMapServiceLayer("http://server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");
	map.addLayer(basemap);

	window.onresize = function(){
		setTimeout(function(){
			map.resize(); 
			map.reposition();
		}, 500);
	};
        if (loadEvent){
            map.onLoad = loadEvent;
        }
}
dojo.addOnLoad(init);
