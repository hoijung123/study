package com.mycom.bitBatch.biz.dao;

public class ComEntity {

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	String currency = "";
	String date = "";
	public Long getBuy_price() {
		return buy_price;
	}

	String status = "";
	Long opening_price = null;
	Long closing_price = null;
	Long min_price = null;
	Long max_price = null;
	Long average_price = null;
	Long units_traded = null;
	Long volume_1day = null;
	Long volume_7day = null;
	Long sell_price = null;

	public void setOpening_price(Long opening_price) {
		this.opening_price = opening_price;
	}

	public void setClosing_price(Long closing_price) {
		this.closing_price = closing_price;
	}

	public void setMin_price(Long min_price) {
		this.min_price = min_price;
	}

	public void setMax_price(Long max_price) {
		this.max_price = max_price;
	}

	public void setAverage_price(Long average_price) {
		this.average_price = average_price;
	}

	public void setUnits_traded(Long units_traded) {
		this.units_traded = units_traded;
	}

	public void setVolume_1day(Long volume_1day) {
		this.volume_1day = volume_1day;
	}

	public void setVolume_7day(Long volume_7day) {
		this.volume_7day = volume_7day;
	}

	public void setSell_price(Long sell_price) {
		this.sell_price = sell_price;
	}

	Long buy_price = null;

	public Long getOpening_price() {
		return opening_price;
	}

	public Long getClosing_price() {
		return closing_price;
	}

	public Long getMin_price() {
		return min_price;
	}

	public Long getMax_price() {
		return max_price;
	}

	public Long getAverage_price() {
		return average_price;
	}

	public Long getUnits_traded() {
		return units_traded;
	}

	public Long getVolume_1day() {
		return volume_1day;
	}

	public Long getVolume_7day() {
		return volume_7day;
	}

	public Long getSell_price() {
		return sell_price;
	}

	public void setBuy_price(Long buy_price) {
		this.buy_price = buy_price;
	}

}
