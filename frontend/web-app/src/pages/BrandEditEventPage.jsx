import React from "react";
import BrandSideBar from "../Components/BrandSideBar";
import BrandEditEvent from "../Components/BrandEditEvent";

function BrandEditEventPage() {
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
            <BrandSideBar categoryData={categoryData} />
            <BrandEditEvent />
        </div>
    );
}

export default BrandEditEventPage;
