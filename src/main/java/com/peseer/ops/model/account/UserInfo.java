package com.peseer.ops.model.account;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.peseer.ops.model.account.base.BaseUserInfo;
import com.peseer.ops.util.StringUtil;

/**
 * 用户信息
 */
@SuppressWarnings("serial")
public class UserInfo extends BaseUserInfo<UserInfo> {
	public static final UserInfo dao = new UserInfo();

	public Page<UserInfo> selectUserInfoByName(String key, int type, int from) {
		String sql = String.format("from %s where 1=1", TableName);
		List<Object> params = new ArrayList<>();
		if (!StringUtil.isNullOrEmpty(key) && (type > 0 && type < 4)) {
			switch (type) {
			case 1:
				sql = sql + " and uid like ?";
				break;
			case 2:
				sql = sql + " and cn_name like ?";
				break;
			case 3:
				sql = sql + " and org_name like ?";
				break;
			default:
				break;
			}
			params.add("%" + key + "%");
		}
		return params.size() == 0 ? (Page<UserInfo>) dao.paginate(from, 20, "select * ", sql)
				: dao.paginate(from, 20, "select * ", sql, params.toArray());
	}

	public boolean deleteAccountInfo(Object uid) {
		try {
			deleteById(uid);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
