package com.duccius.soapui.library


import groovy.sql.Sql
import java.sql.DriverManager

import com.eviware.soapui.support.GroovyUtils
import com.eviware.soapui.impl.wadl.inference.schema.Context
import org.apache.log4j.Logger


class DbConnection {
	//org.apache.log4j.Logger log = new org.apache.log4j.Logger()
	def log
	def context
	def testRunner
	
	def DbConnection()
	{
		com.eviware.soapui.support.GroovyUtils.registerJdbcDriver("com.mysql.jdbc.Driver")		
	}
	def Connect()
	{
		try{
			def dbURL = 'jdbc:mysql://localhost:3306/soapui'
			def dbUserName = 'root'
			def dbPassword = 'root'
			def dbDriver = 'com.mysql.jdbc.Driver'
			
			def db = Sql.newInstance(dbURL,dbUserName,dbPassword,dbDriver)
			log.info ("db. "+db)
			//context.db = db
							
			def rows = db.rows("SELECT username, password, id, xpath, expected FROM soapui.login_data ORDER BY id")
		    		    
		    log.info('Good: ' + rows.size())
		    context.ite =rows.listIterator()
			
			
			def obj = context.ite.getAt(0)
			
			context.username=obj.get('username')
			context.passw=obj.get('password')
			context.id=obj.get('id')
			context.xpath=obj.get('xpath')
			context.expected=obj.get('expected')
			log.info('username' + context.id + ' : '  +context.username)
			log.info('passw' + context.id + ' : '+context.passw)
			log.info('id'+ context.id + ' : ' +context.id)
		
			
			def tcData = new TCData(context.ite)
			tcData.db = db
			
//			def curTC = testRunner.testCase
//			//curTC.testSuite.project.getMockServiceByName("ServiceSoapBinding MockService").getMockRunner()
//			curTC.testSuite.getTestCaseByName("ServerError").setPropertyValue("username",context.username)
//			curTC.testSuite.getTestCaseByName("ServerError").setPropertyValue("passw",context.passw)
//		//    curTC.testSuite.getTestCaseByName("ServerError").setName("login - id: " + context.id)
//			curTC.testSuite.getTestCaseByName("ServerError").setPropertyValue("xpath",context.xpath)
//			curTC.testSuite.getTestCaseByName("ServerError").setPropertyValue("expected",context.expected)
//			
			return tcData
		}catch(Exception e){
			log.info('DB Error')
			log.info(e.getMessage())
		}finally{
		}
	}

}
