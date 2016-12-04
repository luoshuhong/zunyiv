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
 *校验是否为null
 */
function isEmpty(value) {
	if ('' == value || undefined == value || null == value) {
		return true;
	}
	return false;
}