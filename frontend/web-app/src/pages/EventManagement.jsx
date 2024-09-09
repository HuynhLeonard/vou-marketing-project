import React from "react";
import Navigation from "../Components/Navigation";
import VoucherManage from "../Components/VoucherManage";
import NavigationTwo from "../Components/NavigationTwo";
import Sidebar from "../Components/Sidebar";

const EventManagement = () => {
    return (
        <div>
            <Sidebar selectedIndexPage={3} />
            <VoucherManage />
        </div>
    );
};

export default EventManagement;
