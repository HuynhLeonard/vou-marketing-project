import React from "react";
import BrandSidebar from "../Components/BrandSideBar";
import BrandHomepageComponent from "../Components/BrandHomepageComponent";

function BrandHomepage() {
    const categoryData = [
        { name: "TRANG CHỦ", navigation: "/brand", title: "homepage-title" },
        {
            name: "QUẢN LÝ SỰ KIỆN",
            navigation: "/brand/eventManagement",
            title: "eventManagement-title",
        },
    ];

    return (
        <div>
            <BrandSidebar selectedIndexPage={0} categoryData={categoryData} />
            <BrandHomepageComponent />
        </div>
    );
}

export default BrandHomepage;
