import React from "react";
import BrandSideBar from "../Components/BrandSideBar";
import EditBrandForm from "../Components/EditBrandForm";

function EditBrandPage() {
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
            <BrandSideBar categoryData={categoryData} />
            <EditBrandForm />
        </div>
    );
}

export default EditBrandPage;
