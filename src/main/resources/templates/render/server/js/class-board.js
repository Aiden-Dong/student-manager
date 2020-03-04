$(document).ready(function(){
    getAllStudents()
})


function getAllStudents(){
    httpGetMethod("/stm/api/v1/students", function(data){
        var status = data["status"];

        if(status != "success"){
            errorMessage("unexpected error");
        }
        var students = data["body"];

        cityContainer(students);
        stNumContainer(students);
        stClsNumContainer(students);
        stClsVecContainer(students);
        stPoliticalContainer(students);
        stAgeContainer(students);
    })
}

function cityContainer(students){
    var myChart = echarts.init(document.getElementById('city-container'));
    
    var dataObj = {};

    students.forEach(student => {
        var city = student['stCity']

        if (city in dataObj){
            dataObj[city]++;
        }else{
            dataObj[city] = 1;
        }
    });

    var data = []

    for(var city in dataObj){
        data.push({
            name : city,
            value : dataObj[city]
        })
    }
    // var data = [
    //     {name: "海门", value: 9},
    //     {name: "鄂尔多斯", value: 12},
    //     {name: "招远", value: 12},
    //     {name: "舟山", value: 12},
    //     {name: "齐齐哈尔", value: 14},
    //     {name: "盐城", value: 15},
    //     {name: "赤峰", value: 16},
    //     {name: "青岛", value: 18},
    //     {name: "乳山", value: 18},
    //     {name: "金昌", value: 19},
    //     {name: "泉州", value: 21},
    //     {name: "莱西", value: 21},
    //     {name: "日照", value: 21},
    //     {name: "胶南", value: 22},
    //     {name: "南通", value: 23},
    //     {name: "拉萨", value: 24},
    //     {name: "云浮", value: 24},
    //     {name: "梅州", value: 25},
    //     {name: "文登", value: 25},
    //     {name: "上海", value: 25},
    //     {name: "攀枝花", value: 25},
    //     {name: "威海", value: 25},
    //     {name: "承德", value: 25},
    //     {name: "厦门", value: 26},
    //     {name: "汕尾", value: 26},
    //     {name: "潮州", value: 26},
    //     {name: "丹东", value: 27},
    //     {name: "太仓", value: 27},
    //     {name: "曲靖", value: 27},
    //     {name: "烟台", value: 28},
    //     {name: "福州", value: 29},
    //     {name: "瓦房店", value: 30},
    //     {name: "即墨", value: 30},
    //     {name: "抚顺", value: 31},
    //     {name: "玉溪", value: 31},
    //     {name: "张家口", value: 31},
    //     {name: "阳泉", value: 31},
    //     {name: "莱州", value: 32},
    //     {name: "湖州", value: 32},
    //     {name: "汕头", value: 32},
    //     {name: "昆山", value: 33},
    //     {name: "宁波", value: 33},
    //     {name: "湛江", value: 33},
    //     {name: "揭阳", value: 34},
    //     {name: "荣成", value: 34},
    //     {name: "连云港", value: 35},
    //     {name: "葫芦岛", value: 35},
    //     {name: "常熟", value: 36},
    //     {name: "东莞", value: 36},
    //     {name: "河源", value: 36},
    //     {name: "淮安", value: 36},
    //     {name: "泰州", value: 36},
    //     {name: "南宁", value: 37},
    //     {name: "营口", value: 37},
    //     {name: "惠州", value: 37},
    //     {name: "江阴", value: 37},
    //     {name: "蓬莱", value: 37},
    //     {name: "韶关", value: 38},
    //     {name: "嘉峪关", value: 38},
    //     {name: "广州", value: 38},
    //     {name: "延安", value: 38},
    //     {name: "太原", value: 39},
    //     {name: "清远", value: 39},
    //     {name: "中山", value: 39},
    //     {name: "昆明", value: 39},
    //     {name: "寿光", value: 40},
    //     {name: "盘锦", value: 40},
    //     {name: "长治", value: 41},
    //     {name: "深圳", value: 41},
    //     {name: "珠海", value: 42},
    //     {name: "宿迁", value: 43},
    //     {name: "咸阳", value: 43},
    //     {name: "铜川", value: 44},
    //     {name: "平度", value: 44},
    //     {name: "佛山", value: 44},
    //     {name: "海口", value: 44},
    //     {name: "江门", value: 45},
    //     {name: "章丘", value: 45},
    //     {name: "肇庆", value: 46},
    //     {name: "大连", value: 47},
    //     {name: "临汾", value: 47},
    //     {name: "吴江", value: 47},
    //     {name: "石嘴山", value: 49},
    //     {name: "沈阳", value: 50},
    //     {name: "苏州", value: 50},
    //     {name: "茂名", value: 50},
    //     {name: "嘉兴", value: 51},
    //     {name: "长春", value: 51},
    //     {name: "胶州", value: 52},
    //     {name: "银川", value: 52},
    //     {name: "张家港", value: 52},
    //     {name: "三门峡", value: 53},
    //     {name: "锦州", value: 54},
    //     {name: "南昌", value: 54},
    //     {name: "柳州", value: 54},
    //     {name: "三亚", value: 54},
    //     {name: "自贡", value: 56},
    //     {name: "吉林", value: 56},
    //     {name: "阳江", value: 57},
    //     {name: "泸州", value: 57},
    //     {name: "西宁", value: 57},
    //     {name: "宜宾", value: 58},
    //     {name: "呼和浩特", value: 58},
    //     {name: "成都", value: 58},
    //     {name: "大同", value: 58},
    //     {name: "镇江", value: 59},
    //     {name: "桂林", value: 59},
    //     {name: "张家界", value: 59},
    //     {name: "宜兴", value: 59},
    //     {name: "北海", value: 60},
    //     {name: "西安", value: 61},
    //     {name: "金坛", value: 62},
    //     {name: "东营", value: 62},
    //     {name: "牡丹江", value: 63},
    //     {name: "遵义", value: 63},
    //     {name: "绍兴", value: 63},
    //     {name: "扬州", value: 64},
    //     {name: "常州", value: 64},
    //     {name: "潍坊", value: 65},
    //     {name: "重庆", value: 66},
    //     {name: "台州", value: 67},
    //     {name: "南京", value: 67},
    //     {name: "滨州", value: 70},
    //     {name: "贵阳", value: 71},
    //     {name: "无锡", value: 71},
    //     {name: "本溪", value: 71},
    //     {name: "克拉玛依", value: 72},
    //     {name: "渭南", value: 72},
    //     {name: "马鞍山", value: 72},
    //     {name: "宝鸡", value: 72},
    //     {name: "焦作", value: 75},
    //     {name: "句容", value: 75},
    //     {name: "北京", value: 79},
    //     {name: "徐州", value: 79},
    //     {name: "衡水", value: 80},
    //     {name: "包头", value: 80},
    //     {name: "绵阳", value: 80},
    //     {name: "乌鲁木齐", value: 84},
    //     {name: "枣庄", value: 84},
    //     {name: "杭州", value: 84},
    //     {name: "淄博", value: 85},
    //     {name: "鞍山", value: 86},
    //     {name: "溧阳", value: 86},
    //     {name: "库尔勒", value: 86},
    //     {name: "安阳", value: 90},
    //     {name: "开封", value: 90},
    //     {name: "济南", value: 92},
    //     {name: "德阳", value: 93},
    //     {name: "温州", value: 95},
    //     {name: "九江", value: 96},
    //     {name: "邯郸", value: 98},
    //     {name: "临安", value: 99},
    //     {name: "兰州", value: 99},
    //     {name: "沧州", value: 100},
    //     {name: "临沂", value: 103},
    //     {name: "南充", value: 104},
    //     {name: "天津", value: 105},
    //     {name: "富阳", value: 106},
    //     {name: "泰安", value: 112},
    //     {name: "诸暨", value: 112},
    //     {name: "郑州", value: 113},
    //     {name: "哈尔滨", value: 114},
    //     {name: "聊城", value: 116},
    //     {name: "芜湖", value: 117},
    //     {name: "唐山", value: 119},
    //     {name: "平顶山", value: 119},
    //     {name: "邢台", value: 119},
    //     {name: "德州", value: 120},
    //     {name: "济宁", value: 120},
    //     {name: "荆州", value: 127},
    //     {name: "宜昌", value: 130},
    //     {name: "义乌", value: 132},
    //     {name: "丽水", value: 133},
    //     {name: "洛阳", value: 134},
    //     {name: "秦皇岛", value: 136},
    //     {name: "株洲", value: 143},
    //     {name: "石家庄", value: 147},
    //     {name: "莱芜", value: 148},
    //     {name: "常德", value: 152},
    //     {name: "保定", value: 153},
    //     {name: "湘潭", value: 154},
    //     {name: "金华", value: 157},
    //     {name: "岳阳", value: 169},
    //     {name: "长沙", value: 175},
    //     {name: "衢州", value: 177},
    //     {name: "廊坊", value: 193},
    //     {name: "菏泽", value: 194},
    //     {name: "合肥", value: 229},
    //     {name: "武汉", value: 273},
    //     {name: "大庆", value: 279}
    // ];

    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };
    
    option = {
        backgroundColor: '#EEEEEE',
        title: {
            text: '学生位置分布',
            x:'center',
            textStyle: {
                color: '#4D4D4D'
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                return params.name + ' : ' + params.value[2];
            }
        },
        visualMap: {
            min: 0,
            max: 300,
            calculable: true,
            show : false,
            inRange: {
                color: ['#99c2ff', '#0066ff']
            },
            textStyle: {
                color: '#4D4D4D'
            }
        },
        geo: {
            map: 'china',
            zoom : 1,
            itemStyle: {
                areaColor: '#ffffff',
                borderColor: '#111',
                borderWidth : 0.1
            },
            label:{
                show : true,
                color : '#666666',
                fontStyle : 'italic'
            },
            emphasis: {
                itemStyle: {
                    areaColor: '#ffffff'
                },
                label: {
                    show: false
                }
            }
        },
        series: [
            {
                name: '人数',
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(data),
                symbolSize: function (val) {
                    return val[2] ;
                },
                itemStyle: {
                    color: 'purple'
                },
                emphasis: {
                    itemStyle: {
                        borderColor: '#fff',
                        borderWidth: 1
                    }
                }
            }
        ]
    }
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

