package com.peseer.ops.model;

import com.peseer.ops.model.base.BaseOpsOrgExtendEntity;

/**
 * 机构对应的企业实体以及子孙公司（含基金）
 */
@SuppressWarnings("serial")
public class OpsOrgExtendEntity extends BaseOpsOrgExtendEntity<OpsOrgExtendEntity> {
	public static final OpsOrgExtendEntity dao = new OpsOrgExtendEntity();
}
