package com.andreasinternetx.crudmysqljdbc.models;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleDB {
	
	private static final Logger log = LoggerFactory.getLogger(RoleDB.class);
	
	private int id;
	private int userId;
	private int admin;
	private int develop;
	private int cctld;
	private int gtld;
	private int billing;
	private int registry;
	private int purchaseRead;
	private int purchaseWrite;
	private int saleWrite;
	private int sql;
	
	private ArrayList<String> permissions = new ArrayList<String>();
	
	public RoleDB() {
	}
	
	public RoleDB(int userId, int admin, int develop, int cctld, int gtld, int billing, int registry, int purchaseRead,
			int purchaseWrite, int saleWrite, int sql) {
		super();

		this.userId = userId;
		this.admin = admin;
		this.develop = develop;
		this.cctld = cctld;
		this.gtld = gtld;
		this.billing = billing;
		this.registry = registry;
		this.purchaseRead = purchaseRead;
		this.purchaseWrite = purchaseWrite;
		this.saleWrite = saleWrite;
		this.sql = sql;
	}
	
	public void populateList() {
		log.info("gets called correctly");
		if(admin == 1) {
			permissions.add("ROLE_ADMIN");
			}
			if(develop == 1) {
			permissions.add("ROLE_DEVELOP");
			}
			if(cctld == 1) {
			permissions.add("ROLE_CCTLD");
			}
			if(gtld == 1) {
			permissions.add("ROLE_GTLD");
			}
			if(billing == 1) {
			permissions.add("ROLE_BILLING");
			}
			if(registry == 1) {
			permissions.add("ROLE_REGISTRY");
			}
			if(purchaseRead == 1) {
			permissions.add("ROLE_PURCHASEREAD");
			}
			if(purchaseWrite == 1) {
			permissions.add("ROLE_PURCHASEWRITE");
			}
			if(saleWrite == 1) {
			permissions.add("ROLE_SALEWRITE");
			}
			if(sql == 1) {
			permissions.add("ROLE_SQL");
			}
	}
	
	public ArrayList<String> getPermissions(){
		return permissions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getDevelop() {
		return develop;
	}

	public void setDevelop(int develop) {
		this.develop = develop;
	}

	public int getCctld() {
		return cctld;
	}

	public void setCctld(int cctld) {
		this.cctld = cctld;
	}

	public int getGtld() {
		return gtld;
	}

	public void setGtld(int gtld) {
		this.gtld = gtld;
	}

	public int getBilling() {
		return billing;
	}

	public void setBilling(int billing) {
		this.billing = billing;
	}

	public int getRegistry() {
		return registry;
	}

	public void setRegistry(int registry) {
		this.registry = registry;
	}

	public int getPurchaseRead() {
		return purchaseRead;
	}

	public void setPurchaseRead(int purchaseRead) {
		this.purchaseRead = purchaseRead;
	}

	public int getPurchaseWrite() {
		return purchaseWrite;
	}

	public void setPurchaseWrite(int purchaseWrite) {
		this.purchaseWrite = purchaseWrite;
	}

	public int getSaleWrite() {
		return saleWrite;
	}

	public void setSaleWrite(int saleWrite) {
		this.saleWrite = saleWrite;
	}

	public int getSql() {
		return sql;
	}

	public void setSql(int sql) {
		this.sql = sql;
	}	
}
