
/**
 * 画扇形，实际返回一个多边形。最后要通过全局的地图组件 map的addOverlay方法添加。（部分参数可缺省）
 * 终止角度缺省，则在起始角度上+60
 * @param {Object} lng 经度
 * @param {Object} lat 纬度
 * @param {Object} sDegree 起始角度(方向：正东到正北)
 *
 */
let map;

export function drawSectorOmitAngle(lng, lat, sDegree,baiduMap,angle) {
    map = baiduMap;
    var radius = 100; //单位米
    //对传入的角度从减去90度，由从正东起始，转为正北起始
    sDegree = sDegree - 90;
    let eDegree = sDegree + angle;
    var color = "blue";
    return drawSector(lng, lat, radius, sDegree, eDegree, color);
}


export function drawSectorOmit(lng, lat, sDegree,baiduMap) {
    return drawSectorOmitAngle(lng, lat, sDegree,baiduMap,60);
}

/**
 * 画扇形，实际返回一个多边形。最后要通过全局的地图组件 map的addOverlay方法添加
 * @param {Object} lng 经度
 * @param {Object} lat 纬度
 * @param {Object} radius 半径
 * @param {Object} sDegree 起始角度(方向：正东到正北)
 * @param {Object} eDegree 终止角度
 * @param {Object} color 颜色
 *
 */
export function drawSector(lng, lat, radius, sDegree, eDegree, color) {
    if (eDegree < sDegree) {
        console.log("drawSector() ----> 终止角度应大于起始角度");
        return "-1";
    }

    var point = new BMap.Point(lng, lat);

    var strokeColor = color; //边线颜色
    var strokeWeight = 1; //边线宽度
    var strokeOpacity = 0.8; //边线透明度，取值范围0 - 1。
    var strokeStyle = 'solid'; //边线的样式，solid或dashed。
    var fillColour = color; //填充颜色
    var fillOpacity = 0.4; //填充透明度


    var points = []; //创建构成多边形的点数组
    var step = ((eDegree - sDegree) / 1000) || 1000; //根据扇形的总夹角确定每步夹角度数，最大为1000

    points.push(point); //设置第一点（原点）

    //根据步长计算，循环获取每步的圆弧上点的坐标，存入点数组
    for (var i = sDegree; i < eDegree + 0.001; i += step) {
        points.push(EOffsetBearing(point, radius, i));
    }

    points.push(point); //设置最后一点（原点）

    //根据构成的点数组以及其他参数画多边形
    var polygon = new BMap.Polygon(
        points, {
            strokeColor: strokeColor,
            strokeWeight: strokeWeight,
            strokeOpacity: strokeOpacity,
            strokeStyle: strokeStyle,
            fillColor: fillColour,
            fillOpacity: fillOpacity
        });

    return polygon;
}

//使用数学的方法计算需要画扇形的圆弧上的点坐标
//point:原点; dist:半径距离; angle:角度 （方向：纬度差为180时，逆时针；纬度差为-180时，顺时针 ）
export function EOffsetBearing(point, dist, angle) {
    var lngConv = map.getDistance(point, new BMap.Point(point.lng + 0.1, point.lat)) * 10; //计算1经度与原点的距离
    var latConv = map.getDistance(point, new BMap.Point(point.lng, point.lat + 0.1)) * 10; //计算1纬度与原点的距离
    var lat = dist * Math.sin(angle * Math.PI / 180) / latConv; //正弦计算待获取的点的纬度与原点纬度差
    var lng = dist * Math.cos(angle * Math.PI / 180) / lngConv; //余弦计算待获取的点的经度与原点经度差
    return new BMap.Point(point.lng + lng, point.lat + lat);
}

export function MP(ak) {
    return new Promise(function (resolve, reject) {
        window.onload = function () {
            resolve(BMap)
        };
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "http://api.map.baidu.com/api?v=2.0&ak="+ak+"&callback=init";
        script.onerror = reject;
        document.head.appendChild(script);
    })
}
