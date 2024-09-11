import React from "react";
import Sidebar from "../Components/Sidebar";
import BrandHomepageComponent from "../Components/BrandHomepageComponent";

function BrandHomepage() {
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
            <Sidebar selectedIndexPage={0} categoryData={categoryData} />
            <BrandHomepageComponent />
        </div>
    );
}

export default BrandHomepage;
