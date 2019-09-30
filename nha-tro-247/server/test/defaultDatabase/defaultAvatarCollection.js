const Avatar = require("../../models/avartar.model");
const AvatarDao = require("../../dao/avatar.dao");
const UserDao = require("../../dao/user.dao");

const url = "https://scontent.fsgn8-1.fna.fbcdn.net/v/t1.0-9/50308283_2328637147419731_2711687074741747712_n.jpg?_nc_cat=110&_nc_oc=AQkOqu3EIREyazTIzofFfJKZ3XCacLluuO0Y4KZkDMcmGmyX4fDGz1Yb8AHTgmSZLVs&_nc_ht=scontent.fsgn8-1.fna&oh=48743b60b11a2ad54496661f7ebdfc65&oe=5E379E9B";

module.exports.createDefaultCollection = async () => {
    const avatarsArray = await AvatarDao.find();
    if (avatarsArray.length <= 0) {
        console.log("Avatar collection is empty.");
        const users = await UserDao.find();
        if(users.length <= 0){
            console.log("User collection is empty.");
        }
        else{
            users.map(user => {
                const avatar = new Avatar({
                    user: user,
                    url: url
                });
                AvatarDao.save(avatar);
            })
        }
        console.log("Default Avatar collection created.");
    } else {
        console.log("Avatar collection existed.");
    }
};
