package com.example.administrator.myapptextttttttt.utils.popupwindow.citylinkage;

import java.util.List;

public class CityModel {
	private CityBeant cityBean;
	private List<DistrictModel> districtList;

	public CityModel() {
		super();
	}

	public CityBeant getCityBean() {
		return cityBean;
	}

	public List<DistrictModel> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<DistrictModel> districtList) {
		this.districtList = districtList;
	}

	public void setCityBean(CityBeant cityBean) {
		this.cityBean = cityBean;
	}

	@Override
	public String toString() {
		return "CityModel{" +
				"cityBean=" + cityBean +
				", districtList=" + districtList +
				'}';
	}


}
