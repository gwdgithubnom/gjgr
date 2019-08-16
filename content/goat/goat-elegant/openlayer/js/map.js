var map;
var lon = 20;
var lat = 40;
var zoom = 3;
/* 鼠标点击监听*/
OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {                
        defaultHandlerOptions: {
        'single': true,
        'double': false,
        'pixelTolerance': 0,
        'stopSingle': false,
        'stopDouble': false
    },

    initialize: function(options) {
        this.handlerOptions = OpenLayers.Util.extend(
            {}, this.defaultHandlerOptions
        );
        OpenLayers.Control.prototype.initialize.apply(
            this, arguments
        ); 
        this.handler = new OpenLayers.Handler.Click(
            this, {
                'click': this.trigger
            }, this.handlerOptions
        );
    }, 

    trigger: function(e) {
        var lonlat = map.getLonLatFromPixel(e.xy);
		var div_map = document.getElementById('map');
		//alert(div_map.getBoundingClientRect().left);
		//alert('点击处:x='+e.x+'y='+e.y);
       // alert("You clicked near " + lonlat.lat + " N, " + lonlat.lon + " E");
		//alert(document.body.scrollTop+','+document.body.Left);		  
		//document.getElementById('info').style.margin=''+(e.y + document.body.scrollTop-350)+'px'+' 0px 0px '+'0px';
		//document.getElementById('info').style.top = e.y + document.body.scrollTop +div_map.getBoundingClientRect().top;
		//document.getElementById('info').style.left = e.x - document.body.scrollLeft +div_map.getBoundingClientRect().left-(1300-960)/2;
		///document.body.scrollTop
		//document.getElementById('info').style.margin-left=e.x;
		//document.getElementById('info').style.display='block';
	/*
	纬度是正确的，但是经度要加上90才对。可能跟picvms.jsp里面有个值减掉了90度有关。
	*/
    }

});
/* end 鼠标监听*/
function init(){
    map = new OpenLayers.Map('map', {
        controls: [
            new OpenLayers.Control.Navigation(),
            new OpenLayers.Control.PanZoomBar(),
            new OpenLayers.Control.LayerSwitcher({'ascending':false}),
            new OpenLayers.Control.Permalink(),
            new OpenLayers.Control.ScaleLine(),
            new OpenLayers.Control.Permalink('permalink'),
            new OpenLayers.Control.MousePosition(),
            new OpenLayers.Control.OverviewMap(),
            new OpenLayers.Control.KeyboardDefaults()
        ],
        numZoomLevels: 12
        
    });

    var ol_wms = new OpenLayers.Layer.WMS( "Local JSP",
        "http://vmap0.tiles.osgeo.org/wms/vmap0", {layers: 'm'},
			{tileOrigin: new OpenLayers.LonLat(0, 40)}	
		);
        var dm_wms = new OpenLayers.Layer.WMS(
            "人口",
        "http://vmap0.tiles.osgeo.org/wms/vmap0",
        {layers: "bathymetry,land_fn,park,drain_fn,drainage," +
                 "prov_bound,fedlimit,rail,road,popplace",
         transparent: "true", format: "image/png"},
        {visibility: false}
    );

    map.addLayers([ol_wms]);

    if (!map.getCenter()) {
        map.zoomToMaxExtent();
    }
	map.setCenter(new OpenLayers.LonLat(lon, lat), zoom);
	/*添加鼠标监听*/
	var click = new OpenLayers.Control.Click();
    map.addControl(click);
    click.activate();
}
//ajax，把link的返回结果写到id为Eid的内容中元素中。
function ajaxQ_fill(link, Eid) {
	//alert('222');
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var ret = xmlhttp.responseText;
			//alert(ret);
			//ret = Trim(ret);
			//ret = ret.substring(12);
			//alert(ret.indexOf('<'));
			//alert(document.getElementById(Eid).innerHTML);
			document.getElementById(Eid).innerHTML = ret;
			//jsAddItemToSelect(document.getElementById(Eid),'table','table');
			//alert('现在：'+document.getElementById(Eid).innerHTML);
		}
	};
	xmlhttp.open("GET", link, true);
	xmlhttp.send();
}






































            
            
            
            
            
            
            
            
            