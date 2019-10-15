/*
 * jQuery gvChart plugin
 * This plugin was created to simplify things when using Google Visualisation Charts. 
 * It still needs to be used with google script import tag, however now you can
 * crate chart from your table. 
 * @name jquery.gvChart-1.0.1.js
 * @version 1.0.1
 * @category jQuery plugin google charts
 */
(function (jQuery){
	jQuery.fn.gvChart = function(settings){

	defaults={
		hideTable: true,
		chartType: 'AreaChart',
		chartDivID: 'gvChartDiv',
		gvSettings: null
	};
	
	var el = document.createElement('div');
	jQuery(el).insertBefore(this);
	gvChartCount++;
	gvChartID = defaults.chartDivID+gvChartCount;
	jQuery(el).attr('id',gvChartID);
	jQuery(el).addClass('gvChart');

	if(settings){
	jQuery.extend(defaults,settings);
	}
	
	if(defaults.hideTable)
		$(this).hide();

	var data = new google.visualization.DataTable();

	// add X label
	data.addColumn('string','X labels');

	var a = new Array();

	var headers = $(this).find('thead').find('th');
	var rows = $(this).find('tbody').find('tr');

	rows.each(function(index){
		data.addColumn('number',$(this).find('th').text());
	});

	data.addRows(headers.length-1);

	headers.each(function(index){
		if(index){
			data.setCell(index-1, 0, $(this).text());
		}
	});

	rows.each(function(index){
			$(this).find('td').each(function(index2){
				data.setCell(index2, index+1, parseFloat($(this).text()));
			});
	});
	
	chartSettings = {
		title : $(this).find('caption').text()
	};
	
	if(defaults.gvSettings){
		jQuery.extend(chartSettings,defaults.gvSettings);
	}
	
	eval("var chart = new google.visualization."+defaults.chartType+"(document.getElementById('"+gvChartID+"'))");
	chart.draw(data, chartSettings);
  }
})(jQuery);


function gvChartInit(){
	gvChartCount = 0;
	google.load('visualization', '1', {packages: ['corechart']});
}


