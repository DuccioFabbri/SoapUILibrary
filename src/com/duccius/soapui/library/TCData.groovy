package com.duccius.soapui.library

class TCData {
	def db
	def iterator
	
	def username
	def passw
	def xpath
	def expected
	
	def TCData(username,
		passw,
		xpath,
		expected
		)
	{
		this.username = username
		this.passw = passw
		this.xpath = xpath
		this.expected = expected		
	}
	
	def TCData(iterator)
	{
		this.iterator = iterator
		
		def row = iterator.getAt(0)
		
		this.username = row.get('username')
		this.passw = row.get('password')
		this.xpath = row.get('xpath')
		this.expected = row.get('expected')
	}
}
