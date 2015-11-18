package com.soufun.test.entity;

import java.io.Serializable;

/**
 * 监理节点验收接口的实体类
 * 
 * @author soufun
 * 
 */
public class NodeInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 是否成功:0:失败，1成功 */
	public String issuccess;
	/** 错误提示 */
	public String errormessage;
	/** 当前是否有验收节点0：没有，1：有 */
	public String ishasaccept;
	/** 当前待验收节点id */
	public String nowacceptid;
	/** 当前待验收节点名称 */
	public String nowacceptname;
	/** 待验收日期 */
	public String nowacceptdate;
	/** 验收节点id */
	public String acceptid;
	/** 验收节点名称 */
	public String acceptname;
	/** 验收节点状态0:未验收1：已验收 */
	public String type;
	/** 验收日期 */
	public String acceptdate;
	/** 验收距离天数 */
	public String datenum;

}
