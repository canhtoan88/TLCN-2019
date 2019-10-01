const UserDao = require("../../dao/user.dao");
const CityDao = require("../../dao/city.dao");
const DistrictDao = require("../../dao/district.dao");
const Hostel = require("../../models/hostel.model");
const HostelDao = require("../../dao/hostel.dao");

const streets = [
    "Đường Tỉnh 43",
    "Đường Tỉnh 743",
    "Bác Ái",
    "Cầu Bình Đức",
    "Chu Mạnh Trinh",
    "Chương Dương",
    "Công Lý",
    "Dân Chủ",
    "Đặng Văn Bi",
    "Đoàn Công Hớn",
    "Đoàn Kết",
    "Độc Lập",
    "Dương Văn Cam",
    "Einstein",
    "Gò Dưa",
    "Hiệp Bình",
    "Hồ Văn Tư",
    "Hòa Bình",
    "Hoàng Diệu",
    "Hồng Đức",
    "Kha Vạn Cân",
    "Khổng Tử",
    "Lê Thị Hoa",
    "Lê Văn Chí",
    "Linh Trung",
    "Lương Khải Siêu",
    "Ngô Chí Quốc",
    "Nguyễn Khuyến",
    "Nguyễn Thị Nhung",
    "Nguyễn Văn Bá",
    "Pasteur",
    "Phạm Văn Đồng",
    "Phú Châu",
    "Quốc Lộ 1A",
    "Quốc Lộ 1K",
    "Quốc Lộ 13",
    "Tam Bình",
    "Tam Châu",
    "Tam Hà",
    "Thống Nhất",
    "Tô Ngọc Vân",
    "Tô Vĩnh Diện",
    "Vận Hành",
    "Việt Thắng",
    "Võ Văn Ngân",
    "Xa Lộ Hà Nội"
];
// console.log(streets.length);    // 46

module.exports.createDefaultCollection = async () => {
    const hostels = await HostelDao.find();
    if(hostels.length <= 0){
        console.log("Hostel collection is empty.");

        const users = await UserDao.find();
        if(users.length <= 0){
            console.log("User collection is empty");
        }
        else{
            const city = await CityDao.findOneByName("Hồ Chí Minh");
            if(!city){
                console.log("City collection is empty");
            }
            else{
                const district = await DistrictDao.findOneByName("Thủ Đức");
                if(!district){
                    console.log("Districy collection is empty");
                }
                else{
                    var num = 1;
                    streets.map(street => {
                        for (let i = 0; i < 10; i++) {
                            const randomPrice = parseInt(Math.random() * 100) * 100000;
                            const hostelNew = new Hostel({
                                user: users[parseInt(Math.random() * 100) + 10],
                                city: city,
                                district: district,
                                street: street,
                                houseNumber: (i + 1),
                                price: randomPrice,
                                space: randomPrice / 100000,
                                title: "CHO THUÊ PHÒNG TRỌ MỚI RẺ CAO CẤP SẠCH SẼ.." + num,
                                description: "Cho thuê phòng trọ mới rẻ cao cấp sạch sẽ.." + num,
                                isRented: false,
                                isCensored: 1,
                                timeUp: Date.now()
                            });
                            // console.log(hostelNew);

                            // console.log(num);
                            num++;

                            HostelDao.save(hostelNew);
                        }
                    });
                }
            }
        }

        console.log("Hostel collection created");
    }
    else{
        console.log("Hostel collection existed");
    }
};
