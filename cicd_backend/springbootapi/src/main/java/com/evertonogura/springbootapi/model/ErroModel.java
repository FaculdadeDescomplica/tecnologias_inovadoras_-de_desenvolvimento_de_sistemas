package com.evertonogura.springbootapi.model;

import java.io.Serializable;
import java.util.Objects;

public class ErroModel implements Serializable {
	private static final long serialVersionUID = -8724003738255681407L;
	
	private String detail;
	
	public ErroModel() {}

	public ErroModel(String detail) {
		super();
		this.detail = detail;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(detail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErroModel other = (ErroModel) obj;
		return Objects.equals(detail, other.detail);
	}

	@Override
	public String toString() {
		return "Error [detail=" + detail + "]";
	}
}
