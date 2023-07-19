package com.namnd.rest.webservices.restfullwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Some Bean meaning
 * @author Ngs-MT597
 *
 */
//@JsonIgnoreProperties({"filter1", "filter2"})
@JsonFilter("SomeFilterHere")
public class SomeBean {
	private String filter1;
//	@JsonIgnore
	private String filter2;
	private String filter3;

	public SomeBean(String filter1, String filter2, String filter3) {
		super();
		this.filter1 = filter1;
		this.filter2 = filter2;
		this.filter3 = filter3;
	}

	public String getFilter1() {
		return filter1;
	}

	public void setFilter1(String filter1) {
		this.filter1 = filter1;
	}

	public String getFilter2() {
		return filter2;
	}

	public void setFilter2(String filter2) {
		this.filter2 = filter2;
	}

	public String getFilter3() {
		return filter3;
	}

	public void setFilter3(String filter3) {
		this.filter3 = filter3;
	}

	@Override
	public String toString() {
		return "SomeBean [filter1=" + filter1 + ", filter2=" + filter2 + ", filter3=" + filter3 + "]";
	}

}
