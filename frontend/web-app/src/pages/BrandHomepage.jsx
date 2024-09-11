import React from "react";
import Sidebar from "../Components/Sidebar";
import BrandHomepageComponent from "../Components/BrandHomepageComponent";

function BrandHomepage() {
    const categoryData = [
        { name: "TRANG CHỦ", navigation: "/brand", title: "homepage-title" },
        {
            name: "QUẢN LÝ SỰ KIỆN",
            navigation: "/brand/eventrManagement",
            title: "eventManagement-title",
        },
        { name: "THỐNG KÊ", navigation: "/brand/statistic", title: "statistic-title" },
    ];

    return <div></div>;
}

export default BrandHomepage;
