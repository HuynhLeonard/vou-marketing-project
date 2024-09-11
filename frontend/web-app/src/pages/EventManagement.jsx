import React from "react";
import Navigation from "../Components/Navigation";
import VoucherManage from "../Components/VoucherManage";
import NavigationTwo from "../Components/NavigationTwo";
import Sidebar from "../Components/Sidebar";

const EventManagement = () => {
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
            <Sidebar selectedIndexPage={3} categoryData={categoryData} />
            <VoucherManage />
        </div>
    );
};

export default EventManagement;