function stNumContainer(students){
    var myChart = echarts.init(document.getElementById('student-num-container'));
    var stuSize = students.length;
    option = {
        tooltip: {
            formatter: '{a} <br/>{b} : {c}'
        },  
        series: [
            {
                name: '人数指标',
                type: 'gauge',
                splitNumber: 5,
                min : 0,
                max : 2000,
                radius: '90%',
                visualMap: {
                    min: 0,
                    max: 300,
                    calculable: true,
                    show : false,
                    inRange: {
                        color: ['#99c2ff', '#0066ff']
                    },
                    textStyle: {
                        color: '#4D4D4D'
                    }
                },
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        width: 8,
                        color: [[0.2, '#cce0ff'], [0.8, '#4d94ff'], [1, '#0052cc']]
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 10,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length: 12,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                axisLabel: {
                    backgroundColor: 'auto',
                    borderRadius: 1,
                    color: '#eee',
                    padding: 1,
                    fontSize: 10,
                    textShadowBlur: 1,
                    textShadowOffsetX: 1,
                    textShadowOffsetY: 1,
                    textShadowColor: '#222'
                },
                title: {
                    // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontSize: 10,
                    fontStyle: 'italic'
                },
                pointer : {
                    length : '80%',
                    width : 5
                },
                detail: {formatter: '{value}'},
                data: [{value: stuSize, name: '总人数'}]
            }
        ]
    };
    
   
    myChart.setOption(option,true);
    

}

