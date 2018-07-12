package com.megagao.production.ssm.domain.vo;

import com.megagao.production.ssm.domain.UnqualifyApply;

public class UnqualifyApplyVO extends UnqualifyApply {
	private String productName;

	private String empName;

	private String companyId;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

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
