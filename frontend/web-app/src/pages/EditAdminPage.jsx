import React from "react";
import Sidebar from "../Components/Sidebar";
import EditAdminForm from "../Components/EditAdminForm";

function EditAdminPage() {
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
            <Sidebar categoryData={categoryData} />
            <EditAdminForm />
        </div>
    );
}

export default EditAdminPage;
