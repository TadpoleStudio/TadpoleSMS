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

<link rel="stylesheet" href="/TadpoleSMS/js/syntaxHighlighter/css/shCore.css">
<link rel="stylesheet" href="/TadpoleSMS/js/syntaxHighlighter/css/shThemeEclipse.css">
<title>系统开发</title>
<s:include value="/jsps/common/head.jsp" />

</head>
<body>
	<s:include value="/jsps/common/brand.jsp" />
	<s:include value="/jsps/common/menu.jsp" />
	<section class="mainbg">
		<div class="container" id="tadFunctionDataModelContainer">
			<div class="row">
				<div class="three columns">
							 <select data-bind="options: $root.tableNames,
                       							value: tableNameSearch,
                       							selectedOption : tableNameSearch,
                       							optionsCaption: 'Please select the table to load'"
								class="required">
							</select>
				</div>
				<div class="three columns">
					<a data-bind="click : $root.loadSingleFunction" href="#" class="small blue button">Load</a>
					
					<a data-bind="click : $root.removeSingleFunction" href="#" class="small red button" style="margin-left : 20px">Delete</a>
				</div>
				<div class="three columns"></div>
				<div class="three columns">

					<a data-bind="click : $root.reloadPage" href="#" class="small blue button right">Create New Function</a>
				</div>
			</div>
			<div class="row">
				<div style="color: red">
					<s:fielderror />
				</div>
				<div id="tadFunctionDialog" title="管理" style="display: none" data-bind="with : selectedTadAttribute">
					<div class="row">
						<div class="six columns">
							<label>Name</label> <input type="text" data-bind="value : name" />
						</div>
						<div class="three columns">
							<label>Type</label> 
							<select data-bind="options: $root.dataTypes,
                      					   optionsText: 'optionText',
                       											   value: type,
                       											   optionsValue : 'optionValue',
                       											   selectedOption : type,
                       											   optionsCaption: 'Please select'"
								class="required">
							</select>
						</div>
						<div class="three columns">
							<label>Long Text?</label>
							<label class="input-checkbox">
								<input type="checkbox" data-bind="checked : textLong" />
							Text is Long</label>
						</div>
					</div>
					<div class="row">
						<div class="six columns">
							<label>Label</label> <input type="text" data-bind="value : label" />
						</div>
						
						<div class="six columns">
							<label>Option Text</label>
							<input type="text" data-bind="value : optionText" />
						</div>
					</div>
					<div class="row">
						<div class="six columns">
							<label>Search</label>
							<label class="input-checkbox">
								<input type="checkbox" data-bind="checked : searchable" />
							Searchable</label>
						</div>
						<div class="six columns">
							<label>Data source name</label>
							<input type="text" data-bind="value : dataSourceName" />
						</div>
					</div>
					<div class="row">
						<div class="six columns">
							<label>Shown in table list</label>
							<label class="input-checkbox">
								<input type="checkbox" data-bind="checked : shownInTable" />
							Shown</label>
						</div>
						<div class="six columns">
							<label>Search Text Dropdown Data Source</label>
							<input type="text" data-bind="value : searchTextDataSourceName" />
						</div>
					</div>
					<form>
						<fieldset>
							<legend>Validations</legend>
							<div class="row">
								<div class="six columns">
									<label>Required</label> <label class="input-checkbox"> <input type="checkbox" data-bind="checked : required" /> Required Field
									</label>
								</div>
								<div class="six columns">
									<label>Unique</label> <label class="input-checkbox"> <input type="checkbox" data-bind="checked : isUnique" /> Unique Field</label>
								</div>
							</div>
						</fieldset>
					</form>

				</div>
				<div class="app-wrapper ui-corner-top" title="管理">
					<div class="blue module ui-corner-top clearfix">
						<h2>Function Development</h2>
						<h2 class="right"></h2>
					</div>
					<div class="content">
						<h5>First step : create basic TadpoleSMS information.</h5>
						<br>
						<form id="tadFunctionForm">
							<fieldset>
								<legend>基础信息</legend>
								<div data-bind="with : tadFunction">
									<div class="row">
										<div class="four columns">
											<label class="required">Table Name</label> <input class="required" type="text" data-bind="value : tableName" />
										</div>
										<div class="four columns">
											<label class="required">JPA Entity class Name</label> <input class="required" type="text" data-bind="value : entityName" />
										</div>
										<div class="four columns">
											<label class="required">Namespace</label> <input class="required" type="text" data-bind="value : strutsNamespace" />
										</div>
									</div>
									<div class="row">
										<div class="four columns">
											<label class="required">Title</label> <input class="required" type="text" data-bind="value : title" />
										</div>
										<div class="four columns">
											<label class="required">Menu Title</label> <input type="text" class="required" data-bind="value : menuTitle" />
										</div>
										<div class="four columns"></div>
									</div>
								</div>
							</fieldset>
						</form>

						<div>
							<a title="Save" data-bind="click : saveOrUpdateTadFunction" href="#" class="small blue button">Save basic information</a>
						</div>
						<hr>

						<h5>Second step : Assign attributes.</h5>
						<br>
						<form>
							<fieldset>
								<legend>Attributes</legend>
								<div class="row">
									<div class="nine columns"></div>
									<div class="three columns">
										<a title="add attribute" data-bind="click : $root.manageAttribute" href="#" class="right" style="margin-right: 10px"><i class="small icon-plus icon-green"></i></a>
									</div>
								</div>
								<div class="row">
									<table class="dataTable">
										<thead>
											<tr>
												<th style="text-align: center">Name</th>
												<th style="text-align: center">Type</th>
												<th style="text-align: center">Label</th>
												<th></th>
											</tr>
										</thead>
										<tbody data-bind="foreach : attributeDefinitions">
											<tr>
												<td style="text-align: center" data-bind="text : name"></td>
												<td style="text-align: center" data-bind="text : type"></td>
												<td style="text-align: center" data-bind="text : label"></td>
												<td style="text-align: center">
												<a title="update attr" data-bind="click : $root.manageAttribute" style="margin-left: 10px;" href="#"><i class="icon-pencil small icon-blue"></i></a>
												<a title="delete attr" data-bind="click : $root.removeAttribute" style="margin-left: 10px;" href="#"><i class="icon-trash small icon-red"></i></a></td>
											</tr>
										</tbody>
									</table>
									<br>
								</div>
							</fieldset>
						</form>
						
						<hr>

						<h5>Third step : Generate basic TadpoleSMS code</h5>
						<br>
								<div>
									<a title="Generate Code" data-bind="click : $root.generateCode.bind($data, 'all')" href="#" class="small blue button">Generate Code</a>
									<a title="Generate Code" data-bind="click : $root.generateCode.bind($data, 'rest')" href="#" class="small blue button">Generate Restful Code</a>
								</div>
						<br>
						<form data-bind="with : tadFunction">
							<fieldset>
								<legend>Code</legend>
								
								<div class="row">
									<label>JPA Entity code	&nbsp; <span data-bind="text : jpaEntityFilePath, visible : jpaEntityFilePath "></span></label> 
									<pre data-bind="text : jpaEntityCode" class="brush: java;"></pre>
								</div>
								<div class="row">
									<label>JS entity Code &nbsp; <span data-bind="text : jsVoFilePath, visible : jsVoFilePath "></span></label>
									<pre data-bind="text : jsVoCode" class="brush: js;"></pre>
								</div>
								<div class="row">
									<label>Repository additional code</label>
									<textarea data-bind="value:repositoryAdditionalCode"></textarea>
								</div>
								<div class="row">
									<label>Repository code &nbsp; <span data-bind="text : repositoryFilePath, visible : repositoryFilePath "></span></label>
									<pre data-bind="text : repositoryCode" class="brush: java;"></pre>
								</div>
								<div class="row">
									<label>Service Interface code &nbsp; <span data-bind="text : serviceInterfaceFilePath, visible : serviceInterfaceFilePath "></span></label>
									<pre data-bind="text : serviceInterfaceCode" class="brush: java;"></pre>
								</div>
								<div class="row">
									<label>Service Implementation code &nbsp; <span data-bind="text : serviceImplementationFilePath, visible : serviceImplementationFilePath "></span></label>
									<pre data-bind="text : serviceImplementationCode" class="brush: java;"></pre>
								</div>
								<div class="row">
									<label>Action Class code &nbsp; <span data-bind="text : actionFilePath, visible : actionFilePath "></span></label>
									<pre data-bind="text : actionCode" class="brush: java;"></pre>
								</div>
								<div class="row">
									<label>Struts Configuration &nbsp; <span data-bind="text : strutsConfigurationgFilePath, visible : strutsConfigurationgFilePath "></span></label>
									<pre data-bind="text : strutsConfigurationgCode" class="brush: xml;"></pre>
								</div>
								<div class="row">
									<label>Jsp Code &nbsp; <span data-bind="text : jspFilePath, visible : jspFilePath "></span></label>
									<pre data-bind="text : jspCode" class="brush: xml;"></pre>
								</div>
								<div class="row">
									<label>Web services Code &nbsp; <span data-bind="text : restFilePath, visible : restFilePath "></span></label>
									<pre data-bind="text : restCode" class="brush: java;"></pre>
								</div>
								
							</fieldset>
						</form>	
					</div>
				</div>
			</div>
		</div>
	</section>
	<s:include value="/jsps/common/footer.jsp"/>
	<script src="/TadpoleSMS/js/vo/TadFunction.js"></script>
	<script src="/TadpoleSMS/js/vo/TadAttribute.js"></script>
	<script src="/TadpoleSMS/js/syntaxHighlighter/js/shCore.js"></script>
	<script src="/TadpoleSMS/js/syntaxHighlighter/js/shBrushCss.js"></script>
	<script src="/TadpoleSMS/js/syntaxHighlighter/js/shBrushJScript.js"></script>
	<script src="/TadpoleSMS/js/syntaxHighlighter/js/shBrushJava.js"></script>
	<script src="/TadpoleSMS/js/syntaxHighlighter/js/shBrushXml.js"></script>
	
	<script>
		$(document).ready(function() {
			
			SyntaxHighlighter.all();
			
			var TadFunctionDataModel = function() {
				
				var self = this;
				
				self.tableNameSearch = ko.observable('');
				
				self.tadFunction = ko.observable(new TadFunction());
				self.selectedTadAttribute = ko.observable(new TadAttribute());
				self.attributeDefinitions = ko.observableArray([]);
				self.dataTypes = ko.observableArray([]);
				self.tableNames = ko.observableArray([]);
				
				$.ajax({
					url : '/TadpoleSMS/findDropDownDataSouce.action',
					data : {identityType : 'jpa_data_type'},
					success : function(data) {
						self.dataTypes(data);
					}
				});
				
				$.ajax({
					url : '/TadpoleSMS/dev/loadAllTableNames.action',
					success : function(data) {
						self.tableNames(data.object);
					}
				});
				
				self.reloadPage = function() {
					window.location.assign('/TadpoleSMS/dev/develop.action');
				};
				
				self.loadSingleFunction = function() {
					
					if(self.tableNameSearch()) {
						
						$.ajax({
							url : '/TadpoleSMS/dev/loadSingleFunction.action',
							data : {tableNameSearch : self.tableNameSearch()},
							success : function(data) {
								self.tadFunction(data.object);
								
								handleStanderdResponse(data);
								
								self.loadFunctionAttrites();
								
								SyntaxHighlighter.highlight();
							}
						});
					}
					
				};
				self.removeSingleFunction = function() {
					var functionId = self.tadFunction().id;
					
					if(window.confirm('Are you sure to delete?')) {
						
						$.ajax({
							url : 'removeSingleFunction.action',
							method : 'POST',
							data : {
								functionId : functionId
							},
							success : function(data) {
								handleStanderdResponse(data);
								
								if (isOK(data)) {
									self.reloadPage();
								}
							}
						});	
						
					}	
					
				};
				
				self.loadFunctionAttrites = function() {
					var functionId = self.tadFunction().id;
					if(functionId) {
						$.ajax({
							url : '/TadpoleSMS/dev/loadFunctionAttrites.action',
							data : {functionId : functionId},
							success : function(data) {
								self.attributeDefinitions(data.object);
							}
						});
					}
				};
				
				self.saveOrUpdateTadFunction = function(item, event) {
					
					$.ajax({
						url : 'saveOrUpdateTadFunction.action',
						method : 'POST',
						data : {
							tadFunctionJson : JSON.stringify(self.tadFunction()),
							attributeDefinitions : JSON.stringify(self.attributeDefinitions())
						},
						success : function(data) {
							handleStanderdResponse(data);
							
							if(data.object && data.object.id) {
								self.tadFunction(data.object);
							}
						}
					});	
				};
				
				self.manageAttribute = function(item, event) {
					
					if (item.id) {
						self.selectedTadAttribute(item);
					} else {
						self.selectedTadAttribute(new TadAttribute());
					}
					
					$('#tadFunctionDialog').dialog({
						modal : true,
						width : 1046,
						height : 888,
						open : function(e) {
							changeButtonStyleForPopup(e);
						},
						
						buttons : {
							'Save Attribute' : function() {
								self.saveOrUpdateTadAttribute();
							},
							'Close Window' : function() {
								closeDialog('tadFunctionDialog');
							}
						}
					});
				};
				
				self.saveOrUpdateTadAttribute = function(item, event) {
					
					var functionId = self.tadFunction().id;
					if(functionId) {
						
						self.selectedTadAttribute().functionId = functionId;
						
						$.ajax({
							url : 'saveOrUpdateTadAttribute.action',
							method : 'POST',
							data : {
								tadAttributeJson : JSON.stringify(self.selectedTadAttribute()),
							},
							success : function(data) {
								handleStanderdResponse(data);
								//self.attributeDefinitions.push(self.selectedTadAttribute());
								
								self.loadFunctionAttrites();
							}
						});	
					}
				};
				
				self.generateCode = function(codeType, item, event) {
					
					var functionId = self.tadFunction().id;
					if(functionId) {
						
						self.selectedTadAttribute().functionId = functionId;
						
						$.ajax({
							url : 'generateCode.action',
							method : 'POST',
							data : {
								functionId : functionId,
								codeType : codeType
							},
							success : function(data) {
								handleStanderdResponse(data);
								
								if(data.object && data.object.id) {
									self.tadFunction(data.object);
									
									SyntaxHighlighter.highlight();
								}
							}
						});	
					}
				};
				
				self.removeAttribute = function(item, event) {
					
					if(window.confirm("sure to delete?")) {
						
						$.ajax({
							url : 'removeAttribute.action',
							method : 'POST',
							data : {
								attributeId : item.id,
							},
							success : function(data) {
								handleStanderdResponse(data);
								
								if(isOK(data)) {
									self.loadFunctionAttrites();
								}
							}
						});	
					}
				};
			};
			
			var tadFunctionDataModel = new TadFunctionDataModel();

			var $tadFunctionDataModelContainer = $('#tadFunctionDataModelContainer')[0];
			ko.applyBindings(tadFunctionDataModel, $tadFunctionDataModelContainer);

		});

		function activeCurrentMenuItem() {
			$('#develop').addClass('active');
		}
	</script>
</body>
</html>
