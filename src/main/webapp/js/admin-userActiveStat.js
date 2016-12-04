/**
 * Description: 后台渠道推广页用
 * Company: NewLeader
 * author: luoshuhong
 * date 2015-9-18
 */

//初始化
$(function() {
    initQueryDate();  //默认初始化查询时间范围
//    query();          //查询数据
});

/**
 *  一些累计数据
 */
function totalQuery() {
	$.ajax({
		type: "POST",
		url: "../admin/userActive/totalQuery",
		dataType:"json",
		async:false,
		success : function(msg) {
            var json = msg;
			if (json.status == 'success') {
				var data = JSON.parse(json.data);
				$("#regist").text(data.regist);
				$("#school").text(data.school);
				$("#love").text(data.love);
				$("#dna").text(data.dna);
				$("#value").text(data.value);
				$("#behavior").text(data.behavior);
				$("#motivation").text(data.motivation);
				$("#passion").text(data.passion);
				$("#loveInit").text(data.loveInit);
			} else {
			}
		} 
	});
}

/**
 * 按时间查询
 */
function query() {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var postData = {"startDate":startDate, "endDate":endDate};
	$.ajax({
		type: "POST",
		url: "../admin/userActive/query",
		data: postData,
		dataType:"json",
		async:false,
		success : function(msg) {
            var json = msg;
			if (json.status == 'success') {
				var data = JSON.parse(json.data);
				var uv = JSON.parse(data.uv);
				var pv = JSON.parse(data.pv);
				uvFillData(uv.xAxis,uv.series);//绘制图表
				pvFillData(pv.xAxis,pv.series);//绘制图表
			} else {
				alert('错误，请重试！');
			}
		},
		error : function(msg) {
			alert(msg);
		}
	});
}

//默认初始化查询时间范围
function initQueryDate() {
	var now = new Date();
	var starDate = new Date(now.getTime() - 86400000 * 7);
	var endDate = new Date(now.getTime() + 86400000);
	var startDay = starDate.getDate();
	var endDay = endDate.getDate();
	if (startDay <= 9) {
		startDay = '0' + startDay;
	}
	if (endDay <= 9) {
		endDay = '0' + endDay;
	}
	
	var startMonth = starDate.getMonth() + 1;
	var endMonth = endDate.getMonth() + 1;
	if (startMonth <= 9) {
		startMonth = '0' + startMonth;
	}
	if (endMonth <= 9) {
		endMonth = '0' + endMonth;
	}
	var start = starDate.getFullYear() + "-" + startMonth + "-" + startDay;
	var end = endDate.getFullYear() + "-" + endMonth + "-" + endDay;
	
	$("#startDate").val(start);
	$("#endDate").val(end);
}

/**
 * UV统计
 * @param xAxis 横轴
 * @param series 数据
 */
function uvFillData(xAxis, series) {
	//绘制图表
	Highcharts.setOptions(Highcharts.theme);
	$('#uVContainer').highcharts({
		chart : {
			type : 'line'
		},
		title : {
			text : 'UV(独立用户访问量 d-天 w-周 m-月)'
		},
		yAxis : {
			title : {
				text : '数量'
			}
		},
		plotOptions : {
			line : {
				dataLabels : {
					enabled : true
				},
				enableMouseTracking : true
			}
		},
		xAxis : xAxis,
		series : series
	});
}

/**
 * PV统计
 * @param xAxis 横轴
 * @param series 数据
 */
function pvFillData(xAxis, series) {
	//绘制图表
	Highcharts.setOptions(Highcharts.theme);
	$('#pVContainer').highcharts({
		chart : {
			type : 'line'
		},
		title : {
			text : 'PV(单用户30min内算一次  d-天 w-周 m-月)'
		},
		yAxis : {
			title : {
				text : '数量'
			}
		},
		plotOptions : {
			line : {
				dataLabels : {
					enabled : true
				},
				enableMouseTracking : true
			}
		},
		xAxis : xAxis,
		series : series
	});
}

