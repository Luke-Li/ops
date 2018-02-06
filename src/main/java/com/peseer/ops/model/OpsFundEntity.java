package com.peseer.ops.model;

import com.peseer.ops.model.base.BaseOpsFundEntity;

/**
 * 基金映射关系表（新闻披露的基金名称 同 真实的企业实体相映射）
 */
@SuppressWarnings("serial")
public class OpsFundEntity extends BaseOpsFundEntity<OpsFundEntity> {
	public static final OpsFundEntity dao = new OpsFundEntity();
}
