package com.hjw.home.core;

import com.mongodb.MongoClient;

public class MongoUploadUtil {
	public class MongoDB {
		private MongoClient mongoClient;
		private String dbName;
		public MongoClient getMongoClient() {
			return mongoClient;
		}
		public void setMongoClient(MongoClient mongoClient) {
			this.mongoClient = mongoClient;
		}
		public String getDbName() {
			return dbName;
		}
		public void setDbName(String dbName) {
			this.dbName = dbName;
		}
		
	}
}
