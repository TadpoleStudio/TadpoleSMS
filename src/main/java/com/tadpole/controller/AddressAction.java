package com.tadpole.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.tadpole.entity.Address;
import com.tadpole.service.AddressService;
import com.tadpole.vo.ResponseVo;

public class AddressAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "AddressService")
	private AddressService addressService;

	public String saveOrUpdateAddress() {

		String addressJson = getParameter("addressJson");

		if (StringUtils.isEmpty(addressJson)) {
			setResponse(ResponseVo.newFailMessage("Bad request : no data found to save or update."));

			return SUCCESS;
		}

		Address address = (Address)JSONObject.toBean(JSONObject.fromObject(addressJson), Address.class);

		addressService.saveOrUpdateAddress(address);

		return SUCCESS;
	}

}
