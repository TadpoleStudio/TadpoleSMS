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
<title>${title}</title>
<s:include value="/jsps/common/head.jsp" />

</head>
<body>
	<s:include value="/jsps/common/brand.jsp" />
	<s:include value="/jsps/common/menu.jsp" />
	<section class="mainbg">
		<div class="container" id="${javaClassName}DataModelContainer">
			<div class="row">
				<div style="color: red">
					<s:fielderror />
				</div>
				<div id="${firstLetterLowerCaseJavaClassName}Dialog" title="${javaClassName} Management" style="display: none" data-bind="with : selected${javaClassName}">
				<#list tadAttributes?chunk(4) as row>
					<div class="row">
					<#list row as cell>
						<div class="three columns">
						<#if cell.required?? && cell.required>
							<label class='required'>${cell.label}</label>
						<#else>
							<label>${cell.label}</label>
						</#if>
								<#if cell.type == 'Boolean'>
							<label class="input-checkbox">
								<input type="checkbox" data-bind="checked : ${cell.name}" /> ${cell.optionText}
							</label>
								<#elseif cell.type == 'Date'>
							<input type="text" data-bind="datepicker : {dateFormat : 'yy-mm-dd'}, value : ${cell.name}" />
								<#elseif cell.dataSourceName?? && cell.dataSourceName != ''>
							<select data-bind="options: $root.${cell.dataSourceName},
                      					       optionsText: 'optionText',
                       					       value: ${cell.name},
                       					       optionsValue : 'optionValue',
                       						   selectedOption : ${cell.name},
                       						   optionsCaption: 'Please select'">
							</select>		
								<#else>
							<input type="text" data-bind="value : ${cell.name}" />
								</#if>			 
						</div>
					</#list>
					</div>
				</#list>	
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>Search ${javaClassName}</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content" data-bind="with : ${firstLetterLowerCaseJavaClassName}Search">
							<#list searchableTadAttributes?chunk(4) as row>
								<div class="row">
								<#list row as cell>
									<div class="three columns">
										<label>${cell.label}</label>
										<#if cell.type == 'Boolean'>
											<label class="input-checkbox">
												<input type="checkbox" data-bind="checked : ${cell.name}" /> ${cell.optionText}
											</label>
										<#elseif cell.type == 'Date'>
											<input type="text" data-bind="datepicker : {dateFormat : 'yy-mm-dd'}, value : ${cell.name}" />
										<#elseif cell.searchTextDataSourceName?? && cell.searchTextDataSourceName != ''>
												<select data-bind="options: $root.${cell.searchTextDataSourceName},
                      					       		optionsText: 'optionText',
                       					       		value: ${cell.name},
                       					      		optionsValue : 'optionValue',
                       						   		selectedOption : ${cell.name},
                       						   		optionsCaption: 'Please select'">
												</select>		
										<#else>
											<input type="text" data-bind="value : ${cell.name}" />
										</#if> 
									</div>
								</#list>
							</div>
							</#list>
							
							<div class="row">
								<a title="Search ${javaClassName}" data-bind="click : $root.search${javaClassName}WithConditions" href="#" class="small blue button">Seach ${javaClassName}</a>
								<a title="Search ${javaClassName}" data-bind="click : $root.resetSearchConditions" href="#" class="small blue button">Reset</a>
							</div>
						</div>
					</div>	
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>${javaClassName} List</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content">
						
								<div class="row" style="margin-bottom: 5px">
									<div class="nine columns">
										Find <span class="label" data-bind="text : $root.totalCount"></span> records, <span class="label" data-bind="text : $root.totalPageCount"></span> pages.
									</div>
									<div class="three columns">
										<a title="add attribute" data-bind="click : $root.openManage${javaClassName}Dialog" href="#" class="right" style="margin-right: 10px"><i class="small icon-plus icon-green"></i></a>
									</div>
								</div>
								<div class="row">
									<table class="infoTable">
										<thead>
											<tr>
												<#list tadAttributes as attr>
												<#if attr.shownInTable?? && attr.shownInTable>
												<th style="text-align: center">${attr.label}</th>
												<#elseif attr.shownInTable?? && attr.shownInTable == false>
												<#else>
												<th style="text-align: center">${attr.label}</th>
												</#if>
												</#list>
												<th></th>
											</tr>
										</thead>
										<tbody data-bind="foreach : ${firstLetterLowerCaseJavaClassName}List">
											<tr>
												<#list tadAttributes as attr>
												<#if attr.shownInTable?? && attr.shownInTable>
												<td style="text-align: center" data-bind="text : ${attr.name}"></td>
												<#elseif attr.shownInTable?? && attr.shownInTable == false>
												<#else>
												<td style="text-align: center" data-bind="text : ${attr.name}"></td>
												</#if>
												</#list>
												<td style="text-align: center">
													<a title="update ${firstLetterLowerCaseJavaClassName}" data-bind="click : $root.openManage${javaClassName}Dialog" style="margin-left: 10px;" href="#"><i class="icon-pencil small icon-blue"></i></a>
													<a title="delete ${firstLetterLowerCaseJavaClassName}" data-bind="click : $root.delete${javaClassName}" style="margin-left: 10px;" href="#"><i class="icon-trash small icon-red"></i></a>
												</td>
											</tr>
										</tbody>
									</table>
									<br>
								</div>
								<div class="row" id="${firstLetterLowerCaseJavaClassName}PageNavigation"></div>
						</div>
					</div>

				</div>

			</div>
		</div>
	</section>
	<s:include value="/jsps/common/footer.jsp" />
	<script src="/TadpoleSMS/js/vo/${javaClassName}.js"></script>
	<script src="/TadpoleSMS/js/vo/search/${javaClassName}Search.js"></script>
	<script>
		$(document).ready(function() {

			var ${javaClassName}DataModel = function() {

				var self = this;
				
				self.selected${javaClassName} = ko.observable(new ${javaClassName}());
				self.${firstLetterLowerCaseJavaClassName}List = ko.observableArray([]);
				self.totalCount = ko.observable(0);
				self.totalPageCount = ko.observable(0);
				self.currentIndex = ko.observable(1);
				self.${firstLetterLowerCaseJavaClassName}Search = ko.observable(new ${javaClassName}Search());
			<#list datasourceNames as datasource>
				self.${datasource} = ko.observableArray([]);
			</#list>
			<#list datasourceNames as datasource>
				$.ajax({ url : '/TadpoleSMS/loadDatasource.action',
						 data : { dataSourceName : '${datasource}' },
						 success : function(data) {
								self.${datasource}(data);
						}
					});
			</#list>
				
				self.resetSearchConditions = function() {
					self.${firstLetterLowerCaseJavaClassName}Search(new ${javaClassName}Search());
				};
				
				self.search${javaClassName} = function() {
				
					for(var key in self.${firstLetterLowerCaseJavaClassName}Search()) {
						if (eval("self.${firstLetterLowerCaseJavaClassName}Search()." + key) == '') {
							eval("self.${firstLetterLowerCaseJavaClassName}Search()." + key + " = null");
						}
					}
					
					$.ajax({
						url : 'load${javaClassName}s.action',
						data : {currentIndex : self.currentIndex(),
								${firstLetterLowerCaseJavaClassName}SearchVoJson : JSON.stringify(self.${firstLetterLowerCaseJavaClassName}Search())
								},
						success : function(data) {
							
							if (data && data.object && data.object.elements) {
							
								self.${firstLetterLowerCaseJavaClassName}List(data.object.elements);
								self.totalCount(data.object.total);
								self.totalPageCount(data.object.totalPages);
								
								$('#${firstLetterLowerCaseJavaClassName}PageNavigation').pagination(
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
									
									self.${firstLetterLowerCaseJavaClassName}List([]);
									self.totalCount(0);
									self.totalPageCount(0);
								}
							}
						}
					});
				};
				
				self.search${javaClassName}();
				
				self.search${javaClassName}WithConditions = function() {
					self.currentIndex(1);
					self.search${javaClassName}();
				};
				
				self.pageSelectCallback = function(page_index, jq){
        				self.currentIndex(page_index + 1);
        				self.search${javaClassName}();
        				return false;
				};
				
				self.saveOrUpdate${javaClassName} = function() {
					
					$.ajax({
						url : 'saveOrUpdate${javaClassName}.action',
						method : 'POST',
						data : {
							${firstLetterLowerCaseJavaClassName}Json : JSON.stringify(self.selected${javaClassName}())
						},
						success : function(data) {
							handleStanderdResponse(data);
							
							if(data.object && data.object.id) {
								self.selected${javaClassName}(data.object);
								closeDialog('${firstLetterLowerCaseJavaClassName}Dialog');
								
								self.search${javaClassName}();
							}
						}
					});	
					
				};
				
				self.openManage${javaClassName}Dialog = function(item, event) {
					
					if(item.id) {
						self.selected${javaClassName}(item);
					} else {
						self.selected${javaClassName}(new ${javaClassName}());
					}
					
					$('#${firstLetterLowerCaseJavaClassName}Dialog').dialog({
						modal : true,
						width : 1191,
						height : 658,
						open : function(e) {
							changeButtonStyleForPopup(e);
						},
						
						buttons : {
							'Save' : function() {
								self.saveOrUpdate${javaClassName}();
							},
							'Close' : function() {
								closeDialog('${firstLetterLowerCaseJavaClassName}Dialog');
							}
						}
					});
				};
				
				self.delete${javaClassName} = function(item, event) {
					
					if (window.confirm("Are your sure to DELETE it?")) {
						
						$.ajax({
							url : 'delete${javaClassName}.action',
							method : 'POST',
							data : {
								${firstLetterLowerCaseJavaClassName}Id : item.id
							},
							success : function(data) {
								handleStanderdResponse(data);
								
								if(isOK(data)) {
									self.search${javaClassName}();
								}
							}
						});
					}
				};
			};

			var ${firstLetterLowerCaseJavaClassName}DataModel = new ${javaClassName}DataModel();

			var $${firstLetterLowerCaseJavaClassName}DataModelContainer = $('#${javaClassName}DataModelContainer')[0];
			
			ko.applyBindings(${firstLetterLowerCaseJavaClassName}DataModel, $${firstLetterLowerCaseJavaClassName}DataModelContainer);
		});

		function activeCurrentMenuItem() {
			$('#${javaClassName}Link').addClass('active');
		}
	</script>
</body>
</html>