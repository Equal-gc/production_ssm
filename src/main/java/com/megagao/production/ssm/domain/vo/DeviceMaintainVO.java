package com.megagao.production.ssm.domain.vo;

import com.megagao.production.ssm.domain.DeviceMaintain;

public class DeviceMaintainVO extends DeviceMaintain{
	
	private String deviceMaintainEmp;

	private String companyId;

	public String getDeviceMaintainEmp() {
		return deviceMaintainEmp;
	}

	public void setDeviceMaintainEmp(String deviceMaintainEmp) {
		this.deviceMaintainEmp = deviceMaintainEmp;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	
	
}