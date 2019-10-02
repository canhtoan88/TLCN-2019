const City = require("../../models/city.model");
const CityDao = require("../../dao/city.dao");

const cities = [
    { name: "An Giang" },
    { name: "Bà Rịa - Vũng Tàu" },
    { name: "Bắc Giang" },
    { name: "Bắc Kạn" },
    { name: "Bạc Liêu" },
    { name: "Bắc Ninh" },
    { name: "Bến Tre" },
    { name: "Bình Định" },
    { name: "Bình Dương" },
    { name: "Bình Phước" },
    { name: "Bình Thuận" },
    { name: "Cà Mau" },
    { name: "Cần Thơ" },
    { name: "Cao Bằng" },
    { name: "Đà Nẵng" },
    { name: "Đắk Lắk" },
    { name: "Đắk Nông" },
    { name: "Điện Biên" },
    { name: "Đồng Nai" },
    { name: "Đồng Tháp" },
    { name: "Gia Lai" },
    { name: "Hà Giang" },
    { name: "Hà Nam" },
    { name: "Hà Nội" },
    { name: "Hà Tĩnh" },
    { name: "Hải Dương" },
    { name: "Hải Phòng" },
    { name: "Hậu Giang" },
    { name: "Hồ Chí Minh" },
    { name: "Hòa Bình" },
    { name: "Hưng Yên" },
    { name: "Khánh Hòa" },
    { name: "Kiên Giang" },
    { name: "Kon Tum" },
    { name: "Lai Châu" },
    { name: "Lâm Đồng" },
    { name: "Lạng Sơn" },
    { name: "Lào Cai" },
    { name: "Long An" },
    { name: "Nam Định" },
    { name: "Nghệ An" },
    { name: "Ninh Bình" },
    { name: "Ninh Thuận" },
    { name: "Phú Thọ" },
    { name: "Phú Yên" },
    { name: "Quảng Bình" },
    { name: "Quảng Nam" },
    { name: "Quảng Ngãi" },
    { name: "Quảng Ninh" },
    { name: "Quảng Trị" },
    { name: "Sóc Trăng" },
    { name: "Sơn La" },
    { name: "Tây Ninh" },
    { name: "Thái Bình" },
    { name: "Thái Nguyên" },
    { name: "Thanh Hóa" },
    { name: "Thừa Thiên Huế" },
    { name: "Tiền Giang" },
    { name: "Trà Vinh" },
    { name: "Tuyên Quang" },
    { name: "Vĩnh Long" },
    { name: "Vĩnh Phúc" },
    { name: "Yên Bái" }
];

module.exports.createDefaultCollection = async () => {
    const citiesArray = await CityDao.find();
    if (citiesArray.length <= 0) {
        console.log("City collection is empty.");
        cities.map(city => {
            const cityNew = new City(city);
            CityDao.save(cityNew);
        });
        console.log("Default City collection created.");
    } else {
        console.log("City collection existed.");
    }
};
