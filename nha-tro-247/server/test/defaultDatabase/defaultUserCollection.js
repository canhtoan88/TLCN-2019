const User = require("../../models/user.model");
const UserDao = require("../../dao/user.dao");
const Authorization = require("../../models/authorization.model");
const AuthorizationDao = require("../../dao/authorization.dao");

const users = [
    {
        authorizationName: "Admin",
        fullname: { firstname: "Admin1", lastname: "Admin" },
        phone: "123456789",
        email: "admin1@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    },
    {
        authorizationName: "Admin",
        fullname: { firstname: "Admin2", lastname: "Admin" },
        phone: "123456790",
        email: "admin2@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    },
    {
        authorizationName: "Admin",
        fullname: { firstname: "Admin3", lastname: "Admin" },
        phone: "123456791",
        email: "admin3@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    },
    {
        authorizationName: "Admin",
        fullname: { firstname: "Admin4", lastname: "Admin" },
        phone: "123456792",
        email: "admin4@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    },
    {
        authorizationName: "Admin",
        fullname: { firstname: "Admin5", lastname: "Admin" },
        phone: "123456793",
        email: "admin5@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    },
    {
        authorizationName: "Employee",
        fullname: { firstname: "Employee1", lastname: "Employee" },
        phone: "123456794",
        email: "employee1@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    },
    {
        authorizationName: "Employee",
        fullname: { firstname: "Employee2", lastname: "Employee" },
        phone: "123456795",
        email: "employee2@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    },
    {
        authorizationName: "Employee",
        fullname: { firstname: "Employee3", lastname: "Employee" },
        phone: "123456796",
        email: "employee3@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    },
    {
        authorizationName: "Employee",
        fullname: { firstname: "Employee4", lastname: "Employee" },
        phone: "123456797",
        email: "employee4@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    },
    {
        authorizationName: "Employee",
        fullname: { firstname: "Employee5", lastname: "Employee" },
        phone: "123456798",
        email: "employee5@gmail.com",
        password: "123456",
        address: "Thủ Đức, Hồ Chí Minh",
        gender: false,
        birthday: "1999-06-18",
        timeRegister: Date.now()
    }
];

module.exports.createDefaultCollection = async () => {
    
};