function stClsNumContainer(students){
    var myChart = echarts.init(document.getElementById('st-cls-container'));

    var dataObj = {};

    students.forEach(student => {
        var sex = student['stSex']

        if (sex in dataObj){
            dataObj[sex]++;
        }else{
            dataObj[sex] = 1;
        }
    });

    var data = []

    for(var sex in dataObj){
        data.push({
            name : sex,
            value : dataObj[sex]
        })
    }

    option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        visualMap: {
            min: 0,
            max: 100,
            calculable: true,
            show : false,
            inRange: {
                color: ['#cce0ff','#0052cc']
            },
            textStyle: {
                color: '#4D4D4D'
            }
        },
        series: [
            {
                name: '男女人数比例',
                type: 'pie',
                radius: '80%',
                center: ['50%', '60%'],
                data: data,
                label : {
                    show : false
                },
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart.setOption(option,true);
}

function stClsVecContainer(students){
    httpGetMethod('/stm/api/v1/class', function(data){
      var status = data['status']
      
      if(status != 'success'){
          return;
      }

      var classes = data['body']
      var clsMap = {}

      classes.forEach(cls => {
        var clsId = cls['clsId']
        var clsName = cls['clsName']
        
        clsMap[clsId] = clsName
      })

      var clsNumMap = {}

      students.forEach(student => {
          var clsId = student['clsId']
          var clsName = clsMap[clsId]

          if(clsName in clsNumMap){
            clsNumMap[clsName] ++;
          }else{
            clsNumMap[clsName] = 1;
          }
      })

      var clsNameList = []
      var clsNumList = []

      for(var clsName in clsNumMap){
          clsNameList.push(clsName)
          clsNumList.push(clsNumMap[clsName])
      }

      var myChart = echarts.init(document.getElementById('st-cls-v-container'));

      option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        visualMap: {
            calculable: true,
            show : false,
            color : ['#4d94ff'],
            textStyle: {
                color: '#4D4D4D'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: clsNameList
        },
        series: [
            {
                type: 'bar',
                data: clsNumList
            }
        ]
    };
    myChart.setOption(option, true);
    })
}

function stPoliticalContainer(students){
    var myChart = echarts.init(document.getElementById('st-political-container'));


    var politicalObj = {}

    students.forEach(student =>{
        var political = student['stPolitical']

        if(political in politicalObj){
            politicalObj[political] ++
        }else{
            politicalObj[political] = 1
        }
    })

    var politicalList = []

    for(var political in politicalObj){
        politicalList.push({
            name : political,
            value : politicalObj[political]/students.length * 100
        })
    }

    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{c}%"
        },
        toolbox: {
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        visualMap: {
            min: 0,
            max: 100,
            calculable: true,
            show : false,
            //color : ['#4d94ff'],
            inRange: {
                color: ['#99c2ff', '#0066ff']
            },
            textStyle: {
                color: '#4D4D4D'
            }
        },
        series: [
            {
                type:'funnel',
                left: '10%',
                top: 60,
                bottom: 60,
                width: '80%',
                min: 0,
                max: 100,
                minSize: '0%',
                maxSize: '100%',
                sort: 'ascending',
                gap: 2,
                label: {
                    show: true,
                    position: 'inside'
                },
                labelLine: {
                    length: 10,
                    lineStyle: {
                        width: 1,
                        type: 'solid'
                    }
                },
                itemStyle: {
                    borderColor: '#fff',
                    borderWidth: 1
                },
                emphasis: {
                    label: {
                        fontSize: 20
                    }
                },
                data: politicalList
            }
        ]
    };

    
    myChart.setOption(option, true);
}

function stAgeContainer(students){
    var myChart = echarts.init(document.getElementById('st-age-container'));

    var stuMap = {}

    var ageNameList = [];
    var ageValueList = [];

    students.forEach(student =>{
        var age = new Date().getFullYear() - new Date(student['stStartTime']).getFullYear()
        if(age in stuMap){
            stuMap[age] ++
        }else{
            stuMap[age] = 1
        }
    })

    for(var age in stuMap){
        ageNameList.push(age)
        ageValueList.push(stuMap[age])
    }

    console.log(ageNameList)
    console.log(ageValueList)

    option = {
        color: ['#4d94ff'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ageNameList,
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '人数',
                type: 'bar',
                barWidth: '60%',
                data: ageValueList
            }
        ]
    };

    
    myChart.setOption(option, true);
}