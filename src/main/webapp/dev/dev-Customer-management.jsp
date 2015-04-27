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
<title>customer</title>
<s:include value="/jsps/common/head.jsp" />

</head>
<body>
	<s:include value="/jsps/common/brand.jsp" />
	<s:include value="/jsps/common/menu.jsp" />
	<section class="mainbg">
		<div class="container" id="CustomerDataModelContainer">
			<div class="row">
				<div style="color: red">
					<s:fielderror />
				</div>
				<div id="customerDialog" title="Customer Management" style="display: none" data-bind="with : selectedCustomer">
					<div class="row">
						<div class="three columns">
							<label class='required'>Name</label>
							<input type="text" data-bind="value : name" />
						</div>
						<div class="three columns">
							<label class='required'>phone</label>
							<input type="text" data-bind="value : phone" />
						</div>
						<div class="three columns">
							<label>fixed phone</label>
							<input type="text" data-bind="value : fixedPhone" />
						</div>
						<div class="three columns">
							<label>Address</label>
							<input type="text" data-bind="value : address" />
						</div>
					</div>
					<div class="row">
						<div class="three columns">
							<label>Sex</label>
							<select data-bind="options: $root.sexDatasource,
                      					       optionsText: 'optionText',
                       					       value: sex,
                       					       optionsValue : 'optionValue',
                       						   selectedOption : sex,
                       						   optionsCaption: 'Please select'">
							</select>		
						</div>
						<div class="three columns">
							<label>Birthday</label>
							<input type="text" data-bind="datepicker : {dateFormat : 'yy-mm-dd'}, value : birthday" />
						</div>
						<div class="three columns">
							<label>Avatar URL</label>
							<input type="text" data-bind="value : avatar" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>Search Customer</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content" data-bind="with : customerSearch">
								<div class="row">
									<div class="three columns">
										<label>Name</label>
											<input type="text" data-bind="value : name" />
									</div>
									<div class="three columns">
										<label>phone</label>
											<input type="text" data-bind="value : phone" />
									</div>
									<div class="three columns">
										<label>Birthday</label>
											<input type="text" data-bind="datepicker : {dateFormat : 'yy-mm-dd'}, value : birthday" />
									</div>
							</div>
							
							<div class="row">
								<a title="Search Customer" data-bind="click : $root.searchCustomerWithConditions" href="#" class="small blue button">Seach Customer</a>
								<a title="Search Customer" data-bind="click : $root.resetSearchConditions" href="#" class="small blue button">Reset</a>
							</div>
						</div>
					</div>	
				</div>
				<div class="row">
					<div class="app-wrapper ui-corner-top">
						<div class="blue module ui-corner-top clearfix">
							<h2>Customer List</h2>
							<h2 class="right"></h2>
						</div>
						<div class="content">
						
								<div class="row" style="margin-bottom: 5px">
									<div class="nine columns">
										Find <span class="label" data-bind="text : $root.totalCount"></span> records, <span class="label" data-bind="text : $root.totalPageCount"></span> pages.
									</div>
									<div class="three columns">
										<a title="add attribute" data-bind="click : $root.openManageCustomerDialog" href="#" class="right" style="margin-right: 10px"><i class="small icon-plus icon-green"></i></a>
									</div>
								</div>
								<div class="row">
									<table class="infoTable">
										<thead>
											<tr>
												<th style="text-align: center">Name</th>
												<th style="text-align: center">phone</th>
												<th style="text-align: center">Sex</th>
												<th style="text-align: center">Birthday</th>
												<th></th>
											</tr>
										</thead>
										<tbody data-bind="foreach : customerList">
											<tr>
												<td style="text-align: center" data-bind="text : name"></td>
												<td style="text-align: center" data-bind="text : phone"></td>
												<td style="text-align: center" data-bind="text : sex"></td>
												<td style="text-align: center" data-bind="text : birthday"></td>
												<td style="text-align: center">
													<a title="update customer" data-bind="click : $root.openManageCustomerDialog" style="margin-left: 10px;" href="#"><i class="icon-pencil small icon-blue"></i></a>
													<a title="delete customer" data-bind="click : $root.deleteCustomer" style="margin-left: 10px;" href="#"><i class="icon-trash small icon-red"></i></a>
												</td>
											</tr>
										</tbody>
									</table>
									<br>
								</div>
								<div class="row" id="customerPageNavigation"></div>
						</div>
					</div>

				</div>

			</div>
		</div>
	</section>
	<s:include value="/jsps/common/footer.jsp" />
	<script src="/TadpoleSMS/js/vo/Customer.js"></script>
	<script src="/TadpoleSMS/js/vo/search/CustomerSearch.js"></script>
	<script>
		$(document).ready(function() {

			var CustomerDataModel = function() {

				var self = this;
				
				self.selectedCustomer = ko.observable(new Customer());
				self.customerList = ko.observableArray([]);
				self.totalCount = ko.observable(0);
				self.totalPageCount = ko.observable(0);
				self.currentIndex = ko.observable(1);
				self.customerSearch = ko.observable(new CustomerSearch());
				self.sexDatasource = ko.observableArray([]);
				$.ajax({ url : '/TadpoleSMS/loadDatasource.action',
						 data : { dataSourceName : 'sexDatasource' },
						 success : function(data) {
								self.sexDatasource(data);
						}
					});
				
				self.resetSearchConditions = function() {
					self.customerSearch(new CustomerSearch());
				};
				
				self.searchCustomer = function() {
				
					for(var key in self.customerSearch()) {
						if (eval("self.customerSearch()." + key) == '') {
							eval("self.customerSearch()." + key + " = null");
						}
					}
					
					$.ajax({
						url : 'loadCustomers.action',
						data : {currentIndex : self.currentIndex(),
								customerSearchVoJson : JSON.stringify(self.customerSearch())
								},
						success : function(data) {
							
							if (data && data.object && data.object.elements) {
							
								self.customerList(data.object.elements);
								self.totalCount(data.object.total);
								self.totalPageCount(data.object.totalPages);
								
								$('#customerPageNavigation').pagination(
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
									
									self.customerList([]);
									self.totalCount(0);
									self.totalPageCount(0);
								}
							}
						}
					});
				};
				
				self.searchCustomer();
				
				self.searchCustomerWithConditions = function() {
					self.currentIndex(1);
					self.searchCustomer();
				};
				
				self.pageSelectCallback = function(page_index, jq){
        				self.currentIndex(page_index + 1);
        				self.searchCustomer();
        				return false;
				};
				
				self.saveOrUpdateCustomer = function() {
					
					$.ajax({
						url : 'saveOrUpdateCustomer.action',
						method : 'POST',
						data : {
							customerJson : JSON.stringify(self.selectedCustomer())
						},
						success : function(data) {
							handleStanderdResponse(data);
							
							if(data.object && data.object.id) {
								self.selectedCustomer(data.object);
								closeDialog('customerDialog');
								
								self.searchCustomer();
							}
						}
					});	
					
				};
				
				self.openManageCustomerDialog = function(item, event) {
					
					if(item.id) {
						self.selectedCustomer(item);
					} else {
						self.selectedCustomer(new Customer());
					}
					
					$('#customerDialog').dialog({
						modal : true,
						width : 1191,
						height : 658,
						open : function(e) {
							changeButtonStyleForPopup(e);
						},
						
						buttons : {
							'Save' : function() {
								self.saveOrUpdateCustomer();
							},
							'Close' : function() {
								closeDialog('customerDialog');
							}
						}
					});
				};
				
				self.deleteCustomer = function(item, event) {
					
					if (window.confirm("Are your sure to DELETE it?")) {
						
						$.ajax({
							url : 'deleteCustomer.action',
							method : 'POST',
							data : {
								customerId : item.id
							},
							success : function(data) {
								handleStanderdResponse(data);
								
								if(isOK(data)) {
									self.searchCustomer();
								}
							}
						});
					}
				};
			};

			var customerDataModel = new CustomerDataModel();

			var $customerDataModelContainer = $('#CustomerDataModelContainer')[0];
			
			ko.applyBindings(customerDataModel, $customerDataModelContainer);
		});

		function activeCurrentMenuItem() {
			$('#CustomerLink').addClass('active');
		}
	</script>
</body>
</html>