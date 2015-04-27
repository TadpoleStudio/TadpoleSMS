<%@ page contentType="text/html;charset=utf8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>	<html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>	<html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]> <html class="no-js ie9" lang="en"> <!-->
<html lang="en">
<!--<![endif]-->
<head>
<!-- Set the viewport width to device width for mobile -->
<meta name="viewport" content="width=device-width" />
<title>Data Source</title>
<s:include value="/jsps/common/head.jsp" />

</head>
<body>
	<s:include value="/jsps/common/brand.jsp" />
	<s:include value="/jsps/common/menu.jsp" />
	<section class="mainbg">
		<div class="container" id="QuickDataSourceDataModelContainer">
			<div class="row">
				<div style="color: red">
					<s:fielderror />
				</div>
				<div id="quickDataSourceDialog" title="QuickDataSource Management" style="display: none" data-bind="with : selectedQuickDataSource">
					<div class="row">
						<div class="three columns">
							<label>Name</label>
							<input type="text" data-bind="value : name" />
						</div>
						<div class="three columns">
							<label>Type</label>
							<select data-bind="options: $root.QuickDataType,
                      					       optionsText: 'optionText',
                       					       value: type,
                       					       optionsValue : 'optionValue',
                       						   selectedOption : type,
                       						   optionsCaption: 'Please select'">
							</select>		
						</div>
						<div class="three columns">
							<label>Fixed Values</label>
							<input type="text" data-bind="value : keyValues" />
						</div>
						<div class="three columns">
							<label>SQL</label>
							<input type="text" data-bind="value : querySql" />
						</div>
					</div>
					<div class="row">
						<div class="three columns">
							<label>Description</label>
							<select data-bind="options: $root.DataSources,
                      					       optionsText: 'optionText',
                       					       value: description,
                       					       optionsValue : 'optionValue',
                       						   selectedOption : description,
                       						   optionsCaption: 'Please select'">
							</select>		
						</div>
					</div>
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>Search QuickDataSource</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content" data-bind="with : quickDataSourceSearch">
								<div class="row">
									<div class="three columns">
										<label>Name</label>
												<select data-bind="options: $root.DataSources,
                      					       		optionsText: 'optionText',
                       					       		value: name,
                       					      		optionsValue : 'optionValue',
                       						   		selectedOption : name,
                       						   		optionsCaption: 'Please select'">
												</select>		
									</div>
							</div>
							
							<div class="row">
								<a title="Search QuickDataSource" data-bind="click : $root.searchQuickDataSourceWithConditions" href="#" class="small blue button">Seach QuickDataSource</a>
								<a title="Search QuickDataSource" data-bind="click : $root.resetSearchConditions" href="#" class="small blue button">Reset</a>
							</div>
						</div>
					</div>	
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>QuickDataSource List</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content">
						
								<div class="row" style="margin-bottom: 5px">
									<div class="nine columns">
										Find <span class="label" data-bind="text : $root.totalCount"></span> records, <span class="label" data-bind="text : $root.totalPageCount"></span> pages.
									</div>
									<div class="three columns">
										<a title="add attribute" data-bind="click : $root.openManageQuickDataSourceDialog" href="#" class="right" style="margin-right: 10px"><i class="small icon-plus icon-green"></i></a>
									</div>
								</div>
								<div class="row">
									<table class="infoTable">
										<thead>
											<tr>
												<th style="text-align: center">Name</th>
												<th style="text-align: center">Type</th>
												<th style="text-align: center">Fixed Values</th>
												<th style="text-align: center">SQL</th>
												<th style="text-align: center">Description</th>
												<th></th>
											</tr>
										</thead>
										<tbody data-bind="foreach : quickDataSourceList">
											<tr>
												<td style="text-align: center" data-bind="text : name"></td>
												<td style="text-align: center" data-bind="text : type"></td>
												<td style="text-align: center" data-bind="text : keyValues"></td>
												<td style="text-align: center" data-bind="text : querySql"></td>
												<td style="text-align: center" data-bind="text : description"></td>
												<td style="text-align: center">
													<a title="update quickDataSource" data-bind="click : $root.openManageQuickDataSourceDialog" style="margin-left: 10px;" href="#"><i class="icon-pencil small icon-blue"></i></a>
													<a title="delete quickDataSource" data-bind="click : $root.deleteQuickDataSource" style="margin-left: 10px;" href="#"><i class="icon-trash small icon-red"></i></a>
												</td>
											</tr>
										</tbody>
									</table>
									<br>
								</div>
								<div class="row" id="quickDataSourcePageNavigation"></div>
						</div>
					</div>

				</div>

			</div>
		</div>
	</section>
	<s:include value="/jsps/common/footer.jsp" />
	<script src="/TadpoleSMS/js/vo/QuickDataSource.js"></script>
	<script src="/TadpoleSMS/js/vo/search/QuickDataSourceSearch.js"></script>
	<script>
		$(document).ready(function() {

			var QuickDataSourceDataModel = function() {

				var self = this;
				
				self.selectedQuickDataSource = ko.observable(new QuickDataSource());
				self.quickDataSourceList = ko.observableArray([]);
				self.totalCount = ko.observable(0);
				self.totalPageCount = ko.observable(0);
				self.currentIndex = ko.observable(1);
				self.quickDataSourceSearch = ko.observable(new QuickDataSourceSearch());
				self.QuickDataType = ko.observableArray([]);
				self.DataSources = ko.observableArray([]);
				$.ajax({ url : '/TadpoleSMS/loadDatasource.action',
						 data : { dataSourceName : 'QuickDataType' },
						 success : function(data) {
								self.QuickDataType(data);
						}
					});
				$.ajax({ url : '/TadpoleSMS/loadDatasource.action',
						 data : { dataSourceName : 'DataSources' },
						 success : function(data) {
								self.DataSources(data);
						}
					});
				
				self.resetSearchConditions = function() {
					self.quickDataSourceSearch(new QuickDataSourceSearch());
				};
				
				self.searchQuickDataSource = function() {
				
					for(var key in self.quickDataSourceSearch()) {
						if (eval("self.quickDataSourceSearch()." + key) == '') {
							eval("self.quickDataSourceSearch()." + key + " = null");
						}
					}
					
					$.ajax({
						url : 'loadQuickDataSources.action',
						data : {currentIndex : self.currentIndex(),
								quickDataSourceSearchVoJson : JSON.stringify(self.quickDataSourceSearch())
								},
						success : function(data) {
							
							if (data && data.object && data.object.elements) {
							
								self.quickDataSourceList(data.object.elements);
								self.totalCount(data.object.total);
								self.totalPageCount(data.object.totalPages);
								
								$('#quickDataSourcePageNavigation').pagination(
                				self.totalCount(),
        							{
                					num_edge_entries: 1,
                					num_display_entries: 15,
                					callback: self.pageSelectCallback,
                					items_per_page: 10,
                					prev_text: "Last Page",
                					next_text: "Next Page",
                					current_page : self.currentIndex() - 1,
                					load_first_page : false
        							}
								);
							} else {
								
								if (!isOK(data)) {
									
									fail("Error: " + data.message);
									
									self.quickDataSourceList([]);
									self.totalCount(0);
									self.totalPageCount(0);
								}
							}
						}
					});
				};
				
				self.searchQuickDataSource();
				
				self.searchQuickDataSourceWithConditions = function() {
					self.currentIndex(1);
					self.searchQuickDataSource();
				};
				
				self.pageSelectCallback = function(page_index, jq){
        				self.currentIndex(page_index + 1);
        				self.searchQuickDataSource();
        				return false;
				};
				
				self.saveOrUpdateQuickDataSource = function() {
					
					$.ajax({
						url : 'saveOrUpdateQuickDataSource.action',
						method : 'POST',
						data : {
							quickDataSourceJson : JSON.stringify(self.selectedQuickDataSource())
						},
						success : function(data) {
							handleStanderdResponse(data);
							
							if(data.object && data.object.id) {
								self.selectedQuickDataSource(data.object);
								closeDialog('quickDataSourceDialog');
								
								self.searchQuickDataSource();
							}
						}
					});	
					
				};
				
				self.openManageQuickDataSourceDialog = function(item, event) {
					
					if(item.id) {
						self.selectedQuickDataSource(item);
					} else {
						self.selectedQuickDataSource(new QuickDataSource());
					}
					
					$('#quickDataSourceDialog').dialog({
						modal : true,
						width : 1191,
						height : 658,
						open : function(e) {
							changeButtonStyleForPopup(e);
						},
						
						buttons : {
							'Save' : function() {
								self.saveOrUpdateQuickDataSource();
							},
							'Close' : function() {
								closeDialog('quickDataSourceDialog');
							}
						}
					});
				};
				
				self.deleteQuickDataSource = function(item, event) {
					
					if (window.confirm("Are your sure to DELETE it?")) {
						
						$.ajax({
							url : 'deleteQuickDataSource.action',
							method : 'POST',
							data : {
								quickDataSourceId : item.id
							},
							success : function(data) {
								handleStanderdResponse(data);
								
								if(isOK(data)) {
									self.searchQuickDataSource();
								}
							}
						});
					}
				};
			};

			var quickDataSourceDataModel = new QuickDataSourceDataModel();

			var $quickDataSourceDataModelContainer = $('#QuickDataSourceDataModelContainer')[0];
			
			ko.applyBindings(quickDataSourceDataModel, $quickDataSourceDataModelContainer);
		});

		function activeCurrentMenuItem() {
			$('#QuickDataSourceLink').addClass('active');
		}
	</script>
</body>
</html>