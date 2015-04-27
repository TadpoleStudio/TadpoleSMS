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
<title>SMS</title>
<s:include value="/jsps/common/head.jsp" />

</head>
<body>
	<s:include value="/jsps/common/brand.jsp" />
	<s:include value="/jsps/common/menu.jsp" />
	<section class="mainbg">
		<div class="container" id="SmsTemplateDataModelContainer">
			<div class="row">
				<div style="color: red">
					<s:fielderror />
				</div>
				<div id="smsTemplateDialog" title="SmsTemplate Management" style="display: none" data-bind="with : selectedSmsTemplate">
					<div class="row">
						<div class="three columns">
							<label class='required'>Tag Name</label>
							<input type="text" data-bind="value : name" />
						</div>
						<div class="three columns">
							<label class='required'>Content</label>
							<input type="text" data-bind="value : content" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>Search SmsTemplate</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content" data-bind="with : smsTemplateSearch">
								<div class="row">
									<div class="three columns">
										<label>Tag Name</label>
											<input type="text" data-bind="value : name" />
									</div>
							</div>
							
							<div class="row">
								<a title="Search SmsTemplate" data-bind="click : $root.searchSmsTemplateWithConditions" href="#" class="small blue button">Seach SmsTemplate</a>
								<a title="Search SmsTemplate" data-bind="click : $root.resetSearchConditions" href="#" class="small blue button">Reset</a>
							</div>
						</div>
					</div>	
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>SmsTemplate List</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content">
						
								<div class="row" style="margin-bottom: 5px">
									<div class="nine columns">
										Find <span class="label" data-bind="text : $root.totalCount"></span> records, <span class="label" data-bind="text : $root.totalPageCount"></span> pages.
									</div>
									<div class="three columns">
										<a title="add attribute" data-bind="click : $root.openManageSmsTemplateDialog" href="#" class="right" style="margin-right: 10px"><i class="small icon-plus icon-green"></i></a>
									</div>
								</div>
								<div class="row">
									<table class="infoTable">
										<thead>
											<tr>
												<th style="text-align: center">Tag Name</th>
												<th></th>
											</tr>
										</thead>
										<tbody data-bind="foreach : smsTemplateList">
											<tr>
												<td style="text-align: center" data-bind="text : name"></td>
												<td style="text-align: center">
													<a title="update smsTemplate" data-bind="click : $root.openManageSmsTemplateDialog" style="margin-left: 10px;" href="#"><i class="icon-pencil small icon-blue"></i></a>
													<a title="delete smsTemplate" data-bind="click : $root.deleteSmsTemplate" style="margin-left: 10px;" href="#"><i class="icon-trash small icon-red"></i></a>
												</td>
											</tr>
										</tbody>
									</table>
									<br>
								</div>
								<div class="row" id="smsTemplatePageNavigation"></div>
						</div>
					</div>

				</div>

			</div>
		</div>
	</section>
	<s:include value="/jsps/common/footer.jsp" />
	<script src="/TadpoleSMS/js/vo/SmsTemplate.js"></script>
	<script src="/TadpoleSMS/js/vo/search/SmsTemplateSearch.js"></script>
	<script>
		$(document).ready(function() {

			var SmsTemplateDataModel = function() {

				var self = this;
				
				self.selectedSmsTemplate = ko.observable(new SmsTemplate());
				self.smsTemplateList = ko.observableArray([]);
				self.totalCount = ko.observable(0);
				self.totalPageCount = ko.observable(0);
				self.currentIndex = ko.observable(1);
				self.smsTemplateSearch = ko.observable(new SmsTemplateSearch());
				
				self.resetSearchConditions = function() {
					self.smsTemplateSearch(new SmsTemplateSearch());
				};
				
				self.searchSmsTemplate = function() {
				
					for(var key in self.smsTemplateSearch()) {
						if (eval("self.smsTemplateSearch()." + key) == '') {
							eval("self.smsTemplateSearch()." + key + " = null");
						}
					}
					
					$.ajax({
						url : 'loadSmsTemplates.action',
						data : {currentIndex : self.currentIndex(),
								smsTemplateSearchVoJson : JSON.stringify(self.smsTemplateSearch())
								},
						success : function(data) {
							
							if (data && data.object && data.object.elements) {
							
								self.smsTemplateList(data.object.elements);
								self.totalCount(data.object.total);
								self.totalPageCount(data.object.totalPages);
								
								$('#smsTemplatePageNavigation').pagination(
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
									
									self.smsTemplateList([]);
									self.totalCount(0);
									self.totalPageCount(0);
								}
							}
						}
					});
				};
				
				self.searchSmsTemplate();
				
				self.searchSmsTemplateWithConditions = function() {
					self.currentIndex(1);
					self.searchSmsTemplate();
				};
				
				self.pageSelectCallback = function(page_index, jq){
        				self.currentIndex(page_index + 1);
        				self.searchSmsTemplate();
        				return false;
				};
				
				self.saveOrUpdateSmsTemplate = function() {
					
					$.ajax({
						url : 'saveOrUpdateSmsTemplate.action',
						method : 'POST',
						data : {
							smsTemplateJson : JSON.stringify(self.selectedSmsTemplate())
						},
						success : function(data) {
							handleStanderdResponse(data);
							
							if(data.object && data.object.id) {
								self.selectedSmsTemplate(data.object);
								closeDialog('smsTemplateDialog');
								
								self.searchSmsTemplate();
							}
						}
					});	
					
				};
				
				self.openManageSmsTemplateDialog = function(item, event) {
					
					if(item.id) {
						self.selectedSmsTemplate(item);
					} else {
						self.selectedSmsTemplate(new SmsTemplate());
					}
					
					$('#smsTemplateDialog').dialog({
						modal : true,
						width : 1191,
						height : 658,
						open : function(e) {
							changeButtonStyleForPopup(e);
						},
						
						buttons : {
							'Save' : function() {
								self.saveOrUpdateSmsTemplate();
							},
							'Close' : function() {
								closeDialog('smsTemplateDialog');
							}
						}
					});
				};
				
				self.deleteSmsTemplate = function(item, event) {
					
					if (window.confirm("Are your sure to DELETE it?")) {
						
						$.ajax({
							url : 'deleteSmsTemplate.action',
							method : 'POST',
							data : {
								smsTemplateId : item.id
							},
							success : function(data) {
								handleStanderdResponse(data);
								
								if(isOK(data)) {
									self.searchSmsTemplate();
								}
							}
						});
					}
				};
			};

			var smsTemplateDataModel = new SmsTemplateDataModel();

			var $smsTemplateDataModelContainer = $('#SmsTemplateDataModelContainer')[0];
			
			ko.applyBindings(smsTemplateDataModel, $smsTemplateDataModelContainer);
		});

		function activeCurrentMenuItem() {
			$('#SmsTemplateLink').addClass('active');
		}
	</script>
</body>
</html>