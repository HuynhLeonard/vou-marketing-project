import React from "react";
import BrandSidebar from "../Components/BrandSideBar";
import BrandEventManagement from "../Components/BrandEventManagement";

function BrandEventManagementPage() {
    const categoryData = [
        { name: "TRANG CHỦ", navigation: "/brand", title: "homepage-title" },
        {
            name: "QUẢN LÝ SỰ KIỆN",
            navigation: "/brand/eventManagement",
            title: "eventManagement-title",
        },
        {
            name: "TẠO SỰ KIỆN",
            navigation: "/brand/addEvent",
            title: "addEvent-title",
        },
    ];

    return (
        <div>
            <BrandSidebar selectedIndexPage={1} categoryData={categoryData} />
            <BrandEventManagement />
        </div>
    );
}

export default BrandEventManagementPage;
