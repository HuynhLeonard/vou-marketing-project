import React from "react";
import GameManage from "../Components/GameManage";
import Navigation from "../Components/Navigation";
import NavigationTwo from "../Components/NavigationTwo";
import Sidebar from "../Components/Sidebar";

const GameManagementPage = () => {
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
            name: "VOUCHER VÀ EVENT",
            navigation: "/admin/voucherAndEvent",
            title: "voucherAndEvent-title",
        },
        { name: "THỐNG KÊ", navigation: "/admin/statistic", title: "statistic-title" },
    ];

    return (
        <div>
            <Sidebar selectedIndexPage={2} categoryData={categoryData} />
            <GameManage />
        </div>
    );
};

export default GameManagementPage;
