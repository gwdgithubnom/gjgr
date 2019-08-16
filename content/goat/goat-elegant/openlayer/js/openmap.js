var map, drawControls;

OpenLayers.Feature.Vector.style['default']['strokeWidth'] = '2';
var bounds=1;
var options = {
    controls: [],
    maxExtent: bounds,
    maxResolution: "auto",//maxResolution－最大解析度  maxResolution设置为auto，那地图就占满DIV
    projection: "EPSG:4326",
    numZoomLevels: 7,//   （表示有几个缩放级别）
    units: 'degrees'
};
//****************************************************************************
//初始化函数，填加一个基础图和一个矢量图层
//****************************************************************************
function init(){
    map = new OpenLayers.Map('map');
    var wmsLayer = new OpenLayers.Layer.WMS(
        "OpenLayers WMS",
        "http://vmap0.tiles.osgeo.org/wms/vmap0",
        {layers: 'basic'}
    );
    var vectors = new OpenLayers.Layer.Vector("Vector Layer");
    map.addLayers([wmsLayer, vectors]);
//****************************************************************************
// 定义一个Control参数对{point:画要素点,line:画线,polygon:画多边形,select:要素选择,selecthover:要素选择}－
//****************************************************************************
    drawControls = {
//               scaleline:new OpenLayers.Control.ScaleLine(
//                   vectors, OpenLayers.Handler.Point
//               ),
        point: new OpenLayers.Control.DrawFeature(
            vectors, OpenLayers.Handler.Point
        ),
        line: new OpenLayers.Control.DrawFeature(
            vectors, OpenLayers.Handler.Path
        ),
        line_point: new OpenLayers.Control.DrawFeature(
            vectors, OpenLayers.Handler.Path
        ),
        polygon: new OpenLayers.Control.DrawFeature(
            vectors, OpenLayers.Handler.Polygon
        ),
        select: new OpenLayers.Control.SelectFeature(
            vectors,
            {
                clickout: false, toggle: false,
                multiple: false, hover: false,
                toggleKey: "ctrlKey", // ctrl key removes from selection
                multipleKey: "shiftKey", // shift key adds to selection
                box: true
            }
        ),
        selecthover: new OpenLayers.Control.SelectFeature(
            vectors,
            {
                multiple: false, hover: true,
                toggleKey: "ctrlKey", // ctrl key removes from selection
                multipleKey: "shiftKey" // shift key adds to selection
            }
        )
    };
//****************************************************************************
//控制动作选择
//****************************************************************************
    for(var key in drawControls) {
        map.addControl(drawControls[key]);
    }
    map.setCenter(new OpenLayers.LonLat(0, 0), 3);
}
function toggleControl(element) {
    for(key in drawControls) {
        var control = drawControls[key];
        if(element.value == key && element.checked) {
            control.activate();
        } else {
            control.deactivate();
        }
    }
}

function mutiControl(element){
    var e=$(element).attr("value");
    console.log(element);
    //   var array=element.attr("value");
    console.log(e);
}

//****************************************************************************
// 控制撤销选择动作
//****************************************************************************
function update() {
    var clickout = document.getElementById("clickout").checked;
    drawControls.select.clickout = clickout;
    var hover = document.getElementById("hover").checked;
    drawControls.select.hover = hover;
    drawControls.select.box = document.getElementById("box").checked;
    if(drawControls.select.active) {
        drawControls.select.deactivate();
        drawControls.select.activate();
    }
}