package com.peseer.ops.model.account;

import java.util.List;

import com.peseer.ops.model.account.base.BaseLoginInfo;
import com.peseer.ops.util.CryptoUtil;

/**
 * 用户登录信息
 */
@SuppressWarnings("serial")
public class LoginInfo extends BaseLoginInfo<LoginInfo> {
	public static final LoginInfo dao = new LoginInfo();

	private static final String PasswordKey = "keandata";

	public boolean addAccount(String userName) {
		try {
			String tmpPwd = CryptoUtil.genRandomNum(5);
			tmpPwd = "kd-" + tmpPwd;
			String pwd = CryptoUtil.aesEncrypt(tmpPwd, PasswordKey);
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setUid(userName);
			loginInfo.setPwd(pwd);
			return loginInfo.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public String getPwd(String uid) {
		try {
			LoginInfo loginInfo = LoginInfo.dao.findFirst(String.format("select pwd from %s where uid = ?", TableName),
					uid);
			if (loginInfo != null) {
				return CryptoUtil.aesDecrypt(loginInfo.getPwd(), PasswordKey);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public LoginInfo getAccount(String uid) {
		try {
			LoginInfo loginInfo = LoginInfo.dao.findFirst(String.format("select pwd from %s where uid = ?", TableName),
					uid);
			if (loginInfo != null) {
				loginInfo.setPwd(CryptoUtil.aesDecrypt(loginInfo.getPwd(), PasswordKey));
				return loginInfo;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void deleteAccount(String uid) {
		LoginInfo record = LoginInfo.dao.findFirst(String.format("select * from %s where uid=?", TableName), uid);
		if (record != null) {
			record.delete();
		}
	}

	public void encryptAll() throws Exception {
		List<LoginInfo> records = LoginInfo.dao.find(String.format("select * from %s", TableName));

		if (!records.isEmpty()) {
			for (LoginInfo info : records) {
				info.setPwd(CryptoUtil.aesEncrypt(info.getPwd(), PasswordKey));
				info.update();
			}
		}
	}

	public void decryptAll() throws Exception {
		List<LoginInfo> records = LoginInfo.dao.find(String.format("select * from %s", TableName));

		if (!records.isEmpty()) {
			for (LoginInfo info : records) {
				info.setPwd(CryptoUtil.aesDecrypt(info.getPwd(), PasswordKey));
				info.update();
			}
		}
	}
}
