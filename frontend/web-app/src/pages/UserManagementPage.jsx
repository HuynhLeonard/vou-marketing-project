import React from "react";
import Navigation from "../Components/Navigation";
import UserManage from "../Components/UserManage";
import NavigationTwo from "../Components/NavigationTwo";
import Sidebar from "../Components/Sidebar";

const UserManagementPage = () => {
    const categoryData = [
        { name: "TRANG CHỦ", navigation: "/admin", title: "homepage-title" },
        {
            name: "QUẢN LÝ NGƯỜI DÙNG",
            navigation: "/admin/userManagement",
            title: "userManagement-title",
        },
        {
            name: "QUẢN LÝ TRÒ CHƠI",
            navigation: "/admin/gameManagement",
            title: "gameManagement-title",
        },
        {
            name: "QUẢN LÝ EVENT",
            navigation: "/admin/eventManagement",
            title: "voucherAndEvent-title",
        },
        { name: "THỐNG KÊ", navigation: "/admin/statistic", title: "statistic-title" },
    ];

    return (
        <div>
            <Sidebar selectedIndexPage={1} categoryData={categoryData} />
            <UserManage />
        </div>
    );
};

export default UserManagementPage;
