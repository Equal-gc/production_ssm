package com.megagao.production.ssm.domain.vo;

import com.megagao.production.ssm.domain.DeviceFault;

public class DeviceFaultVO extends DeviceFault {
	
	private String deviceName;

	private String companyId;
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}