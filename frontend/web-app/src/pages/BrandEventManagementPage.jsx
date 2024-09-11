import React from "react";
import Sidebar from "../Components/Sidebar";
import BrandEventManagement from "../Components/BrandEventManagement";

function BrandEventManagementPage() {
    const categoryData = [
        { name: "TRANG CHỦ", navigation: "/brand", title: "homepage-title" },
        {
            name: "QUẢN LÝ SỰ KIỆN",
            navigation: "/brand/eventManagement",
            title: "eventManagement-title",
        },
        { name: "THỐNG KÊ", navigation: "/brand/statistic", title: "statistic-title" },
    ];

    return (
        <div>
            <Sidebar selectedIndexPage={1} categoryData={categoryData} />
            <BrandEventManagement />
        </div>
    );
}

export default BrandEventManagementPage;
