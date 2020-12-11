docker container rm DictionaryWebApp
docker run -e "app.base_url=http://192.168.0.5:8080" ^
	-d ^
	-p 8989:8989 ^
	--name DictionaryWebApp ^
	gsw91/d-ws2_app:latest
pause