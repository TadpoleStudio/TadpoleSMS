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
<title>Struts namespace</title>
<s:include value="/jsps/common/head.jsp" />

</head>
<body>
	<s:include value="/jsps/common/brand.jsp" />
	<s:include value="/jsps/common/menu.jsp" />
	<section class="mainbg">
		<div class="container" id="TadStrutsNamespaceDataModelContainer">
			<div class="row">
				<div style="color: red">
					<s:fielderror />
				</div>
				<div id="tadStrutsNamespaceDialog" title="TadStrutsNamespace Management" style="display: none" data-bind="with : selectedTadStrutsNamespace">
					<div class="row">
						<div class="six columns">
							<label>Name</label> 
							<input type="text" data-bind="value : name" />
						</div>
						<div class="six columns">
							<label>Created By</label> 
							<input type="text" data-bind="value : createBy" />
						</div>
					</div>
					<div class="row">
						<div class="six columns">
							<label>Updated By</label> 
							<input type="text" data-bind="value : updatedBy" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>TadStrutsNamespace List</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content">
						
							<div class="row">
									<div class="nine columns"></div>
									<div class="three columns">
										<a title="add attribute" data-bind="click : $root.openManageTadStrutsNamespaceDialog" href="#" class="right" style="margin-right: 10px"><i class="small icon-plus icon-green"></i></a>
									</div>
								</div>
								<div class="row">
									<table class="dataTable">
										<thead>
											<tr>
												<th style="text-align: center">Name</th>
												<th style="text-align: center">Created By</th>
												<th style="text-align: center">Updated By</th>
												<th></th>
											</tr>
										</thead>
										<tbody data-bind="foreach : tadStrutsNamespaceList">
											<tr>
												<td style="text-align: center" data-bind="text : name"></td>
												<td style="text-align: center" data-bind="text : createBy"></td>
												<td style="text-align: center" data-bind="text : updatedBy"></td>
												<td style="text-align: center">
													<a title="update tadStrutsNamespace" data-bind="click : $root.openManageTadStrutsNamespaceDialog" style="margin-left: 10px;" href="#"><i class="icon-pencil small icon-blue"></i></a>
													<a title="delete tadStrutsNamespace" data-bind="click : $root.deleteTadStrutsNamespace" style="margin-left: 10px;" href="#"><i class="icon-trash small icon-red"></i></a>
												</td>
											</tr>
										</tbody>
									</table>
									<br>
								</div>
						</div>
					</div>

				</div>

			</div>
		</div>
	</section>
	<s:include value="/jsps/common/footer.jsp" />
	<script src="/TadpoleSMS/js/vo/TadStrutsNamespace.js"></script>

	<script>
		$(document).ready(function() {

			var TadStrutsNamespaceDataModel = function() {

				var self = this;
				
				self.selectedTadStrutsNamespace = ko.observable(new TadStrutsNamespace());
				self.tadStrutsNamespaceList = ko.observableArray([]);
				
				self.searchTadStrutsNamespace = function() {
					
					$.ajax({
						url : 'loadTadStrutsNamespaces.action',
						success : function(data) {
							
							if (data && data.object && data.object.elements) {
								self.tadStrutsNamespaceList(data.object.elements);
							}
							
							handleStanderdResponse(data);
							
						}
					});
				};
				
				self.searchTadStrutsNamespace();
				
				self.saveOrUpdateTadStrutsNamespace = function() {
					
					$.ajax({
						url : 'saveOrUpdateTadStrutsNamespace.action',
						method : 'POST',
						data : {
							tadStrutsNamespaceJson : JSON.stringify(self.selectedTadStrutsNamespace())
						},
						success : function(data) {
							handleStanderdResponse(data);
							
							if(data.object && data.object.id) {
								self.selectedTadStrutsNamespace(data.object);
							}
						}
					});	
					
				};
				
				self.openManageTadStrutsNamespaceDialog = function(item, event) {
					
					if(item.id) {
						self.selectedTadStrutsNamespace(item);
					} else {
						self.selectedTadStrutsNamespace(new TadStrutsNamespace());
					}
					
					$('#tadStrutsNamespaceDialog').dialog({
						modal : true,
						width : 700,
						height : 350,
						open : function(e) {
							changeButtonStyleForPopup(e);
						},
						
						buttons : {
							'Save' : function() {
								self.saveOrUpdateTadStrutsNamespace();
							},
							'Close' : function() {
								closeDialog('tadStrutsNamespaceDialog');
							}
						}
					});
				};
				
			};

			var tadStrutsNamespaceDataModel = new TadStrutsNamespaceDataModel();

			var $tadStrutsNamespaceDataModelContainer = $('#TadStrutsNamespaceDataModelContainer')[0];
			
			ko.applyBindings(tadStrutsNamespaceDataModel, $tadStrutsNamespaceDataModelContainer);
		});

		function activeCurrentMenuItem() {
			$('#TadStrutsNamespaceLink').addClass('active');
		}
	</script>
</body>
</html>