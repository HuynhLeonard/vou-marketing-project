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
    ];

    return (
        <div className="bg-white">
            <BrandSideBar categoryData={categoryData} />
            <AddNewEventForm />
        </div>
    );
}

export default AddNewEventPage;
