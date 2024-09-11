import React from "react";
import BrandSideBar from "../Components/BrandSideBar";
import AddNewEventForm from "../Components/AddNewEventForm";

function AddNewEventPage() {
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
            <BrandSideBar selectedIndexPage={2} categoryData={categoryData} />
            <AddNewEventForm />
        </div>
    );
}

export default AddNewEventPage;
