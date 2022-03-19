1. Run ```docker-compose up --build``` to start redis and mongodb
2. Run ```db.createUser({user: "adminuser", pwd: "admin", roles: [ "root" ]})``` in mongo-shell to create admin user