//主题
Highcharts.theme = {
	colors : [ "#DDDF0D", "#7798BF", "#55BF3B", "#DF5353", "#aaeeee",
			"#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee" ],
	chart : {
		backgroundColor : {
			linearGradient : {
				x1 : 0,
				y1 : 0,
				x2 : 0,
				y2 : 1
			},
			stops : [ [ 0, 'rgb(96, 96, 96)' ], [ 1, 'rgb(16, 16, 16)' ] ]
		},
		borderWidth : 0,
		borderRadius : 0,
		plotBackgroundColor : null,
		plotShadow : false,
		plotBorderWidth : 0
	},
	title : {
		style : {
			color : '#FFF',
			font : '16px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
		}
	},
	subtitle : {
		style : {
			color : '#DDD',
			font : '12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
		}
	},
	xAxis : {
		gridLineWidth : 0,
		lineColor : '#999',
		tickColor : '#999',
		labels : {
			style : {
				color : '#999',
				fontWeight : 'bold'
			}
		},
		title : {
			style : {
				color : '#AAA',
				font : 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			}
		}
	},
	yAxis : {
		alternateGridColor : null,
		minorTickInterval : null,
		gridLineColor : 'rgba(255, 255, 255, .1)',
		minorGridLineColor : 'rgba(255,255,255,0.07)',
		lineWidth : 0,
		tickWidth : 0,
		labels : {
			style : {
				color : '#999',
				fontWeight : 'bold'
			}
		},
		title : {
			style : {
				color : '#AAA',
				font : 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			}
		}
	},
	legend : {
		itemStyle : {
			color : '#CCC'
		},
		itemHoverStyle : {
			color : '#FFF'
		},
		itemHiddenStyle : {
			color : '#333'
		}
	},
	labels : {
		style : {
			color : '#CCC'
		}
	},
	tooltip : {
		backgroundColor : {
			linearGradient : {
				x1 : 0,
				y1 : 0,
				x2 : 0,
				y2 : 1
			},
			stops : [ [ 0, 'rgba(96, 96, 96, .8)' ],
					[ 1, 'rgba(16, 16, 16, .8)' ] ]
		},
		borderWidth : 0,
		style : {
			color : '#FFF'
		}
	},

	plotOptions : {
		series : {
			nullColor : '#444444'
		},
		line : {
			dataLabels : {
				color : '#CCC'
			},
			marker : {
				lineColor : '#333'
			}
		},
		spline : {
			marker : {
				lineColor : '#333'
			}
		},
		scatter : {
			marker : {
				lineColor : '#333'
			}
		},
		candlestick : {
			lineColor : 'white'
		}
	},

	toolbar : {
		itemStyle : {
			color : '#CCC'
		}
	},

	navigation : {
		buttonOptions : {
			symbolStroke : '#DDDDDD',
			hoverSymbolStroke : '#FFFFFF',
			theme : {
				fill : {
					linearGradient : {
						x1 : 0,
						y1 : 0,
						x2 : 0,
						y2 : 1
					},
					stops : [ [ 0.4, '#606060' ], [ 0.6, '#333333' ] ]
				},
				stroke : '#000000'
			}
		}
	},

	// scroll charts
	rangeSelector : {
		buttonTheme : {
			fill : {
				linearGradient : {
					x1 : 0,
					y1 : 0,
					x2 : 0,
					y2 : 1
				},
				stops : [ [ 0.4, '#888' ], [ 0.6, '#555' ] ]
			},
			stroke : '#000000',
			style : {
				color : '#CCC',
				fontWeight : 'bold'
			},
			states : {
				hover : {
					fill : {
						linearGradient : {
							x1 : 0,
							y1 : 0,
							x2 : 0,
							y2 : 1
						},
						stops : [ [ 0.4, '#BBB' ], [ 0.6, '#888' ] ]
					},
					stroke : '#000000',
					style : {
						color : 'white'
					}
				},
				select : {
					fill : {
						linearGradient : {
							x1 : 0,
							y1 : 0,
							x2 : 0,
							y2 : 1
						},
						stops : [ [ 0.1, '#000' ], [ 0.3, '#333' ] ]
					},
					stroke : '#000000',
					style : {
						color : 'yellow'
					}
				}
			}
		},
		inputStyle : {
			backgroundColor : '#333',
			color : 'silver'
		},
		labelStyle : {
			color : 'silver'
		}
	},

	navigator : {
		handles : {
			backgroundColor : '#666',
			borderColor : '#AAA'
		},
		outlineColor : '#CCC',
		maskFill : 'rgba(16, 16, 16, 0.5)',
		series : {
			color : '#7798BF',
			lineColor : '#A6C7ED'
		}
	},

	scrollbar : {
		barBackgroundColor : {
			linearGradient : {
				x1 : 0,
				y1 : 0,
				x2 : 0,
				y2 : 1
			},
			stops : [ [ 0.4, '#888' ], [ 0.6, '#555' ] ]
		},
		barBorderColor : '#CCC',
		buttonArrowColor : '#CCC',
		buttonBackgroundColor : {
			linearGradient : {
				x1 : 0,
				y1 : 0,
				x2 : 0,
				y2 : 1
			},
			stops : [ [ 0.4, '#888' ], [ 0.6, '#555' ] ]
		},
		buttonBorderColor : '#CCC',
		rifleColor : '#FFF',
		trackBackgroundColor : {
			linearGradient : {
				x1 : 0,
				y1 : 0,
				x2 : 0,
				y2 : 1
			},
			stops : [ [ 0, '#000' ], [ 1, '#333' ] ]
		},
		trackBorderColor : '#666'
	},

	// special colors for some of the demo examples
	legendBackgroundColor : 'rgba(48, 48, 48, 0.8)',
	background2 : 'rgb(70, 70, 70)',
	dataLabelsColor : '#444',
	textColor : '#E0E0E0',
	maskColor : 'rgba(255,255,255,0.3)'
};
