#in postman

#hit post request as http://localhost:8085/oauth/token
under authorization
 basic auth == mohit-client/mohit-secret
 
under body
select x-www-form
 then provide params
 username -- Alex123
 password -- password
 grant_type -password
 
#use get api as http://localhost:8085/users/user?access_token=
 
#for new token hit post request as http://localhost:8085/oauth/token
 select x-www-form
 then provide params
 username -- Alex123
 password -- password
 grant_type -password
 refresh_token--provide yours