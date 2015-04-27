package com.tadpole.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;

import org.apache.commons.lang.StringUtils;

import com.tadpole.vo.search.CustomerSearchVo;

import com.tadpole.entity.Customer;
import com.tadpole.service.CustomerService;
import com.tadpole.vo.ResponseVo;
import com.tadpole.vo.PagedElement;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;

@Component("CustomerAction")
@Scope("prototype")
public class CustomerAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "CustomerService")
	private CustomerService customerService;

	public String saveOrUpdateCustomer() {
	
		try {
			String customerJson = getParameter("customerJson");

			if (StringUtils.isEmpty(customerJson)) {
				setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

				return SUCCESS;
			}
			
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
			
			Customer customer = (Customer)JSONObject.toBean(JSONObject.fromObject(customerJson), Customer.class);

			Customer saved = customerService.saveOrUpdateCustomer(customer);
			
			ResponseVo response = ResponseVo.newSuccessMessage("The customer is successfully saved.");
			response.setObject(saved);
			
			setResponse(response);
			
		} catch (Exception e) {
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		return SUCCESS;
	}

	public String deleteCustomer() {
	
		try {
			String customerId = getParameter("customerId");

			if (StringUtils.isEmpty(customerId)) {
				setResponse(ResponseVo.newFailMessage("Bad request : no data is provided to delete."));

				return SUCCESS;
			}

			customerService.deleteCustomer(customerId);
			
			ResponseVo response = ResponseVo.newSuccessMessage("The customer is successfully deleted.");
			
			setResponse(response);
			
		} catch (Exception e) {
			
			setResponse(ResponseVo.newFailMessage(e.getMessage()));
		}

		return SUCCESS;
	}
	
	public String loadCustomers() {

		try {
			String currentIndex = getParameter("currentIndex");
			if (StringUtils.isEmpty(currentIndex)) {
        		currentIndex = "1";
			}
			
			String customerSearchVoJson = getParameter("customerSearchVoJson");
			
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
			
			CustomerSearchVo customerSearchVo = (CustomerSearchVo)JSONObject.toBean(JSONObject.fromObject(customerSearchVoJson), CustomerSearchVo.class);
			
			Page<Customer> customers = customerService.loadCustomers(currentIndex, customerSearchVo);

			PagedElement<Customer> pageElement = new PagedElement<Customer>(customers);

			ResponseVo response = ResponseVo.newSuccessMessage("loaded successfully.");
			response.setObject(pageElement);

			setResponse(response);

		} catch (Exception e) {
			setResponse(ResponseVo.newFailMessage(e.getMessage()));

		}

		return SUCCESS;
	}
}
