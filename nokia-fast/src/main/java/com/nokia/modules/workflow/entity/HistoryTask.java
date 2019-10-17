package com.nokia.modules.workflow.entity;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryTask {
	private String id;

	private String name;

	private String actProcInstId;

	private String actReProcdefId;

	private Date startTime;

	private Date endTime;

	private String deleteReason;

	private String comment;

	private Integer status;

	private String owner;

	private String assignee;
}
