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
<title>Menus</title>
<s:include value="/jsps/common/head.jsp" />

</head>
<body>
	<s:include value="/jsps/common/brand.jsp" />
	<s:include value="/jsps/common/menu.jsp" />
	<section class="mainbg">
		<div class="container" id="MenuDataModelContainer">
			<div class="row">
				<div style="color: red">
					<s:fielderror />
				</div>
				<div id="menuDialog" title="Menu Management" style="display: none" data-bind="with : selectedMenu">
					<div class="row">
						<div class="six columns">
							<label>Display Title</label>
							<input type="text" data-bind="value : title" />
						</div>
						<div class="six columns">
							<label>URL</label>
							<input type="text" data-bind="value : url" />
						</div>
					</div>
					<div class="row">
						<div class="six columns">
							<label>Element Id</label>
							<input type="text" data-bind="value : elementId" />
						</div>
						<div class="six columns">
							<label>Element Id</label>
							<label class="input-checkbox">
								<input type="checkbox" data-bind="checked : visible" /> Shown on the Menu tab
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>Search Menu</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content" data-bind="with : menuSearch">
								<div class="row">
									<div class="three columns">
										<label>Display Title</label>
											<input type="text" data-bind="value : title" />
									</div>
									<div class="three columns">
										<label>URL</label>
											<input type="text" data-bind="value : url" />
									</div>
							</div>
							
							<div class="row">
								<a title="Search Menu" data-bind="click : $root.searchMenu" href="#" class="small blue button">Seach Menu</a>
							</div>
						</div>
					</div>	
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>Menu List</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content">
						
								<div class="row" style="margin-bottom: 5px">
									<div class="nine columns">
										Find <span class="label" data-bind="text : $root.totalCount"></span> records, <span class="label" data-bind="text : $root.totalPageCount"></span> pages.
									</div>
									<div class="three columns">
										<a title="add attribute" data-bind="click : $root.openManageMenuDialog" href="#" class="right" style="margin-right: 10px"><i class="small icon-plus icon-green"></i></a>
									</div>
								</div>
								<div class="row">
									<table class="dataTable">
										<thead>
											<tr>
												<th style="text-align: center">Display Title</th>
												<th style="text-align: center">URL</th>
												<th style="text-align: center">Element Id</th>
												<th style="text-align: center">Element Id</th>
												<th></th>
											</tr>
										</thead>
										<tbody data-bind="foreach : menuList">
											<tr>
												<td style="text-align: center" data-bind="text : title"></td>
												<td style="text-align: center" data-bind="text : url"></td>
												<td style="text-align: center" data-bind="text : elementId"></td>
												<td style="text-align: center" data-bind="text : visible"></td>
												<td style="text-align: center">
													<a title="update menu" data-bind="click : $root.openManageMenuDialog" style="margin-left: 10px;" href="#"><i class="icon-pencil small icon-blue"></i></a>
													<a title="delete menu" data-bind="click : $root.deleteMenu" style="margin-left: 10px;" href="#"><i class="icon-trash small icon-red"></i></a>
												</td>
											</tr>
										</tbody>
									</table>
									<br>
								</div>
								<div class="row" id="menuPageNavigation"></div>
						</div>
					</div>

				</div>

			</div>
		</div>
	</section>
	<s:include value="/jsps/common/footer.jsp" />
	<script src="/TadpoleSMS/js/vo/Menu.js"></script>
	<script src="/TadpoleSMS/js/vo/search/MenuSearch.js"></script>
	<script>
		$(document).ready(function() {

			var MenuDataModel = function() {

				var self = this;
				
				self.selectedMenu = ko.observable(new Menu());
				self.menuList = ko.observableArray([]);
				self.totalCount = ko.observable(0);
				self.totalPageCount = ko.observable(0);
				self.currentIndex = ko.observable(1);
				self.menuSearch = ko.observable(new MenuSearch());
				self.searchMenu = function() {
					
					$.ajax({
						url : 'loadMenus.action',
						data : {currentIndex : self.currentIndex(),
								menuSearchVoJson : JSON.stringify(self.menuSearch())
								},
						success : function(data) {
							
							if (data && data.object && data.object.elements) {
								self.menuList(data.object.elements);
								self.totalCount(data.object.total);
								self.totalPageCount(data.object.totalPages);
								$('#menuPageNavigation').pagination(
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
							}
						}
					});
				};
				
				self.searchMenu();
				
				self.pageSelectCallback = function(page_index, jq){
        				self.currentIndex(page_index + 1);
        				self.searchMenu();
        				return false;
				};
				
				self.saveOrUpdateMenu = function() {
					
					$.ajax({
						url : 'saveOrUpdateMenu.action',
						method : 'POST',
						data : {
							menuJson : JSON.stringify(self.selectedMenu())
						},
						success : function(data) {
							handleStanderdResponse(data);
							
							if(data.object && data.object.id) {
								self.selectedMenu(data.object);
								closeDialog('menuDialog');
								
								self.searchMenu();
							}
						}
					});	
					
				};
				
				self.openManageMenuDialog = function(item, event) {
					
					if(item.id) {
						self.selectedMenu(item);
					} else {
						self.selectedMenu(new Menu());
					}
					
					$('#menuDialog').dialog({
						modal : true,
						width : 1191,
						height : 658,
						open : function(e) {
							changeButtonStyleForPopup(e);
						},
						
						buttons : {
							'Save' : function() {
								self.saveOrUpdateMenu();
							},
							'Close' : function() {
								closeDialog('menuDialog');
							}
						}
					});
				};
				
				self.deleteMenu = function(item, event) {
					
					if (window.confirm("Are your sure to DELETE it?")) {
						
						$.ajax({
							url : 'deleteMenu.action',
							method : 'POST',
							data : {
								menuId : item.id
							},
							success : function(data) {
								handleStanderdResponse(data);
								
								if(isOK(data)) {
									self.searchMenu();
								}
							}
						});
					}
				};
			};

			var menuDataModel = new MenuDataModel();

			var $menuDataModelContainer = $('#MenuDataModelContainer')[0];
			
			ko.applyBindings(menuDataModel, $menuDataModelContainer);
		});

		function activeCurrentMenuItem() {
			$('#MenuLink').addClass('active');
		}
	</script>
</body>
</html>