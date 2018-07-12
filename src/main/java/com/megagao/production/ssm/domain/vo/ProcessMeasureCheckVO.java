package com.megagao.production.ssm.domain.vo;

import com.megagao.production.ssm.domain.ProcessMeasureCheck;

public class ProcessMeasureCheckVO extends ProcessMeasureCheck{
	private String empName;

	private String companyId;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
