var map, drawControls;

OpenLayers.Feature.Vector.style['default']['strokeWidth'] = '2';
var bounds=1;
var options = {
    controls: [],
    maxExtent: bounds,
    maxResolution: "auto",//maxResolution����������  maxResolution����Ϊauto���ǵ�ͼ��ռ��DIV
    projection: "EPSG:4326",
    numZoomLevels: 7,//   ����ʾ�м������ż���
    units: 'degrees'
};
//****************************************************************************
//��ʼ�����������һ������ͼ��һ��ʸ��ͼ��
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
// ����һ��Control������{point:��Ҫ�ص�,line:����,polygon:�������,select:Ҫ��ѡ��,selecthover:Ҫ��ѡ��}��
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
//���ƶ���ѡ��
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
// ���Ƴ���ѡ����
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