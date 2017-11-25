var Mock = require('mockjs');
var qs = require('qs');

let codesData = Mock.mock({
    data: [{
        "key": "026001",
        "name": "Dashboard",
        "url": null,
        "extra": "laptop",
        "order": 1,
        "children": []
    }, {
        "key": "026002",
        "name": "会员分析",
        "url": null,
        "extra": "user",
        "order": 2,
        "children": [{
            "key": "026002001",
            "name": "会员新增升级分析",
            "url": "/mem/memberAdd",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "026002002",
            "name": "会员活跃分析",
            "url": "/mem/memberActive",
            "extra": null,
            "order": 2,
            "children": []
        }]
    }, {
        "key": "026003",
        "name": "订单分析",
        "url": null,
        "extra": "shopping-cart",
        "order": 3,
        "children": [{
            "key": "026003001",
            "name": "订单趋势",
            "url": "/order/orderTrend",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "026003002",
            "name": "订单会员分布",
            "url": "/order/orderMemDistribute",
            "extra": null,
            "order": 2,
            "children": []
        }]
    }, {
        "key": "026004",
        "name": "营销统计",
        "url": null,
        "extra": "file-excel",
        "order": 4,
        "children": [{
            "key": "026004001",
            "name": "专车营销统计报告",
            "url": "/market/marketStatistics",
            "extra": null,
            "order": 1,
            "children": []
        }]
    }, {
        "key": "026005",
        "name": "用户分析",
        "url": null,
        "extra": "file-excel",
        "order": 5,
        "children": [{
            "key": "026005001",
            "name": "新增用户",
            "url": "/userAnalysis/newUser",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "026005002",
            "name": "活跃用户",
            "url": "/userAnalysis/orderMemDistribute",
            "extra": null,
            "order": 2,
            "children": []
        }, {
            "key": "026005003",
            "name": "沉默用户",
            "url": "/userAnalysis/orderMemDistribute",
            "extra": null,
            "order": 3,
            "children": []
        }, {
            "key": "026005004",
            "name": "版本分布",
            "url": "/userAnalysis/orderMemDistribute",
            "extra": null,
            "order": 4,
            "children": []
        }]
    }, {
        "key": "126001",
        "name": "网站分析",
        "url": null,
        "extra": "desktop",
        "order": 6,
        "children": [{
            "key": "126001001",
            "name": "趋势分析",
            "url": "/analyze/flowTrend",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "126001002",
            "name": "页面分析",
            "url": "/analyze/visitDetails",
            "extra": null,
            "order": 2,
            "children": []
        }, {
            "key": "126001003",
            "name": "网站对比分析",
            "url": "/analyze/contrast",
            "extra": null,
            "order": 3,
            "children": []
        }, {
            "key": "126001004",
            "name": "页面对比分析",
            "url": "/analyze/pageContrast",
            "extra": null,
            "order": 4,
            "children": []
        }, {
            "key": "126001005",
            "name": "访问统计",
            "url": "/analyze/visitCount",
            "extra": null,
            "order": 5,
            "children": []
        }, {
            "key": "126001006",
            "name": "营销成本分析",
            "url": "/marketing/cost",
            "extra": null,
            "order": 6,
            "children": []
        }]
    }, {
        "key": "126002",
        "name": "转化分析",
        "url": null,
        "extra": "filter",
        "order": 7,
        "children": [{
            "key": "126002001",
            "name": "预约看车转化",
            "url": "/conversion/seeCar",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "126002002",
            "name": "订单转化",
            "url": "/conversion/order",
            "extra": null,
            "order": 2,
            "children": []
        }]
    }, {
        "key": "126003",
        "name": "用户画像",
        "url": null,
        "extra": "solution",
        "order": 8,
        "children": [{
            "key": "126003001",
            "name": "留资用户所属地区",
            "url": "/userPortrait/theirArea",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "126003002",
            "name": "常购品牌",
            "url": "/userPortrait/oftenBuyBrand",
            "extra": null,
            "order": 2,
            "children": []
        }]
    }, {
        "key": "126004",
        "name": "页面版本管理",
        "url": null,
        "extra": "book",
        "order": 8,
        "children": [{
            "key": "126004001",
            "name": "版本查询",
            "url": "/pageVersion/manage",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "126004002",
            "name": "版本管理",
            "url": "/pageVersion/search",
            "extra": null,
            "order": 2,
            "children": []
        }]
    }, {
        "key": "026006",
        "name": "用户构成",
        "url": null,
        "extra": "file-excel",
        "order": 6,
        "children": [{
            "key": "026006001",
            "name": "周用户构成",
            "url": "/userConsist/weekUserConsist",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "026006002",
            "name": "变化系数趋势",
            "url": "/userConsist/loadChangeTrend",
            "extra": null,
            "order": 2,
            "children": []
        }]
    }, {
        "key": "026007",
        "name": "终端属性",
        "url": null,
        "extra": "mobile",
        "order": 6,
        "children": [{
            "key": "026007001",
            "name": "设备终端",
            "url": null,
            "extra": null,
            "order": 1,
            "children": [
                {
                    "key": "0260070001",
                    "name": "机型",
                    "url": "/terminalAttribute/devTerminal/phoneType",
                    "extra": null,
                    "order": 1,
                    "children": []
                }, {
                    "key": "0260070002",
                    "name": "操作系统",
                    "url": "/terminalAttribute/devTerminal/operatorSystem",
                    "extra": null,
                    "order": 1,
                    "children": []
                }, {
                    "key": "0260070003",
                    "name": "分辨率",
                    "url": "/terminalAttribute/devTerminal/resolution",
                    "extra": null,
                    "order": 1,
                    "children": []
                }
            ]
        }, {
            "key": "026007002",
            "name": "联网方式",
            "url": "/terminalAttribute/netType",
            "extra": null,
            "order": 2,
            "children": []
        }, {
            "key": "026007003",
            "name": "运营商",
            "url": "/terminalAttribute/mobileOperator",
            "extra": null,
            "order": 3,
            "children": []
        }, {
            "key": "026007004",
            "name": "地域",
            "url": "/terminalAttribute/city",
            "extra": null,
            "order": 4,
            "children": []
        }]
    }, {
        "key": "026008",
        "name": "留存分析",
        "url": null,
        "extra": "file-excel",
        "order": 8,
        "children": [{
            "key": "026008001",
            "name": "用户新鲜度",
            "url": "/retentionAnalysis/freshness",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "026008002",
            "name": "用户活跃度",
            "url": "/retentionAnalysis/activeDegree",
            "extra": null,
            "order": 2,
            "children": []
        }]
    }, {
        "key": "026009",
        "name": "功能使用",
        "url": null,
        "extra": "file-excel",
        "order": 8,
        "children": [{
            "key": "026009001",
            "name": "自定义事件",
            "url": "/function/customEvent",
            "extra": null,
            "order": 1,
            "children": []
        },{
            "key": "026009002",
            "name": "事件趋势",
            "url": "/function/eventTrend",
            "extra": null,
            "order": 1,
            "children": []
        }]
    }, {
        "key": "026010",
        "name": "租车数据分析",
        "url": null,
        "extra": "filter",
        "order": 10,
        "children": [{
            "key": "026010001",
            "name": "用户转化",
            "url": "/zuche/dataShow",
            "extra": null,
            "order": 1,
            "children": []
        },
            {
                "key": "026010002",
                "name": "spark任务",
                "url": "/zuche/sparkTask",
                "extra": null,
                "order": 2,
                "children": []
            }
        ]
    }, {
        "key": "026011",
        "name": "用户参与度",
        "url": null,
        "extra": "filter",
        "order": 10,
        "children": [{
            "key": "026011001",
            "name": "使用时长",
            "url": "/userParticipation/useTime",
            "extra": null,
            "order": 1,
            "children": []
        }, {
            "key": "026011002",
            "name": "使用频率",
            "url": "/userParticipation/frequency",
            "extra": null,
            "order": 2,
            "children": []
        },{
            "key": "026011003",
            "name": "访问页面",
            "url": "/userParticipation/pageCount",
            "extra": null,
            "order": 2,
            "children": []
        }
        ]
    }
    ],
    oneData: {
        name: '菜单111',
        key: '001001001',
        parentKey: '001001',
        projectId: '1'
    }
});


module.exports = {
    '/ucartemple/user/login': function (req, res) {
        // const userItem = req.body;
        let result = true;
        // if (userItem.emailType == 'ucarinc' && userItem.username == 'admin' && userItem.password == 'admin') {
        //     result = true
        // }
        const response = {
            success: result,
            data: {username: 'guest', userRole: 1, userId: 1, infoComplete: false},
            message: ''
        };
        res.json(response)
    },

    '/ucartemple/menu/treeList': function (req, res) {
        // const page = qs.parse(req.query);
        res.json({
            success: true,
            data: codesData.data
        })
    }
};
