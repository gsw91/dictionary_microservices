# dictionary_microservices

MySql shema:

	\CONFIG-EXAMPLE\mysql_schema.sql

Docker images: 
	
	Rest Web Service: https://hub.docker.com/r/gsw91/d-ws2
	Web Application: https://hub.docker.com/r/gsw91/d-ws2_app
	
Docker configuration:

	*** ALL CONFIG EXAMPLES ARE IN CONFIG-EXAMPLE/ DIR!

	Rest Web Service: 
	
		1. Create dir /dws-config/
			a. Create file dws-config.properties
			b. Set content: 
				## MySQL DB Access
				dws.app.db_connection_string= >>>here connection string<<<
				dws.app.db_user= >>>here db user<<<
				dws.app.db_password= >>>here db password<<<
				
				example:
				
				## MySQL DB Access
				dws.app.db_connection_string=jdbc:mysql://192.168.0.5:3306/dictionary?serverTimezone=Europe/Warsaw&useSSL=False
				dws.app.db_user=user
				dws.app.db_password=password
				
		2. Create dir /file-processing/archive/
			a. Dir /file-processing/ for files to process (with webhook call)
			b. Dir /file-processing/archive/ here the processed files are moving
			c. Files types must be .csv with three columns incuding header, example:
				type;id;setActive
				item;18;0
				dict;10;0
				item;19;0
				dict;3;1
			
		3. Prepare and run command (example cmd):
		
			### delete container if exists
			docker container rm DictionaryWebService
			
			### delete volume if exists (read-only)
			docker volume rm dws-config
			
			### create container with volumes (where --mount type=bind,source=YOUROWNPATH place your own path)
			docker container create --name DictionaryWebService -v dws-config:/dws-config ^
					--mount type=bind,source=YOUROWNPATH/file-processing,target=/file-processing ^
					-p 8080:8080 gsw91/d-ws2:latest
				
			### copy config file to volume and start
			docker cp dws-config/dws-config.properties DictionaryWebService:/dws-config/dws-config.properties
			docker start DictionaryWebService 
			pause 

	Web Application: 
		
		1. Prepare and run command (example cmd):
			### delete container if exists
			docker container rm DictionaryWebApp
			
			### run container with param - Web Service base url (with port)!
			docker run -e "app.base_url=http://192.168.0.5:8080" ^
					-d ^
					-p 8989:8989 ^
					--name DictionaryWebApp ^
					gsw91/d-ws2_app:latest
			