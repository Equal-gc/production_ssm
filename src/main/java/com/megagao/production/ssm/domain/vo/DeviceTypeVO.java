package com.megagao.production.ssm.domain.vo;

import com.megagao.production.ssm.domain.DeviceType;

public class DeviceTypeVO extends DeviceType {
	
	private String deviceTypeIdd;

	private String companyId;

	public String getDeviceTypeIdd() {
		return deviceTypeIdd;
	}

	public void setDeviceTypeIdd(String deviceTypeIdd) {
		this.deviceTypeIdd = deviceTypeIdd;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}