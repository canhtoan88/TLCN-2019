const UserDao = require("../../dao/user.dao");
const CityDao = require("../../dao/city.dao");
const DistrictDao = require("../../dao/district.dao");
const AuthorizationDao = require("../../dao/authorization.dao");

module.exports.createDefaultCollection = async () => {

    const user = await UserDao.findById("5d922aab11be103230bbbc92");
    console.log(user);
    const city = await CityDao.findOneByName("Hồ Chí Minh");
    console.log(city);




    const usersArray = await UserDao.find();
    if(usersArray.length <= 0){
        console.log("User collection is empty.");
        const authorizations = await AuthorizationDao.find();
        if(authorizations.length <= 0){
            console.log("Authorization collection is empty.");
        }
        else{            
            authorizations.map(authorization => {
                users.map(user => {
                    if (user.authorizationName.localeCompare(authorization.name) == 0) {
                        const userNew = new User({
                            authorization: authorization,
                            fullname: user.fullname,
                            phone: user.phone,
                            email: user.email,
                            password: user.password,
                            address: user.address,
                            gender: user.gender,
                            birthday: user.birthday,
                            timeRegister: user.timeRegister
                        });    
                        UserDao.save(userNew);
                    }
                });
            });
            console.log("Default User collection created.");   
        }   
    }
    else{
        console.log("User collection existed.");
    }
};
