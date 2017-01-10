package com.rachierudragos.dotatracker.Wrapper.account;

import java.util.Date;

public class AccountDetail {
	public String solo_competitive_rank;
	public String competitive_rank;
	public Profile profile;
	public class Profile{
		public Long account_id;
		public String personaname;
		public Integer cheese;
		public String steamid;
		public String profileurl;
		public Date last_login;
		public String avatarfull;
		public String loccountrycode;
		public String name;
	}
}
