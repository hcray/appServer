package com.krakentouch.server.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Stages")
@XmlType(name = "StageBeans", propOrder = {"stages" })
public class StageBeans {
	
	@XmlElement(name="Stage", required = true)
	private List<StageBean> stages;

	public List<StageBean> getStages() {
		return stages;
	}

	public void setStages(List<StageBean> stages) {
		this.stages = stages;
	}
	
}
