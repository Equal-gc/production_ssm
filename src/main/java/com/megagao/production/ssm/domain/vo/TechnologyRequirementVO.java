package com.megagao.production.ssm.domain.vo;


import com.megagao.production.ssm.domain.TechnologyRequirement;

public class TechnologyRequirementVO extends TechnologyRequirement{
	
	private String technologyName;

	private String companyId;

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}