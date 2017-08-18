package com.hjw.home.core;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
public class MongoGridfsUtil {
	Mongo connection;
	MongoDatabase mongoDatabase;
	DB db;
	DBCollection collection;
	// private static GridFS myFS = null;
	GridFS myFS;
	//Properties configProperties = (Properties) AppUtil.getBean("configproperties");
	String mongoDBHost = "localhost";
	int mongoDBPort = Integer.parseInt("27017");
	String dbName = "local";
	String collectionName = "fs";
	private MongoClient mongoClient = null;

	public MongoGridfsUtil(){
		_init();
	}

	public MongoGridfsUtil(String mongoDBHost, int mongoDBPort, String dbName,
			String collectionName) throws UnknownHostException, MongoException,
			NoSuchAlgorithmException {
		this.mongoDBHost = mongoDBHost;
		this.mongoDBPort = mongoDBPort;
		this.dbName = dbName;
		this.collectionName = collectionName;
		_init();
	}

	public MongoGridfsUtil(String collectionName) throws UnknownHostException,
			MongoException, NoSuchAlgorithmException {
		this.collectionName = collectionName;
		_init();
	}

	private void _init(){
		// connection = new Mongo(mongoDBHost, mongoDBPort);
		if (mongoClient == null) {
			MongoClientOptions.Builder build = new MongoClientOptions.Builder();
			build.connectionsPerHost(50); // 与目标数据库能够建立的最大connection数量为50
			// build.autoConnectRetry(true); //自动重连数据库启动
			build.threadsAllowedToBlockForConnectionMultiplier(50); // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
			/*
			 * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
			 * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
			 * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
			 */
			build.maxWaitTime(1000 * 60 * 10);
			build.connectTimeout(1000 * 60 * 1); // 与数据库建立连接的timeout设置为1分钟
			MongoClientOptions myOptions = build.build();
			// 数据库连接实例
			mongoClient = new MongoClient(new ServerAddress(mongoDBHost,
					mongoDBPort), myOptions);

		}
		// mongoDatabase = mongoClient.getDatabase(dbName);
		// connection= new MongoClient(mongoDBHost, mongoDBPort);
		// db = connection.getDB(dbName);
		// collection = db.getCollection(collectionName);
		db = mongoClient.getDB(dbName);
		myFS = new GridFS(db);
	}

	/**
	 * 用给出的id，保存文件，透明处理已存在的情况 id 可以是string，long，int，org.bson.types.ObjectId 类型
	 * 
	 * @param in
	 * @param id
	 */
	public void save(InputStream in, Object id, String Filename, String type) {
		GridFSDBFile gridFSDBFile = this.getById(id);

		if (gridFSDBFile != null)
			return;

		GridFSInputFile gridFSInputFile = myFS.createFile(in);
		gridFSInputFile.setFilename(Filename);
		gridFSInputFile.setContentType(type);
		DBObject metadata = new BasicDBObject();
		//metadata.put("userId", ContextUtil.getCurrentUserId());
		//metadata.put("orgId", ContextUtil.getCurrentOrgId());
		gridFSInputFile.setMetaData(metadata);
		gridFSInputFile.setId(id);
		gridFSInputFile.save();
		// destory();
		return;
	}

	/**
	 * 用给出的id，保存文件，透明处理已存在的情况 id 可以是string，long，int，org.bson.types.ObjectId 类型
	 * 
	 * @param in
	 * @param id
	 */
	public void save(InputStream in, Object id, String Filename, String type,
			DBObject metadata) {

		/*
		 * DBObject query = new BasicDBObject("_id", id); GridFSDBFile
		 * gridFSDBFile = myFS.findOne(query);
		 */

		GridFSDBFile gridFSDBFile = this.getById(id);

		if (gridFSDBFile != null)
			return;

		GridFSInputFile gridFSInputFile = myFS.createFile(in);
		gridFSInputFile.setFilename(Filename);
		gridFSInputFile.setContentType(type);
		// DBObject metadata = new BasicDBObject();
		//metadata.put("userId", ContextUtil.getCurrentUserId());
		//metadata.put("orgId", ContextUtil.getCurrentOrgId());
		gridFSInputFile.setMetaData(metadata);
		gridFSInputFile.setId(id);
		gridFSInputFile.save();
		// destory();
		return;
	}

	/**
	 * 用给出的id，保存文件，透明处理已存在的情况 id 可以是string，long，int，org.bson.types.ObjectId 类型
	 * 
	 * @param in
	 * @param id
	 */
	public void saveForByte(byte[] img, Object id, String Filename, String type) {
		GridFSDBFile gridFSDBFile = this.getById(id);

		if (gridFSDBFile != null)
			return;

		GridFSInputFile gridFSInputFile = myFS.createFile(img);
		gridFSInputFile.setFilename(Filename);
		gridFSInputFile.setContentType(type);
		DBObject metadata = new BasicDBObject();
		//metadata.put("userId", ContextUtil.getCurrentUserId());
		//metadata.put("orgId", ContextUtil.getCurrentOrgId());
		gridFSInputFile.setMetaData(metadata);
		gridFSInputFile.setId(id);
		gridFSInputFile.save();
		// destory();
		return;
	}

	/**
	 * 据id返回文件
	 * 
	 * @param id
	 * @return
	 */
	public GridFSDBFile getById(Object id) {
		DBObject query = null;
		GridFSDBFile gridFSDBFile = null;
		if (StringUtils.isNumeric(id.toString())) {
			Long l = Long.valueOf(id.toString());
			query = new BasicDBObject("_id", l);
		} else {
			query = new BasicDBObject("_id", id.toString());
		}
		try {
			gridFSDBFile = myFS.findOne(query);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		// destory();
		return gridFSDBFile;
	}

	/**
	 * 据文件名返回文件，只返回第一个
	 * 
	 * @param fileName
	 * @return
	 */
	public GridFSDBFile getByFileName(String fileName) {
		DBObject query = new BasicDBObject("filename", fileName);
		GridFSDBFile gridFSDBFile = myFS.findOne(query);
		// destory();
		return gridFSDBFile;
	}

	public void destory() {
		if (mongoClient != null)
			mongoClient.close();
		mongoClient = null;
		db = null;
		myFS = null;
		System.gc();
	}
	


}
