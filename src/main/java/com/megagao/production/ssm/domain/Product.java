package com.megagao.production.ssm.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Product {
	
	//@Size(max=40, message="{id.length.error}")
    private String productId;

	@Size(max=100, message="{name.length.error}")
    private String productName;

	@Size(max=100, message="产品种类的长度限制在100个字符之内")
    private String productType;

    private String image;

    @Size(max=5000, message="{note.length.error}")
    private String note;

   // @Size(max=40, message="{companyId.length.error}")
    private String companyId;

    //@Size(max=40, message="{version.length.error}")
    private String version;

    //@Size(max=40, message="{brand.length.error}")
    private String brand;
    @Size(max=40, message="{brand.length.error}")
    private String cost;
    @Size(max=40, message="{brand.length.error}")
    private String price;

    private Integer status;
    @Min(0)
    private String repertory;
    
	@Min(0)
    private String stocktaking;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getRepertory() {
		return repertory;
	}

	public void setRepertory(String repertory) {
		this.repertory = repertory;
	}

	public String getStocktaking() {
		return stocktaking;
	}

	public void setStocktaking(String stocktaking) {
		this.stocktaking = stocktaking;
	}

}